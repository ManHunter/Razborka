<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 18.04.2015
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Панель администратора</title>
    <%@include file="../layout/header.jsp" %>
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
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Управление категориями</h3>
                </div>
                <div class="panel-body">
                    <div class="list-group">
                        <a href="/admin/category/brand" class="list-group-item">Марки автомобилей</a>
                        <a href="/admin/category/model" class="list-group-item">Модели автомобилей</a>
                        <a href="/admin/category/body" class="list-group-item">Типы кузовов</a>
                        <a href="/admin/category/kpp" class="list-group-item">Виды КПП</a>
                        <a href="/admin/category/fuel" class="list-group-item">Тип топлива</a>
                        <a href="/admin/category/part_group" class="list-group-item">Группы з/ч</a>
                        <a href="/admin/category/part_type" class="list-group-item">Типы з/ч</a>
                        <a href="/admin/category/repairType" class="list-group-item">Виды услуг СТО</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Управление пользователями</h3>
                </div>
                <div class="panel-body">
                    <div class="list-group">
                        <a href="/admin/category/brand" class="list-group-item">Просмотр разборок</a>
                        <a href="/admin/category/brand" class="list-group-item">Просмотр СТО</a>
                        <a href="/admin/category/brand" class="list-group-item">Просмотр пользователей</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Прочее</h3>
                </div>
                <div class="panel-body">
                    <div class="list-group">
                        <a href="/admin/category/brand" class="list-group-item">Просмотр отзывов</a>
                        <a href="/admin/category/brand" class="list-group-item">Просмотр сообщений</a>
                        <a href="/admin/category/brand" class="list-group-item">Просмотр запчастей</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
