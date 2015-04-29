<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить запчасть</title>
    <%@ include file="../layout/header.jsp" %>

    <script>
        $(document).ready(function () {
            $('#model').chained('#brand');

            if ($('div#photo_row').children().length > 0) {
                $('div#upload_photo').hide();
            }
        });

        function deletePhoto(photo) {
            console.log(photo);
            $.ajax({
                url: "/image/delete/car/" + photo,
                type: "POST",
                success: function (s) {
                    $('div#photo_row').empty();
                    $('div#upload_photo').show("slow");
                    console.info("deleteCarPhoto success");
                },
                error: function (e) {
                    console.info("deleteCarPhoto error");
                }
            })
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

    <form:form action="/cars/edit/${car.id}" method="post" modelAttribute="car" enctype="multipart/form-data">
        <div class="row">
            <div class="col-md-5">
                <h2>Информация об автомобиле</h2>
                <hr>
                <div class="well">
                    <div class="form-group">
                        <label for="brand">Марка авто: </label>
                        <form:select class="form-control" path="brand.id" id="brand">
                            <form:option value="0" label="Выберите марку"/>
                            <form:options itemValue="id" itemLabel="name" items="${brands}"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="model">Модель авто: </label>
                        <form:select class="form-control" path="model.id" id="model">
                            <form:option value="0" label="Выберите модель"/>
                            <c:forEach items="${models}" var="model">
                                <form:option value="${model.id}" label="${model.name}" class="${model.brand.id}"/>
                            </c:forEach>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="volume">Объем двигателя: </label>
                        <form:input class="form-control" path="volume" id="volume" placeholder="Введите объем"/>
                    </div>

                    <div class="form-group">
                        <label for="fuel">Тип топлива: </label>
                        <form:select class="form-control" path="fuel.id" id="fuel">
                            <form:option value="0" label="Выберите тип топлива"/>
                            <form:options itemValue="id" itemLabel="name" items="${fuels}"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="body">Кузов: </label>
                        <form:select class="form-control" path="body.id" id="body">
                            <form:option value="0" label="Выберите кузов"/>
                            <form:options itemValue="id" itemLabel="name" items="${bodies}"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="kpp">КПП: </label>
                        <form:select class="form-control" path="kpp.id" id="kpp">
                            <form:option value="0" label="Выберите кпп"/>
                            <form:options itemValue="id" itemLabel="name" items="${kpps}"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="year_from">Год выпуска с: </label>
                        <form:input class="form-control" path="year_from" id="year_from"/>
                    </div>

                    <div class="form-group">
                        <label for="year_to">Год выпуска по: </label>
                        <form:input class="form-control" path="year_to" id="year_to"/>
                    </div>

                    <div id="photo_row" class="row">
                        <c:if test="${car.photo ne null}">
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail">
                                    <form:hidden path="photo"/>
                                    <img src="/image/car/${car.photo}" alt="${car.brand.name} ${car.model.name}">

                                    <div class="caption">
                                        <a onclick="deletePhoto('${car.photo}')" class="btn btn-danger" role="button">
                                            <span class="glyphicon glyphicon-remove"></span>Удалить
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <div id="upload_photo" class="form-group">
                        <label for="car_image">Фото автомобиля:</label>
                        <input class="btn btn-default btn-file" id="car_image" name="car_image_file" type="file"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <input type="submit" class="btn btn-success"/>
        </div>
    </form:form>
</div>
</body>
</html>
