<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 25.05.2015
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Автомобили в разборе</title>
    <%@include file="layout/header.jsp" %>
    <script>
        $(document).ready(function () {
            $('#model').chained('#brand');
            $('#part_type').chained('#part_group');
            //SELECT2
            $(".select2").select2();
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
                <li class="active">Запчасти</li>
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

                    <form id="filter" action="/cars" class="form-inline">

                        <input type="hidden" name="razborka" value="0"/>
                        <input type="hidden" name="page" value="1"/>

                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="col-md-3">
                                        <label style="width: 200px;"><h3 style="margin: 0;">Запчасти для: </h3></label>
                                    </div>
                                    <div class="col-md-3">
                                        <select class="form-control select2" id="brand" name="brand">
                                            <option value="0">Выберите марку</option>
                                            <c:forEach items="${brands}" var="brand">
                                                <option value="${brand.id}" ${brand.id eq brand_select ? "selected" : ""}>${brand.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-3">
                                        <select class="form-control select2" id="model" name="model">
                                            <option value="0" class="0">Выберите модель</option>
                                            <c:forEach items="${models}" var="model">
                                                <option value="${model.id}"
                                                        class="${model.brand.id}" ${model.id eq model_select ? "selected" : ""}>${model.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-3">
                                        <select class="form-control select2" id="year" name="year">
                                            <option value="0">Выберите год</option>
                                            <c:forEach begin="1970" end="2015" var="year">
                                                <option value="${year}" ${year_select eq year ? "selected" : ""}>${year}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label style="width: 200px;"><h3>Параметры авто:</h3></label>
                                    <select class="form-control select2" id="volume" name="volume">
                                        <option value="0" class="0">Выберите объем двигателя</option>
                                        <c:forEach items="${volumes}" var="volume">
                                            <option value="${volume}" ${volume eq volume_select ? "selected" : ""}>${volume}</option>
                                        </c:forEach>
                                    </select>

                                    <select class="form-control select2" id="fuel" name="fuel">
                                        <option value="0" class="0">Выберите модель</option>
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

                        <div class="form-group">
                            <input type="submit" class="btn btn-primary btn-lg" value="Подобрать"/>
                        </div>
                    </form>
                </div>
            </div>
            <hr class="line">
        </div>
    </div>

    <br>
    <br>

    <c:forEach items="${cars}" var="car">
    <div class="row">
        <div class="col-lg-offset-1 col-md-10">
            <div class="col-xs-6 col-md-2">
                <a href="/parts/${part.id}" class="thumbnail">
                    <img class="img-rounded"
                         src="<c:if test="${car.photo eq null}"><c:url value="/resources/image/no_photo.png"/></c:if><c:if test="${car.photo ne null}">/image/car/${car.photo}</c:if>"
                         alt="Фото нет">
                </a>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <a href="/cars/${car.id}" class="lead">
                            ${car.brand.name} ${car.model.name} ${car.year_from}-${car.year_to}
                    </a>

                    <p><b>Описание:</b></p>

                    <p>${car.description}</p>
                </div>
                <div class="row">
                    <p>
                        <b>Двигатель:</b> ${car.fuel.name} ${car.volume}
                        <b>КПП:</b> ${car.kpp.name}
                        <b>Кузов</b> ${car.body.name}
                    </p>
                </div>
            </div>
            <div class="col-md-4 text-right">
                <h3 class="text-right"><joda:format value="${car.date}" pattern="dd.MM.yyyy HH:mm"/></h3>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-offset-1 col-md-10">
            <hr class="line">
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
</body>
</html>
