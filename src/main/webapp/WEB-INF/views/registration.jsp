<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Регистрация на сайте</title>
    <%@ include file="layout/header.jsp" %>
</head>

<body>
<%@ include file="layout/navbar.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="navbar navbar-default">
                <div class="container">
                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li id="st1" class="active"><a href="#">Шаг 1 — Тип учетной записи</a></li>
                            <li id="st2"><a href="#">Шаг 2 — Контактное лицо</a></li>
                            <li id="st3"><a href="#">Шаг 3 — Пользовательское соглашение</a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div id="step-1">
                <h3>Выберите тип учетной записи:</h3>

                <div class="radio">
                    <label>
                        <input type="radio" class="accountType" name="accountType" value="SELLER" checked>
                        Разборка, Магазин
                    </label>
                </div>
                <div class="radio">
                    <label>
                        <input type="radio" class="accountType" name="accountType" value="STO">
                        СТО
                    </label>
                </div>
                <div class="radio">
                    <label>
                        <input type="radio" class="accountType" name="accountType" value="CUSTOMER">
                        Частное лицо
                    </label>
                </div>

                <button type="button" class="btn btn-default" onclick="backStep(1)">Назад</button>
                <button type="button" class="btn btn-primary ml20px" onclick="nextStep(1)">Вперед</button>
            </div>

            <div id="step-2" style="display: none;">
                <h3>Заполните данные</h3>
                <div class="row">
                    <div class="col-md-5">
                        <div class="form-group">
                            <label for="fio">ФИО: </label>
                            <input type="text" class="form-control" path="fio" id="fio" name="fio" placeholder="Введите ФИО"/>
                        </div>
                        <div id="nameInput" class="form-group">
                            <label for="name">Название: </label>
                            <input type="text" class="form-control" path="name" id="name" name="name" placeholder="Введите название организации" />
                        </div>
                        <div class="form-group">
                            <label for="email">Email: </label> </td>
                            <input type="email" class="form-control" path="email" id="email" name="email" placeholder="Введите email"/>
                        </div>
                        <div class="form-group">
                            <label for="password">Пароль: </label>
                            <input type="password" class="form-control" path="password" id="password" name="password" placeholder="пароль"/>
                        </div>
                    </div>
                </div>

                <button type="button" class="btn btn-default" onclick="backStep(2)">Назад</button>
                <button type="button" class="btn btn-primary ml20px" onclick="nextStep(2)">Вперед</button>
            </div>

            <div id="step-3" style="display: none;">
                <h3>Правила</h3>
                <%@include file="layout/rules.jsp" %>
                <button type="button" class="btn btn-default" onclick="backStep(3)">Назад</button>
                <button type="button" class="btn btn-primary ml20px" onclick="submitRegistration()">Завершить регистрацию</button>
            </div>

        </div>
    </div>
</div>

<script>
    function submitRegistration() {

        var data = {
            "fio": $("#fio").val(),
            "name": $("#name").val(),
            "email": $("#email").val(),
            "password": $("#password").val(),
            "role": $(".accountType:checked").val()
        };

        $.ajax({
            url: "/register",
            data: JSON.stringify(data),
            type: "POST",
            contentType: "application/json",
//            dataType: "json",
            success: function (role) {
                switch (role) {
                    case 'SELLER':
                        window.location = "/profile/seller";
                        break;
                    case 'CUSTOMER':
                        window.location = "/profile/user";
                        break;
                    case 'STO':
                        window.location = "/profile/sto";
                        break
                }
            },
            error: function (e) {

            }
        })
    }

    function nextStep(step) {
        switch (step){
            case 1:
                $("#step-1").hide();
                $("#step-2").show();
                showName();
                reactivateLiTitle(step, step+1)
                break;

            case 2:
                $("#step-2").hide();
                $("#step-3").show();
                reactivateLiTitle(step, step+1)
                break;

            case 3:
                submitRegistration();
                break;
        }
    }

    function backStep(step) {
        switch (step){
            case 1:
                window.location = '/';
                break;

            case 2:
                $("#step-2").hide();
                $("#step-1").show();
                reactivateLiTitle(step, step-1)
                break;

            case 3:
                $("#step-3").hide();
                $("#step-2").show();
                reactivateLiTitle(step, step-1)
                break;

            case 4:
                $("#step-4").hide();
                $("#step-3").show();
                reactivateLiTitle(step, step-1)
                break;
        }
    }

    function reactivateLiTitle(deact, act) {
        $("#st"+deact).removeClass("active");
        $("#st"+act).addClass("active");
    }

    function showName() {
        if($(".accountType:checked").val() == 'CUSTOMER') {
            $("#nameInput").hide();
        } else {
            $("#nameInput").show();
        }
    }
</script>
</body>
</html>