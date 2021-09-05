<%--
  Created by IntelliJ IDEA.
  User: Jiwaii
  Date: 06/08/2021
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
<c:if test="${sessionScope.SessionUserEntity.roles.role.trim() == 'administrateur' ||
                sessionScope.SessionUserEntity.roles.role.trim() == 'directeur' ||
                sessionScope.SessionUserEntity.roles.role.trim() == 'préparateur'}">
    <h5>Ajouter un fournisseur</h5>
<form id="formSupplierAdd" action="supplierCreate" method="post">
    <input type="text" name="supplierName" class="form-control" id="formNewSupplierName" required>
    <input type="submit" class="btn btn-primary" id="submitNewSupplier">
</form>
</c:if>
<%@include file="footer.jsp"%>