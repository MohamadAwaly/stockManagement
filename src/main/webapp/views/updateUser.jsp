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
    <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Mettre a jour</h5>
    </div>
    <div class="modal-body" >
        <form class="formUpdateUser" action="userUpdate" method="post" >
            <div class="mb-3">
                <c:if test="${ !empty errorPassword}"><p class="alert alert-danger"><c:out value="${ errorPassword }"/></p></c:if>
            </div>
                <%--                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
            <button type="submit" class="btn btn-outline-info">Valider</button>
            <div class="mb-1">
                <label for="iduserUpdate" class="form-label" hidden><span class="etoile">*</span> id</label>
                <input type="number" name="iduserUpdate" class="form-control" id="iduserUpdate" aria-describedby="idUser"
                       required readonly hidden value="<c:out value="${ user[0].idUser }"/>">
            </div>
            <div class="col">
                <div class="mb-1">
                    <input type="checkbox" id="activeUpdate" name="activeUpdate" ${ user[0].active == true ? 'checked' : "" }>
                    <label for="activeUpdate">Active</label>
                </div>
            </div>
            <div class="mb-1">
                <label for="loginUpdate" class="form-label"><span class="etoile">*</span> Login</label>
                <input type="text" name="loginUpdate" class="form-control" id="loginUpdate" aria-describedby="login"
                       required readonly value=<c:out value="${ user[0].login }"/>>
            </div>
            <div class="row">
                <div class="col">
                    <div class="mb-1">
                        <label for="lastNameUpdate" class="form-label"><span class="etoile">*</span> Nom </label>
                        <input type="text" name="lastNameUpdate" class="form-control" id="lastNameUpdate"
                               aria-describedby="Nom" required value=<c:out value="${ user[0].lastName }"/>>
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
                        <input type="text" name="firstNameUpdate" class="form-control" id="firstNameUpdate"
                               aria-describedby="prenom" required value=<c:out value="${ user[0].firstName }"/>>
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
                        <input type="Date" name="dayOfBirthUpdate" class="form-control" id="dayOfBirthUpdate"
                               aria-describedby="date de naissance" value=<c:out value="${ user[0].dayOfBirth }"/>>
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
                                value="${ user[0].inscriptionDate }"/>>
                    </div>
                </div>

            </div>
            <div class="mb-1">
                <label for="vatUpdate" class="form-label">Num&eacute;ro de tva</label>
                <input type="text" name="vatUpdate" class="form-control" id="vatUpdate"
                       aria-describedby="Numéro de tva"
                       placeholder="BE123456789" value=<c:out value="${ user[0].vat }"/>>
                <div id="errorTvaUpdate" hidden class="alert alert-danger">Numero de tva non valide</div>
            </div>
            <div class="mb-1">
                <label for="emailUpdate" class="form-label">adresse email</label>
                <input type="email" name="emailUpdate" class="form-control" id="emailUpdate"
                       placeholder="name@example.com" value="<c:out value="${ user[0].mail }"/>">
                <div id="errorEmailUpdate" hidden class="alert alert-danger">Adresse email non valide</div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="mb-1">
                        <label for="passwordUpdate" class="form-label"><span class="etoile">*</span> Mot de
                            passe</label>
                        <input type="password" name="passwordUpdate" class="form-control" id="passwordUpdate"
                               required value="<c:out value="${ user[0].password }"/>">
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
                        <input type="password" name="rpPasswordUpdate" class="form-control" id="rpPasswordUpdate"
                               required value="<c:out value="${ user[0].password }"/>">
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
                    <option disabled selected><c:out value="${ user[0].roles.role } "/></option>
                    <c:forEach var="roles" items="${ roles }">
                    <OPTION value="${ roles.idRole}">
                        <c:out value="${ roles.role } "/>
                        </c:forEach>
                    </OPTION>
                </SELECT>
            </div>
            <div class="row">
                <div class="col">
                    <div class="mb-1">
                        <label for="idTypeadresseUpdate" class="form-label"><span class="etoile">*</span> Type
                            Adresse: </label>
                        <SELECT id="idTypeadresseUpdate" name="TypeadresseUpdate" size="1" class="form-control"
                                required>
                            <option disabled selected><c:out value="${ user[2].typeAdress } "/></option>
                            <c:forEach var="allTypeAdress" items="${ allTypeAdress }">
                            <OPTION value="${ allTypeAdress}">
                                <c:out value="${allTypeAdress } "/>
                                </c:forEach>
                            </OPTION>
                        </SELECT>
                    </div>
                </div>
                <div class="col">
                    <div class="mb-1">
                        <label for="idCityUpdate" class="form-label"><span class="etoile">*</span> Ville et code
                            postal :
                        </label>
                        <SELECT id="idCityUpdate" name="cityUpdate" size="1" class="form-control" required>
                            <option disabled selected><c:out value="${ user[1].city.citie } "/></option>
                            <c:forEach var="cities" items="${ cities }">
                            <OPTION value="${ cities.idCity}">
                                <c:out value="${ cities.postalCode } "/>
                                <c:out value="${ cities.citie } "/>
                                </c:forEach>
                            </OPTION>
                        </SELECT>

                    </div>
                </div>
            </div>
            <div class="mb-1">
                <label for="streetUpdate" class="form-label"><span class="etoile">*</span> Rue</label>
                <input type="text" name="streetUpdate" class="form-control" id="streetUpdate" aria-describedby="Rue"
                       required value="<c:out value="${ user[1].street }"/>">
            </div>
            <div class="row">
                <div class="col">
                    <div class="mb-1">
                        <label for="numberUpdate" class="form-label"><span class="etoile">*</span>
                            Num&eacute;ro</label>
                        <input type="number" name="numberUpdate" class="form-control" id="numberUpdate"
                               aria-describedby="Numéro" required value="<c:out value="${ user[1].number }"/>">
                    </div>
                </div>
                <div class="col">
                    <div>
                        <label for="boxUpdate" class="form-label">Boite</label>
                        <input type="number" name="boxUpdate" class="form-control" id="boxUpdate"
                               aria-describedby="boite" value="<c:out value="${ user[1].box }"/>">
                    </div>
                </div>
            </div>

        </form>
    </div>
</c:forEach>
<c:import url="footer.jsp"/>