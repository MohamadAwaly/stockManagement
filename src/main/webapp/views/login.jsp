<%--
  Created by IntelliJ IDEA.
  User: Awaly Mohamad
  Date: 13-08-21
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Stock management</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/LoginPage.css">
</head>
<body>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <div class="mb-3">
            <c:if test="${ !empty error}"><p class="alert alert-danger"><c:out value="${ error }"/></p></c:if>
        </div>
        <div class="fadeIn first">
            <img src="${pageContext.request.contextPath}/resources/pictures/Logo.png" id="icon" alt="User Icon"/>
        </div>
        <form method="post" action="Login">
            <div id="errorLoginPassword" hidden class="alert alert-danger">L'utilisateur existe d&eacute;ja</div>
            <input type="text" id="login-user" class="fadeIn second" name="login-user" placeholder="nom d'utilisateur" required>
            <input type="password" id="password" class="fadeIn third" name="password" placeholder="mot de passe" required>
            <input type="submit" class="fadeIn fourth" value="se connecter">
        </form>
        <div id="formFooter">
            <a class="underlineHover" href="adduser"> Inscription</a>
        </div>
    </div>
</div>
</body>
</html>