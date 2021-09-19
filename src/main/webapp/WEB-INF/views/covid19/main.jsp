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
		<h1>코로나 감염 현황</h1>
	</div>	
	<br>
	<!-- end div -->
	
	<div>
		<table class="infecStatusTbl">
			<tr>
				<th>확진자</th>
				<th>사망자</th>
				<th>확진률</th>
				<th>치료 중인 환자</th>
				<th>격리 해제</th>
			</tr>
			<tr>
				<td>
					<c:out value="${item.totalDecideCnt}" />
					<br>
					<c:if test="${item.decideCnt > 0}">
						(+)<c:out value="${item.decideCnt}" />
					</c:if>
					<c:if test="${item.decideCnt == 0}">
						(-)
					</c:if>
				</td>
				<td>
					<c:out value="${item.totalDeathCnt}" />
					<br>
					<c:if test="${item.deathCnt > 0}">
						(+)<c:out value="${item.deathCnt}" />
					</c:if>
					<c:if test="${item.deathCnt == 0}">
						(-)
					</c:if>
				</td>
				<td><c:out value="${item.accDefRate}" />%</td>
				<td><c:out value="${item.careCnt}" /></td>
				<td><c:out value="${item.clearCnt}" /></td>
			</tr>
			<tr>
				<th>검사 진행 수</th>
				<th>누적 검사 수</th>
				<th>누적 검사 완료 수</th>
				<th>결과 음성 수</th>
			</tr>
			<tr>
				<td><c:out value="${item.examCnt}" /></td>
				<td><c:out value="${item.accExamCnt}" /></td>
				<td><c:out value="${item.accExamCompCnt}" /></td>
				<td><c:out value="${item.resutlNegCnt}" /></td>
			</tr>
		</table>
	</div>
<%@ include file="../include/footer.jsp" %>