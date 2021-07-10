<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="header.jsp"/>
<h2>Liste de tout les utilisateurs</h2>
<table class="table table-striped table-hover" border="1px solid black">
    <thead>
    <tr>
        <th>ID</th>
        <th>login</th>
        <th>Role</th>
        <th>lastName</th>
        <th>firstName</th>
        <th>dayOfBirth</th>
        <th>inscriptionDate</th>
        <th>VAT</th>
        <th>mail</th>
        <th>active</th>
        <th>rue</th>
        <th>Ville</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${ user }">
        <fmt:formatDate value="${user[0].dayOfBirth}" pattern="dd/MM/yyyy" var="dayOfBirth"/>
        <fmt:formatDate value="${user[0].inscriptionDate}" pattern="dd/MM/yyyy" var="inscriptionDate"/>
        <tr>
            <td><c:out value="${ user[0].idUser } "/></td>
            <td><c:out value="${ user[0].login } "/></td>
            <td><c:out value="${ user[0].roles.role } "/></td>
            <td><c:out value="${ user[0].lastName } "/></td>
            <td><c:out value="${ user[0].firstName } "/></td>
            <td><c:out value="${ dayOfBirth } "/></td>
            <td><c:out value="${ inscriptionDate } "/></td>
            <td><c:out value="${ user[0].vat } "/></td>
            <td><c:out value="${ user[0].mail } "/></td>
            <td><c:out value="${ user[0].active } "/></td>
            <td><c:out value="${ user[1].street } "/></td>
            <td><c:out value="${ user[1].city.citie } "/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:import url="footer.jsp"/>