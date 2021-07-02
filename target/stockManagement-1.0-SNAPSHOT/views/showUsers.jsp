<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
<%--        <th>password</th>--%>
        <th>active</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${ user }">
        <fmt:formatDate value="${user.dayOfBirth}" pattern="dd/MM/yyyy" var="dayOfBirth" />
        <fmt:formatDate value="${user.inscriptionDate}" pattern="dd/MM/yyyy" var="inscriptionDate" />
        <tr>
            <td><c:out value="${ user.idUser } "/></td>
            <td><c:out value="${ user.login } "/></td>
            <td><c:out value="${ user.roles.role } "/></td>
            <td><c:out value="${ user.lastName } "/></td>
            <td><c:out value="${ user.firstName } "/></td>
            <td><c:out value="${ dayOfBirth } " /></td>
            <td><c:out value="${ inscriptionDate } "/></td>
            <td><c:out value="${ user.vat } "/></td>
            <td><c:out value="${ user.mail } "/></td>
<%--            <td><c:out value="${ user.password } "/></td>--%>
            <td><c:out value="${ user.active } "/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:import url="footer.jsp"/>