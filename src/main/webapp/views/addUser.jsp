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
        <c:if test="${ !empty error}"><p class="alert alert-danger"><c:out value="${ error }"/></p></c:if>
    </div>
    <div class="mb-3">
        <label for="login" class="form-label"><span class="etoile">*</span> Login</label>
        <input type="text" name="login" class="form-control" id="login" aria-describedby="login" required value=<c:out
                value="${user.login}"/>>
        <div id="errorLogin" hidden class="alert alert-danger">Le login doit contenir au moin 4 caract&egrave;res</div>
    </div>
    <div class="row">
        <div class="col">
            <div class="mb-3">
                <label for="lastName" class="form-label"><span class="etoile">*</span> Nom de famille</label>
                <input type="text" name="lastName" class="form-control" id="lastName" aria-describedby="Nom" required
                       value=<c:out value="${user.lastName}"/>>
                <div id="errorlastName" hidden class="alert alert-danger">Le nom doit contenir au moin 3 caract&egrave;res</div>
            </div>
        </div>
        <div class="col">
            <div class="mb-3">
                <label for="firstName" class="form-label"><span class="etoile">*</span> Prenom</label>
                <input type="text" name="firstName" class="form-control" id="firstName" aria-describedby="prenom"
                       required value="<c:out value="${user.firstName}"/>">
                <div id="errorfirstName" hidden class="alert alert-danger">Le prenom doit contenir au moin 3 caract&egrave;re</div>
            </div>
        </div>
    </div>
    <div class="mb-3">
        <label for="dayOfBirth" class="form-label">Date de naissance</label>
        <input type="Date" name="dayOfBirth" class="form-control" id="dayOfBirth"
               aria-describedby="date de naissance" value=<c:out value="${user.dayOfBirth}"/>>
        <div id="errodayOfBirth" hidden class="alert alert-danger">Date non valide</div>
        <div id="errodayOfBirth17ans" hidden class="alert alert-danger">vous devez avoir minimum 17 ans</div>
    </div>
    <div class="mb-3">
        <label for="vat" class="form-label">Num&eacute;ro de tva</label>
        <input type="text" name="vat" class="form-control" id="vat" aria-describedby="Numéro de tva"
               placeholder="BE123456789" value=<c:out value="${user.vat}"/>>
        <div id="errorTva" hidden class="alert alert-danger">Numero de tva non valide</div>
    </div>
    <div class="mb-3">
        <label for="email" class="form-label">Adresse email</label>
        <input type="email" name="email" class="form-control" id="email"
               placeholder="name@example.com" value=<c:out value="${user.mail}"/>>
        <div id="errorEmail" hidden class="alert alert-danger">Adresse email non valide</div>
    </div>
    <div class="row">
        <div class="col">
            <div class="mb-3">
                <label for="password" class="form-label"><span class="etoile">*</span> Mot de passe</label>
                <input type="password" name="password" class="form-control" id="password" required>
                <div id="erroPassword" hidden class="alert alert-danger">Mot de passe doit contenir au moin 8 caract&egrave;re,
                    une lettre majuscule,
                    une lettre minuscule, un caract&egrave;re sp&eacute;ciale
                </div>
                <div id="errorRP" hidden class="alert alert-danger">Les mots de passe ne sont pas identique</div>
            </div>
        </div>
        <div class="col">
            <div class="mb-3">
                <label for="rpPassword" class="form-label"><span class="etoile">*</span> Confirmer le mot de
                    passe</label>
                <input type="password" name="rpPassword" class="form-control" id="rpPassword" required>
                <div id="errorRPassword" hidden class="alert alert-danger">Les mots de passe ne sont pas identique</div>
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
        <SELECT id="idTypeadresse" name="typeAdresse" size="1" class="form-control" required>
            <c:forEach var="allTypeAdress" items="${ allTypeAdress }">
            <OPTION value="${ allTypeAdress}">
                    <c:out value="${allTypeAdress } "/>
                </c:forEach>
        </SELECT>
    </div>
    <div class="mb-3">
        <label for="idCity" class="form-label"><span class="etoile">*</span> Ville et code postal : </label>
        <SELECT id="idCity" name="city" size="1" class="form-control" required>
            <c:forEach var="cities" items="${ cities }">
            <OPTION value="${ cities.idCity}">
                    <c:out value="${ cities.postalCode } "/>
                    <c:out value="${ cities.citie } "/>
                </c:forEach>
        </SELECT>
    </div>
    <div class="mb-3">
        <label for="street" class="form-label"><span class="etoile">*</span> Rue</label>
        <input type="text" name="street" class="form-control" id="street" aria-describedby="Rue" required>
    </div>
    <div class="row">
        <div class="col">
            <div class="mb-3">
                <label for="number" class="form-label"><span class="etoile">*</span> Num&eacute;ro</label>
                <input type="number" name="number" class="form-control" id="number" aria-describedby="Numéro" required>
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