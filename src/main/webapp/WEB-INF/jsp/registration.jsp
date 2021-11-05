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

input {
	padding: 10px 12px 10px 6px;
	border-radius: 4px;
	cursor: pointer;
	width: 25%;
	margin-top: 6px;
	box-sizing: border-box;
	border: 1px solid #555;
	outline: none;
	font-size: 16px;
}

input:focus {
	background-color: lightblue;
}

.header1 {
	display: inline;
	font: 50px Arial, sans-serif;
}

.button {
	font-family: Arial, Helvetica, sans-serif;
	border-color: lightslategray;
	background-color: white;
	color: lightslategray;
	text-align: center;
	font-size: 16px;
	cursor: pointer;
	border-radius: 12px;
	border-right: none;
	margin: 6px;
	padding: 6px 12px 6px 12px;
}

.button_in_line {
	display: inline-block;
}

.button_locale {
	border-radius: 12px;
	margin: 4px 2px;
	display: inline-block;
	right: 0px;
}

.answer {
	font: 22px Arial, Helvetica, sans-serif;
	text-align: center;
}

.requirements {
	font: 16px Arial, Helvetica, sans-serif;
	border: 3px solid red;
	border-radius: 12px;
	padding: 4px;
	width: 65%;
}
</style>
<meta charset="utf-8">
<title>Registration</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization/local" var="loc" />

<fmt:message bundle="${loc}" key="local.registration.page"
	var="registration" />
<fmt:message bundle="${loc}" key="local.registration.password.error"
	var="passwordError" />
<fmt:message bundle="${loc}" key="local.registration.error.validation"
	var="validationError" />
<fmt:message bundle="${loc}" key="local.registration.validation.name"
	var="validationName" />
<fmt:message bundle="${loc}" key="local.registration.validation.surname"
	var="validationSurname" />
<fmt:message bundle="${loc}" key="local.registration.validation.login"
	var="validationLogin" />
<fmt:message bundle="${loc}" key="local.registration.validation.email"
	var="validationEmail" />
<fmt:message bundle="${loc}"
	key="local.registration.validation.password" var="validationPassword" />

<fmt:message bundle="${loc}"
	key="local.registration.error.login.email.exists"
	var="loginOrEmailExists" />

<fmt:message bundle="${loc}" key="local.registration.enter.name"
	var="enter_name" />
<fmt:message bundle="${loc}" key="local.registration.enter.surname"
	var="enter_surname" />
<fmt:message bundle="${loc}" key="local.enter.login" var="enter_login" />
<fmt:message bundle="${loc}" key="local.registration.enter.email"
	var="enter_email" />
<fmt:message bundle="${loc}" key="local.enter.password"
	var="enter_password" />
<fmt:message bundle="${loc}" key="local.registration.repeat.password"
	var="repeat_password" />
<fmt:message bundle="${loc}" key="local.locbutton.send"
	var="button_send" />

</head>
<body>

	<jsp:include page="/WEB-INF/jsp/commandsSignInSignUp.jsp" />
	<br>
	<br>

	<div class="header1">${registration}<img
			src="recources/pictures/leaf.png" width="70" height="90">
	</div>

	<hr>
	<hr>
	<div class="answer">
		<c:if test="${not empty param.passwordErrorMismatch}">
			<c:out value="${passwordError}" />
		</c:if>

		<c:if test="${not empty param.messageErrorValidation}">
			<c:out value="${validationError}" />
		</c:if>

		<c:if test="${not empty param.messageErrorLoginOrEmailExists}">
			<c:out value="${loginOrEmailExists}" />
		</c:if>
	</div>

	<form action="Controller" method="post">
		<input type="hidden" name="command" value="registration_new_user" />

		<br /> <input type="text" name="name" placeholder="${enter_name}"
			pattern="[a-zA-Z]{2,20}$)|([а-яА-яёЁ]{2,20}$" value="${param.name}"
			title="Имя должно содержать от 2 до 20 символов" /><br />

		<c:if test="${not empty param.messageErrorValidation}">
			<br />
			<div class="requirements">
				<c:out value="${validationName}" />
			</div>
		</c:if>

		<br /> <br /> <input type="text" name="surname"
			pattern="[a-zA-Z]{2,20}$)|([а-яА-яёЁ]{2,20}$"
			title="Фамилия должна содержать от 2 до 20 символов"
			placeholder="${enter_surname}" value="${param.surname}" /><br />

		<c:if test="${not empty param.messageErrorValidation}">
			<br />
			<div class="requirements">
				<c:out value="${validationSurname}" />
			</div>
		</c:if>

		<br /> <br /> <input type="text" name="login"
			placeholder="${enter_login}" value="${param.login}"
			pattern="[a-zA-Zа-яА-яёЁ!_$^]{6,20}$"
			title="Логин должен содержать от 6 до 20 символов, в том числе может содержать ! _ $ ^ " />
		<br />

		<c:if test="${not empty param.messageErrorValidation}">
			<br />
			<div class="requirements">
				<c:out value="${validationLogin}" />
			</div>
		</c:if>

		<br /> <br /> <input type="email" name="email"
			placeholder="${enter_email}" value="${param.email}" /><br />

		<c:if test="${not empty param.messageErrorValidation}">
			<br />
			<div class="requirements">
				<c:out value="${validationEmail}" />
			</div>
		</c:if>

		<br /> <br /> <input type="password" name="password1"
			pattern="(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}"
			placeholder="${enter_password}" value=""
			title="Пароль: \n - содержит хотя бы одно число; \n - содержит хотя бы один спецсимвол [!@#$%^&*]; \n - содержит хотя бы одну латинскую букву в нижнем регистре; \n -  содержит хотя бы одну латинскую букву в верхнем регистре; \n - состоит не менее, чем из 8 вышеупомянутых символов. " />
		<br />

		<c:if test="${not empty param.messageErrorValidation}">
			<br />
			<div class="requirements">
				<c:out value="${validationPassword}" />
			</div>
		</c:if>

		<br /> <br /> <input type="password" name="password2"
			pattern="(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}"
			placeholder="${repeat_password}" value="" /><br /> <br />

		<div class="button_in_line">
			<button class="button">${button_send}</button>
		</div>


	</form>

</body>
</html>