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

.button_in_line {
	display: inline-block;
}

.button {
	font-family: Arial, sans-serif;
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

.header1 {
	display: inline;
	font: 50px Arial, sans-serif;
}

.content {
	font: 20px Arial, sans-serif;
	width: 40%;
	border: 3px solid #B9B29F;
		padding: 10px;
	
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

</style>
<meta charset="utf-8">
<title>Check news</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization/local" var="loc" />

<fmt:message bundle="${loc}" key="local.checkOfferedNews.offered.news"
	var="offered_news" />
<fmt:message bundle="${loc}"
	key="local.checkOfferedNews.approve.publication"
	var="approve_publication" />
<fmt:message bundle="${loc}"
	key="local.checkOfferedNews.deny.publication" var="deny_publication" />
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

	<form action="Controller" method="post">
		<input type="hidden" name="command" value="VIEW_OFFERED_NEWS" />
		<button class="button">${offered_news}</button>
	</form>



	<br>
	<h2>Title</h2>

	<div class="content">
		<c:out value="${news.title}" />
	</div>


	<br>

	<h2>Brief</h2>

	<div class="content">
		<c:out value="${news.brief}" />
	</div>

	<br>
	<h2>Content</h2>
	<br>
	<div class="content">
		<c:out value="${news.content}" />
	</div>

	<br>
	<br>
	<div class="button_in_line">
		<form action="Controller" method="post">
			<input type="hidden" name="idNews" value="${news.id}" /> <input
				type="hidden" name="command" value="APPROVE_PUBLICATION" /> <input
				type="submit" class="button" value="${approve_publication}" />
		</form>
	</div>
	<div class="button_in_line">
		<form action="Controller" method="post">
			<input type="hidden" name="idNews" value="${news.id}" /> <input
				type="hidden" name="command" value="deny_to_publish" /> <input
				type="submit" class="button" value="${deny_publication}" />
		</form>
	</div>
	<br>
	<br>

</body>
</html>