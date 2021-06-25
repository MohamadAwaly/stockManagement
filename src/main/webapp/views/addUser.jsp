<%--
  Created by IntelliJ IDEA.
  User: AWAGlass
  Date: 25-06-21
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="header.jsp"/>
<form>
    <div class="mb-3">
        <label for="Login" class="form-label">Login</label>
        <input type="login" class="form-control" id="Login" aria-describedby="login">
    </div>
    <div class="mb-3">
        <label for="lastName" class="form-label">Nom de famille</label>
        <input type="text" class="form-control" id="lastName" aria-describedby="Nom de famille">
    </div>
    <div class="mb-3">
        <label for="firstName" class="form-label">Prenom</label>
        <input type="text" class="form-control" id="firstName" aria-describedby="prenom">
    </div>
    <div class="mb-3">
        <label for="dayOfBirth" class="form-label">Date de naissance</label>
        <input type="Date" class="form-control" id="dayOfBirth" aria-describedby="date de naissance">
    </div>
    <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label">Password</label>
        <input type="password" class="form-control" id="exampleInputPassword1">
    </div>
    <div class="mb-3 form-check">
        <input type="checkbox" class="form-check-input" id="exampleCheck1">
        <label class="form-check-label" for="exampleCheck1">Check me out</label>
    </div>
    <div class="mb-3">
        <label for="exampleFormControlInput1" class="form-label">Email address</label>
        <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
<c:import url="footer.jsp"/>