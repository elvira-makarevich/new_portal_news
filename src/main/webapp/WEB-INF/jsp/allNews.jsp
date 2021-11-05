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

.button_right {
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
<title>All news</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization/local" var="loc" />

<fmt:message bundle="${loc}" key="local.allNews.title" var="all_news" />
<fmt:message bundle="${loc}" key="local.locbutton.read.news"
	var="read_news" />
<fmt:message bundle="${loc}" key="local.locbutton.edit.news"
	var="edit_news" />
<fmt:message bundle="${loc}" key="local.locbutton.delete.news"
	var="delete_news" />
<fmt:message bundle="${loc}" key="local.allNews.button.previous"
	var="previous" />
<fmt:message bundle="${loc}" key="local.allNews.button.next" var="next" />
<fmt:message bundle="${loc}" key="local.locbutton.main.page"
	var="mainPage" />

</head>

<body>

	<div class="button_right"><jsp:include
			page="/WEB-INF/jsp/commands.jsp" /></div>
	<br>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_main_page" />
		<button class="button">${mainPage}</button>
	</form>
	<br>
	<br>
	<div class="header1">${all_news}<img
			src="recources/pictures/leaf.png" width="70" height="90">
	</div>

	<table id="news">
		<c:forEach var="oneNews" items="${limitNews}">
			<tr>

				<td>${oneNews.title}</td>
			<tr>
				<td>${oneNews.brief}</td>
				<td>
					<div class="button_in_line">
						<form action="Controller" method="post">
							<input type="hidden" name="idNews" value="${oneNews.id}" /> <input
								type="hidden" name="title" value="${oneNews.title}" /> <input
								type="hidden" name="command" value="read_news" /> <input
								type="submit" class="button" value="${read_news}" />
						</form>
					</div> <c:if test="${user.role == 'admin'}">
						<div class="button_in_line">
							<form action="Controller" method="post">
								<input type="hidden" name="idNews" value="${oneNews.id}" /> <input
									type="hidden" name="command" value="GO_TO_EDIT_NEWS_PAGE" /> <input
									type="submit" class="button" value="${edit_news}" />
							</form>
						</div>
						<div class="button_in_line">
							<form action="Controller" method="post">
								<input type="hidden" name="idNews" value="${oneNews.id}" /> <input
									type="hidden" name="command" value="delete_news" /> <input
									type="submit" class="button" value="${delete_news}" />
							</form>
						</div>
					</c:if>
				</td>
			</tr>

		</c:forEach>
	</table>
	<br>
	<br>

	<%--For displaying Previous page except for the 1st page --%>
	<c:if test="${requestScope.currentPage != 1}">
		<td>
			<form action="Controller" method="post">
				<input type="hidden" name="currentPage"
					value="${requestScope.currentPage-1}" /> <input type="hidden"
					name="command" value="read_All_News" /> <input type="submit"
					class="button" value="${previous}" />
			</form>
		</td>
	</c:if>

	<%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<c:forEach begin="1" end="${requestScope.numberOfPages}" var="i">
				<c:choose>
					<c:when test="${requestScope.currentPage eq i}">
						<td>${i}</td>
					</c:when>
					<c:otherwise>
						<td>
							<form action="Controller" method="post">
								<input type="hidden" name="currentPage" value="${i}" /> <input
									type="hidden" name="command" value="read_All_News" /> <input
									type="submit" class="button" value="${i}" />
							</form>
						</td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	</table>

	<%--For displaying Next page --%>

	<c:if test="${requestScope.currentPage lt requestScope.numberOfPages}">
		<td><form action="Controller" method="post">
				<input type="hidden" name="command" value="read_All_News" /> <input
					type="hidden" name="currentPage"
					value="${requestScope.currentPage+1}" /> <input type="submit"
					class="button" value="${next}" />
			</form></td>
	</c:if>

</body>
</html>