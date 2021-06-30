<%--
  Created by IntelliJ IDEA.
  User: AWAGlass
  Date: 25-06-21
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="header.jsp"/>
<form action="adduser" method="post">
    <div class="mb-3">
        <label for="login" class="form-label">* Login</label>
        <input type="text" name="login" class="form-control" id="login" aria-describedby="login" required>
    </div>
    <div class="mb-3">
        <label for="lastName" class="form-label">Nom de famille</label>
        <input type="text" name="lastName" class="form-control" id="lastName" aria-describedby="Nom">
    </div>
    <div class="mb-3">
        <label for="firstName" class="form-label">Prenom</label>
        <input type="text" name="firstName" class="form-control" id="firstName" aria-describedby="prenom">
    </div>
    <div class="mb-3">
        <label for="dayOfBirth" class="form-label">Date de naissance</label>
        <input type="Date" name="dayOfBirth" class="form-control" id="dayOfBirth" aria-describedby="date de naissance">
    </div>
    <div class="mb-3">
        <label for="inscriptionDate" class="form-label">Date d'inscription</label>
        <input type="Date" name="inscriptionDate" class="form-control" id="inscriptionDate" aria-describedby="date d'inscription">
    </div>
    <div class="mb-3">
        <label for="vat" class="form-label">Num&eacute;ro de tva</label>
        <input type="text" name="vat" class="form-control" id="vat" aria-describedby="Numéro de tva">
    </div>
    <div class="mb-3">
        <label for="exampleFormControlInput1" class="form-label">Email address</label>
        <input type="email" name="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Mot de passe</label>
        <input type="password" name="password" class="form-control" id="password">
    </div>
    <div class="mb-3 form-check">
        <input type="checkbox" class="form-check-input" id="active" name="active">
        <label class="form-check-label" for="active">active</label>
    </div>
    <button type="submit" class="btn btn-primary">Valider</button>
</form>
<c:import url="footer.jsp"/>