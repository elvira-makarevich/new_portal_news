<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="main.css" />
<style>
</style>

<meta charset="utf-8">
<title>Insert title here</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization/local" var="loc" />

<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="local.locbutton.main.page"
	var="mainPage" />

</head>
<body>

	<div class="button_in_line">
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="go_to_main_page" />
			<button class="button">${mainPage}</button>
		</form>
	</div>


		<div class="button_locale">
			<form action="Controller" method="post">
				<input type="hidden" name="local" value="ru" /> <input
					type="hidden" name="command" value="CHANGE_LOCALE" />
				<button class="button_locale">${ru_button}</button>
				<br />
			</form>
		</div>

		<div class="button_locale">
			<form action="Controller" method="post">
				<input type="hidden" name="local" value="en" /> <input
					type="hidden" name="command" value="CHANGE_LOCALE" />
				<button class="button_locale">${en_button}</button>
			</form>
		</div>
	



</body>
</html>