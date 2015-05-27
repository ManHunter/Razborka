<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24.05.2015
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Пользователи</title>
    <%@include file="../layout/header.jsp" %>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-9">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Администратор</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-9">
                <ul class="nav navbar-nav">
                    <li><a href="/admin">Главная</a></li>
                    <li><a href="/">Сайт</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Управление категорией &laquo;Пользователи&raquo;</h3>
                </div>
                <div class="panel-body">

                    <c:forEach items="${users}" var="user">

                        <div class="row" style="padding-top: 20px">
                            <div class="col-sm-10 col-md-10 col-lg-10">
                                <c:if test="${param.role eq 'CUSTOMER'}">
                                    <h4>${user.fio}</h4>
                                </c:if>
                                <c:if test="${param.role ne 'CUSTOMER'}">
                                    <h4>${user.name}</h4>
                                </c:if>
                                <div class="col-xs-6 col-md-2">
                                    <a href="/razborka/${user.id}" class="thumbnail">
                                        <img src="<c:if test="${user.photo1 eq null}"><c:url value="/resources/image/no_photo.png"/></c:if><c:if test="${user.photo1 ne null}">/image/user/${user.photo1}</c:if>"
                                             alt="Фото">
                                    </a>
                                </div>
                                <div class="col-md-8">
                                    <p>
                                        <b>ID:</b> ${user.id}
                                        <b>Автомобилей в разборе:</b> ${fn:length(user.cars)}
                                        <b>Запчастей:</b> ${fn:length(user.reviews)}
                                        <b>Отзывов:</b> ${fn:length(user.reviews)}
                                    </p>
                                    <p>
                                        Дата регистрации: <b><joda:format value="${user.date}" pattern="dd.MM.yyyy HH:mm"/></b>
                                    </p>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <br>
                                <br>
                                <a href="/admin/category/user/delete?id=${user.id}&role=${param.role}" class="btn btn-danger">Удалить</a>
                            </div>
                        </div>
                        <hr class="line">
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

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
</div>
</body>
</html>
