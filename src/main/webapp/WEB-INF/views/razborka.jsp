<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 30.04.2015
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>${razborka.name}</title>
    <%@include file="layout/header.jsp" %>
    <script>
        $(document).ready(function () {
            $('#model').chained('#brand');
            $("[data-toggle=popover]").popover();
        });

        var coordinates = [];
        var addresses = [];
        <c:forEach items="${razborka.addresses}" var="a">
        var points = '${a.coordinates}'.split(',');
        coordinates.push([points[0], points[1]]);
        addresses.push('${a.contry}' + ', ' + '${a.city}' + ', ' + '${a.address}');
        </c:forEach>

        var myMap;

        ymaps.ready(function () {
            var myCollection = new ymaps.GeoObjectCollection();
            console.log(myCollection);

            myMap = new ymaps.Map("YMapsID", {
                center: [53.531205, 28.030985], // Начальные значения центра карты
                zoom: 6,         // Начальное значение зума карты
                controls: ['zoomControl']
            });
            myMap.controls.add('zoomControl', {left: 5, top: 5})

            for (i = 0; i < coordinates.length; i++) {
                var myPlacemark = new ymaps.Placemark([
                    coordinates[i][0], coordinates[i][1]
                ], {
                    balloonContentHeader: "${razborka.name}",
                    balloonContentBody: addresses[i],
                    balloonContentFooter: "Наш сайт: <a href='http://${razborka.site}'>${razborka.site}</a>",
                    hintContent: "Адрес"
                });
                myCollection.add(myPlacemark);
            }

            myMap.geoObjects.add(myCollection);
            myMap.setBounds(myCollection.getBounds(), {checkZoomRange: true});
        });
    </script>
</head>
<body>
<%@include file="layout/navbar.jsp" %>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <c:if test="${razborka.photo1 ne null}">
            <div class="item active">
                <img data-src="holder.js/900x500/auto/#777:#7a7a7a/text:First slide" alt="First slide"
                     src="/image/user/${razborka.photo1}" height="300" style="margin: 0 auto;">
            </div>
        </c:if>
        <c:if test="${razborka.photo2 ne null}">
            <div class="item">
                <img data-src="holder.js/900x500/auto/#777:#7a7a7a/text:First slide" alt="First slide"
                     src="/image/user/${razborka.photo2}" height="300" style="margin: 0 auto;">
            </div>
        </c:if>
        <c:if test="${razborka.photo3 ne null}">
            <div class="item">
                <img data-src="holder.js/900x500/auto/#777:#7a7a7a/text:First slide" alt="First slide"
                     src="/image/user/${razborka.photo3}" height="300" style="margin: 0 auto;">
            </div>
        </c:if>
    </div>
    <a class="left carousel-control" href="#myCarousel" data-slide="prev"><span
            class="glyphicon glyphicon-chevron-left"></span></a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next"><span
            class="glyphicon glyphicon-chevron-right"></span></a>
</div>

