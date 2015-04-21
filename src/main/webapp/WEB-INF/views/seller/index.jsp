<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.04.2015
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Продавец</title>

    <%@ include file="../layout/header.jsp" %>
</head>
<body>
<div class="container">
    <div class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="#" class="navbar-brand">РАЗБОРКА.by</a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <a href="/profile/seller/">Кабинет</a>
                    </li>
                    <li>
                        <a href="/profile/seller/">Учетная запись</a>
                    </li>
                    <li>
                        <a href="/profile/seller/parts">Запчасти</a>
                    </li>
                    <li>
                        <a href="/profile/seller/orders">Заявки</a>
                    </li>
                    <li>
                        <a href="/">Сайт</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4">
            <div class="well">
                <p class="text-center"><span class="glyphicon glyphicon-user gi-5x" aria-hidden="true"></span></p>
                <p class="text-center">Авторазборка (Продавец)</p>
            </div>
        </div>

        <div class="col-md-4">
            <div class="well">
                <p class="text-center"><span class="glyphicon glyphicon-wrench gi-5x" aria-hidden="true"></span></p>
                <p class="text-center">Запчастей: ${partCount}</p>
            </div>
        </div>

        <div class="col-md-4">
            <div class="well">
                <p class="text-center"><span class="glyphicon glyphicon-time gi-5x" aria-hidden="true"></span></p>
                <p class="text-center">Время: 11:42</p>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-7">
            <div class="well">
                <p class="lead">Мои адреса</p>
                <table class="table table-condensed">
                    <tr>
                        <th>Страна</th>
                        <th>Город</th>
                        <th>Адрес</th>
                    </tr>
                    <c:forEach items="${addresses}" var="address">
                        <tr>
                            <td>${address.contry}</td>
                            <td>${address.city}</td>
                            <td>${address.address}</td>
                        </tr>
                    </c:forEach>
                </table>
                <a href="/profile/seller/addAddress" class="btn btn-primary">Добавить адрес</a>
            </div>
        </div>

        <div class="col-md-5">
            <div class="well">
                <p class="lead">Номера телефонов</p>
                <ul class="list-group">
                    <c:forEach items="${phones}" var="phone">
                        <li class="list-group-item">${phone.phoneNumber}</li>
                    </c:forEach>
                </ul>
                <a href="/profile/seller/addPhone" class="btn btn-primary">Добавить телефон</a>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <div class="well">
                <img src="<c:url value="/resources/image/calendar.jpg"/>">
            </div>
        </div>

        <div class="col-md-6">
            <div class="well">
                <h2>Новости</h2><hr><br>
                Новость 1<hr><br>
                Новость 1<hr><br>
                Новость 1<hr><br>
                Новость 1<hr><br>
            </div>
        </div>
    </div>

    <%@include file="../layout/footer.jsp"%>
</div>
</body>
</html>
