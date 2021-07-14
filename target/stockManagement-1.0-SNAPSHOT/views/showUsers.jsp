<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="header.jsp"/>
<!-- Button trigger modal -->
<div class="pull-right mb-3">
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <button type="button" class="btn btn-outline-info" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
            Mettre à jour
        </button>
        <button type="button" class="btn btn-outline-info" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
            Supprimer
        </button>
    </div>
</div>
<form class="selectedUserForm">
    <div class="row">
        <div class="col">
            <div class="mb-3">
                <input type="text" name="selectedUser-Login" class="form-control" id="selectedUserLogin"
                       aria-describedby="selectedUser">
            </div>
        </div>
        <div class="col">
<%--            <div class="mb-3">--%>
                <input type="text" name="selectedUser-id" class="form-control" id="selectedUserid"
                       aria-describedby="selectedUser" hidden>
<%--            </div>--%>
        </div>
    </div>
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
        <th>rue</th>
        <th>Numero</th>
        <th>boite</th>
        <th>Ville</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${ user }">
        <fmt:formatDate value="${user[0].dayOfBirth}" pattern="dd/MM/yyyy" var="dayOfBirth"/>
        <fmt:formatDate value="${user[0].inscriptionDate}" pattern="dd/MM/yyyy" var="inscriptionDate"/>
        <tr class="usersList" id="${ user[0].idUser }">
            <td><c:out value="${ user[0].idUser }"/></td>
            <td><c:out value="${ user[0].login }"/></td>
            <td><c:out value="${ user[0].roles.role }"/></td>
            <td><c:out value="${ user[0].lastName }"/></td>
            <td><c:out value="${ user[0].firstName }"/></td>
            <td><c:out value="${ dayOfBirth }"/></td>
            <td><c:out value="${ inscriptionDate }"/></td>
            <td><c:out value="${ user[0].vat }"/></td>
            <td><c:out value="${ user[0].mail }"/></td>
            <td><c:out value="${ user[0].active }"/></td>
            <td><c:out value="${ user[1].street }"/></td>
            <td><c:out value="${ user[1].number }"/></td>
            <td><c:out value="${ user[1].box }"/></td>
            <td><c:out value="${ user[1].city.citie }"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:import url="updateUser.jsp"/>
<c:import url="footer.jsp"/>