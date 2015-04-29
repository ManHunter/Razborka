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
    <div class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <a href="#" class="navbar-brand">РАЗБОРКА.by</a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/profile/seller/parts">Мои запчасти</a>
                    </li>
                    <li class="active">
                        <a href="/profile/seller/">Профиль</a>
                    </li>
                    <li>
                        <a href="#">СТО</a>
                    </li>
                    <li>
                        <a href="#">Помощь</a>
                    </li>
                    <li>
                        <a href="#">Контакты</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4">
            <h2>${user.email}</h2>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4">
            <form:form action="seller" method="POST" commandName="user">
                <div class="form-group">
                    <label for="fio">ID: </label>
                    <form:input class="form-control" path="id" readonly="true" disabled="true" />
                </div>
                <div class="form-group">
                    <label for="fio">ФИО: </label>
                    <form:input type="text" class="form-control" path="fio" id="fio" value="${user.fio}" />
                </div>
                <div class="form-group">
                    <label for="name">Name: </label>
                    <form:input type="text" class="form-control" path="name" id="name" value="Колосова А.Д."/>
                </div>
                <div class="form-group">
                    <label for="email">email: </label> </td>
                    <form:input type="text" class="form-control" path="email" id="email" value="${user.email}"/>
                </div>
                <div class="form-group">
                    <label for="password">Password: </label>
                    <form:input type="text" class="form-control" path="password" id="password" value="пароль"/>
                </div>

                <input class="btn btn-default" type="submit" value="Изменить"/>
            </form:form>
        </div>
    </div>

</div>
</body>
</html>
