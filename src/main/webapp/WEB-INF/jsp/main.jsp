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

#news {
	border-collapse: collapse;
	border: 1px solid #ddd;
}

#news tr:nth-child(even) {
	font-size: 18px;
		border: 1px solid #ddd;
	padding: 10px;
	text-align: left;


}

#news tr:nth-child(odd) {
	font-size: 22px;
	font-weight: bold;

	padding: 10px;
	text-align: left;

}

td {
	padding: 10px;
}

.answer {
	font: 22px Arial, Helvetica, sans-serif;
	text-align: center;
}

.main_title {
	font: 30px Arial, Helvetica, sans-serif;
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

.button_in_line {
	display: inline-block;
}

.button_locale {
	border-radius: 12px;
	margin: 4px 2px;
	display: inline-block;
}

.right_button {
	position: absolute;
	right: 0px;
	padding: 3px;
}

.header1 {
	display: inline;
	font: 65px Arial, sans-serif;
}

.header2 {
	display: inline;
	font-size: 55px;
}
</style>



<meta charset="utf-8">

<title>News portal</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization/local" var="loc" />

<fmt:message bundle="${loc}" key="local.locbutton.read.news"
	var="read_news" />

<fmt:message bundle="${loc}" key="local.locbutton.edit.news"
	var="edit_news" />

<fmt:message bundle="${loc}" key="local.locbutton.delete.news"
	var="delete_news" />

<fmt:message bundle="${loc}" key="local.locbutton.offer.news"
	var="offer_news" />

<fmt:message bundle="${loc}" key="local.main.locbutton.all.news"
	var="all_news" />

<fmt:message bundle="${loc}" key="local.locbutton.add.news"
	var="add_news" />

<fmt:message bundle="${loc}" key="local.main.add.news.ok"
	var="addNewsOk" />


<fmt:message bundle="${loc}" key="local.main.add.news.error.validation"
	var="errorValidationAddNews" />

<fmt:message bundle="${loc}" key="local.main.edit.news.ok"
	var="editNewsOk" />

<fmt:message bundle="${loc}" key="local.main.edit.news.error.validation"
	var="errorValidationEditNews" />

<fmt:message bundle="${loc}"
	key="local.main.response.command.offer.news.ok" var="offerNewsOk" />
<fmt:message bundle="${loc}"
	key="local.main.offer.news.error.validation"
	var="errorValidationOfferNews" />

<fmt:message bundle="${loc}" key="local.main.no.rights"
	var="messageErrorNoRights" />

<fmt:message bundle="${loc}"
	key="local.response.command.service.exception"
	var="responseCommandServiceException" />

<fmt:message bundle="${loc}" key="local.main.success.sign.in"
	var="messageSignInOk" />

<fmt:message bundle="${loc}" key="local.main.delete.news.ok"
	var="deleteNewsOk" />


</head>
<body>

	<div class="right_button">
		<jsp:include page="/WEB-INF/jsp/commandsMain.jsp" />
	</div>
	<br>
	<br>
	<br>




	<div class="headercolor">
		<br>

		<div class="header1">
			<c:out value="NEWS" />
			|
		</div>
		<div class="header2">
			<i> <c:out value="Read, learn, be inspired..." /></i>
		</div>

		<img src="recources/pictures/leaf.png" width="70" height="90"> <br>
		<br>

	</div>
	<hr>
	<hr>

	<div class="button_in_line">
		<c:if test="${user.role != 'admin'}">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="go_to_offer_news_page" />
				<button class="button">${offer_news}</button>
			</form>
		</c:if>
	</div>

	<c:if test="${user.role == 'admin'}">
		<div class="button_in_line">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="GO_TO_ADD_NEWS_PAGE" />
				<button class="button">${add_news}</button>
			</form>
		</div>
	</c:if>


	<div class="button_in_line">
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="read_All_News" />
			<button class="button">${all_news}</button>
		</form>
	</div>

	<br>
	<br>
	<div class="answer">
		<c:if test="${not empty param.responseCommandDeleteNews}">
			<c:out value="${deleteNewsOk}" />
		</c:if>


		<c:if test="${not empty param.messageSuccessSignIn}">
			<c:out value="${messageSignInOk}" />
		</c:if>

		<c:if test="${not empty param.responseCommandOfferNews}">
			<c:out value="${offerNewsOk}" />
		</c:if>

		<c:if
			test="${not empty param.responseCommandOfferNewsErrorValidation}">
			<c:out value="${errorValidationOfferNews}" />
		</c:if>

		<c:if test="${not empty param.responseCommandAddNews}">
			<c:out value="${addNewsOk}" />
		</c:if>

		<c:if test="${not empty param.responseCommandAddNewsErrorValidation}">
			<c:out value="${errorValidationAddNews}" />
		</c:if>

		<c:if test="${not empty param.responseCommandNewsEditOk}">
			<c:out value="${editNewsOk}" />
		</c:if>

		<c:if test="${not empty param.messageErrorValidationEditNews}">
			<c:out value="${errorValidationEditNews}" />
		</c:if>


		<c:if test="${not empty param.messageErrorNoRights}">
			<c:out value="${messageErrorNoRights}" />
		</c:if>

		<c:if test="${not empty param.responseCommandServiceException}">
			<c:out value="${responseCommandServiceException}" />
		</c:if>
	</div>
	<br>

	<br>
	<br>
	<div class="main_title">The latest news</div>
	<br>
	<br>
	<table id="news">
		<c:forEach var="oneNews" items="${latestNews}">
			<tr>

				<td>${oneNews.title}</td>
				<td></td>
			<tr>
				<td><fmt:formatDate value="${oneNews.publishingDate}"
						pattern="yyyy-MM-dd" /> <br> ${oneNews.brief}</td>
				<td>
					<div class="button_in_line">
						<form action="Controller" method="post">
							<input type="hidden" name="idNews" value="${oneNews.id}" /> <input
								type="hidden" name="title" value="${oneNews.title}" /> <input
								type="hidden" name="command" value="read_news" /> <input
								type="submit" class="button" value="${read_news}" />
						</form>
					</div> <c:if test="${user.role == 'admin'}">
						<div class="button_in_line">
							<form action="Controller" method="post">
								<input type="hidden" name="idNews" value="${oneNews.id}" /> <input
									type="hidden" name="command" value="GO_TO_EDIT_NEWS_PAGE" /> <input
									type="submit" class="button" value="${edit_news}" />
							</form>
						</div>
						<div class="button_in_line">
							<form action="Controller" method="post">
								<input type="hidden" name="idNews" value="${oneNews.id}" /> <input
									type="hidden" name="command" value="delete_news" /> <input
									type="submit" class="button" value="${delete_news}" />
							</form>
						</div>
					</c:if>
				</td>
			</tr>

		</c:forEach>
	</table>
	<br>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>