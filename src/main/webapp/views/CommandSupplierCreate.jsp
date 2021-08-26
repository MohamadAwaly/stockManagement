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
                    <label for="inputSelectUser">Utilisateur</label>
                    <select id="inputSelectUser" name="User" class="form-control">
                        <c:forEach var="user" items="${users}">
                        <option value="<c:out value="${user[0]}"/>">
                            <c:out value="${user[1]}"/>
                        </option>

                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col">
                <div class="mb-3">
                    <label for="inputSelectProduct">Produit</label>
                    <select id="inputSelectProduct" name="Product" class="form-control">
                        <c:forEach var="product" items="${products}">

                        <option value="<c:out value="${product[0]}"/>">
                            <c:out value="${product[1]}"/>
                        </option>
                        </c:forEach>
                    </select>
                    <label for="inputQuantity">Quantité</label>
                    <input type="text" id="inputQuantity" name="Quantity" class="form-control" placeholder="Quantité"/>
                </div>
            </div>
        </div>
        <input type="submit" class="btn btn-primary" value="Enregistrer la commande"/>
    </form>
</div>

<%@include file="footer.jsp"%>