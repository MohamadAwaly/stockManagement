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
<h3>Info sur la Commande</h3>
<table class="table table-striped table-hover" border="1px solid black">
    <thead>
    <th>Produit</th>
    <th># Lot fournisseur</th>
    <th>Qté</th>
    </thead>
    <tbody id="CmdSupListContent" >
    <c:forEach var="cs" items="${commandSuppliersBatch}">
        <tr>
            <td><c:out value="${cs[0].designation}"/></td>
            <td><c:out value="${cs[1].numberBatch}"/></td>
            <td><c:out value="${cs[1].quantity}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="container-sm"></div>

<%@include file="footer.jsp"%>
