<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15.05.2015
  Time: 0:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить новость</title>
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
        <div class="col-md-6">
            <form:form modelAttribute="news" action="/admin/news/edit" method="post">
                <form:hidden path="id"/>
                <form:hidden path="date"/>
                <div class="form-group">
                    <label for="title">Заголовок</label>
                    <form:input path="title" id="title" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <label for="text">Текст</label>
                    <form:textarea rows="6" path="text" id="text" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:checkbox path="pub"/><span for="text">Публичная</span>
                </div>
                <input type="submit" class="btn btn-primary" value="Отправить"/>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
