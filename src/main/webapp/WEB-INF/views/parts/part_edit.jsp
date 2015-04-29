<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17.04.2015
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование</title>
    <%@include file="../layout/header.jsp" %>

    <script>
        $(document).ready(function () {
            $('#type').chained('#group');
        });

        function deletePhoto(caller, photo) {
            console.log(photo);
            $.ajax({
                url: "/image/delete/part/" + photo,
                type: "POST",
                success: function (s) {
                    $(caller).parents(".col-sm-6").remove();
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
                        <a href="/profile/">Кабинет</a>
                    </li>
                    <li>
                        <a href="/profile/">Учетная запись</a>
                    </li>
                    <li>
                        <a href="/profile/cars">Авто</a>
                    </li>
                    <li>
                        <a href="/profile/parts">Запчасти</a>
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
        <form:form action="/parts/edit/${part.id}" method="post" modelAttribute="part" enctype="multipart/form-data">
        <div class="col-md-5">
            <h2>Информация о запчасти</h2>
            <hr>
            <div class="well">
                <div class="form-group">
                    <label for="id">ID: </label>
                    <form:input class="form-control" path="id" readonly="true" disabled="true"/>
                </div>
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
                    <form:textarea rows="5" class="form-control" path="description" id="description"/>
                </div>

                <div class="row">
                    <c:forEach items="${part.photos}" var="photo">
                        <div class="col-sm-6 col-md-4">
                            <div class="thumbnail">
                                <img src="/image/part/${photo.picture}" alt="${part.type.name}">

                                <div class="caption">
                                    <a id="delete" onclick="deletePhoto(this, '${photo.picture}')" class="btn btn-danger" role="button">
                                        <span class="glyphicon glyphicon-remove"></span>Удалить
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="form-group">
                    <label for="part_image">Фото запчасти:</label>
                    <input class="btn btn-default btn-file" id="part_image" name="part_image_files[]" type="file"
                           multiple="multiple"/>
                </div>
            </div>
        </div>
    </div>
    <input type="submit" class="btn btn-success"/>
    </form:form>
</div>

</body>
</html>
