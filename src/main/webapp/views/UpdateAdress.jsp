<%--
  Created by IntelliJ IDEA.
  User: AWAGlass
  Date: 09-08-21
  Time: 09:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="header.jsp"/>
<c:if test="${sessionScope['sessionOK'] == 'OK'}">
    <c:forEach var="adress" items="${ adress }">
        <form action="UpdateAdress" method="post">
            <div class="mb-3">
                <c:if test="${ !empty error}"><p class="alert alert-danger"><c:out value="${ error }"/></p></c:if>
            </div>

            <div class="mb-1">
                <label for="idUseradressUpdate" class="form-label" hidden><span class="etoile">*</span> id user</label>
                <input type="number" name="idUseradressUpdate" class="form-control" id="idUseradressUpdate"
                       aria-describedby="idUseradressUpdate" hidden
                       required readonly value=<c:out value="${iduser}"/>>
                    <%--        value="<c:out value="${ user.idUser }"/>"--%>
            </div>

            <div class="mb-1">
                <label for="idadressUpdate" class="form-label" hidden><span class="etoile">*</span> id adress</label>
                <input type="number" name="idadressUpdate" class="form-control" id="idadressUpdate"
                       aria-describedby="idadressUpdate" hidden
                       required readonly value=<c:out value="${adress[0].idAdress}"/>>
            </div>
            <div class="mb-3">
                <label for="idTypeadresse" class="form-label"><span class="etoile">*</span> Type d'adresse</label>
                <SELECT id="idTypeadresse" name="typeAdresse" size="1" class="form-control" required>
                    <c:forEach var="allTypeAdress" items="${ allTypeAdress }">
                    <OPTION value="${ allTypeAdress}" ${allTypeAdress == adress[1].typeAdress ? 'selected' : ''}>
                        <c:out value="${allTypeAdress } "/>
                        </c:forEach>
                    </OPTION>
                </SELECT>
            </div>
            <div class="mb-3">
                <label for="idCity" class="form-label"><span class="etoile">*</span> Ville et code postal</label>
                <SELECT id="idCity" name="city" size="1" class="form-control" required>
                    <c:forEach var="cities" items="${ cities }">
                        <OPTION value="${cities.idCity}" ${cities.idCity == adress[0].city.idCity ? 'selected' : ''}>
                            <c:out value="${ cities.postalCode } "/>
                            <c:out value="${ cities.citie } "/>
                        </OPTION>
                    </c:forEach>
                </SELECT>
            </div>
            <div class="mb-3">
                <label for="street" class="form-label"><span class="etoile">*</span> Rue</label>
                <input type="text" name="street" class="form-control" id="street" aria-describedby="Rue" required
                       value="<c:out value="${ adress[0].street }"/>">
            </div>
            <div class="row">
                <div class="col">
                    <div class="mb-3">
                        <label for="number" class="form-label"><span class="etoile">*</span> Num&eacute;ro</label>
                        <input type="number" name="number" class="form-control" id="number" aria-describedby="Num??ro"
                               required value="<c:out value="${ adress[0].number }"/>">
                    </div>
                </div>
                <div class="col">
                    <div>
                        <label for="box" class="form-label">Bo&icirc;te</label>
                        <input type="number" name="box" class="form-control" id="box" aria-describedby="boite"
                               value="<c:out value="${ adress[0].box }"/>">
                    </div>
                </div>
            </div>
            <div class="d-flex flex-row-reverse bd-highlight">
                <button type="submit" class="btn btn-primary p-2 bd-highlight">Valider</button>
            </div>
        </form>
    </c:forEach>
</c:if>
<c:import url="footer.jsp"/>