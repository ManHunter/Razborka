<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить запчасть</title>
    <%@ include file="../layout/header.jsp" %>

    <script>
        $(document).ready(function () {
            $('#model').chained('#brand');
            $('#type').chained('#group');
        });
    </script>
</head>
<body>
<div class="container">
    <%@include file="../layout/navbar_profile.jsp" %>

    <form:form action="/parts/car/add" method="post" modelAttribute="part" enctype="multipart/form-data">
        <div class="row">
            <div class="col-md-5">
                <h2>Информация об автомобиле</h2>
                <hr>
                <div class="well">
                    <div class="form-group">
                        <label for="brand">Марка авто: </label>
                        <form:select class="form-control" path="car.brand.id" id="brand">
                            <form:option value="0" label="Выберите марку"/>
                            <form:options itemValue="id" itemLabel="name" items="${brands}"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="model">Модель авто: </label>
                        <form:select class="form-control" path="car.model.id" id="model">
                            <form:option value="0" label="Выберите модель"/>
                            <c:forEach items="${models}" var="model">
                                <form:option value="${model.id}" label="${model.name}" class="${model.brand.id}"/>
                            </c:forEach>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="volume">Объем двигателя: </label>
                        <form:input class="form-control" path="car.volume" id="volume" placeholder="Введите объем"/>
                    </div>

                    <div class="form-group">
                        <label for="fuel">Тип топлива: </label>
                        <form:select class="form-control" path="car.fuel.id" id="fuel">
                            <form:option value="0" label="Выберите тип топлива"/>
                            <form:options itemValue="id" itemLabel="name" items="${fuels}"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="body">Кузов: </label>
                        <form:select class="form-control" path="car.body.id" id="body">
                            <form:option value="0" label="Выберите кузов"/>
                            <form:options itemValue="id" itemLabel="name" items="${bodies}"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="kpp">КПП: </label>
                        <form:select class="form-control" path="car.kpp.id" id="kpp">
                            <form:option value="0" label="Выберите кпп"/>
                            <form:options itemValue="id" itemLabel="name" items="${kpps}"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="year_from">Год выпуска с: </label>
                        <form:input class="form-control" path="car.year_from" id="year_from"/>
                    </div>

                    <div class="form-group">
                        <label for="year_to">Год выпуска по: </label>
                        <form:input class="form-control" path="car.year_to" id="year_to"/>
                    </div>

                    <security:authorize access="hasRole('STO')">
                    <div class="form-group">
                        <label for="description">Описание: </label>
                        <form:textarea rows="5" class="form-control" path="car.description" id="description"/>
                    </div>

                    <div class="form-group">
                        <label for="car_image">Фото автомобиля:</label>
                        <input class="btn btn-default btn-file" id="car_image" name="car_image_file" type="file"/>
                    </div>
                    </security:authorize>
                </div>
            </div>

            <div class="col-md-5">
                <h2>Информация о з/ч</h2>
                <hr>
                <div class="well">
                    <div class="form-group">
                        <label for="group">Группа запчасти: </label>
                        <form:select class="form-control" path="group.id" id="group">
                            <form:option value="0" label="Выберите группу"/>
                            <form:options itemValue="id" itemLabel="name" items="${partGroups}"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="type">Тип запчасти: </label>
                        <form:select class="form-control" path="type.id" id="type">
                            <form:option value="0" label="Выберите тип"/>
                            <c:forEach items="${partTypes}" var="pt">
                                <form:option value="${pt.id}" label="${pt.name}" class="${pt.group.id}"/>
                            </c:forEach>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="condition">Состояние: </label>
                        <form:select class="form-control" path="condition" id="condition">
                            <form:option value="0" label="Состояние з/ч"/>
                            <form:option value="НОВЫЙ" label="Новый"/>
                            <form:option value="Б/У" label="Б/У"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="price">Цена: </label>
                        <form:input class="form-control" path="price" id="price"/>
                    </div>

                    <div class="form-group">
                        <label for="catalogNumber">Номер по каталогу: </label>
                        <form:input class="form-control" path="catalogNumber" id="catalogNumber"/>
                    </div>

                    <div class="form-group">
                        <label for="description">Описание: </label>
                        <form:textarea rows="8" class="form-control" path="description" id="description"/>
                    </div>

                    <div class="form-group">
                        <label for="part_image">Фото запчасти:</label>
                        <input class="btn btn-default btn-file" id="part_image" name="part_image_files[]" type="file"
                               multiple="multiple"/>
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
