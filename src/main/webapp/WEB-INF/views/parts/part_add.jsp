<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить запчасть</title>
    <%@ include file="../layout/header.jsp" %>

    <script>
        $(document).ready(function () {
            $('#type').chained('#group');
        });
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
        <form:form action="/parts/add/${car_id}" method="post" modelAttribute="part" enctype="multipart/form-data">
            <div class="col-md-5">
                <h2>Информация о з/ч</h2>
                <hr>
                <div class="well">
                    <div class="form-group">
                        <label for="group">Группа з/ч: </label>
                        <form:select class="form-control" path="group.id" id="group">
                            <form:option value="0" label="Выберите группу"/>
                            <form:options itemValue="id" itemLabel="name" items="${partGroups}"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="type">Тип з/ч: </label>
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
                        <input class="btn btn-default btn-file" id="part_image" name="part_image_files[]" type="file" multiple="multiple"  />
                    </div>
                </div>
            </div>
            <input type="submit" class="btn btn-success"/>
        </form:form>
    </div>

</div>
</body>
</html>
