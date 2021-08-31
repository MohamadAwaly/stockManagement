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
<input id="CmdSupBchSearchBar" type="text" class="form-control" name="searchBar" placeholder="Chercher fournisseur ou l'utilisateur d'une commande"/>
<table class="table table-striped table-hover" border="1px solid black">
    <thead>
    <th># Commande</th>
    <th>date de la commande</th>
    <th>Fournisseur</th>
    <th>Utilisateur</th>
    </thead>
    <tbody id="CmdSupListContent" >
    <c:forEach var="cs" items="${commandSuppliers}">
        <tr>
            <td><c:out value="${cs[1].idCommandSuppliers}"/></td>
            <td><c:out value="${cs[1].orderDate}"/></td>
            <td><c:out value="${cs[0].name}"/></td>
            <td><c:out value="${cs[1].users.lastName}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="container-sm"></div>
<input type="button" id="GetCmdSuppPdf" class="btn btn-primary" value="Obtenir en PDF"/>


<%@include file="footer.jsp"%>
