<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12.05.2015
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Управление комментариями</title>
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
                    <h3 class="panel-title">Управление категорией &laquo;Комментариями&raquo;</h3>
                </div>
                <div class="panel-body">
                    <div class="col-md-12">
                        <table class="table table-striped">
                            <tr>
                                <th>ID</th>
                                <th>Автор</th>
                                <th>Комментарий</th>
                                <th>Дата</th>
                                <th>Операции</th>
                            </tr>
                            <c:forEach items="${comments}" var="c">
                                <tr>
                                    <td>${c.id}</td>
                                    <td>${c.user.fio}</td>
                                    <td>${c.comment}</td>
                                    <td>${c.date}</td>
                                    <td>
                                        <a href="/admin/category/comment/delete?id=${c.id}" class="btn btn-danger">Удалить</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
