<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 19.04.2015
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <%@include file="layout/header.jsp"%>
</head>
<body>
<%@include file="layout/navbar.jsp"%>

<div class="container" style="padding-top: 20px">

    <div class="row">
        <div class="col-md-12">
            <ol class="breadcrumb">
                <li><a href="/">Главная</a></li>
                <li class="active"><a href="/parts">Запчасти</a></li>
            </ol>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <h3>Запчасти для <span class="label label-default">Авто</span></h3><br>
            <div class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Default <span class="caret"></span></button>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                </ul>
            </div><!-- /btn-group -->
            <div class="btn-group">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Primary <span class="caret"></span></button>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                </ul>
            </div><!-- /btn-group -->
            <div class="btn-group">
                <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Success <span class="caret"></span></button>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                </ul>
            </div><!-- /btn-group -->
            <div class="btn-group">
                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Info <span class="caret"></span></button>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                </ul>
            </div><!-- /btn-group -->
            <div class="btn-group">
                <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Warning <span class="caret"></span></button>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                </ul>
            </div><!-- /btn-group -->
            <div class="btn-group">
                <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Danger <span class="caret"></span></button>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                </ul>
            </div><!-- /btn-group -->
        </div>
    </div>

    <c:forEach items="${parts}" var="part">
    <div class="row" style="padding-top: 20px">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a href="/parts/?id=${part.id}">
                        <h3 class="panel-title">${part.car.brand.name} ${part.car.model.name} ${part.car.year_from}-${part.car.year_to}</h3>
                    </a>
                </div>
                <div class="panel-body">
                    <div class="col-xs-6 col-md-2">
                        <a href="/parts/?id=${part.id}" class="thumbnail">
                            <img src="<c:url value="/resources/image/no_photo.png"/>" alt="Фото нет">
                        </a>
                    </div>
                    <div class="col-md-8">
                        <div class="row">
                            <p><b>Описание:</b></p>
                            <p>${part.description}</p>
                        </div>
                        <div class="row">
                            <p>
                                <b>Группа:</b> ${part.group.name}
                                <b>Тип:</b> ${part.type.name}
                                <b>№: по каталогу</b> ${part.catalogNumber}
                            </p>
                        </div>
                    </div>
                    <div class="col-md-2 text-right">
                        <h3 class="text-right">${part.date}</h3>

                        <h3><span class="label label-success">${part.condition}</span></h3>
                        <h3><span class="label label-danger">Цена: ${part.price}</span></h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </c:forEach>

</div>

</body>
</html>
