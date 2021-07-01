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
        <label for="login" class="form-label"><span class="etoile">*</span> Login</label>
        <input type="text" name="login" class="form-control" id="login" aria-describedby="login" required>
    </div>
    <div class="row">
        <div class="col">
            <div class="mb-3">
                <label for="lastName" class="form-label"><span class="etoile">*</span> Nom de famille</label>
                <input type="text" name="lastName" class="form-control" id="lastName" aria-describedby="Nom">
            </div>
        </div>
        <div class="col">
            <div class="mb-3">
                <label for="firstName" class="form-label"><span class="etoile">*</span> Prenom</label>
                <input type="text" name="firstName" class="form-control" id="firstName" aria-describedby="prenom">
            </div>
        </div>
    </div>
    <div class="mb-3">
        <label for="dayOfBirth" class="form-label">Date de naissance</label>
        <input type="Date" name="dayOfBirth" class="form-control" id="dayOfBirth"
               aria-describedby="date de naissance">
    </div>
    <div class="mb-3">
        <label for="vat" class="form-label">Num&eacute;ro de tva</label>
        <input type="text" name="vat" class="form-control" id="vat" aria-describedby="Numéro de tva">
    </div>
    <div class="mb-3">
        <label for="exampleFormControlInput1" class="form-label">Email address</label>
        <input type="email" name="email" class="form-control" id="exampleFormControlInput1"
               placeholder="name@example.com">
    </div>
    <div class="row">
        <div class="col">
            <div class="mb-3">
                <label for="password" class="form-label"><span class="etoile">*</span> Mot de passe</label>
                <input type="password" name="password" class="form-control" id="password">
            </div>
        </div>
        <div class="col">
            <div class="mb-3">
                <label for="rpPassword" class="form-label"><span class="etoile">*</span> Confirmer le mot de
                    passe</label>
                <input type="password" name="rpPassword" class="form-control" id="rpPassword">
            </div>
        </div>
    </div>

    <div class="mb-3">
        <label for="idRole" class="form-label"><span class="etoile">*</span> Role : </label>
        <SELECT id="idRole" name="role" size="1" class="form-control">
            <c:forEach var="roles" items="${ roles }">
            <OPTION value="${ roles.idRole}">
                    <c:out value="${ roles.role } "/>
                </c:forEach>
        </SELECT>
    </div>
    <div class="mb-3">
        <label for="idTypeadresse" class="form-label"><span class="etoile">*</span> Type Adresse: </label>
        <SELECT id="idTypeadresse" name="typeAdresse" size="1" class="form-control">
            <c:forEach var="allTypeAdress" items="${ allTypeAdress }">
            <OPTION value="${ allTypeAdress}">
                    <c:out value="${allTypeAdress } "/>
                </c:forEach>
        </SELECT>
    </div>
    <div class="mb-3">
        <label for="idCity" class="form-label"><span class="etoile">*</span> Ville et code postal : </label>
        <SELECT id="idCity" name="city" size="1" class="form-control">
            <c:forEach var="cities" items="${ cities }">
            <OPTION value="${ cities.idCity}">
                    <c:out value="${ cities.postalCode } "/>
                    <c:out value="${ cities.citie } "/>
                </c:forEach>
        </SELECT>
    </div>
    <div class="mb-3">
        <label for="street" class="form-label"><span class="etoile">*</span> Rue</label>
        <input type="text" name="street" class="form-control" id="street" aria-describedby="Rue">
    </div>
    <div class="row">
        <div class="col">
            <div class="mb-3">
                <label for="number" class="form-label"><span class="etoile">*</span> Num&eacute;ro</label>
                <input type="number" name="number" class="form-control" id="number" aria-describedby="Numéro">
            </div>
        </div>
        <div class="col">
            <div>
                <label for="box" class="form-label">Boite</label>
                <input type="number" name="box" class="form-control" id="box" aria-describedby="boite">
            </div>
        </div>
    </div>
    <button type="submit" class="btn btn-primary">Valider</button>
</form>
<c:import url="footer.jsp"/>