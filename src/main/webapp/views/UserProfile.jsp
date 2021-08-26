<%--
  Created by IntelliJ IDEA.
  User: AWAGlass
  Date: 24-08-21
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="header.jsp"/>
<body class="bodyProfile">
<c:forEach var="user" items="${ user }">
    <div class="fram">
        <div class="titleFram">
            <h2 id="logUserProfile"><c:out value="${ user.login }"/></h2>
        </div>
        <table class="profileTable">
            <tr hidden>
                <th>id</th>
                <td id="idUserprofile"><c:out value="${ user.idUser }"/></td>
            </tr>
            <tr>
                <th>Nom</th>
                <td><c:out value="${ user.lastName }"/></td>
            </tr>
            <tr>
                <th>Prenom</th>
                <td><c:out value="${ user.firstName }"/></td>
            </tr>
            <tr>
                <th>Date de naissance</th>
                <td><c:out value="${ user.dayOfBirth }"/></td>
            </tr>
            <tr>
                <th>Date d'inscrition</th>
                <td><c:out value="${ user.inscriptionDate }"/></td>
            </tr>
            <tr>
                <th>Numéro de tva</th>
                <td><c:out value="${ user.vat }"/></td>
            </tr>
            <tr>
                <th>Email</th>
                <td><c:out value="${ user.mail }"/></td>
            </tr>
            <tr>
                <th>Role</th>
                <td><c:out value="${ user.roles.role }"/></td>
            </tr>

        </table>
        <button class="btn btn-primary float-right btnprofile" id="id-btn-profile" type="button">mettre a jour</button>
    </div>
</c:forEach>
</body>

<c:import url="footer.jsp"/>