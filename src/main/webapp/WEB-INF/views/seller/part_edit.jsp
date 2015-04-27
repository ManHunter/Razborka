<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17.04.2015
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование</title>
    <%@include file="../layout/header.jsp" %>
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
        <form:form action="edit" method="post" modelAttribute="part">


            <div class="col-md-5">
                <h2>Информация о з/ч</h2>
                <hr>
                <div class="well">

                    <div class="form-group">
                        <label for="brand">Марка авто: </label>
                        <form:select class="form-control" path="car.brand.id" id="brand">
                            <form:option value="0" label="Выберите марку"/>
                            <form:options itemValue="id" itemLabel="name" items="${brands}"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="model">Модель авто: </label>
                        <form:select class="form-control" path="car.model.id" id="model">
                            <form:option value="0" label="Выберите модель"/>
                            <form:options itemValue="id" itemLabel="name" items="${model}"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="volume">Объем двигателя: </label>
                        <form:input class="form-control" path="car.volume" id="volume"/>
                    </div>

                    <div class="form-group">
                        <label for="fuel">Тип топлива: </label>
                        <form:select class="form-control" path="car.fuel.id" id="fuel">
                            <form:option value="0" label="Выберите тип топлива"/>
                            <form:options itemValue="id" itemLabel="name" items="${fuels}"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="body">Кузов: </label>
                        <form:select class="form-control" path="car.body.id" id="body">
                            <form:option value="0" label="Выберите кузов"/>
                            <form:options itemValue="id" itemLabel="name" items="${bodies}"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="kpp">КПП: </label>
                        <form:select class="form-control" path="car.kpp.id" id="kpp">
                            <form:option value="0" label="Выберите кпп"/>
                            <form:options itemValue="id" itemLabel="name" items="${kpps}"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="year_from">Год выпуска с: </label>
                        <form:input class="form-control" path="car.year_from" id="year_from"/>
                    </div>

                    <div class="form-group">
                        <label for="year_to">Год выпуска по: </label>
                        <form:input class="form-control" path="car.year_to" id="year_to"/>
                    </div>
                </div>
            </div>

            <div class="col-md-5">
                <h2>Информация об автомобиле</h2>
                <hr>
                <div class="well">
                    <div class="form-group">
                        <label for="id">ID: </label>
                        <form:input class="form-control" path="id" readonly="true" disabled="true"/>
                    </div>
                    <div class="form-group">
                        <label for="group">Группа з/ч: </label>
                        <form:select class="form-control" path="group.id" id="group">
                            <form:option value="0" label="Выберите группу"/>
                            <form:options itemValue="id" itemLabel="name" items="${partGroups}"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="type">Тип з/ч: </label>
                        <form:select class="form-control" path="type.id" id="type">
                            <form:option value="0" label="Выберите тип"/>
                            <form:options itemValue="id" itemLabel="name" items="${partTypes}"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="condition">Состояние: </label>
                        <form:select class="form-control" path="condition" id="condition">
                            <form:option value="0" label="Состояние з/ч"/>
                            <form:option value="НОВЫЙ" label="Новый"/>
                            <form:option value="Б/У" label="Б/У"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="price">Цена: </label>
                        <form:input class="form-control" path="price" id="price"/>
                    </div>

                    <div class="form-group">
                        <label for="catalogNumber">Номер по каталогу: </label>
                        <form:input class="form-control" path="catalogNumber" id="catalogNumber"/>
                    </div>

                    <div class="form-group">
                        <label for="description">Описание: </label>
                        <form:textarea rows="5" class="form-control" path="description" id="description"/>
                    </div>
                </div>
            </div>
    </div>
    <input type="submit" class="btn btn-success"/>
    </form:form>
</div>

</body>
</html>
