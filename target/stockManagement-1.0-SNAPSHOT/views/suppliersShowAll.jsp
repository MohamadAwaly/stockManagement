<%--
  Created by IntelliJ IDEA.
  User: Jiwaii
  Date: 06/08/2021
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>

<%--brouillon--%>
<%--<c:set var="Nom" value="Laurent"></c:set>--%>

<table class="table table-striped table-hover" border="1px solid black">
    <thead>
    <th>Nom</th>
    </thead>
    <c:forEach var="supplier" items="${suppliers}">
    <tr id="<c:out value="${supplier[0]}"/>">
        <td><c:out value="${supplier[1]}"/></td>
    </tr>
    </c:forEach>
</table>

<%@include file="footer.jsp"%>

