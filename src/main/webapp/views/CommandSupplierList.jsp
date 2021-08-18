<%--
  Created by IntelliJ IDEA.
  User: Jiwaii
  Date: 18/08/2021
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>

<table class="table table-striped table-hover" border="1px solid black">
    <thead>
    <th># Commande</th>
    <th>Produit</th>
    <th>Lot</th>
    <th>date de la commande</th>
    <th>Fournisseur</th>
    <th>Utilisateur</th>
    </thead>
    <c:forEach var="supplier" items="${suppliers}">
        <tr>
            <td><c:out value="${supplier[0]}"></c:out></td>
            <td><c:out value="${supplier[1]}"></c:out></td>
        </tr>
    </c:forEach>
</table>

<%@include file="footer.jsp"%>
