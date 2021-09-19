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
		<h1>시 · 도 별 감염 현황</h1>
	</div>	
	<br>
	<!-- end div -->
	
	<div>	
		<table width="100%" border="1">
			<tr>
				<th>지역</th>
				<th>확진자</th>
				<th>사망자</th>
				<th>총 확진자</th>
				<th>총 사망자</th>
				<th>격리 해제</th>
				<th>격리 환자</th>
				<th>지역 발생</th>
				<th>해외 유입</th>
			</tr>
			<c:forEach var="item" items="${itemList}">
				<tr>
					<td><c:out value="${item.gubun}" /></td>
					<td><c:out value="${item.defCnt}" /></td>
					<td><c:out value="${item.deathCnt}" /></td>
					<td><c:out value="${item.totalDefCnt}" /></td>
					<td><c:out value="${item.totalDeathCnt}" /></td>
					<td><c:out value="${item.isolClearCnt}" /></td>
					<td><c:out value="${item.isolIngCnt}" /></td>
					<td><c:out value="${item.localOccCnt}" /></td>
					<td><c:out value="${item.overFlowCnt}" /></td>
				</tr>	
			</c:forEach>
		</table>
	</div>
	<!-- end div -->
<%@ include file="../include/footer.jsp" %>