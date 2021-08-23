<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="header.jsp"/>
<!-- Button trigger modal -->
<%--<div class="pull-right mb-3">--%>
<%--&lt;%&ndash;    #staticBackdrop&ndash;%&gt;--%>
<%--    <div class="d-grid gap-2 d-md-flex justify-content-md-end">--%>
<%--        <button type="button" class="btn btn-outline-info" data-bs-toggle="modal" data-bs-target="#staticBackdrop" onclick="userUpdate">--%>
<%--            Mettre à jour--%>
<%--        </button>--%>
<%--        <button type="button" class="btn btn-outline-info" data-bs-toggle="modal" data-bs-target="#staticBackdrop">--%>
<%--            Supprimer--%>
<%--        </button>--%>
<%--    </div>--%>
<%--</div>--%>
<form action="userUpdate" method="get" class="selectedUserForm float-right bg-transparent border-0">
<%--    <div class="row">--%>
<%--        <div class="col">--%>
<%--            <div class="mb-3">--%>
                <input type="text" name="selectedUser-Login" class="form-control" id="selectedUserLogin"
                       aria-describedby="selectedUser" hidden>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col">--%>
<%--            <div class="mb-3">--%>
                <input type="text" name="selectedUser-id" class="form-control" id="selectedUserid"
                       aria-describedby="selectedUser" hidden>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
    <button type="submit" class="btn btn-primary float-right">Mettre à jour</button>
</form>
<table class="table table-striped table-hover" border="1px solid black">
    <thead>
    <tr>
        <th>ID</th>
        <th>login</th>
        <th>Role</th>
        <th>Nom</th>
        <th>Prenom</th>
        <th>Date de naissance</th>
        <th>Date d'insdcription</th>
        <th>TVA</th>
        <th>Email</th>
        <th>active</th>
<%--        <th>rue</th>--%>
<%--        <th>Numero</th>--%>
<%--        <th>boite</th>--%>
<%--        <th>Ville</th>--%>
<%--        <th>Type addresse</th>--%>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${ user }">
        <fmt:formatDate value="${user.dayOfBirth}" pattern="dd/MM/yyyy" var="dayOfBirth"/>
        <fmt:formatDate value="${user.inscriptionDate}" pattern="dd/MM/yyyy" var="inscriptionDate"/>
        <tr class="usersList" id="${ user.idUser }">
            <td><c:out value="${ user.idUser }"/></td>
            <td><c:out value="${ user.login }"/></td>
            <td><c:out value="${ user.roles.role }"/></td>
            <td><c:out value="${ user.lastName }"/></td>
            <td><c:out value="${ user.firstName }"/></td>
            <td><c:out value="${ dayOfBirth }"/></td>
            <td><c:out value="${ inscriptionDate }"/></td>
            <td><c:out value="${ user.vat }"/></td>
            <td><c:out value="${ user.mail }"/></td>
            <td><c:out value="${ user.active }"/></td>
<%--            <td><c:out value="${ user[1].street }"/></td>--%>
<%--            <td><c:out value="${ user[1].number }"/></td>--%>
<%--            <td><c:out value="${ user[1].box }"/></td>--%>
<%--            <td><c:out value="${ user[1].city.citie }"/></td>--%>
<%--            <td><c:out value="${ user[2].typeAdress }"/></td>--%>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:import url="footer.jsp"/>