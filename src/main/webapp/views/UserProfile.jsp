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
<c:forEach var="user" items="${ user }">
    <c:out value="${ user.login }"/>
</c:forEach>

<body class="bodyProfile">

</body>

<c:import url="footer.jsp"/>