<div class="container" style="padding-top: 20px">

    <div class="row">
        <div class="col-md-12">
            <ol class="breadcrumb h4">
                <li><a href="/">Главная</a></li>
                <li><a href="/razborki">Разборки</a></li>
                <li class="active">Разборка ${razborka.name}</li>
            </ol>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <h2>
                ${razborka.name}
                <small>
                    ${razborka.name}
                </small>
            </h2>
        </div>
    </div>

    <div class="row equal" style="padding-top: 20px">
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-body">
                    <dl class="dl-horizontal">
                        <dt>Адрес:</dt>
                        <dd>
                            <c:forEach items="${razborka.addresses}" var="address">
                                <p>${address.contry}, ${address.city}, ${address.address}</p>
                            </c:forEach>
                        </dd>
                        <dt>Телефон:</dt>
                        <dd>
                            <c:forEach items="${razborka.phones}" var="phone">
                                <p>${phone.phoneNumber}</p>
                            </c:forEach>
                        </dd>
                        <dt>Skype:</dt>
                        <dd>${razborka.skype}</dd>
                        <dt>Сайт:</dt>
                        <dd>${razborka.site}</dd>
                    </dl>

                    <h4>Описание</h4>
                    <hr>
                    <p>
                        ${razborka.description}
                    </p>

                    <security:authorize access="hasAnyRole('CUSTOMER')">
                        <button class="btn btn-success" data-toggle="modal" data-target="#requestModal">
                            Отправить запрос
                        </button>
                    </security:authorize>
                </div>
            </div>

            <div class="col-md-6">
                <div class="col-md-6">
                    <div id="YMapsID" style="width: 550px; height: 370px"></div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <h3>Отзывы</h3>

            <div class="panel panel-default">
                <div class="panel-body">

                    <security:authorize access="hasAnyRole('CUSTOMER')">
                        <a data-toggle="modal" data-target="#reviewModal" class="btn btn-primary">Написать отзыв</a>
                    </security:authorize>

                    <security:authorize access="isAnonymous()">
                        <a data-container="body" data-toggle="popover" data-placement="right"
                           data-content="Оставлять отзывы могут только зарегистрированные пользователи"
                           class="btn btn-primary">Написать отзыв</a>
                    </security:authorize>

                    <hr>

                    <div class="row">
                        <c:if test="${fn:length(razborka.reviews) lt 1}">
                            <div class="alert alert-warning text-center">Нет отзывов</div>
                        </c:if>
                        <c:forEach items="${razborka.reviews}" var="review">
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-6"><h3 style="margin-top: 0px">${review.user_from.fio}</h3></div>
                                    <div class="col-md-6"><strong><joda:format value="${review.date}"
                                                                               pattern="dd.MM.yyyy HH:mm"/></strong>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <input id="input-id" type="number" class="rating" readonly="true"
                                               value="${review.rating}"
                                               data-size="xs"/>

                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <p>${review.description}</p>
                                    </div>
                                </div>
                                <hr class="line">
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <h3>Авто в разборе</h3>

            <div class="panel panel-default">
                <div class="panel-body">
                    <c:forEach items="${razborka.cars}" var="car" begin="0" end="2">
                        <div class="row">
                            <div class="col-md-6">
                                <a href="/cars/${car.id}">
                                    <img id="car_photo_${car.id}" class="img-rounded" height="130"
                                         src="<c:if test="${car.photo eq null}"><c:url value="/resources/image/no_photo.png"/></c:if><c:if test="${car.photo ne null}">/image/car/${car.photo}</c:if>"
                                         alt="${car.brand.name} ${car.model.name}">
                                </a>
                            </div>
                            <div class="col-md-6">
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
                        <div class="row">
                            <div class="col-md-6">
                                <h3>Описание</h3>
                                    ${car.description}
                            </div>
                        </div>
                        <hr>
                    </c:forEach>

                    <a href="/cars?razborka=${razborka.id}" class="btn btn-default">Просмотреть все автомобили</a>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <h3>Запчасти пользователя</h3>

            <div class="panel panel-default">
                <div class="panel-body">
                    <table class="table table-striped table-condensed">
                        <tr>
                            <th>Марка</th>
                            <th>Модель</th>
                            <th>Название</th>
                            <th>Состояние</th>
                            <th>Цена</th>
                            <th>Ссылка</th>
                        </tr>
                        <c:set var="partsCount" value="0"/>
                        <c:forEach items="${razborka.cars}" var="car">
                            <c:if test="${partsCount lt 15}">
                                <c:forEach items="${car.parts}" var="part">
                                    <c:if test="${partsCount lt 15}">
                                        <tr>
                                            <td>${part.car.brand.name}</td>
                                            <td>${part.car.model.name}</td>
                                            <td>${part.type.name}</td>
                                            <td>${part.condition}</td>
                                            <td>${part.price}</td>
                                            <td><a href="/parts/${part.id}">подробнее</a></td>
                                        </tr>
                                        <c:set var="partsCount" value="${partsCount + 1}" scope="page"/>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </table>
                    <a href="/parts?razborka=${razborka.id}" class="btn btn-default">Просмотреть все запчасти этой
                        разборки</a>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="requestModal" tabindex="-1" role="dialog" aria-labelledby="requestModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="requestModalLabel">Запрос разборке</h4>
                </div>
                <div class="modal-body">
                    <form id="request_form">
                        <input type="hidden" name="user" value="${razborka.id}"/>

                        <div class="form-group">
                            <label for="brand">Марка авто: </label>
                            <select class="form-control" id="brand" name="brand">
                                <option value="0">Выберите марку</option>
                                <c:forEach items="${brands}" var="brand">
                                    <option value="${brand.name}">${brand.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="model">Модель авто: </label>
                            <select class="form-control" id="model" name="model">
                                <option value="0" class="0">Выберите модель</option>
                                <c:forEach items="${models}" var="model">
                                    <option value="${model.name}" class="${model.brand.name}">${model.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="year">Год: </label>
                            <select class="form-control select2" id="year" name="year">
                                <option value="0">Выберите год</option>
                                <c:forEach begin="1970" end="2015" var="year">
                                    <option value="${year}" ${year_select eq year ? "selected" : ""}>${year}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="catalog">Номер по каталогу: </label>
                            <input class="form-control" type="text" name="catalog" id="catalog"
                                   placeholder="Номер запчасти по каталогу"/>
                        </div>

                        <div class="form-group">
                            <label for="desc">Описание: </label>
                            <textarea class="form-control" cols="100" rows="5" name="desc" id="desc"
                                      placeholder="Напишите подробнее что Вам необходимо"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Отменить</button>
                    <button type="button" class="btn btn-primary" onclick="addRequest()">Отправить запрос</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="reviewModal" tabindex="-1" role="dialog" aria-labelledby="reviewModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="reviewModalLabel">Ваш отзыв о ${razborka.name}</h4>
                </div>
                <div class="modal-body">
                    <form id="reviewForm" method="post">
                        <input type="hidden" name="user" value="${razborka.id}"/>

                        <div class="form-group">
                            <label>Оценка</label>
                            <input name="rating" type="number" class="rating" min="0" max="5" step="1" data-size="md"/>
                        </div>

                        <div class="form-group">
                            <label for="description">Отзыв</label>
                            <textarea rows="5" id="description" name="description" class="form-control"
                                      placeholder="Введите отзыв"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                    <button type="button" class="btn btn-primary" onclick="addReview()">Сохранить изменения</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
