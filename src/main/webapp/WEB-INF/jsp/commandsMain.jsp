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

<fmt:message bundle="${loc}" key="local.locbutton.log.in" var="log_in" />
<fmt:message bundle="${loc}" key="local.locbutton.sign.up" var="sign_up" />
<fmt:message bundle="${loc}" key="local.locbutton.log.out" var="log_out" />
<fmt:message bundle="${loc}" key="local.locbutton.profile" var="profile" />

</head>
<body>



<c:if test="${user.role == 'guest'}">
		<div class="button_in_line">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="GO_TO_AUTHORIZATION_PAGE" />
				<input type="submit" class="button" value="${log_in}" />
			</form>
		</div>
		<div class="button_in_line">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="GO_TO_REGISTRATION_PAGE" />
				<input type="submit" class="button" value="${sign_up}" />
			</form>
		</div>
	</c:if>

	<c:if test="${user.role != 'guest'}">
		<div class="button_in_line">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="LOG_OUT" /> <input
					type="submit" class="button" value="${log_out}" />
			</form>
		</div>
		<div class="button_in_line">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="open_profile" /> <input
					type="submit" class="button" value="${profile}" />
			</form>
		</div>

	</c:if>

	<div class="button_locale">
		<form action="Controller" method="post">
			<input type="hidden" name="local" value="ru" /> <input type="hidden"
				name="command" value="CHANGE_LOCALE" /> <input type="submit"
				class="button_locale" value="${ru_button}" /><br />
		</form>
	</div>

	<div class="button_locale">
		<form action="Controller" method="post">
			<input type="hidden" name="local" value="en" /> <input type="hidden"
				name="command" value="CHANGE_LOCALE" /> <input type="submit"
				class="button_locale" value="${en_button}" /><br />
		</form>
	</div>



</body>
</html>