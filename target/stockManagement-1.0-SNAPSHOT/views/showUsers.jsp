<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="header.jsp"/>
<c:if test="${sessionScope['sessionOK'] == 'OK'}">
    <input id="usersSearchBar" type="text" class="form-control" name="usersSearchBar"
           placeholder="Recherche"/>

    <form action="userUpdate" method="get" class="selectedUserForm float-right bg-transparent border-0">

        <input type="text" name="selectedUser-Login" class="form-control" id="selectedUserLogin"
               hidden>

        <input type="text" name="selectedUser-id" class="form-control" id="selectedUserid"
               hidden>

        <button type="submit" class="btn btn-primary float-right" id="updateUserbtn" disabled>Mettre à jour</button>
    </form>
    <table class="table table-striped table-hover tableListUser" id="tableListUser">
        <thead>
        <tr>
            <th hidden>ID</th>
            <th>Login</th>
            <th>Rôle</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Date de naissance</th>
            <th>Date d'inscription</th>
            <th>TVA</th>
            <th>Email</th>
            <th>Active/Inactive</th>
        </tr>
        </thead>
        <tbody id="UsersList">
        <c:forEach var="user" items="${ user }">
            <fmt:formatDate value="${user.dayOfBirth}" pattern="dd/MM/yyyy" var="dayOfBirth"/>
            <fmt:formatDate value="${user.inscriptionDate}" pattern="dd/MM/yyyy" var="inscriptionDate"/>
            <tr class="usersList" id="${ user.idUser }">
                <td hidden><c:out value="${ user.idUser }"/></td>
                <td><c:out value="${ user.login }"/></td>
                <td><c:out value="${ user.roles.role }"/></td>
                <td><c:out value="${ user.lastName }"/></td>
                <td><c:out value="${ user.firstName }"/></td>
                <td><c:out value="${ dayOfBirth }"/></td>
                <td><c:out value="${ inscriptionDate }"/></td>
                <td><c:out value="${ user.vat }"/></td>
                <td><c:out value="${ user.mail }"/></td>
                <td><c:out value="${ user.active == 'true'?'active':'inactive' }"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:import url="footer.jsp"/>