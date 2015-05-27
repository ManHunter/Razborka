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
    <title>Мои отзывы</title>
    <%@ include file="../layout/header.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="../layout/navbar_profile.jsp" %>

    <div class="row">
        <div class="col-md-12">
            <table class="table table-bordered">
                <tr>
                    <th>ID</th>
                    <th>Автор</th>
                    <th>Отзыв</th>
                    <th>Оценка</th>
                    <th>Дата</th>
                </tr>
                <c:if test="${fn:length(reviews) le 0}">
                    <tr>
                        <td colspan="5">
                            <div class="alert alert-info">
                                <h4 class="text-center">Нет отзывов</h4>
                            </div>
                        </td>
                    </tr>
                </c:if>
                <c:forEach items="${reviews}" var="review">
                    <tr>
                        <td>${review.id}</td>
                        <td>
                            <c:if test="${review.user.id ne null}">
                                <p>${review.user.fio}</p>

                                <p>${review.user.email}</p>
                            </c:if>
                            <c:if test="${review.user.id eq null}">
                                <p>${review.name}</p>

                                <p>${review.email}</p>
                            </c:if>
                        </td>
                        <td>${review.description}</td>
                        <td>
                            <input id="input-id" type="number" class="rating" readonly="true" value="${review.rating}"
                                   data-size="sm"/>
                        </td>
                        <td>${review.date}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
