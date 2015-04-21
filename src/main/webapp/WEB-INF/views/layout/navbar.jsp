<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a href="/" class="navbar-brand">РАЗБОРКА.by</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active">
                    <a href="/parts">Запчасти</a>
                </li>
                <li>
                    <a href="/unmounters">Разборки</a>
                </li>
                <li>
                    <a href="/sto">СТО</a>
                </li>
                <li>
                    <a href="/help">Помощь</a>
                </li>
                <li>
                    <a href="/contacts">Контакты</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <security:authorize access="!isAuthenticated()">
                    <form class="navbar-form navbar-right">
                        <li>
                            <a href="<c:url value="/login" />" class="btn btn-success" role="button">Вход</a>
                            <a href="/registration" class="btn btn-primary" role="button">Регистрация</a>
                        </li>
                    </form>
                </security:authorize>

                <security:authorize access="isAuthenticated()">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Профиль <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/profile">Профиль <security:authentication property="principal.username" /></a></li>
                            <li><a href="/profile">Мои запчасти</a></li>
                            <li class="divider"></li>
                            <li><a class="btn btn-dange" href="<c:url value="/logout" />" role="button">Выйти</a></li>
                        </ul>
                    </li>
                </security:authorize>
            </ul>
        </div>
    </div>
</nav>
<br>
<br>