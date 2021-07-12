<%--
  Created by IntelliJ IDEA.
  User: Gaming
  Date: 12/07/2021
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg p-3 mb-2 bg-transparent">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Mettre a jour</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="mb-3">
                        <label for="login" class="form-label"><span class="etoile">*</span> Login</label>
                        <input type="text" name="login" class="form-control" id="login" aria-describedby="login" required>
                    </div>

                    <div class="row">
                        <div class="col">
                            <div class="mb-3">
                                <label for="lastName" class="form-label"><span class="etoile">*</span> Nom de famille</label>
                                <input type="text" name="lastName" class="form-control" id="lastName" aria-describedby="Nom" required>
                                <div id="errorlastName" hidden class="alert alert-danger">Le nom doit contenir au moin 3 caract&egrave;res</div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="mb-3">
                                <label for="firstName" class="form-label"><span class="etoile">*</span> Prenom</label>
                                <input type="text" name="firstName" class="form-control" id="firstName" aria-describedby="prenom" required>
                                <div id="errorfirstName" hidden class="alert alert-danger">Le prenom doit contenir au moin 3 caract&egrave;re</div>
                            </div>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Understood</button>
            </div>
        </div>
    </div>
</div>