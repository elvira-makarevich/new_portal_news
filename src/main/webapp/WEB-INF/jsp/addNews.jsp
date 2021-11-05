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

.header1 {
	display: inline;
	font: 50px Arial, sans-serif;
}

.button_right {
	position: absolute;
	right: 0px;
	padding: 3px;
}

.button_locale {
	border-radius: 12px;
	margin: 4px 2px;
	display: inline-block;
}

input[type=text], textarea {
	font-family: Arial, Helvetica, sans-serif;
	width: 70%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 16px;
}

.answer {
	font: 22px Arial, Helvetica, sans-serif;
	width: 70%;
}
</style>
<meta charset="utf-8">

<title>Adding News</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization/local" var="loc" />

<fmt:message bundle="${loc}" key="local.addNews.adding.news"
	var="add_news" />
<fmt:message bundle="${loc}" key="local.addNews.offer.news"
	var="offer_news" />
<fmt:message bundle="${loc}" key="local.enter.title" var="enter_title" />
<fmt:message bundle="${loc}" key="local.enter.brief" var="enter_brief" />
<fmt:message bundle="${loc}" key="local.enter.content"
	var="enter_content" />
<fmt:message bundle="${loc}" key="local.locbutton.send"
	var="button_send" />
<fmt:message bundle="${loc}" key="local.addNews.error.empty"
	var="addNewsErrorEmpty" />
<fmt:message bundle="${loc}"
	key="local.addNews.error.too.long.parameter"
	var="addNewsTooLongParameter" />
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
	<div class="header1">

		<c:if test="${user.role == 'admin'}">
			<c:out value="${add_news}" />
		</c:if>

		<c:if test="${user.role == 'user'}">
			<c:out value="${offer_news}" />
		</c:if>

		<img src="recources/pictures/leaf.png" width="70" height="90">
	</div>
	<br>
	<br>
	<div class="answer">
		<c:if test="${not empty param.messageErrorEmpty}">
			<c:out value="${addNewsErrorEmpty}" />
		</c:if>

		<c:if test="${not empty param.messageErrorTooLongParameter}">
			<c:out value="${addNewsTooLongParameter}" />
		</c:if>
	</div>

	<form action="Controller" method="post">

		<c:if test="${user.role == 'admin'}">
			<input type="hidden" name="command" value="add_news" />
		</c:if>

		<c:if test="${user.role == 'user'}">
			<input type="hidden" name="command" value="offer_news" />
		</c:if>

		<br /> <input type="text" name="title" placeholder="${enter_title}"
			value="${param.title}" required /> <br /> <br /> <input
			type="text" name="brief" placeholder="${enter_brief}"
			value="${param.brief}" /><br /> <br />

		<textarea name="content" placeholder="${enter_content}"
			style="height: 200px">${param.content}</textarea>
		<br /> <br /> <br />
		<button class="button">${button_send}</button>
	</form>

	<br>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>