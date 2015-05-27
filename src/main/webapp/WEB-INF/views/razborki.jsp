<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.04.2015
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Разборки</title>
    <%@include file="layout/header.jsp" %>
    <script>
        $(document).ready(function () {
            $('#model').chained('#brand');
            $('#part_type').chained('#part_group');
            //SELECT2
            $(".select2").select2();
        });

        var coordinates = [];
        var addresses = [];
        var user = [];
        <c:forEach items="${addresses}" var="a">
        var points = '${a.coordinates}'.split(',');
        coordinates.push([points[0], points[1]]);
        addresses.push('${a.contry}' + ', ' + '${a.city}' + ', ' + '${a.address}');
        user.push(['${a.user.id}', '${a.user.name}']);
        </c:forEach>

        ymaps.ready(function () {
            var myCollection = new ymaps.GeoObjectCollection();

            var myMap = new ymaps.Map("YMapsID", {
                center: [53.531205, 28.030985],// res.geoObjects.get(0).geometry.getCoordinates(),
                zoom: 6,
                controls: ['zoomControl']
            });

            myMap.controls.add('zoomControl', {left: 5, top: 5})

            for (i = 0; i < coordinates.length; i++) {
                var myPlacemark = new ymaps.Placemark([
                    coordinates[i][0], coordinates[i][1]
                ], {
                    balloonContentHeader: user[i][1],
                    balloonContentBody: addresses[i],
                    balloonContentFooter: "<a href='/razborka/" + user[i][0] + "'>Перейти</a>",
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
<div class="container" style="padding-top: 20px">
    <div class="row">
        <div class="col-lg-offset-1 col-sm-10 col-md-10 col-lg-10">
            <ol class="breadcrumb h4">
                <li><a href="/">Главная</a></li>
                <li class="active">Разборки</li>
            </ol>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-offset-1 col-sm-10 col-md-10 col-lg-10">
            <h2>
                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                    <span class="label label-default">Показать фильтр</span>
                </a>
            </h2>

            <div id="collapseOne" class="panel-collapse collapse">
                <div class="panel-body">
                    <form id="filter" action="/razborki/search" class="form-inline">

                        <input type="hidden" name="razborka" value="0"/>
                        <input type="hidden" name="page" value="1"/>

                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label style="width: 200px;"><h3>Запчасти для: </h3></label>
                                    <select class="form-control select2" id="brand" name="brand">
                                        <option value="0">Выберите марку</option>
                                        <c:forEach items="${brands}" var="brand">
                                            <option value="${brand.id}" ${brand.id eq brand_select ? "selected" : ""}>${brand.name}</option>
                                        </c:forEach>
                                    </select>

                                    <select class="form-control select2" id="model" name="model">
                                        <option value="0" class="0">Выберите модель</option>
                                        <c:forEach items="${models}" var="model">
                                            <option value="${model.id}"
                                                    class="${model.brand.id}" ${model.id eq model_select ? "selected" : ""}>${model.name}</option>
                                        </c:forEach>
                                    </select>
                                    <select class="form-control select2" id="year" name="year">
                                        <option value="0">Выберите год</option>
                                        <c:forEach begin="1970" end="2015" var="year">
                                            <option value="${year}" ${year_select eq year ? "selected" : ""}>${year}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label style="width: 200px;"><h3>Параметры авто:</h3></label>
                                    <select class="form-control select2" id="volume" name="volume">
                                        <option value="0" class="0">Выберите объем двигателя</option>
                                        <c:forEach items="${volumes}" var="volume">
                                            <option value="${volume}" ${volume eq volume_select ? "selected" : ""}>${volume}</option>
                                        </c:forEach>
                                    </select>

                                    <select class="form-control select2" id="fuel" name="fuel">
                                        <option value="0" class="0">Топливо</option>
                                        <c:forEach items="${fuels}" var="fuel">
                                            <option value="${fuel.id}" ${fuel.id eq fuel_select ? "selected" : ""}>${fuel.name}</option>
                                        </c:forEach>
                                    </select>
                                    <select class="form-control select2" id="body" name="body">
                                        <option value="0" class="0">Выберите тип кузова</option>
                                        <c:forEach items="${bodys}" var="body">
                                            <option value="${body.id}" ${body.id eq body_select ? "selected" : ""}>${body.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label style="width: 200px;"><h3>Остальное: </h3></label>
                                    <select class="form-control select2" id="part_group" name="part_group">
                                        <option value="0" class="0">Выберите группу запчастей</option>
                                        <c:forEach items="${groups}" var="group">
                                            <option value="${group.id}" ${group.id eq group_select ? "selected" : ""}>${group.name}</option>
                                        </c:forEach>
                                    </select>
                                    <select class="form-control select2" id="part_type" name="part_type">
                                        <option value="0" class="0">Выберите тип запчасти</option>
                                        <c:forEach items="${types}" var="type">
                                            <option value="${type.id}"
                                                    class="${type.group.id}" ${type.id eq type_select ? "selected" : ""}>${type.name}</option>
                                        </c:forEach>
                                    </select>
                                    <select class="form-control select2" id="city" name="city">
                                        <option value="0" class="0">Выберите город</option>
                                        <c:forEach items="${citys}" var="city">
                                            <option value="${city}" ${city eq city_select ? "selected" : ""}>${city}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary" value="Подобрать"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="YMapsID" class="map"></div>

<div class="container" style="padding-top: 20px">
    <c:forEach items="${users}" var="razborka">
        <div class="row" style="padding-top: 20px">
            <div class="col-lg-offset-1 col-sm-10 col-md-10 col-lg-10">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a href="/razborka/${razborka.id}">
                            <h3 class="panel-title">${razborka.name}</h3>
                        </a>
                    </div>
                    <div class="panel-body">
                        <div class="col-xs-6 col-md-2">
                            <a href="/razborka/${razborka.id}" class="thumbnail">
                                <img src="<c:if test="${razborka.photo1 eq null}"><c:url value="/resources/image/no_photo.png"/></c:if><c:if test="${razborka.photo1 ne null}">/image/user/${razborka.photo1}</c:if>"
                                     alt="Фото">
                            </a>
                        </div>
                        <div class="col-md-8">
                            <div class="row">
                                <p><b>Адрес:</b></p>
                                <c:forEach items="${razborka.addresses}" var="address">
                                    <p>${address.contry}, ${address.city}, ${address.address}</p>
                                </c:forEach>
                            </div>
                            <div class="row">
                                <dl class="dl-horizontal">
                                    <dt>Разбираем:</dt>
                                    <dd>
                                        <c:forEach items="${razborka.cars}" var="car">
                                            ${car.brand.name} ${car.model.name},
                                        </c:forEach>
                                    </dd>
                                    <c:set var="sum" value="${0}"/>
                                    <c:set var="count" value="${fn:length(razborka.reviews)}"/>
                                    <c:forEach items="${razborka.reviews}" var="review">
                                        <c:set var="sum" value="${sum + review.rating}"/>
                                    </c:forEach>
                                    <c:set var="avg" value="${sum / count}"/>

                                    <dt>Рейтинг:</dt>
                                    <dd><input id="input-id" type="number" class="rating" readonly="true" step="0.1"
                                               value="<fmt:formatNumber type="number" maxFractionDigits="1" value="${avg}" />"
                                               data-size="sm"/></dd>
                                </dl>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

    <div class="row">
        <div class="col-md-3 col-centered">

            <c:if test="${numberOfPages gt 1}">
                <div id="page-selection" style="text-align:center;"></div>
                <script>
                    $('#page-selection').bootpag({
                        page: ${page},
                        total: ${numberOfPages},
                        maxVisible: 10
                    }).on("page", function (event, pageNumber) {
                        var sPageURL = window.location.href;
                        var currentPage = getUrlParameter('page');
                        if (currentPage == undefined)
                            sPageURL += "?page=" + pageNumber;
                        else
                            sPageURL = sPageURL.replace("page=" + currentPage, "page=" + pageNumber);
                        window.location = sPageURL;
                    });
                </script>
            </c:if>
        </div>
    </div>

    <%@include file="layout/footer.jsp" %>
</div>
</body>
</html>
