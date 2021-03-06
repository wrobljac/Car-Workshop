<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hotshot
  Date: 14.08.18
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<%@ include file="WEB-INF/includes/header.jspf" %>

<body id="page-top">

<%@ include file="WEB-INF/includes/navbar.jspf" %>

<div id="wrapper">

    <%@ include file="WEB-INF/includes/sidebar.jspf" %>

    <div id="content-wrapper">

        <div class="container-fluid">

            <!-- Breadcrumbs-->
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="/home">Strona główna</a>
                </li>
                <li class="breadcrumb-item">
                    <a href="/vehicles">Samochody</a>
                </li>
                <li class="breadcrumb-item active">Edytuj samochód</li>
            </ol>

            <c:if test="${not empty success}">
                <div class="card card-register mx-auto mt-5">
                    <div class="card-body"><span style="color: lawngreen; font-weight: bold;">Pomyślnie zmieniono samochód w bazie</span></div>
                </div>
            </c:if>

            <c:if test="${not empty error}">
                <div class="card card-register mx-auto mt-5">
                    <div class="card-body"><span style="color: red; font-weight: bold;">BŁĄD: nie zmieniono samochodu w bazie</span></div>
                </div>
            </c:if>

            <div class="card card-register mx-auto mt-5">
                <div class="card-header">Edytuj samochód</div>
                <div class="card-body">

                    <form action="" method="post">
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <div class="form-label-group">
                                        <input type="text" id="brand" name="brand" class="form-control" placeholder="Marka:" value="${vehicle.brand}" />
                                        <label for="brand">Marka:</label>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-label-group">
                                        <input type="text" id="model" name="model" class="form-control" placeholder="Model:" value="${vehicle.model}" />
                                        <label for="model">Model:</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <div class="form-label-group">
                                        <input type="date" id="productionDate" name="productionDate" class="form-control" placeholder="Rok produkcji:" value="${vehicle.productionDate}" />
                                        <label for="productionDate">Rok produkcji:</label>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-label-group">
                                        <input type="date" id="nextService" name="nextService" class="form-control" placeholder="Następny przegląd:" value="${vehicle.nextService}" />
                                        <label for="nextService">Następny przegląd:</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-label-group">
                                <input type="text" id="plateNumber" name="plateNumber" class="form-control" placeholder="Tablica rejestracyjna:" value="${vehicle.plateNumber}" />
                                <label for="plateNumber">Tablica rejestracyjna:</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <label for="customer">
                                        Wybierz właściciela:</label>
                                </div>
                                <div class="col-md-6">
                                    <select id="customer" class="custom-select custom-select-sm form-control form-control-sm" name="customer_id">
                                        <c:forEach var="customer" items="${customers}">
                                            <c:choose>
                                                <c:when test="${vehicle.customer_id==customer.id}">
                                                    <option value="${customer.id}" selected="selected">${customer.name} ${customer.surname} </option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${customer.id}">${customer.name} ${customer.surname} </option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <input type="submit" value="Zmień"  class="btn btn-primary btn-block" />

                    </form>
                </div>
            </div>

        </div>
        <!-- /.container-fluid -->
        <%@ include file="WEB-INF/includes/footer.jspf" %>

</body>
</html>
