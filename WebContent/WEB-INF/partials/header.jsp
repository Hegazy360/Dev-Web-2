<%@page contentType="text/html" pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
      <c:set var="root" value="${pageContext.request.contextPath}" />
      <!DOCTYPE html>
      <html>

      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link type="text/css" rel="stylesheet" href="${root}/assets/css/main.css" />
        <link type="text/css" rel="stylesheet" href="${root}/assets/css/pages/${pageStyle}.css" />
        <title>${pageTitle}</title>
      </head>

      <body>
      <div class="header">
        <div class="title">
Test
        </div>
        <div class="buttons">
          <a href = "user?action=signin">Log In</a>
          <a href = "user?action=new" class="signup-btn">Sign Up</a>
        </div>
      </div>
