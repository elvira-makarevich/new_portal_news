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

.answer {
	font: 22px Arial, Helvetica, sans-serif;
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

.requirements {
	font: 16px Arial, Helvetica, sans-serif;
	border: 3px solid red;
	border-radius: 12px;
	padding: 4px;
	width: 65%;
}
</style>
<meta charset="utf-8">

<title>Authorization</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization/local" var="loc" />

<fmt:message bundle="${loc}" key="local.authorization.page"
	var="authorization" />
<fmt:message bundle="${loc}" key="local.enter.login" var="enter_login" />
<fmt:message bundle="${loc}" key="local.enter.password"
	var="enter_password" />
<fmt:message bundle="${loc}" key="local.locbutton.send"
	var="button_send" />
<fmt:message bundle="${loc}"
	key="local.authorization.no.session.no.user"
	var="messageErrorNoSessionNoUser" />
<fmt:message bundle="${loc}" key="local.authorization.error.validation"
	var="validationError" />
<fmt:message bundle="${loc}" key="local.authorization.validation.login"
	var="validationLogin" />
<fmt:message bundle="${loc}"
	key="local.authorization.validation.password" var="validationPassword" />
<fmt:message bundle="${loc}" key="local.authorization.invalid.data"
	var="invalidDataError" />
<fmt:message bundle="${loc}"
	key="local.authorization.message.registration.ok"
	var="messageSuccessRegistration" />
</head>

<body>


	<jsp:include page="/WEB-INF/jsp/commandsSignInSignUp.jsp" />

	<br>
	<br>




	<div class="header1">
		<c:out value="${authorization}" />
		<img src="recources/pictures/leaf.png" width="70" height="90">
	</div>

	<hr>
	<hr>
	<div class="answer">
		<c:if test="${not empty param.messageErrorNoSessionNoUser}">
			<c:out value="${messageErrorNoSessionNoUser}" />
		</c:if>

		<c:if test="${not empty param.messageErrorValidationSignIn}">
			<c:out value="${validationError}" />
		</c:if>

		<c:if test="${not empty param.messageErrorInvalidData}">
			<c:out value="${invalidDataError}" />
		</c:if>

		<c:if test="${not empty param.messageSuccessRegistration}">
			<c:out value="${messageSuccessRegistration}" />
		</c:if>
	</div>



	<form action="Controller" method="post">
		<input type="hidden" name="command" value="sign_in" /> <br /> <input
			type="text" name="login" placeholder="${enter_login}" value=""
			required pattern="[a-zA-Z]{2,20}$)|([а-яА-яёЁ]{2,20}$"
			title="Логин должен содержать от 6 до 20 символов, в том числе ! _ $ ^ " />

		<br />
		<c:if test="${not empty param.messageErrorValidationSignIn}">
			<br />
			<div class="requirements">
				<c:out value="${validationLogin}" />
			</div>
		</c:if>


		<br /> <br /> <input type="password" name="password"
			placeholder="${enter_password}" value="" required pattern="(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}"
			title="Пароль: \n - содержит хотя бы одно число; \n - содержит хотя бы один спецсимвол [!@#$%^&*]; \n - содержит хотя бы одну латинскую букву в нижнем регистре; \n -  содержит хотя бы одну латинскую букву в верхнем регистре; \n - состоит не менее, чем из 8 вышеупомянутых символов. " />
		<br />
		<c:if test="${not empty param.messageErrorValidationSignIn}">
			<br />
			<div class="requirements">
				<c:out value="${validationPassword}" />
			</div>
		</c:if>

		<br />
		<div class="button_in_line">
			<button class="button">${button_send}</button>
		</div>

	</form>
	<br>
	
</body>
</html>