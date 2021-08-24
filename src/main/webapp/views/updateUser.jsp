<%--
  Created by IntelliJ IDEA.
  User: Gaming
  Date: 12/07/2021
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="header.jsp"/>
<!-- Modal -->
<%--<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"--%>
<%--     aria-labelledby="staticBackdropLabel" aria-hidden="true">--%>
<%--    <div class="modal-dialog modal-dialog-centered modal-xl p-3 mb-2 ">--%>
<%--        <div class="modal-content">--%>
<c:forEach var="user" items="${ user }">
    <div class="modal-header ">
        <h5 class="modal-title" id="staticBackdropLabel">Mettre a jour</h5>
    </div>
    <div class="modal-body">
        <form class="formUpdateUser" action="userUpdate" method="post">
            <div class="mb-3">
                <c:if test="${ !empty errorPassword}"><p class="alert alert-danger"><c:out
                        value="${ errorPassword }"/></p></c:if>
            </div>
                <%--                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
            <button type="submit" class="btn btn-outline-info float-right">Valider</button>
            <div class="mb-1">
                <label for="iduserUpdate" class="form-label" hidden><span class="etoile">*</span> id</label>
                <input type="number" name="iduserUpdate" class="form-control" id="iduserUpdate"
                       aria-describedby="idUser"
                       required readonly value="<c:out value="${ user.idUser }"/>">
            </div>
            <div class="col">
                <div class="mb-1">
                    <input type="checkbox" id="activeUpdate"
                           name="activeUpdate" ${ user.active == true ? 'checked' : "" }>
                    <label for="activeUpdate">Active</label>
                </div>
            </div>
            <div class="mb-1 ">
                <label for="loginUpdate" class="form-label login"><span class="etoile">*</span> Login</label>
                <input type="text" name="loginUpdate" class="form-control" id="loginUpdate" aria-describedby="login"
                       required readonly value=<c:out value="${ user.login }"/>>
            </div>
            <div class="row">
                <div class="col">
                    <div class="mb-1">
                        <label for="lastNameUpdate" class="form-label"><span class="etoile">*</span> Nom </label>
                        <input type="text" name="lastNameUpdate" class="form-control lastNameClass" id="lastNameUpdate"
                               aria-describedby="Nom" required value=<c:out value="${ user.lastName }"/>>
                        <div id="errorlastNameUpdate" hidden class="alert alert-danger">Le nom doit contenir au
                            moin 3
                            caract&egrave;res
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="mb-1">
                        <label for="firstNameUpdate" class="form-label"><span class="etoile">*</span>
                            Prenom</label>
                        <input type="text" name="firstNameUpdate" class="form-control firstNameClass"
                               id="firstNameUpdate"
                               aria-describedby="prenom" required value=<c:out value="${ user.firstName }"/>>
                        <div id="errorfirstNameUpdate" hidden class="alert alert-danger">Le prenom doit contenir
                            au
                            moin 3 caract&egrave;re
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="mb-1">
                        <label for="dayOfBirthUpdate" class="form-label">Date de naissance</label>
                        <input type="Date" name="dayOfBirthUpdate" class="form-control dayOfBirthClass"
                               id="dayOfBirthUpdate"
                               aria-describedby="date de naissance" value=<c:out value="${ user.dayOfBirth }"/>>
                        <div id="errodayOfBirthUpdate" hidden class="alert alert-danger">Date non valide</div>
                        <div id="errodayOfBirth17ansUpdate" hidden class="alert alert-danger">vous devez avoir
                            minimum 17
                            ans
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="mb-1">
                        <label for="inscriptionDateUpdate" class="form-label">Date d'inscription</label>
                        <input type="Date" name="inscriptionDateUpdate" class="form-control" id="inscriptionDateUpdate"
                               aria-describedby="date d'inscription" readonly value=<c:out
                                value="${ user.inscriptionDate }"/>>
                    </div>
                </div>
            </div>
            <div class="mb-1">
                <label for="vatUpdate" class="form-label">Num&eacute;ro de tva</label>
                <input type="text" name="vatUpdate" class="form-control vatClass" id="vatUpdate"
                       aria-describedby="Numéro de tva"
                       placeholder="BE123456789" value=<c:out value="${ user.vat }"/>>
                <div id="errorTvaUpdate" hidden class="alert alert-danger">Numero de tva non valide</div>
            </div>
            <div class="mb-1">
                <label for="emailUpdate" class="form-label">adresse email</label>
                <input type="email" name="emailUpdate" class="form-control emailClass" id="emailUpdate"
                       placeholder="name@example.com" value="<c:out value="${ user.mail }"/>">
                <div id="errorEmailUpdate" hidden class="alert alert-danger">Adresse email non valide</div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="mb-1">
                        <label for="passwordUpdate" class="form-label"><span class="etoile">*</span> Mot de
                            passe</label>
                        <input type="password" name="passwordUpdate" class="form-control passwordClass"
                               id="passwordUpdate"
                               required value="<c:out value="${ user.password }"/>">
                        <div id="erroPasswordUpdate" hidden class="alert alert-danger">Mot de passe doit
                            contenir au
                            moin 8 caract&egrave;re,
                            une lettre majuscule,
                            une lettre minuscule, un caract&egrave;re sp&eacute;ciale
                        </div>
                        <div id="errorRPUpdate" hidden class="alert alert-danger">Les mots de passe ne sont pas
                            identique
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="mb-1">
                        <label for="rpPasswordUpdate" class="form-label"><span class="etoile">*</span> Confirmer
                            le
                            mot de
                            passe</label>
                        <input type="password" name="rpPasswordUpdate" class="form-control rpPasswordClass"
                               id="rpPasswordUpdate"
                               required value="<c:out value="${ user.password }"/>">
                        <div id="errorRPasswordUpdate" hidden class="alert alert-danger">Les mots de passe ne
                            sont pas
                            identique
                        </div>
                    </div>
                </div>
            </div>
            <div class="mb-1">
                <label for="idRoleUpdate" class="form-label"><span class="etoile">*</span> Role : </label>
                <SELECT id="idRoleUpdate" name="RoleUpdate" size="1" class="form-control">
                        <%--                    <option disabled selected><c:out value="${ user[0].roles.role } "/></option>--%>
                    <c:forEach var="roles" items="${ roles }">
                        <OPTION value="${ roles.idRole}" ${roles.role == user.roles.role ? 'selected' : ''}>
                            <c:out value="${ roles.role } "/>
                        </OPTION>
                    </c:forEach>
                </SELECT>
            </div>
        </form>
    </div>
