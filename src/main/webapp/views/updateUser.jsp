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
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-xl p-3 mb-2 ">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Mettre a jour</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form class="formUpdateUser">
                    <div class="mb-1">
                        <label for="iduserUpdate" class="form-label" hidden><span class="etoile">*</span> id</label>
                        <input type="number" name="iduser" class="form-control" id="iduserUpdate" aria-describedby="idUser"
                               required readonly>
                    </div>
                    <div class="mb-1">
                        <label for="loginUpdate" class="form-label"><span class="etoile">*</span> Login</label>
                        <input type="text" name="login" class="form-control" id="loginUpdate" aria-describedby="login"
                               required readonly>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="mb-1">
                                <label for="lastNameUpdate" class="form-label"><span class="etoile">*</span> Nom </label>
                                <input type="text" name="lastName" class="form-control" id="lastNameUpdate"
                                       aria-describedby="Nom" required>
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
                                <input type="text" name="firstName" class="form-control" id="firstNameUpdate"
                                       aria-describedby="prenom" required>
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
                                <input type="Date" name="dayOfBirth" class="form-control" id="dayOfBirthUpdate"
                                       aria-describedby="date de naissance">
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
                                <input type="Date" name="dayOfBirth" class="form-control" id="inscriptionDateUpdate"
                                       aria-describedby="date d'inscription" readonly>
                            </div>
                        </div>

                    </div>
                    <div class="mb-1">
                        <label for="vatUpdate" class="form-label">Num&eacute;ro de tva</label>
                        <input type="text" name="vat" class="form-control" id="vatUpdate"
                               aria-describedby="Numéro de tva"
                               placeholder="BE123456789">
                        <div id="errorTvaUpdate" hidden class="alert alert-danger">Numero de tva non valide</div>
                    </div>
                    <div class="mb-1">
                        <label for="emailUpdate" class="form-label">adresse email</label>
                        <input type="email" name="email" class="form-control" id="emailUpdate"
                               placeholder="name@example.com">
                        <div id="errorEmailUpdate" hidden class="alert alert-danger">Adresse email non valide</div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="mb-1">
                                <label for="passwordUpdate" class="form-label"><span class="etoile">*</span> Mot de
                                    passe</label>
                                <input type="password" name="password" class="form-control" id="passwordUpdate"
                                       required>
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
                                <input type="password" name="rpPassword" class="form-control" id="rpPasswordUpdate"
                                       required>
                                <div id="errorRPasswordUpdate" hidden class="alert alert-danger">Les mots de passe ne
                                    sont pas
                                    identique
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="mb-1">
                        <label for="idRoleUpdate" class="form-label"><span class="etoile">*</span> Role : </label>
                        <SELECT id="idRoleUpdate" name="role" size="1" class="form-control">
                            <c:forEach var="roles" items="${ roles }">
                            <OPTION value="${ roles.idRole}">
                                    <c:out value="${ roles.role } "/>
                                </c:forEach>
                        </SELECT>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="mb-1">
                                <label for="idTypeadresseUpdate" class="form-label"><span class="etoile">*</span> Type
                                    Adresse: </label>
                                <SELECT id="idTypeadresseUpdate" name="typeAdresse" size="1" class="form-control"
                                        required>
                                    <c:forEach var="allTypeAdress" items="${ allTypeAdress }">
                                    <OPTION value="${ allTypeAdress}">
                                            <c:out value="${allTypeAdress } "/>
                                        </c:forEach>
                                </SELECT>
                            </div>
                        </div>
                        <div class="col">
                            <div class="mb-1">
                                <label for="idCityUpdate" class="form-label"><span class="etoile">*</span> Ville et code
                                    postal :
                                </label>
                                <SELECT id="idCityUpdate" name="city" size="1" class="form-control" required>
                                    <c:forEach var="cities" items="${ cities }">
                                    <OPTION value="${ cities.idCity}">
                                            <c:out value="${ cities.postalCode } "/>
                                            <c:out value="${ cities.citie } "/>
                                        </c:forEach>
                                </SELECT>
                            </div>
                        </div>
                    </div>
                    <div class="mb-1">
                        <label for="streetUpdate" class="form-label"><span class="etoile">*</span> Rue</label>
                        <input type="text" name="street" class="form-control" id="streetUpdate" aria-describedby="Rue"
                               required>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="mb-1">
                                <label for="numberUpdate" class="form-label"><span class="etoile">*</span>
                                    Num&eacute;ro</label>
                                <input type="number" name="number" class="form-control" id="numberUpdate"
                                       aria-describedby="Numéro" required>
                            </div>
                        </div>
                        <div class="col">
                            <div>
                                <label for="boxUpdate" class="form-label">Boite</label>
                                <input type="number" name="box" class="form-control" id="boxUpdate"
                                       aria-describedby="boite">
                            </div>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Femer</button>
                <button type="button" class="btn btn-primary">valider</button>
            </div>
        </div>
    </div>
</div>
<c:import url="footer.jsp"/>