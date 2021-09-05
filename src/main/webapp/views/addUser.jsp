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
        <c:if test="${ !empty error}"><p class="alert alert-danger" id="error"><c:out value="${ error }"/></p>
        </c:if>
    </div>
    <div class="mb-3">
        <label for="login" class="form-label login"><span class="etoile">*</span> Login</label>
        <input type="text" name="login" class="form-control" id="login" aria-describedby="login" required value=
        <c:out
                value="${user.login}"/>>
        <div id="errorLogin" hidden class="alert alert-danger">Le login doit contenir au minimum 4 caract&egrave;res
        </div>
        <div id="errorUserExist" hidden class="alert alert-danger">L'utilisateur existe d&eacute;ja</div>
    </div>
    <div class="row">
        <div class="col">
            <div class="mb-3">
                <label for="lastName" class="form-label"><span class="etoile">*</span> Nom de famille</label>
                <input type="text" name="lastName" class="form-control lastNameClass" id="lastName"
                       aria-describedby="Nom" required
                       value=<c:out value="${user.lastName}"/>>
                <div id="errorlastName" hidden class="alert alert-danger errorlastNameClass">Le nom doit contenir au
                    minimum 3 caract&egrave;res et ne doit pas contenir des chiffres ni des espaces
                </div>
            </div>
        </div>
        <div class="col">
            <div class="mb-3">
                <label for="firstName" class="form-label"><span class="etoile">*</span> Pr&eacute;nom</label>
                <input type="text" name="firstName" class="form-control firstNameClass" id="firstName"
                       aria-describedby="prenom"
                       required value="<c:out value="${user.firstName}"/>">
                <div id="errorfirstName" hidden class="alert alert-danger">Le pr&eacute;nom doit contenir au minimum 3 caract&egrave;re
                    et ne doit pas contenir des chiffres ni des espaces
                </div>
            </div>
        </div>
    </div>
    <div class="mb-3">
        <label for="dayOfBirth" class="form-label">Date de naissance</label>
        <input type="Date" name="dayOfBirth" class="form-control dayOfBirthClass" id="dayOfBirth"
               aria-describedby="date de naissance" value=<c:out value="${user.dayOfBirth}"/>>
        <div id="errodayOfBirth" hidden class="alert alert-danger">Date non valide</div>
        <div id="errodayOfBirth17ans" hidden class="alert alert-danger">vous devez avoir au moins 17 ans
        </div>
    </div>
    <div class="mb-3">
        <label for="vat" class="form-label">Num&eacute;ro de TVA</label>
        <input type="text" name="vat" class="form-control vatClass" id="vat" aria-describedby="Numéro de tva"
               placeholder="BE123456789" value=<c:out value="${user.vat}"/>>
        <div id="errorTva" hidden class="alert alert-danger">le num&eacute;ro de TVA commence par BE et contient 9
            chiffres
        </div>
        <div id="errorTvaExist" hidden class="alert alert-danger">Numero de tva existe</div>
    </div>
    <div class="mb-3">
        <label for="email" class="form-label">Adresse email</label>
        <input type="email" name="email" class="form-control emailClass" id="email"
               placeholder="name@example.com" value=<c:out value="${user.mail}"/>>
        <div id="errorEmail" hidden class="alert alert-danger">Adresse email non valide</div>
    </div>
    <div class="row">
        <div class="col">
            <div class="mb-3">
                <label for="password" class="form-label"><span class="etoile">*</span> Mot de passe</label>
                <input type="password" name="password" class="form-control passwordClass" id="password" required>
                <div id="erroPassword" hidden class="alert alert-danger">Le mot de passe doit comporter au moins 8 caract&egrave;res, un chiffre,
                    une majuscule,
                    une minuscule et un caract&egrave;re sp&eacute;cial.
                </div>
                <div id="errorRP" hidden class="alert alert-danger">Les mots de passe ne sont pas identiques</div>
            </div>
        </div>
        <div class="col">
            <div class="mb-3">
                <label for="rpPassword" class="form-label"><span class="etoile">*</span> Confirmer le mot de
                    passe</label>
                <input type="password" name="rpPassword" class="form-control rpPasswordClass" id="rpPassword"
                       required>
                <div id="errorRPassword" hidden class="alert alert-danger">Les mots de passe ne sont pas identique
                </div>
            </div>
        </div>
    </div>
    <div class="mb-3">

        <c:if test="${sessionScope.SessionUserEntity.roles.role.trim() == 'administrateur' || sessionScope.SessionUserEntity.roles.role.trim() == 'directeur'}">
            <label for="idRole" class="form-label"><span class="etoile">*</span> R&ocirc;le : </label>
            <SELECT id="idRole" name="role" size="1" class="form-control">
                <c:forEach var="roles" items="${ roles }">
                    <OPTION value="${ roles.idRole}">
                        <c:out value="${ roles.role } "/>
                    </OPTION>
                </c:forEach>
            </SELECT>
        </c:if>

        <c:if test="${sessionScope.SessionUserEntity.roles.role.trim() == null}">
            <label for="idRole" class="form-label" hidden><span class="etoile">*</span> R&ocirc;le : </label>
            <SELECT id="idRole" name="role" size="1" class="form-control" hidden>
                <c:forEach var="roles" items="${ roles }">
                    <OPTION hidden value="${ roles.idRole}" ${roles.role.trim() == 'client' ? 'selected' : ""}>
                        <c:out value="${ roles.role } "/>
                    </OPTION>
                </c:forEach>
            </SELECT>
        </c:if>
    </div>
    <div class="mb-3">
        <label for="idTypeadresse" class="form-label"><span class="etoile">*</span> Type d'adresse: </label>
        <SELECT id="idTypeadresse" name="typeAdresse" size="1" class="form-control" required>
            <c:forEach var="allTypeAdress" items="${ allTypeAdress }">
                <OPTION value="${ allTypeAdress}">
                    <c:out value="${allTypeAdress } "/>
                </OPTION>
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
                </OPTION>
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
                <input type="number" name="number" class="form-control" id="number" aria-describedby="Numéro"
                       required>
            </div>
        </div>
        <div class="col">
            <div>
                <label for="box" class="form-label">Bo&icirc;te</label>
                <input type="number" name="box" class="form-control" id="box" aria-describedby="boite">
            </div>
        </div>
    </div>
    <button type="submit" id="btn-addUser" class="btn btn-primary">Valider</button>
</form>
<c:import url="footer.jsp"/>