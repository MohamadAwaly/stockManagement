<%--
  Created by IntelliJ IDEA.
  User: Jiwaii
  Date: 15/08/2021
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
<c:if test="${sessionScope.SessionUserEntity.roles.role.trim() == 'administrateur' ||
sessionScope.SessionUserEntity.roles.role.trim() == 'directeur' ||
sessionScope.SessionUserEntity.roles.role.trim() == 'préparateur'}">
    <div>
        <form action="CommandSupplierCreate" method="post">
            <div class="row">
                <div class="col">
                    <div class="mb-3">
                        <label for="inputSelectSupplierName">Fournisseur</label>
                        <select id="inputSelectSupplierName" name="Supplier" class="form-control">
                            <c:forEach var="supplier" items="${suppliers}">
                                <option value="<c:out value="${supplier[0]}"/>">
                                    <c:out value="${supplier[1]}"/>
                                </option>
                            </c:forEach>
                        </select>
                        <input type="text" name="User" value="${sessionScope.SessionUserEntity.idUser}" hidden />
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row Products">
                    <table id="tableProducts" class="products">
                        <thead>
                        <th>Produit</th>
                        <th>Quantité</th>
                        <th></th>
                        </thead>
                        <tr id="product1" class="product">
                            <td class="productName">
                                <select name="Product" class="form-control">
                                    <c:forEach var="product" items="${products}">
                                        <option value="<c:out value="${product[0]}"/>">
                                            <c:out value="${product[1]}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td class="productQuantity">
                                <input type="text" name="Quantity" class="inputQuantity form-control" placeholder="Quantité" required/>
                            </td>
                            <td class="productRowDelete"></td>
                        </tr>
                    </table>
                </div>
            </div>
            <input type="text" id="nbRowProduct" name="nbRow" value="1" hidden/>
            <br/>
            <input id="CmdSuppAddProductToCmd" type="button" class="btn btn-alert" value="Ajouter un produit"/>
            <br/>
            <input type="submit" class="btn btn-primary" value="Enregistrer la commande"/>
        </form>
    </div>
</c:if>
<%@include file="footer.jsp"%>