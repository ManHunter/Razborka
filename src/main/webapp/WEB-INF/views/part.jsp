<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <%@include file="layout/header.jsp" %>
</head>
<body>
<%@include file="layout/navbar.jsp" %>

<div class="container" style="padding-top: 20px">

    <div class="row">
        <div class="col-md-12">
            <ol class="breadcrumb h4">
                <li><a href="/">Главная</a></li>
                <li><a href="/parts">Запчасти</a></li>
                <li class="active">${part.car.brand.name} ${part.car.model.name} ${part.car.year_from}-${part.car.year_to}</li>
            </ol>
        </div>
    </div>

    <div class="row">
        <div class="col-md-9">
            <h2>${part.type.name} ${part.car.brand.name} ${part.car.model.name} ${part.car.year_from}-${part.car.year_to}</h2>
        </div>
        <div class="col-md-3 text-right">
            <h2><span class="label label-danger">ЦЕНА: ${part.price}</span></h2>
        </div>
    </div>

    <div class="row equal">
        <div class="col-md-6">
            <c:if test="${fn:length(part.photos) lt 1}">
                <div class="well">
                    <img src="<c:url value="/resources/image/no_photo.png"/>" width="500" height="300"/>
                </div>
            </c:if>
            <c:if test="${fn:length(part.photos) ne 0}">
                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" role="listbox">
                        <c:forEach items="${part.photos}" var="photo" varStatus="loop">
                            <div class="item peopleCarouselImg ${loop.index eq 0?"active":""}">
                                <img class="peopleCarouselImg" src="/image/part/${photo.picture}" alt="...">

                                <div class="carousel-caption">
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <!-- Controls -->
                    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </c:if>
        </div>

        <div class="col-md-6">
            <div class="panel panel-default" style="width: 100%">
                <div class="panel-body">
                    <h4>Информация о продавце</h4>
                    <hr>
                    <dl class="dl-horizontal">
                        <dt>ID продавца</dt>
                        <dd>${part.car.user.id}</dd>
                        <dt>Название</dt>
                        <dd>${part.car.user.name}</dd>
                        <dt>Адрес</dt>
                        <c:forEach items="${part.car.user.addresses}" var="address">
                            <dd>${address.contry}, ${address.city}, ${address.address}</dd>
                        </c:forEach>
                        <dt>Телефон</dt>
                        <c:forEach items="${part.car.user.phones}" var="phone">
                            <dd>${phone.phoneNumber}</dd>
                        </c:forEach>
                    </dl>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <br>

        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="col-md-6">
                        <h4>Информация о запчасти</h4>
                        <hr>
                        <dl class="dl-horizontal">
                            <dt>Группа</dt>
                            <dd>${part.group.name}</dd>
                            <dt>Тип</dt>
                            <dd>${part.type.name}</dd>
                            <dt>№ по каталогу</dt>
                            <dd>${part.catalogNumber}</dd>
                            <dt>Состояние</dt>
                            <dd>${part.condition}</dd>
                            <dt>Год</dt>
                            <dd>${part.car.year_from}-${part.car.year_to}</dd>
                            <dt>Объем двигателя</dt>
                            <dd>${part.car.volume}</dd>
                            <dt>Топливо</dt>
                            <dd>${part.car.fuel.name}</dd>
                            <dt>КПП</dt>
                            <dd>${part.car.kpp.name}</dd>
                            <dt>Кузов</dt>
                            <dd>${part.car.body.name}</dd>
                        </dl>
                    </div>
                    <div class="col-md-6">
                        <h4>Описание</h4>
                        <hr>
                        <p>${part.description}</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <script type="text/javascript" charset="utf-8"
                    src="https://api-maps.yandex.ru/services/constructor/1.0/js/?sid=XHE7gEBYTXJ8-FgvgUL2U-a1lw5b4HfJ&width=550&height=300"></script>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <h4>Другие запчасти ${part.car.brand.name} ${part.car.model.name} ${part.car.year_from}-${part.car.year_to}
                от этого продавца</h4>

            <div class="panel panel-default">
                <div class="panel-body">
                    <table class="table table-condensed">
                        <tr>
                            <th>Название запчасти</th>
                            <th>Состояние</th>
                            <th>Цена</th>
                            <th>Подробнее</th>
                        </tr>
                        <c:if test="${fn:length(other_parts) lt 1}">
                            <tr>
                                <td colspan="4">
                                    <div class="alert alert-warning">
                                        У продавца не найдено запчастей к этому авто
                                    </div>
                                </td>
                            </tr>
                        </c:if>
                        <c:forEach items="${other_parts}" var="op">
                            <tr>
                                <td>${op.type.name}</td>
                                <td>${op.condition}</td>
                                <td>${op.price}</td>
                                <td><a href="/parts/${op.id}">Перейти</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <h4>Запчасти ${part.car.brand.name} ${part.car.model.name} у других продавцов</h4>

            <div class="panel panel-default">
                <div class="panel-body">
                    <table class="table table-condensed">
                        <tr>
                            <th>Название запчасти</th>
                            <th>Состояние</th>
                            <th>Цена</th>
                            <th>Подробнее</th>
                        </tr>
                        <c:if test="${fn:length(other_seller) lt 1}">
                            <tr>
                                <td colspan="4">
                                    <div class="alert alert-warning">
                                        У других продавцов не найдено запчастей
                                    </div>
                                </td>
                            </tr>
                        </c:if>
                        <c:forEach items="${other_seller}" var="osp">
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
    </div>

    <div class="row">
        <div class="col-md-12">
            <h3>Комментарии</h3>

            <div class="panel panel-default">
                <div class="panel-body">
                    <security:authorize access="isFullyAuthenticated()">
                        <form:form id="addCommentForm" action="/parts/comment/add/${part.id}" modelAttribute="comment">
                            <div class="row">
                                <div class="col-md-6">
                                    <form:hidden path="user.id"/>
                                    <form:hidden path="part.id"/>
                                    <form:textarea rows="6" path="comment" cssClass="form-control"/>
                                </div>
                                <div class="col-md-2">
                                    <input type="submit" class="btn btn-primary"/>
                                </div>
                            </div>
                        </form:form>
                    </security:authorize>

                    <security:authorize access="isAnonymous()">
                        <div class="alert alert-warning">
                            Оставлять комментарии могут только <a href="/registration"
                                                                  class="alert-link">зарегистрированные</a>
                            пользователи.
                        </div>
                    </security:authorize>


                    <c:forEach items="${comments}" var="c">
                        <div class="row">
                            <h4>${c.user.fio}
                                <small class="text-right"><joda:format value="${c.date}"
                                                                       pattern="dd.MM.yyyy HH:mm"/></small>
                            </h4>
                            <p>${c.comment}</p>
                            <hr>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