</c:forEach>

<%--<form action="userUpdate" method="get" class="selectedUserForm float-right bg-transparent border-0">--%>
<div class="d-flex flex-row-reverse float-right">
    <form action="addAdress" method="get" class="selectedUserForm float-right bg-transparent border-0 d-flex " >
        <input type="text" name="selectedUserUpdate-Login" class="form-control p-2" id="selectedUserLoginUpdate"
               aria-describedby="selectedUser" hidden>
        <input type="text" name="selectedUserUpdate-id" class="form-control p-2" id="selectedUseridUpdate"
               aria-describedby="selectedUser" hidden>
        <button type="submit" class="btn btn-outline-info float-right p-2 " id="idAddAdress">Ajouter une adresse</button>
    </form>
    <form action="UpdateAdress" method="get" class="selectedUserForm float-right bg-transparent border-0 d-flex     ">
        <input type="text" name="selected-IdAdress" class="form-control p-2" id="selected-IdAdress"
               aria-describedby="selectedUser" hidden>
        <input type="text" name="user-id" class="form-control p-2" id="user-id"
               aria-describedby="selectedUser" hidden>
        <button type="submit"  disabled class="btn btn-outline-info float-right p-2" id="id-UpdateAdress">Mettre à jour</button>
    </form>
</div>
<table class="table table-striped table-hover" border="1px solid black">
    <h3>Liste des adresses</h3>
    <thead>
    <tr>
        <th>id</th>
        <th>ville</th>
        <th>Type d'adresse</th>
        <th>Rue</th>
        <th>Numéro</th>
        <th>boite</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="adress" items="${ adress }">
        <tr class="adressList usersList">
            <td><c:out value="${ adress[0].idAdress }"/></td>
            <td><c:out value="${ adress[2].citie }"/></td>
            <td><c:out value="${ adress[1].typeAdress }"/></td>
            <td><c:out value="${ adress[0].street }"/></td>
            <td><c:out value="${ adress[0].number }"/></td>
            <td><c:out value="${ adress[0].box }"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:import url="footer.jsp"/>