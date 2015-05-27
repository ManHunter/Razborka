<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05.05.2015
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Комментарии</title>
    <%@ include file="../layout/header.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="../layout/navbar_profile.jsp" %>
    <div class="row">
        <div class="col-md-12">
            <ul class="nav nav-tabs">
                <li class="active"><a href="#parts" data-toggle="tab">Запчасти</a></li>

                <security:authorize access="hasRole('SELLER')">
                    <li><a href="#auto" data-toggle="tab">Автомобили</a></li>
                </security:authorize>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div class="tab-pane active" id="parts">
                    <table class="table table-bordered">
                        <tr>
                            <th>ID</th>
                            <th>Автор</th>
                            <th>Комментарий</th>
                            <th>Дата</th>
                            <th>Ссылка</th>
                        </tr>
                        <c:if test="${fn:length(part_comments) le 0}">
                            <tr>
                                <td colspan="5">
                                    <div class="alert alert-info">
                                        <h4 class="text-center">Нет комментариев</h4>
                                    </div>
                                </td>
                            </tr>
                        </c:if>
                        <c:forEach items="${part_comments}" var="comment">
                            <tr>
                                <td>${comment.id}</td>
                                <td>${comment.user.fio}</td>
                                <td>${comment.comment}</td>
                                <td>${comment.date}</td>
                                <td><a href="/parts/${comment.part.id}">Перейти в запчасть</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <security:authorize access="hasRole('SELLER')">
                    <div class="tab-pane" id="auto">
                        <table class="table table-bordered">
                            <tr>
                                <th>ID</th>
                                <th>Автор</th>
                                <th>Комментарий</th>
                                <th>Дата</th>
                                <th>Ссылка</th>
                            </tr>
                            <c:if test="${fn:length(car_comments) le 0}">
                                <tr>
                                    <td colspan="5">
                                        <div class="alert alert-info">
                                            <h4 class="text-center">Нет комментариев</h4>
                                        </div>
                                    </td>
                                </tr>
                            </c:if>
                            <c:forEach items="${car_comments}" var="comment">
                                <tr>
                                    <td>${comment.id}</td>
                                    <td>${comment.user.fio}</td>
                                    <td>${comment.comment}</td>
                                    <td>${comment.date}</td>
                                    <td><a href="/cars/${comment.car.id}">Перейти в запчасть</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </security:authorize>
            </div>
        </div>
    </div>
</div>
</body>
</html>
