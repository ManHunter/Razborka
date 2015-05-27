<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Профиль</title>
    <%@ include file="../layout/header.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="../layout/navbar_profile.jsp" %>

    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <tr>
                    <th>#</th>
                    <th>Авто</th>
                    <th>Описание</th>
                    <th>Контакты</th>
                    <th>Дата</th>
                    <th>Операции</th>
                </tr>

                <c:if test="${fn:length(requests) le 0}">
                    <tr>
                        <td colspan="6">
                            <div class="alert alert-info">
                                <h4 class="text-center">Нет заявок</h4>
                            </div>
                        </td>
                    </tr>
                </c:if>

                <c:forEach items="${requests}" var="request">
                    <tr>
                        <td>${request.id}</td>
                        <td>
                            <dl class="dl-horizontal">
                                <dt>Марка</dt>
                                <dd>${request.brand}</dd>
                                <dt>Модель</dt>
                                <dd>${request.model}</dd>
                                <dt>Год</dt>
                                <dd>${request.year}</dd>
                            </dl>
                            <security:authorize access="hasAnyRole('SELLER', 'STO')">
                                <a href="/profile/new_message/${request.user_from.id}" class="btn btn-success">Ответить</a>
                            </security:authorize>
                        </td>
                        <td>
                            <dl class="dl-horizontal">
                                <dt>Номер по каталогу</dt>
                                <dd>${request.catalogNumber}</dd>
                            </dl>
                                ${request.description}
                        </td>
                        <td>
                            <dl class="dl-horizontal">
                                <dt>Телефон</dt>
                                <dd>
                                    <c:forEach items="${request.user_from.phones}" var="phone">
                                        <p>${phone.phoneNumber}</p>
                                    </c:forEach>
                                </dd>
                                <dt>email</dt>
                                <dd>${request.user_from.email}</dd>
                                <dt>Skype</dt>
                                <dd>${request.user_from.skype}</dd>
                            </dl>
                        </td>
                        <td><joda:format value="${request.date}" pattern="dd.MM.yyyy HH:mm"/></td>
                        <security:authorize access="hasAnyRole('CUSTOMER')">
                        <td>
                            <a href="/request/delete/${request.id}" class="btn btn-danger">
                                <span class="glyphicon glyphicon-remove"></span> Удалить
                            </a>
                        </td>
                        </security:authorize>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>