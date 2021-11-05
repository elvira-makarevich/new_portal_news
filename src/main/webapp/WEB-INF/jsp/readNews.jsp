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

#comments {
	border-collapse: collapse;
	width: 45%;
}

#comments tr:nth-child(even) {
	font-size: 15px;	
	padding: 10px;
	text-align: right;
	
}

#comments tr:nth-child(odd) {
	font-size: 20px;
	padding: 10px;
	text-align: left;
	
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

.button_right {
	position: absolute;
	right: 0px;
	padding: 3px;
}

.header1 {
	display: inline;
	font: 50px Arial, sans-serif;
}

.content {
	font: 20px Arial, sans-serif;
	width: 60%;
}

.answer {
	font: 22px Arial, Helvetica, sans-serif;
}

input[type=text] {
	padding: 5px;
	border-radius: 4px;
	cursor: pointer;
	width: 20%;
	box-sizing: border-box;
	border: 1px solid #555;
	outline: none;
	font-size: 16px;
	border-radius: 4px;
	cursor: pointer;
	width: 25%;
	box-sizing: border-box;
	border: 1px solid #555;
	outline: none;
}
</style>
<meta charset="utf-8">
<title>Read news</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization/local" var="loc" />


<fmt:message bundle="${loc}"
	key="local.readNews.add.comment.error.validation"
	var="addCommentErrorValidation" />

<fmt:message bundle="${loc}" key="local.readNews.add.comment.error"
	var="addCommentError" />

<fmt:message bundle="${loc}" key="local.readNews.add.to.favorites"
	var="responseCommandAddToFavorites" />

<fmt:message bundle="${loc}"
	key="local.readNews.response.delete.from.favorites"
	var="responseCommandDeleteFromFavorites" />

<fmt:message bundle="${loc}"
	key="local.response.command.service.exception"
	var="responseCommandServiceException" />

<fmt:message bundle="${loc}" key="local.readNews.delete.from.favorites"
	var="delete_from_favorites" />

<fmt:message bundle="${loc}"
	key="local.readNews.button.add.to.favorites" var="add_to_favorites" />

<fmt:message bundle="${loc}" key="local.readNews.placeholder.comment"
	var="placeholder_comment" />

<fmt:message bundle="${loc}" key="local.readNews.add.comment"
	var="add_comment" />
<fmt:message bundle="${loc}" key="local.locbutton.main.page"
	var="mainPage" />
<fmt:message bundle="${loc}" key="local.readNews.comments"
	var="comments" />

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

	<div class="answer">
		<c:if
			test="${not empty requestScope.messageErrorValidationAddComment}">
			<c:out value="${addCommentErrorValidation}" />
		</c:if>

		<c:if test="${not empty requestScope.responseCommandServiceException}">
			<c:out value="${responseCommandServiceException}" />
		</c:if>

		<c:if test="${not empty requestScope.responseCommandAddToFavorites}">
			<c:out value="${responseCommandAddToFavorites}" />
		</c:if>

		<c:if
			test="${not empty requestScope.responseCommandDeleteFromFavorites}">
			<c:out value="${responseCommandDeleteFromFavorites}" />
		</c:if>
	</div>

	<br>
	<div class="header1">
		<c:out value="${newsForReading.title}" />
		<img src="recources/pictures/leaf.png" width="70" height="90">
	</div>
	<br>
	<br>

	<c:if test="${requestScope.isFavorite==true}">
		<form action="Controller" method="post">
			<input type="hidden" name="idNews" value="${requestScope.idNews}" />
			<input type="hidden" name="command" value="delete_from_favorites" />
			<input type="image" src="recources/pictures/true.png" width="30"
				height="30">
		</form>

	</c:if>

	<c:if test="${requestScope.isFavorite==false}">
		<form action="Controller" method="post">
			<input type="hidden" name="idNews" value="${requestScope.idNews}" />
			<input type="hidden" name="command" value="add_to_favorites" /> <input
				type="image" src="recources/pictures/false.png" width="30"
				height="30">
		</form>
	</c:if>

	<fmt:formatDate value="${newsForReading.publishingDate}"
		pattern="yyyy-MM-dd" />
	<br>
	<br>
	<div class="content">
		<c:out value="${newsForReading.content}" />
	</div>
	<br>
	<br>
	<br>
	<div class="answer">
		<c:out value="${comments}" />
	</div>

	<br>
	<br>
	<table id="comments">

		<c:forEach var="comment" items="${requestScope.comments}">
			<tr>
				<td>${comment.content}</td>
		
			</tr>

			<tr>
				<td><fmt:formatDate value="${comment.publishingDate}"
						pattern="yyyy-MM-dd" /></td>
						
			</tr>

		</c:forEach>
	</table>
	<br>
	<br>
	<form action="Controller" method="post">
		<input type="hidden" name="idNews" value="${requestScope.idNews}" />
		<input type="hidden" name="command" value="add_comment" /> <input
			type="text" name="comment" placeholder="${placeholder_comment}"
			value="" /> <br> <br>

		<button class="button">${add_comment}</button>
	</form>



</body>
</html>