<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 18.04.2015
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Управление категорией "Кузов авто"</title>
    <%@include file="../layout/header.jsp"%>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-9">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Администратор</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-9">
                <ul class="nav navbar-nav">
                    <li><a href="/admin">Главная</a></li>
                    <li><a href="/">Сайт</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="row">
        <div class="col-md-6">
            <h3>Управление категорией <span class="label label-default">КПП</span></h3>
            <form:form modelAttribute="kpp" method="post" class="form-inline">
                <div class="form-group">
                    <label for="name">Название КПП</label>
                    <form:input class="form-control" path="name" id="name" />
                </div>
                <button class="btn btn-success" type="submit">Добавить</button>
            </form:form>
        </div>
    </div>

    <div class="row">
        <div class="col-md-5">
            <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>Кузов</th>
                    <th>Операции</th>
                </tr>
                <c:forEach items="${kpps}" var="k">
                    <tr>
                        <td>${k.id}</td>
                        <td>${k.name}</td>
                        <td>
                            <a href="/admin/category/kpp/edit?id=${k.id}" class="btn btn-warning">Редактировать</a>
                            <a href="/admin/category/kpp/delete?id=${k.id}" class="btn btn-danger">Удалить</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
