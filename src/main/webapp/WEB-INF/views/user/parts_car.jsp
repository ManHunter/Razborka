<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28.04.2015
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Мои запчасти</title>
    <%@include file="../layout/header.jsp" %>
    <script>
        function deletePart(part_id) {
            if(confirm("Удалить запчасть"))
            {
                $.ajax({
                    url: "/parts/delete/" + part_id,
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
        <div class="col-md-12 well">
            <a class="btn btn-success" href="/profile/part/add/${car.id}">Добавить запчасть</a>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 well">
            <table class="table">
                <tr>
                    <th>ID</th>
                    <th>Фото</th>
                    <th>Запчасть</th>
                    <th>Просмотры</th>
                    <th>Цена</th>
                    <th>Дата добавления</th>
                    <th>Действия</th>
                </tr>

                <c:forEach items="${parts}" var="part">
                    <tr>
                        <td><strong>${part.id}</strong></td>
                        <td>
                            <img style="width: 180px;" src="<c:if test="${fn:length(part.photos) == 0}"><c:url value="/resources/image/no_photo.png"/></c:if><c:if test="${fn:length(part.photos) > 0}">/image/part/${part.photos[0].picture}</c:if>"
                                 alt="${car.brand.name} ${car.model.name}"
                                 class="img-rounded">
                        </td>
                        <td>
                            <strong>${part.group.name} ${part.type.name}</strong>
                            <dl class="dl-horizontal">
                                <dt>Номер по каталогу:</dt>
                                <dd>${part.catalogNumber}</dd>
                            </dl>
                        </td>
                        <td>
                            <p>За сегодня: 0</p>

                            <p>За месяц: 0</p>

                            <p>Всего: 0</p>
                        </td>
                        <td>
                                ${part.price}
                        </td>
                        <td>
                                ${part.date}
                        </td>
                        <td>
                            <a class="btn btn-default" href="/profile/part/edit/${part.id}"><span
                                    class="glyphicon glyphicon-pencil"></span> Редактировать</a>
                            <a class="btn btn-default" onclick="deletePart('${part.id}')"><span
                                    class="glyphicon glyphicon-remove"></span> Удалить</a>
                            <a href="#">Просмотреть на сайте</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

</body>
</html>
