<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="../layout/header.jsp" %>

    <script>
        $(document).ready(function() {
            $('a[data-toggle=modal], button[data-toggle=modal]').click(function () {
                var data_id = '';
                if (typeof $(this).data('car-id') !== 'undefined') {
                    data_id = $(this).data('car-id');
                }
                $('#car_id').val(data_id);
            });
        })

        function deleteCar(car_id) {
            if(confirm("Вы уверены? Удаление авто приведет к удалению всех связанных запчастей.")) {
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
            <a href="/profile/car/add" class="btn btn-success btn-lg">Добавить</a>
            <br><br>
        </div>
    </div>

    <c:forEach items="${cars}" var="car">
        <div class="row">
            <div class="col-md-12">
                <div class="well">
                    <div class="row">
                        <div class="col-md-8">
                            <h3><span id="car_name_${car.id}">${car.brand.name} ${car.model.name}</span>
                                <small id="car_year_${car.id}">${car.year_from}-${car.year_to}</small>
                            </h3>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-6 col-md-2">
                            <a href="/image/car/${car.photo}" class="thumbnail">
                                <img id="car_photo_${car.id}" src="<c:if test="${car.photo eq null}"><c:url value="/resources/image/no_photo.png"/></c:if><c:if test="${car.photo ne null}">/image/car/${car.photo}</c:if>" alt="${car.brand.name} ${car.model.name}">
                            </a>
                        </div>
                        <div class="col-md-6">
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
                        <div class="col-md-3">
                            <a class="btn btn-success" onclick="loadParts(${car.id})" href="/profile/parts/${car.id}">Просмотреть запчасти
                                <span class="badge" id="partCount_${car.id}">${fn:length(car.parts)}</span></a>
                            <a class="btn btn-danger" href="/profile/part/add/${car.id}">Добавить запчасть</a>
                            <a class="btn btn-danger" href="/profile/car/edit/${car.id}">Редактировать</a>
                            <a class="btn btn-danger" onclick="deleteCar('${car.id}')">Удалить</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
