<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.05.2015
  Time: 0:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Сообщение</title>
    <%@ include file="../layout/header.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="../layout/navbar_profile.jsp" %>
    <div class="row">
        <div class="col-md-5">
            <div class="well">
                <p>Отправитель: ${message.user_from.name eq "" ? message.user_from.fio : message.user_from.name}</p>

                <p>Получатель: ${message.user.name eq "" ? message.user.fio : message.user.name}</p>

                <p>Дата: <joda:format value="${message.date}" pattern="dd.MM.yyyy HH:mm"/></p>

                <p>Тема: ${message.subject}</p>

                <p>Сообщение: ${message.message}</p>

                <c:if test="${user.id ne message.user_from.id}">
                    <a href="/profile/new_message/${message.user_from.id}" class="btn btn-default">Ответить</a>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
