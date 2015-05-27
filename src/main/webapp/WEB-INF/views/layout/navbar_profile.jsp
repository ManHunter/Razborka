<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.04.2015
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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

                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Профиль <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/profile/user_info/">Учетная запись</a></li>
                            <li><a href="/profile/change_password">Сменить пароль</a></li>
                        </ul>
                    </li>
                </ul>
                <security:authorize access="hasRole('SELLER')">
                    <li>
                        <a href="/profile/cars">Мои авто</a>
                    </li>
                </security:authorize>
                <li>
                    <a href="/profile/parts">Мои запчасти</a>
                </li>
                <li>
                    <a href="/profile/request">Заявки</a>
                </li>
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Сообщения <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/profile/messages/">Личные сообщения</a></li>
                            <li><a href="/profile/reviews">Отзывы</a></li>
                            <li><a href="/profile/comments">Комментарии</a></li>
                        </ul>
                    </li>
                </ul>
                <li>
                    <a href="/">Сайт</a>
                </li>
            </ul>
        </div>
    </div>
</div>
