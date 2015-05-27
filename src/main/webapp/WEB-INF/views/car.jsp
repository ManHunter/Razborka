<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05.05.2015
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Автомобиль</title>
    <%@include file="layout/header.jsp" %>
</head>
<body>
<%@include file="layout/navbar.jsp" %>

<div class="container" style="padding-top: 20px">
    <div class="row">
        <div class="col-md-12">
            <ol class="breadcrumb h4">
                <li><a href="/">Главная</a></li>
                <li><a href="/razborki">Разборки</a></li>
                <li class="active">Авто ${car.brand.name} ${car.model.name}</li>
            </ol>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <img id="car_photo_${car.id}" class="img-rounded" height="350"
                 src="<c:if test="${car.photo eq null}"><c:url value="/resources/image/no_photo.png"/></c:if><c:if test="${car.photo ne null}">/image/car/${car.photo}</c:if>"
                 alt="${car.brand.name} ${car.model.name}">
        </div>

        <div class="col-md-6">
            <div class="well">
                <dl class="dl-horizontal">
                    <dt>Марка</dt>
                    <dd>${car.brand.name}</dd>
                    <dt>Модель</dt>
                    <dd>${car.model.name}</dd>
                    <dt>Года</dt>
                    <dd>${car.year_from}-${car.year_to}</dd>
                    <dt>Кузов</dt>
                    <dd>${car.body.name}</dd>
                    <dt>КПП</dt>
                    <dd>${car.kpp.name}</dd>
                    <dt>Двигатель</dt>
                    <dd>${car.fuel.name} ${car.volume}</dd>
                </dl>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <h3>Описание</h3>

            <div class="well">
                ${car.description}
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <h3>Другие запчасти ${car.brand.name} ${car.model.name} ${car.year_from}-${car.year_to} от этого продавца</h3>
            <div class="well">
                <table class="table-striped">
                    <tr>
                        <th>Название запчасти</th>
                        <th>Состояние</th>
                        <th>Цена</th>
                        <th>Подробнее</th>
                    </tr>
                    <c:forEach items="${owner_car_parts}" var="ocp">
                        <tr>
                            <td>${ocp.type.name}</td>
                            <td>${ocp.condition}</td>
                            <td>${ocp.price}</td>
                            <td><a href="/parts/${ocp.id}">Перейти</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="col-md-6">
            <h3>Запчасти ${car.brand.name} ${car.model.name} у других продавцов</h3>
            <div class="well">
                <table class="table-striped">
                    <tr>
                        <th>Название запчасти</th>
                        <th>Состояние</th>
                        <th>Цена</th>
                        <th>Подробнее</th>
                    </tr>
                    <c:forEach items="${other_sellers_parts}" var="osp">
                        <tr>
                            <td>${osp.type.name}</td>
                            <td>${osp.condition}</td>
                            <td>${osp.price}</td>
                            <td><a href="/parts/${osp.id}">Перейти</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="col-md-12">
            <h3>Комментарии</h3>

            <div class="well">
                <form:form id="addCommentForm" action="/cars/comment/add/${car.id}" modelAttribute="comment">
                    <div class="row">
                        <div class="col-md-6">
                            <form:hidden path="user.id"/>
                            <form:hidden path="car.id"/>
                            <form:textarea rows="6" path="comment" cssClass="form-control"/>
                        </div>
                        <div class="col-md-2">
                            <input type="submit" class="btn btn-primary"/>
                        </div>
                    </div>
                </form:form>
                <c:forEach items="${comments}" var="c">
                    <div class="row">
                        <h4>${c.user.fio}
                            <small class="text-right">${c.date}</small>
                        </h4>
                        <p>${c.comment}</p>
                        <hr>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

</div>
</body>
</html>
