<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="../layout/header.jsp" %>

    <script>
        $(document).ready(function () {
            $('a[data-toggle=modal], button[data-toggle=modal]').click(function () {
                var data_id = '';
                if (typeof $(this).data('car-id') !== 'undefined') {
                    data_id = $(this).data('car-id');
                }
                $('#car_id').val(data_id);
            });
        })

        function deleteCar(car_id) {
            if (confirm("Вы уверены? Удаление авто приведет к удалению всех связанных запчастей.")) {
                $.ajax({
                    url: "/cars/delete/" + car_id,
                    type: "POST",
                    success: function (s) {
                        location.reload();
                    },
                    error: function (e) {
                        console.log("deletePart error")
                    }
                })
            }
        }
    </script>
</head>
<body>
<div class="container">
    <%@include file="../layout/navbar_profile.jsp" %>

    <div class="row">
        <div class="col-md-4">
            <a href="/profile/car/add" class="btn btn-success btn-lg">
                <span class="glyphicon glyphicon-plus"></span> Добавить</a>
            <br><br>
        </div>
    </div>

    <c:forEach items="${cars}" var="car">
        <div class="row">
            <div class="col-md-12">
                <div class="well">
                    <div class="row">
                        <div class="col-md-9">
                            <h3 style="margin-top: 0px;">
                                <span id="car_name_${car.id}">${car.brand.name} ${car.model.name}</span>
                                <small id="car_year_${car.id}">${car.year_from}-${car.year_to}</small>
                            </h3>
                        </div>
                        <div class="col-md-3">
                            <h4>
                                Добавлен: <joda:format value="${car.date}" pattern="dd.MM.yyyy HH:mm"/>
                            </h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-6 col-md-2">
                            <a href="/image/car/${car.photo}" class="thumbnail">
                                <img id="car_photo_${car.id}"
                                     src="<c:if test="${car.photo eq null}"><c:url value="/resources/image/no_photo.png"/></c:if><c:if test="${car.photo ne null}">/image/car/${car.photo}</c:if>"
                                     alt="${car.brand.name} ${car.model.name}">
                            </a>
                        </div>
                        <div class="col-md-3">
                            <dl class="dl-horizontal">
                                <dt>Тип топлива</dt>
                                <dd id="car_fuel_${car.id}">${car.fuel.name}</dd>
                                <dt>Объем двигателя</dt>
                                <dd id="car_volume_${car.id}">${car.volume}</dd>
                                <dt>Кузов</dt>
                                <dd id="car_body_${car.id}">${car.body.name}</dd>
                                <dt>КПП</dt>
                                <dd id="car_kpp_${car.id}">${car.kpp.name}</dd>
                            </dl>
                        </div>
                        <div class="col-md-4">
                            <label>Описание:</label>

                            <p>${car.description}</p>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <a class="btn btn-success pull-right" onclick="loadParts(${car.id})"
                                   href="/profile/parts/${car.id}">Просмотреть
                                    запчасти
                                    <span class="badge" id="partCount_${car.id}">${fn:length(car.parts)}</span></a><br><br>

                                <div class="btn-group pull-right">
                                    <button type="button" class="btn btn-default dropdown-toggle"
                                            data-toggle="dropdown"><span
                                            class="glyphicon glyphicon-th-list"></span> Действия <span class="caret"></span></button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="/profile/part/add/${car.id}"><span
                                                class="glyphicon glyphicon-plus"></span> Добавить запчасть</a></li>
                                        <li><a href="/profile/car/edit/${car.id}"><span
                                                class="glyphicon glyphicon-edit"></span> Редактировать</a></li>
                                        <li><a onclick="deleteCar('${car.id}')"><span
                                                class="glyphicon glyphicon-remove"></span> Удалить</a></li>
                                    </ul>
                                </div>
                            </div>


                                <%--<a class="btn btn-danger" href="/profile/part/add/${car.id}">--%>
                                <%--<span class="glyphicon glyphicon-plus"></span> Добавить запчасть</a>--%>
                                <%--<a class="btn btn-danger" href="/profile/car/edit/${car.id}">--%>
                                <%--<span class="glyphicon glyphicon-edit"></span> Редактировать</a>--%>
                                <%--<a class="btn btn-danger" onclick="deleteCar('${car.id}')">--%>
                                <%--<span class="glyphicon glyphicon-remove"></span> Удалить</a>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
