<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<style>
body {
	background-color: lavender;
}

#news_user {
	border-collapse: collapse;
	border: 1px solid #ddd;
	font-size: 22px;
}

#news_user td {
	border: 2px solid #ddd;
}

#news {
	border-collapse: collapse;
	border: 1px solid #ddd;
}

#news tr:nth-child(even) {
	font-size: 18px;
	border-bottom: 5px solid #B9B29F;
	padding: 10px;
	text-align: left;
	border: 1px solid #ddd;
	background-color: gainsboro;
}

#news tr:nth-child(odd) {
	font-size: 22px;
	font-weight: bold;
	border-bottom: 5px solid #B9B29F;
	padding: 10px;
	text-align: left;
	border: 3px solid #ddd;
}

td {
	padding: 10px;
}

.answer {
	font-size: 22px;
}

.button {
	font-family: Arial, Helvetica, sans-serif;
	border-color: lightslategray;
	background-color: white;
	color: lightslategray;
	text-align: center;
	padding: 6px 6px 6px 6px;
	font-size: 16px;
	cursor: pointer;
	border-radius: 12px;
	border-right: none;
}

.button_in_line {
	display: inline-block;
}

.button_locale {
	border-radius: 12px;
	margin: 4px 2px;
	display: inline-block;
}

.right_button {
	position: absolute;
	right: 0px;
	padding: 3px;
}

.header1 {
	display: inline;
	font: 50px Arial, sans-serif;
}
</style>


<meta charset="utf-8">

<title>Offered news</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization/local" var="loc" />

<fmt:message bundle="${loc}" key="local.offeredNews.title"
	var="offered_news" />
<fmt:message bundle="${loc}" key="local.offeredNews.message.empty"
	var="empty_offered_news" />
<fmt:message bundle="${loc}" key="local.offeredNews.check"
	var="check_offered_news" />
<fmt:message bundle="${loc}"
	key="local.checkOfferedNews.approve.publication"
	var="approve_publication" />
<fmt:message bundle="${loc}"
	key="local.checkOfferedNews.deny.publication" var="deny_publication" />
<fmt:message bundle="${loc}" key="local.locbutton.main.page"
	var="mainPage" />
	
	<fmt:message bundle="${loc}" key="local.offeredNews.news.status.on.approval"
	var="on_approval" />
	<fmt:message bundle="${loc}" key="local.offeredNews.news.status.denied"
	var="denied" />
	<fmt:message bundle="${loc}" key="local.offeredNews.news.status.published"
	var="published" />
</head>

<body>

	<div class="right_button"><jsp:include
			page="/WEB-INF/jsp/commands.jsp" /></div>
	<br>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_main_page" />
		<button class="button">${mainPage}</button>
	</form>

	<br>
	<div class="header1">${offered_news}<img
			src="recources/pictures/leaf.png" width="70" height="90">
	</div>
	<br>
	<br>


	<c:if test="${user.role == 'user'}">
		<c:choose>
			<c:when test="${not empty offeredNews}">
				<table id="news_user">

					<c:forEach var="oneNews" items="${offeredNews}">
						<tr>
							<td style="font-size: 25px; font-weight: bold;">${oneNews.title}</td>



							<td>${oneNews.brief}</td>

							<td><c:if test="${oneNews.status == 'ON_APPROVAL'}">${on_approval}</c:if>
								<c:if test="${oneNews.status == 'PUBLISHED'}">${published}</c:if> <c:if
									test="${oneNews.status == 'DENIED_PUBLICATION'}">${denied}</c:if>
							</td>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>

				<div class="answer">
					<c:out value="${empty_offered_news}" />
				</div>
			</c:otherwise>
		</c:choose>
	</c:if>


	<c:if test="${user.role == 'admin'}">
		<c:choose>

			<c:when test="${not empty offeredNews}">

				<table id="news">

					<c:forEach var="oneNews" items="${offeredNews}">
						<tr>

							<td>${oneNews.title}</td>
							<td></td>
						<tr>
							<td>${oneNews.brief}</td>
							<td><c:if test="${user.role == 'admin'}">
									<div class="button_in_line">
										<form action="Controller" method="post">
											<input type="hidden" name="idNews" value="${oneNews.id}" />
											<input type="hidden" name="command"
												value="go_to_check_offered_news_page" /> <input
												type="submit" class="button" value="${check_offered_news}" />
										</form>
									</div>
									<div class="button_in_line">
										<form action="Controller" method="post">
											<input type="hidden" name="idNews" value="${oneNews.id}" />
											<input type="hidden" name="command"
												value="APPROVE_PUBLICATION" /> <input type="submit"
												class="button" value="${approve_publication}" />
										</form>
									</div>
									<br>
									<div class="button_in_line">
										<form action="Controller" method="post">
											<input type="hidden" name="idNews" value="${oneNews.id}" />
											<input type="hidden" name="command" value="deny_to_publish" />
											<input type="submit" class="button"
												value="${deny_publication}" />
										</form>
									</div>
								</c:if></td>
						</tr>

					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<div class="answer">
					<c:out value="${empty_offered_news}" />
				</div>
			</c:otherwise>
		</c:choose>
	</c:if>

	<br>

</body>
</html>