<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Профиль</title>
    <%@ include file="../layout/header.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="../layout/navbar_profile.jsp" %>

    <form:form id="profileForm" action="/profile/user_info/edit" method="POST" commandName="user"
               enctype="multipart/form-data">

        <div class="row">
            <div class="col-md-5">
                <div class="well">

                    <form:hidden path="id"/>
                    <form:hidden path="role"/>
                    <form:hidden path="date"/>
                    <form:hidden path="password"/>

                    <div class="form-group">
                        <label for="fio">ФИО: </label>
                        <form:input type="text" class="form-control" path="fio" id="fio" placeholder="Введите ФИО"/>
                    </div>
                    <security:authorize access="hasAnyRole('SELLER', 'STO')">
                        <div class="form-group">
                            <label for="name">Название организации: </label>
                            <form:input type="text" class="form-control" path="name" id="name"
                                        placeholder="Введите название разборки"/>
                        </div>
                    </security:authorize>
                    <div class="form-group">
                        <label for="email">email: </label> </td>
                        <form:input type="text" class="form-control" path="email" id="email"
                                    placeholder="Введите email"/>
                    </div>
                    <div class="form-group">
                        <label for="skype">skype: </label> </td>
                        <form:input type="text" class="form-control" path="skype" id="skype"
                                    placeholder="Введите skype"/>
                    </div>
                    <security:authorize access="hasAnyRole('SELLER', 'STO')">
                        <div class="form-group">
                            <label for="site">Сайт: </label> </td>
                            <form:input type="text" class="form-control" path="site" id="site"
                                        placeholder="Введите адрес вашего сайта"/>
                        </div>
                        <div class="form-group">
                            <label for="description">Описание разборки: </label> </td>
                            <form:textarea cols="5" type="text" class="form-control" path="description" id="description"
                                           placeholder="Введите описание вашей разборки"/>
                        </div>
                    </security:authorize>

                    <input class="btn btn-primary" type="submit" value="Сохранить"/>
                </div>
            </div>
            <div class="col-md-5">

                <security:authorize access="hasAnyRole('SELLER', 'STO')">
                <div class="well">
                    <div class="form-group">
                        <label for="razborka_image">Фотографии разборки/СТО:</label>
                        <input class="btn btn-default btn-file" id="razborka_image" name="razborka_image_files[]"
                               type="file" multiple="multiple"/>
                    </div>


                    <div class="row equal">
                        <c:if test="${user.photo1 ne null}">
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail">
                                    <form:hidden path="photo1"/>
                                    <img src="/image/user/${user.photo1}">

                                    <div class="caption">
                                        <a id="delete" onclick="deletePhoto(this, '${user.photo1}')"
                                           class="btn btn-danger" role="button">
                                            <span class="glyphicon glyphicon-remove"></span>Удалить
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${user.photo2 ne null}">
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail">
                                    <form:hidden path="photo2"/>
                                    <img src="/image/user/${user.photo2}">

                                    <div class="caption">
                                        <a id="delete" onclick="deletePhoto(this, '${user.photo2}')"
                                           class="btn btn-danger" role="button">
                                            <span class="glyphicon glyphicon-remove"></span>Удалить
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${user.photo3 ne null}">
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail">
                                    <form:hidden path="photo3"/>
                                    <img src="/image/user/${user.photo3}">

                                    <div class="caption">
                                        <a id="delete" onclick="deletePhoto(this, '${user.photo3}')"
                                           class="btn btn-danger" role="button">
                                            <span class="glyphicon glyphicon-remove"></span>Удалить
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <hr>
                    </security:authorize>
                    <security:authorize access="hasRole('STO')">
                        <h2>Услуги СТО</h2>
                        <select id="repair_types" class="form-control">
                            <option selected="selected">Выберите услугу</option>
                            <c:forEach items="${repairTypes}" var="rt">
                                <option value="${rt.id}">${rt.name}</option>
                            </c:forEach>
                        </select>
                        <input type="button" class="btn btn-primary" onclick="addService()" value="Добавить">

                        <div class="row">
                            <div id="serviceList" class="col-md-6">
                                <c:forEach items="${myServices}" var="s">
                                    <p id="service_${s.id}">${s.type.name} <a onclick="deleteService('${s.id}')"
                                                                              class="btn btn-danger"><span
                                            class="glyphicon glyphicon-remove"></span>
                                    </a>
                                    </p>
                                </c:forEach>
                            </div>
                        </div>
                    </security:authorize>
                </div>
            </div>
        </div>
    </form:form>
</div>

<script>
    $('#profileForm').validate({
        rules: {
            fio: {
                minlength: 5,
                maxlength: 30,
                required: true
            },
            email: {
                required: true,
                email: true
            }
        },
        messages: {
            fio: {
                required: "ФИО не может быть пустым",
                minlength: "ФИО не менее 5 символов",
                maxlength: "ФИО не более 30 символов"
            },
            email: {
                required: "Email не может быть пустым",
                email: "Email введен не верно"
            }
        }
    });

</script>
</body>
</html>