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
</style>

<meta charset="utf-8">
<title>Unknown Command</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization/local" var="loc" />

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

	<h1>Something went wrong...</h1>
	<h2>
		If you have any questions you can send them by email: <br>
		makarevich-elvira@mail.ru
	</h2>
</body>
</html>