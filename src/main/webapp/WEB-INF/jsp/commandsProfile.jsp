<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="main.css" />
<meta charset="utf-8">
<title>Insert title here</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization/local" var="loc" />

<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="local.profile.change.password"
	var="change_password" />
<fmt:message bundle="${loc}" key="local.locbutton.log.in" var="log_in" />
<fmt:message bundle="${loc}" key="local.locbutton.sign.up" var="sign_up" />
<fmt:message bundle="${loc}" key="local.locbutton.log.out" var="log_out" />
<fmt:message bundle="${loc}" key="local.locbutton.main.page"
	var="mainPage" />


</head>
<body>

	<div class="button_in_line">
		<form action="Controller" method="post">
			<input type="hidden" name="command"
				value="go_to_change_password_page" /> <input type="submit"
				class="button" value="${change_password}" />

		</form>
	</div>

	
		<div class="button_in_line">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="LOG_OUT" /> <input
					type="submit" class="button" value="${log_out}" />
			</form>
		</div>




	<div class="button_in_line">
		<form action="Controller" method="post">
			<input type="hidden" name="local" value="ru" /> <input type="hidden"
				name="command" value="CHANGE_LOCALE" /> <input type="submit"
				class="button_locale" value="${ru_button}" /><br />
		</form>
	</div>

	<div class="button_in_line">
		<form action="Controller" method="post">
			<input type="hidden" name="local" value="en" /> <input type="hidden"
				name="command" value="CHANGE_LOCALE" /> <input type="submit"
				class="button_locale" value="${en_button}" /><br />
		</form>
	</div>

</body>
</html>