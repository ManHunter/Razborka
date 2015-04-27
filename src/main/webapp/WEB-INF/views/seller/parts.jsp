<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="../layout/header.jsp" %>

    <script>
        $(document).ready(function() {
            $('a[data-toggle=modal], button[data-toggle=modal]').click(function () {
                var data_id = '';
                if (typeof $(this).data('car-id') !== 'undefined') {
                    data_id = $(this).data('car-id');
                }
                $('#car_id').val(data_id);
            });
        })
    </script>
</head>
<body>
<div class="container">
    <div class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="#" class="navbar-brand">РАЗБОРКА.by</a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <a href="/profile/seller/">Кабинет</a>
                    </li>
                    <li>
                        <a href="/profile/seller/">Учетная запись</a>
                    </li>
                    <li>
                        <a href="/profile/seller/parts">Запчасти</a>
                    </li>
                    <li>
                        <a href="/profile/seller/orders">Заявки</a>
                    </li>
                    <li>
                        <a href="/">Сайт</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4">
            <a href="/parts/car_part/add" class="btn btn-success btn-lg">Добавить з/ч</a>
            <br><br>
        </div>
    </div>

    <c:forEach items="${cars}" var="car">
        <div class="row">
            <div class="col-md-12">
                <div class="well">
                    <div class="row">
                        <div class="col-md-8">
                            <h3><span id="car_name_${car.id}">${car.brand.name} ${car.model.name}</span>
                                <small id="car_year_${car.id}">${car.year_from}-${car.year_to}</small>
                            </h3>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-6 col-md-2">
                            <a href="/parts/?id=1" class="thumbnail">
                                <img src="<c:url value="/resources/image/no_photo.png"/>" alt="Фото нет">
                            </a>
                        </div>
                        <div class="col-md-6">
                            <dl class="dl-horizontal">
                                <dt>Тип топлива</dt>
                                <dd id="car_fuel_${car.id}">${car.fuel.name}</dd>
                                <dt>Объем двигателя</dt>
                                <dd id="car_volume_${car.id}">${car.volume}</dd>
                                <dt>Кузов</dt>
                                <dd id="car_body_${car.id}">${car.body.name}</dd>
                                <dt>КПП</dt>
                                <dd id="car_kpp_${car.id}">${car.kpp.name}</dd>
                            </dl>
                        </div>
                        <div class="col-md-3">
                            <a class="btn btn-success" data-toggle="collapse" data-target="#parts_spoiler${car.id}"
                               onclick="loadParts(${car.id})">Просмотреть запчасти
                                <span class="badge" id="partCount_${car.id}">${fn:length(car.parts)}</span></a>
                            <a class="btn btn-danger" data-toggle="modal" href="#addPartModal" data-car-id="${car.id}">Добавить
                                запчасть</a>
                            <a class="btn btn-danger" data-toggle="modal" href="#editCarModal" data-car-id="${car.id}"
                               onclick="editCar(${car.id})">Редактировать</a>
                        </div>

                        <div class="col-md-12">
                            <div id="parts_spoiler${car.id}" class="collapse">
                                <table class="table table-hover" id="partTable${car.id}">
                                    <tr>
                                        <th>ID</th>
                                        <th>Фото</th>
                                        <th>Группа</th>
                                        <th>Тип</th>
                                        <th>Состояние</th>
                                        <th>Описание</th>
                                        <th>Цена</th>
                                        <th>Дата добавления</th>
                                        <th>Операции</th>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

    <div class="modal fade" id="addPartModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="addPartModalLabel">Добавление</h4>
                </div>
                <div class="modal-body">
                    <form:form method="post" modelAttribute="part" id="addPartForm">
                        <div class="well">

                            <div >
                                <div class="form-group" style="display: none">
                                    <input class="form-control" id="car_id"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="group">Группа з/ч: </label>
                                <form:select path="group.id" class="form-control" id="group">
                                    <option value="0">Выберите группу</option>
                                    <c:forEach items="${partGroups}" var="pg">
                                        <option value="${pg.id}">${pg.name}</option>
                                    </c:forEach>
                                </form:select>
                            </div>

                            <div class="form-group">
                                <label for="type">Тип з/ч: </label>
                                <form:select path="type.id" class="form-control" id="type">
                                    <option value="0" label="Выберите тип"/>
                                </form:select>
                            </div>

                            <div class="form-group">
                                <label for="condition">Состояние: </label>
                                <form:select path="condition" class="form-control" id="condition">
                                    <option value="0">Состояние з/ч</option>
                                    <option value="НОВЫЙ">Новый</option>
                                    <option value="Б/У">Б/У</option>
                                </form:select>
                            </div>

                            <div class="form-group">
                                <label for="price">Цена: </label>
                                <form:input path="price" class="form-control" id="price"/>
                            </div>

                            <div class="form-group">
                                <label for="catalogNumber">Номер по каталогу: </label>
                                <form:input path="catalogNumber" class="form-control" id="catalogNumber"/>
                            </div>

                            <div class="form-group">
                                <label for="description">Описание: </label>
                                <form:textarea path="description" rows="5" class="form-control" id="description"></form:textarea>
                            </div>
                        </div>
                    </form:form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                    <button type="button" class="btn btn-primary" onclick="addPart()">Сохранить</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="editPartModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="editPartModalLabel">Редактирование</h4>
                </div>
                <div class="modal-body">
                    <form:form method="post" modelAttribute="part" id="editPartForm">
                        <div class="well">
                            <div class="form-group">
                                <label for="id">ID: </label>
                                <form:input path="id" class="form-control" readonly="true" disabled="true" id="id"/>
                            </div>
                            <div class="form-group">
                                <label for="group">Группа з/ч: </label>
                                <form:select path="group.id" class="form-control" id="group">
                                    <option value="0">Выберите группу</option>
                                    <c:forEach items="${partGroups}" var="pg">
                                        <option value="${pg.id}">${pg.name}</option>
                                    </c:forEach>
                                </form:select>
                            </div>

                            <div class="form-group">
                                <label for="type">Тип з/ч: </label>
                                <form:select path="type.id" class="form-control" id="type">
                                    <option value="0" label="Выберите тип"/>
                                </form:select>
                            </div>

                            <div class="form-group">
                                <label for="condition">Состояние: </label>
                                <form:select path="condition" class="form-control" id="condition">
                                    <option value="0">Состояние з/ч</option>
                                    <option value="НОВЫЙ">Новый</option>
                                    <option value="Б/У">Б/У</option>
                                </form:select>
                            </div>

                            <div class="form-group">
                                <label for="price">Цена: </label>
                                <form:input path="price" class="form-control" id="price"/>
                            </div>

                            <div class="form-group">
                                <label for="catalogNumber">Номер по каталогу: </label>
                                <form:input path="catalogNumber" class="form-control" id="catalogNumber"/>
                            </div>

                            <div class="form-group">
                                <label for="description">Описание: </label>
                                <form:textarea path="description" rows="5" class="form-control" id="description"></form:textarea>
                            </div>
                        </div>
                    </form:form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                    <button type="button" class="btn btn-primary" onclick="savePart()">Сохранить</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="editCarModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="editCarModalLabel">Редактирование</h4>
                </div>
                <div class="modal-body">
                    <form:form method="post" modelAttribute="car" id="editCarForm">
                        <div class="well">
                            <div class="form-group">
                                <label for="id">ID: </label>
                                <form:input path="id" class="form-control" readonly="true" disabled="true" id="id_car"/>
                            </div>
                            <div class="form-group">
                                <label for="brand">Марка: </label>
                                <form:select path="brand.id" class="form-control" id="brand">
                                    <option value="0">Выберите марку</option>
                                    <c:forEach items="${brands}" var="b">
                                        <option value="${b.id}">${b.name}</option>
                                    </c:forEach>
                                </form:select>
                            </div>

                            <div class="form-group">
                                <label for="model">Модель: </label>
                                <form:select path="model.id" class="form-control" id="model">
                                    <option value="0" label="Выберите модель"/>
                                </form:select>
                            </div>

                            <div class="form-group">
                                <label for="volume">Объем двигателя: </label>
                                <form:input path="volume" class="form-control" id="volume"/>
                            </div>

                            <div class="form-group">
                                <label for="fuel">Тип топлива: </label>
                                <form:select path="fuel.id" class="form-control" id="fuel">
                                    <option value="0">Выберите топливо</option>
                                    <c:forEach items="${fuels}" var="f">
                                        <option value="${f.id}">${f.name}</option>
                                    </c:forEach>
                                </form:select>
                            </div>

                            <div class="form-group">
                                <label for="body">Кузов: </label>
                                <form:select path="body.id" class="form-control" id="body">
                                    <option value="0">Выберите кузов</option>
                                    <c:forEach items="${bodies}" var="body">
                                        <option value="${body.id}">${body.name}</option>
                                    </c:forEach>
                                </form:select>
                            </div>

                            <div class="form-group">
                                <label for="kpp">КПП: </label>
                                <form:select path="kpp.id" class="form-control" id="kpp">
                                    <option value="0">Выберите кузов</option>
                                    <c:forEach items="${kpps}" var="k">
                                        <option value="${k.id}">${k.name}</option>
                                    </c:forEach>
                                </form:select>
                            </div>

                            <div class="form-group">
                                <label for="year_from">Год с: </label>
                                <form:input path="year_from" class="form-control" id="year_from"/>
                            </div>

                            <div class="form-group">
                                <label for="year_to">Год по: </label>
                                <form:input path="year_to" class="form-control" id="year_to"/>
                            </div>
                        </div>
                    </form:form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                    <button type="button" class="btn btn-primary" onclick="saveCar()">Сохранить</button>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
