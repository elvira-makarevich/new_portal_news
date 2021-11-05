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

.button_profile {
	font-family: Arial, Helvetica, sans-serif;
	border-color: white;
	background-color: thistle;
	color: white;
	text-align: center;
	padding: 6px 6px 6px 6px;
	font-size: 23px;
	cursor: pointer;
	border-radius: 12px;
	border-right: none;
	background-color: lightslategray;
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

.answer {
	font-size: 22px;
}

.header1 {
	display: inline;
	font: 50px Arial, sans-serif;
}

.picture_profile {
	width: 100%;
	height: 400px;
	background-image: url('recources/pictures/pro.png');
	background-size: cover;
}
</style>

<meta charset="utf-8">
<title>My profile</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization/local" var="loc" />

<fmt:message bundle="${loc}" key="local.profile.favorite.news"
	var="favorite_news" />

<fmt:message bundle="${loc}" key="local.locbutton.offer.news"
	var="offer_news" />

<fmt:message bundle="${loc}" key="local.locbutton.add.news"
	var="add_news" />
<fmt:message bundle="${loc}" key="local.profile.user.offered.news"
	var="user_offered_news" />

<fmt:message bundle="${loc}" key="local.profile.offered.news"
	var="offered_news" />



<fmt:message bundle="${loc}" key="local.profile.change.password.ok"
	var="changePasswordOk" />

<fmt:message bundle="${loc}" key="local.locbutton.main.page"
	var="mainPage" />

</head>
<body>

	<div class="button_right"><jsp:include
			page="/WEB-INF/jsp/commandsProfile.jsp" /></div>


	<form action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_main_page" />
		<button class="button">${mainPage}</button>
	</form>

	<br>
	<hr>
	<hr>


	<div class="answer">
		<c:if test="${not empty param.messageSuccessChangePassword}">
			<c:out value="${changePasswordOk}" /><br>
		</c:if>
	</div>

	<div class="button_in_line">
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="view_favorite_news" /> <input
				type="submit" class="button_profile" value="${favorite_news}" />
		</form>
	</div>


	<c:if test="${user.role == 'user'}">
		<div class="button_in_line">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="go_to_offer_news_page" />
				<input type="submit" class="button_profile" value="${offer_news}" />
			</form>
		</div>
	</c:if>



	<c:if test="${user.role == 'admin'}">
		<div class="button_in_line">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="GO_TO_ADD_NEWS_PAGE" />
				<input type="submit" class="button_profile" value="${add_news}" />
			</form>
		</div>
	</c:if>

	<c:if test="${user.role == 'user'}">
		<div class="button_in_line">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="VIEW_USER_OFFERED_NEWS" />
				<input type="submit" class="button_profile"
					value="${user_offered_news}" />
			</form>
		</div>
	</c:if>

	<c:if test="${user.role == 'admin'}">
		<div class="button_in_line">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="VIEW_OFFERED_NEWS" /> <input
					type="submit" class="button_profile" value="${offered_news}" />
			</form>
		</div>
	</c:if>

	<br>
	<br>
	<br>

	<div class="picture_profile"></div>

	<br>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>