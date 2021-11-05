<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>

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
<fmt:message bundle="${loc}" key="local.locbutton.main.page"
	var="mainPage" />


</head>
<body>

	<c:if test="${user.role == 'guest'}">
		<div class="button_in_line">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="GO_TO_AUTHORIZATION_PAGE" />
			
					<button class="button">${log_in}</button>
			</form>
		</div>
		<div class="button_in_line">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="GO_TO_REGISTRATION_PAGE" />
				
						<button class="button">${sign_up}</button>
				
				
			</form>
		</div>
	</c:if>

	<c:if test="${user.role != 'guest'}">
		<div class="button_in_line">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="LOG_OUT" /> 
				<button class="button">${log_out}</button>
					
			</form>
		</div>
		<div class="button_in_line">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="open_profile" /> 
				
					<button class="button">${profile}</button>
			</form>
		</div>
	</c:if>
	<div class="button_in_line">
		<form action="Controller" method="post">
			<input type="hidden" name="local" value="ru" /> <input type="hidden"
				name="command" value="CHANGE_LOCALE" /> 
				<button class="button_locale">${ru_button}</button>
				
		</form>
	</div>

	<div class="button_in_line">
		<form action="Controller" method="post">
			<input type="hidden" name="local" value="en" /> <input type="hidden"
				name="command" value="CHANGE_LOCALE" /> 
				<button class="button_locale">${en_button}</button>
		</form>
	</div>

</body>
</html>