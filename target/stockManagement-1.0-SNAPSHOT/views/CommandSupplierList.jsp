<%--
  Created by IntelliJ IDEA.
  User: Jiwaii
  Date: 18/08/2021
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp"%>
<c:if test="${sessionScope.SessionUserEntity.roles.role.trim() == 'administrateur' ||
                sessionScope.SessionUserEntity.roles.role.trim() == 'directeur' ||
                sessionScope.SessionUserEntity.roles.role.trim() == 'préparateur'}">
<input id="CmdSupBchSearchBar" type="text" class="form-control" name="searchBar" placeholder="Chercher fournisseur, utilisateur ou numéro de commande"/>
<table class="table table-striped table-hover" border="1px solid black">
    <thead>
    <th>Numéro de Commande</th>
    <th>Date de la commande</th>
    <th>Fournisseur</th>
    <th>Créée par</th>
    </thead>
    <tbody id="CmdSupListContent" >
    <c:forEach var="cs" items="${commandSuppliers}">
        <tr id="<c:out value="${cs[0]}"/>" class="rowCommand">
            <td><c:out value="${cs[0]}"/> </td>
            <td><fmt:formatDate value="${cs[1]}" pattern="dd/MM/yyyy"/></td>
            <td><c:out value="${cs[2]}"/></td>
            <td><c:out value="${cs[3]}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="container-sm"></div>
<input type="button" id="GetCmdSuppPdf" class="btn btn-primary" value="Obtenir la liste en PDF"/>
</c:if>

<%@include file="footer.jsp"%>
