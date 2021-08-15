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

<form id="formSupplierAdd" action="supplierCreate" method="post">
    <input type="text" name="supplierName" class="form-control" id="formNewSupplierName">
    <input type="submit" class="btn btn-primary" id="submitNewSupplier">
</form>
<%@include file="footer.jsp"%>