<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>
	<div id="menu">
		<ul>
			<li><a href="/covid19/main">감염 현황</a></li>
			<li><a href="/covid19/cityInfec">시 · 도 별 감염 현황</a></li>
			<li><a href="/covid19/map">선별 진료소 위치</a></li>
		</ul>
	</div>
	<br>
	<!-- end menu -->
	
	<div>
		<h1>코로나 선별 진료소</h1>
	</div>
	<br>
	<!-- end div -->
	
	<div id="map" style="top:25%;left:10%;width:80%;height:730px;"></div>
	
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	
	<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=3qgciqrne3"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			initMap();
		});
		
		function initMap() {
			var trArr = new Array();
			
			<c:forEach items="${dataList}" var="data">
				trArr.push({
					sido: "<c:out value='${data.sido}' />", // 시도명
					sigungu: "<c:out value='${data.sigungu}' />", // 시군구명
					centerName: "<c:out value='${data.centerName}' />", // 예방접좁 센터명
					facilityName: "<c:out value='${data.facilityName}' />", // 시설명
					address: "<c:out value='${data.address}' />", // 예방접종 센터 주소
					phoneNumber: "<c:out value='${data.phoneNumber}' />", // 예방접종 센터 전화번호
					lat: "<c:out value='${data.lat}' />", // 위도
					lng: "<c:out value='${data.lng}' />" // 경도
				});
			</c:forEach>
			
			// 지도 생성
			var map = new naver.maps.Map("map", {
				center: new naver.maps.LatLng(37.3595704, 127.105399),
				zoom: 10
			});
			
			var markers = new Array();
			var infoWindows = new Array();
			
			for(var i = 0; i < trArr.length; i++) {
				// 네이버 지도에 마커 생성
				var marker = new naver.maps.Marker({
					map: map,
					position: new naver.maps.LatLng(trArr[i].lat, trArr[i].lng)
				});
				
				// 정보창 생성
				var infoWindow = new naver.maps.InfoWindow({
					content: "<h3 style='text-align:center;'>" + trArr[i].centerName + "</h3>" + 
						"<p>주소: " + trArr[i].sido + "&nbsp;" + trArr[i].sigungu + "&nbsp;" + trArr[i].address + "</p>" +
						"<p>시설명: " + trArr[i].facilityName + "</p>" +
						"<p>전화번호: " + trArr[i].phoneNumber + "</p>"
				});
				
				markers.push(marker);
				infoWindows.push(infoWindow);
			}
			
			// 마커에 마우스를 대면 정보창 출력
			for(var i = 0, j = markers.length; i < j; i++) {
				naver.maps.Event.addListener(markers[i], "mouseover", getMouseOverHandler(i));
			}
			
			function getMouseOverHandler(seq) {
				return function(e) {
					var marker = markers[seq];
					var infoWindow = infoWindows[seq];
					
					if(infoWindow.getMap()) {
						infoWindow.close();
					} else {
						infoWindow.open(map, marker);
					}
				}
			} // end getMouseOverHandler
		} // end initMap
	</script>
<%@ include file="../include/footer.jsp" %>