<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <%@include file="layout/header.jsp"%>
</head>
<body>
<%@include file="layout/navbar.jsp"%>

<div class="container" style="padding-top: 20px">

    <div class="row">
        <div class="col-md-12">
            <ol class="breadcrumb">
                <li><a href="/">Главная</a></li>
                <li><a href="/parts">Запчасти</a></li>
                <li class="active"><a href="/parts/?id=${part.id}">${part.car.brand.name} ${part.car.model.name} ${part.car.year_from}-${part.car.year_to}</a></li>
            </ol>
        </div>
    </div>

    <div class="row">
        <div class="col-md-8">
            <h2>${part.car.brand.name} ${part.car.model.name} ${part.car.year_from}-${part.car.year_to}</h2>
        </div>
        <div class="col-md-4">
            <h3><span class="label label-danger">ЦЕНА: ${part.price}</span></h3>
        </div>
    </div>

    <div class="row" >
        <div class="col-md-6">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item peopleCarouselImg active">
                        <img class="peopleCarouselImg" src="<c:url value="/resources/image/ford_1.JPG"/>" alt="...">
                        <div class="carousel-caption">
                        </div>
                    </div>
                    <div class="item peopleCarouselImg">
                        <img class="peopleCarouselImg" src="<c:url value="/resources/image/ford_2.jpg"/>" alt="...">
                        <div class="carousel-caption">
                        </div>
                    </div>
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
        </div>

        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="col-md-6">
                        <b>Информация о запчасти</b>
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
                        <b>Информация о продавце</b>
                        <hr>
                        <dl class="dl-horizontal">
                            <dt>Название</dt>
                            <dd>${part.user.name}</dd>
                            <dt>Страна</dt>
                            <dd>${part.user.contry}</dd>
                            <dt>Город</dt>
                            <dd>${part.user.city}</dd>
                            <dt>Адрес</dt>
                            <dd>${part.user.address}</dd>
                            <dt>Телефон</dt>
                            <dd>${part.user.phone}</dd>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row" >
        <br>
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-body">
                    <b>Информация о запчасти</b>
                    <hr>
                    <p>${part.description}</p>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <script type="text/javascript" charset="utf-8" src="https://api-maps.yandex.ru/services/constructor/1.0/js/?sid=XHE7gEBYTXJ8-FgvgUL2U-a1lw5b4HfJ&width=550&height=300"></script>
        </div>
    </div>

    <div class="row">
        <br>
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-body">
                    <b>Отзывы</b>
                    <hr>
                    <c:forEach items="${reviews}" var="review">
                        <p>Имя: ${review.name}</p>
                        <p>Отзыв: ${review.description}</p>
                        <p>Оценка: ${review.rating}</p>
                        <p>Дата: ${review.date}</p>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
