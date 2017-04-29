<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link type="text/css" rel="stylesheet"
	href="${root}/assets/css/main.css" />
<link type="text/css" rel="stylesheet"
	href="${root}/assets/css/pages/${pageStyle}.css" />
<title>${pageTitle}</title>
</head>

<body>
	<div class="header" id="header">
		<div class="title">DevWeb2</div>
		<c:choose>
			<c:when test="${sessionScope.user == null}">
			<div class="buttons">
				<button id="signIn">Log In</button>
				<button id="signUp" class="signup-btn">Sign Up</button>
			</div>
			</c:when>
			<c:otherwise>
			<a href="">
			<div class="profile-section">
			<img class="profile-img" src="${root}/uploads/${sessionScope.user.id}${sessionScope.user.image}"/> 
				<span class="profile-name">${sessionScope.user.uname}</span>
			</div>
			</a>
			</c:otherwise>
		</c:choose>
	</div>
	<%@ include file="signin.jsp"%>
	<%@ include file="signup.jsp"%>
	