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
<c:if test="${sessionScope['sessionOK'] == 'OK'}">
    <div class="bodyProfile">
        <c:forEach var="user" items="${ user }">
            <div class="fram">
                <div class="titleFram">
                    <h2 id="logUserProfile"><c:out value="${ user.login }"/></h2>
                </div>
                <table class="profileTable">
                    <tr>
                        <th>Nom</th>
                        <td><c:out value="${ user.lastName }"/></td>
                    </tr>
                    <tr>
                        <th>Prénom</th>
                        <td><c:out value="${ user.firstName }"/></td>
                    </tr>
                    <tr>
                        <th>Date de naissance</th>
                        <td><c:out value="${ user.dayOfBirth }"/></td>
                    </tr>
                    <tr>
                        <th>Date d'inscription</th>
                        <td><c:out value="${ user.inscriptionDate }"/></td>
                    </tr>
                    <tr>
                        <th>Numéro de TVA</th>
                        <td><c:out value="${ user.vat }"/></td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td><c:out value="${ user.mail }"/></td>
                    </tr>
                    <tr>
                        <th>Rôle</th>
                        <td><c:out value="${ user.roles.role }"/></td>
                    </tr>
                </table>
                <form action="userUpdate" method="get" class="float-right bg-transparent border-0">
                    <input type="text" name="selectedUser-id" class="form-control" id="selectedUserid"
                           value="<c:out value="${ user.idUser }"/>"
                           hidden>
                    <button type="submit" class="btn btn-primary float-right" id="update-customer-btn">Mettre à jour
                    </button>
                </form>
            </div>
        </c:forEach>
    </div>
</c:if>
<c:import url="footer.jsp"/>