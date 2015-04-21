<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Интернет площадка по прадаже автозапчастей</title>

    <%@ include file="layout/header.jsp" %>
</head>
<body>
<%@ include file="layout/navbar.jsp" %>

<div class="jumbotron">
    <div class="container">
        <h1>Hello, world!</h1>
        <p>This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
        <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more »</a></p>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-5">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Найти запчасти</h3>
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <select class="form-control">
                            <option selected="selected">Выберите марку</option>
                            <option>Audi</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <select class="form-control">
                            <option selected="selected">Выберите модель</option>
                            <option>Audi</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <select class="form-control">
                            <option selected="selected">Выберите двигатель</option>
                            <option>Audi</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <select class="form-control">
                            <option selected="selected">Выберите кузов</option>
                            <option>Audi</option>
                        </select>
                    </div>

                    <button type="button" class="btn btn-success">Найти запчасти</button>
                </div>
            </div>
        </div>

        <div class="col-md-5">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Найти запчасти</h3>
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <select class="form-control">
                            <option selected="selected">Выберите город</option>
                            <option>Audi</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <select class="form-control">
                            <option selected="selected">Вид ремонта</option>
                            <option>Audi</option>
                        </select>
                    </div>
                    <p>
                        <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d2403600.0869770087!2d28.328247507812495!3d53.96254912093044!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sru!2sru!4v1429179145529" width="400" height="300" frameborder="0" style="border:0"></iframe>
                    </p>
                </div>
            </div>
        </div>
    </div>

    <%@include file="layout/footer.jsp"%>
</div>
</body>
</html>
