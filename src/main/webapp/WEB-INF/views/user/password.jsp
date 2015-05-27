<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Смена пароля</title>

    <%@ include file="../layout/header.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="../layout/navbar_profile.jsp" %>


    <div class="row">
        <div class="col-md-4">
            <div class="well">
                <form id="changePasswordForm" action="/profile/change_password" method="post">
                    <div class="form-group">
                        <label for="old_password">Текущий пароль пароль</label>
                        <input id="old_password" name="old_password" type="password" class="form-control"
                               placeholder="Введите старый пароль"/>
                    </div>
                    <div class="form-group">
                        <label for="new_password">Новый пароль пароль</label>
                        <input id="new_password" name="new_password" type="password" class="form-control"
                               placeholder="Введите новый пароль"/>
                    </div>
                    <div class="form-group">
                        <label for="repeat_new_password">Повторите</label>
                        <input id="repeat_new_password" name="repeat_new_password" type="password" class="form-control"
                               placeholder="Повторите новый пароль"/>
                    </div>

                    <div class="form-group">
                        <input type="submit" class="btn btn-default" value="Изменить"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script>
    $('#changePasswordForm').validate({
        rules: {
            old_password: {
                required: true,
                minlength: 6
            },
            new_password: {
                required: true,
                minlength: 6
            },
            repeat_new_password: {
                required: true,
                equalTo: "#new_password"
            }
        },
        messages: {
            old_password: {
                required: "Пароль не может быть пустым",
                minlength: "Пароль не менее 5 символов"
            },
            new_password: {
                required: "Пароль не может быть пустым",
                minlength: "Пароль не менее 5 символов"
            },
            repeat_new_password: {
                required: "Нужно повторить пароль",
                equalTo: "Пароли не совпадают"
            }
        }
    });

</script>
</body>
</html>
