<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Авторизация</title>
    <%@ include file="layout/header.jsp" %>

</head>
<body>
<%@ include file="layout/navbar.jsp" %>
<div class="container">

    <c:url value="/j_spring_security_check" var="loginUrl" />

    <form class="form-signin" action="${loginUrl}" method="post">
        <h2 class="form-signin-heading">Вход на сайт</h2>
        <label for="inputEmail" class="sr-only">Email адрес</label>
            <input type="email" id="inputEmail" name="j_username" class="form-control" placeholder="Email адрес" required="" autofocus="">
        <label for="inputPassword" class="sr-only">Пароль</label>
            <input type="password" id="inputPassword" name="j_password" class="form-control" placeholder="Пароль" required="">
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Запомнить
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
    </form>
</div>
</body>
</html>