<%--
  Created by IntelliJ IDEA.
  User: Awaly Mohamad
  Date: 25-05-21
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/allCss.css"/>
    <title>Stock management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/allCss.css" />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
    <link rel="stylesheet" href="https://bootswatch.com/4/flatly/bootstrap.min.css">
    <%--    <link rel="stylesheet" href="../resources/css/allCss.css">--%>
    <%--    <script type="text/javascript" src="../resources/js/script.js"></script>--%>
    <%--    <script type="text/javascript" src="../resources/js/jquery-3.5.1.js"></script>--%>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
            crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"> Stock management</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01"
                aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarColor01">
            <ul class="navbar-nav me-auto">
                <c:if test="${sessionScope.SessionUserEntity.roles.role.trim() == 'administrateur' || sessionScope.SessionUserEntity.roles.role.trim() == 'directeur'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                           aria-haspopup="true" aria-expanded="false">Users</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="adduser">Ajouter utilisateur</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="UsersShowAll">Liste des utilisateurs</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                           aria-haspopup="true" aria-expanded="false">Fournisseurs</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="supplierCreate">Ajouter fournisseurs</a>
                            <a class="dropdown-item" href="CommandSupplierCreate">Faire une commande au fournisseur</a>
                            <a class="dropdown-item" href="CommandSupplierShowAll">Voir les commandes fournisseurs</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="suppliersShowAll">Liste des fournisseurs</a>
                        </div>
                    </li>
                </c:if>
            </ul>
            <c:choose>
                <c:when test="${sessionScope.SessionUser != null}">
                    <a class="navbar-brand " href="UserProfile"> Ma Fiche </a>
                </c:when>
            </c:choose>

        </div>

    </div>

</nav>

<header>
    <c:choose>
        <c:when test="${sessionScope.SessionUser != null}">
            <form class="form-login">
                <SELECT name="nom" size="1" action="UserSignOut" class="class-headerlogin" onchange="location = this.value">
                    <option><c:out value="${sessionScope.SessionUser}"/></option>
                    <option value="UserSignOut">se deconnecter</option>
                </SELECT>
            </form>
        </c:when>
        <c:when test="${sessionScope.SessionUser == null}">
            <form method="get" action="Login" class="form-login">
                <button type="submit" class="btn btn-light class-headerlogin">Se connecter</button>
            </form>
        </c:when>
    </c:choose>
</header>