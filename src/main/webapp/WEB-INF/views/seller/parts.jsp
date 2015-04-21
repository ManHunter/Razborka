<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
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
            <a href="/profile/seller/part/add" class="btn btn-success btn-lg">Добавить з/ч</a>
            <br><br>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <table class="table">
                <tr>
                    <th>ID</th>
                    <th>Марка</th>
                    <th>Модель</th>
                    <th>Объем двигателя</th>
                    <th>Тип топлива</th>
                    <th>Кузов</th>
                    <th>КПП</th>
                    <th>Группа з/ч</th>
                    <th>Тип з/ч</th>
                    <th>Фото</th>
                    <th>Состояние</th>
                    <th>Цена</th>
                    <th>№ по каталогу</th>
                    <th>Описание</th>
                    <th>Дата</th>
                </tr>


                <c:forEach items="${parts}" var="p">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.car.brand.name}</td>
                        <td>${p.car.model.name}</td>
                        <td>${p.car.volume}</td>
                        <td>${p.car.fuel.name}</td>
                        <td>${p.car.body.name}</td>
                        <td>${p.car.kpp.name}</td>
                        <td>${p.group.name}</td>
                        <td>${p.type.name}</td>
                        <td>пусто</td>
                        <td>${p.condition}</td>
                        <td>${p.price}</td>
                        <td>${p.catalogNumber}</td>
                        <td>${p.description}</td>
                        <td>${p.date}</td>
                    </tr>
                    <tr>
                        <td colspan="14">
                            <a href="/profile/seller/part/edit?id=${p.id}" class="btn btn-warning">Редактирвать</a>
                            <a href="/profile/seller/part/delete?id=${p.id}" class="btn btn-danger">Удалить</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</div>
</body>
</html>
