/**
 * Created by Admin on 22.04.2015.
 */
function saveAddress() {
    var country = $("#country").val();
    var city = $("#city").val();
    var address = $("#address").val();

    $.ajax({
        url: "/contact/save_address",
        data: "country=" + country + "&city=" + city + "&address=" + address,
        type: "POST",
        success: function (result) {
        },
        error: function (e) {
        }
    })
}

function savePhone() {
    var phone = $("#phone").val();

    $.ajax({
        url: "/contact/save_phone",
        data: "phone=" + phone,
        type: "POST",
        success: function (result) {
        },
        error: function (e) {
        }
    })
}

function counterParts(car_id, count) {
    var current_count = parseInt($('span#partCount_' + car_id).text());

    if(count == 1) {
        $('span#partCount_' + car_id).text(current_count+1);
    } else {
        $('span#partCount_' + car_id).text(current_count-1);
    }
}

function loadParts(car_id) {
    $.ajax({
        url: "/parts/car/" + car_id,
        type: "POST",
        dataType: "json",
        success: function (parts) {
            $("tr#tr" + car_id).remove();
            var tr = '';
            console.log(parts);
            for (var i = 0; i < parts.length; i++) {
                tr += '<tr id=tr' + car_id + '>' +
                '<td>' + parts[i].id + '</td>' +
                '<td>' + '<img src="/image/get/' + parts[i].photos[0].id + '" width="100" height="100">' + '</td>' +
                '<td>' + parts[i].group.name + '</td>' +
                '<td>' + parts[i].type.name + '</td>' +
                '<td>' + parts[i].condition + '</td>' +
                '<td>' + parts[i].description + '</td>' +
                '<td>' + parts[i].price + '</td>' +
                '<td>' + parts[i].date.dayOfMonth + '.' +
                parts[i].date.monthOfYear + '.' +
                parts[i].date.year + '</td>' +
                '<td>' + '<button class="btn btn-warning" onclick="editPart(' + parts[i].id +
                ')" data-toggle="modal" data-target="#editPartModal"><span class="glyphicon glyphicon-pencil"></span></button>' +
                '<button class="btn btn-danger" onclick="deletePart(' + parts[i].id +
                ')"><span class="glyphicon glyphicon-trash"></span></button>' + '</td>'
                + '</tr>';
            }
            $("table#partTable" + car_id).append(tr);
        },
        error: function (e) {
        }
    })
}

function editPart(part_id) {
    $.ajax({
        url: "/parts/get/" + part_id,
        type: "POST",
        dataType: "json",
        success: function (part) {
            $('input#id').val(part.id);
            $('select#group').val(part.group.id);
            loadPartType();
            $('select#type').val(part.type.id);
            $('select#condition').val(part.condition);
            $('input#price').val(part.price);
            $('input#catalogNumber').val(part.catalogNumber);
            $('textarea#description').val(part.description);
        },
        error: function (e) {

        }
    })
}

function savePart() {
    var data = $('#editPartForm').serialize();
    $.ajax({
        type: "post",
        data: data,
        url: "part/edit/" + $('input#id').val(),
        success: function (car_id) {
            loadParts(car_id);
            $('#editPartModal').modal('hide');
            //alert("success");
        },
        error: function (e) {

        }
    });
}

function addPart() {
    var data = $('#addPartForm').serialize();
    $.ajax({
        type: "post",
        data: data,
        url: "part/add",
        success: function (car_id) {
            loadParts(car_id);
            counterParts(car_id, 1);
            $('#addPartModal').modal('hide');
        },
        error: function (e) {

        }
    });
}

function updateCar(car) {
    var id = car.id;
    $('span#car_name_' + id).text(car.brand.name + ' ' + car.model.name);
    $('small#car_year_' + id).text(car.year_from + '-' + car.year_to);
    $('dd#car_fuel_' + id).text(car.fuel.name);
    $('dd#car_volume_' + id).text(car.volume);
    $('dd#car_body_' + id).text(car.body.name);
    $('dd#car_kpp_' + id).text(car.kpp.name);
}

function saveCar() {
    var data = $('#editCarForm').serialize();
    $.ajax({
        type: "post",
        data: data,
        url: "/car/edit/" + $('input#id_car').val(),
        dataType: "json",
        success: function (car) {
            updateCar(car);
            $('#editCarModal').modal('hide');
            //alert("success");
        },
        error: function (e) {

        }
    });
}

function editCar(car_id) {
    $.ajax({
        url: "/car/get/" + car_id,
        type: "POST",
        dataType: "json",
        success: function (car) {
            $('input#id_car').val(car.id);
            $('select#brand').val(car.brand.id);
            loadCarModel();
            $('select#model').val(car.model.id);
            $('input#volume').val(car.volume);
            $('select#fuel').val(car.fuel.id);
            $('select#body').val(car.body.id);
            $('select#kpp').val(car.kpp.id);
            $('input#year_from').val(car.year_from);
            $('input#year_to').val(car.year_to);
        },
        error: function (e) {

        }
    })
}

function deletePart(part_id) {
    if (confirm('Удалить запчасть?')) {
        $.ajax({
            url: "/parts/delete/" + part_id,
            type: "POST",
            dataType: "json",
            success: function (car_id) {
                loadParts(car_id);
                counterParts(car_id, -1);
            },
            error: function (e) {

            }
        })
    }
}

$(function () {
    $("#brand").change(function () {
        loadCarModel();
        //$.ajax({
        //    url: "/car/models",
        //    data: 'brandId=' + $("#brand").val(),
        //    type: "POST",
        //    dataType: "json",
        //    success: function (models) {
        //        var options = '';
        //        for (var i = 0; i < models.length; i++) {
        //            options += '<option value="' + models[i].id + '">' + models[i].name + '</option>';
        //        }
        //        $("select#model").html(options);
        //    },
        //    error: function (e) {
        //
        //    }
        //})
    })
});

$(function () {
    $("#group").change(function () {
        loadPartType();
    })
});

function loadPartType() {
    $.ajax({
        url: "/car/part_types",
        data: 'groupId=' + $("#group").val(),
        type: "POST",
        dataType: "json",
        success: function (partTypes) {
            var options = '';
            for (var i = 0; i < partTypes.length; i++) {
                options += '<option value="' + partTypes[i].id + '">' + partTypes[i].name + '</option>';
            }
            $("select#type").html(options);
        },
        error: function (e) {

        }
    })
}

function loadCarModel() {
    $.ajax({
        url: "/car/models",
        data: 'brandId=' + $("#brand").val(),
        type: "POST",
        dataType: "json",
        success: function (models) {
            var options = '';
            for (var i = 0; i < models.length; i++) {
                options += '<option value="' + models[i].id + '">' + models[i].name + '</option>';
            }
            $("select#model").html(options);
        },
        error: function (e) {

        }
    })
}

//
//$(function () {
//    $("#brand").change(function () {
//
//        $.ajax({
//            url: "/car/models",
//            data: 'brandId=' + $("#brand").val(),
//            type: "POST",
//            dataType: "json",
//            success: function (models) {
//                var options = '';
//                for (var i = 0; i < models.length; i++) {
//                    options += '<option value="' + models[i].id + '">' + models[i].name + '</option>';
//                }
//                $("select#model").html(options);
//            },
//            error: function (e) {
//
//            }
//        })
//    })
//});
//
//$(function () {
//    $("#group").change(function () {
//
//        $.ajax({
//            url: "/car/part_types",
//            data: 'groupId=' + $("#group").val(),
//            type: "POST",
//            dataType: "json",
//            success: function (partTypes) {
//                var options = '';
//                for (var i = 0; i < partTypes.length; i++) {
//                    options += '<option value="' + partTypes[i].id + '">' + partTypes[i].name + '</option>';
//                }
//                $("select#type").html(options);
//            },
//            error: function (e) {
//
//            }
//        })
//    })
//});

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
    switch (step) {
        case 1:
            $("#step-1").hide();
            $("#step-2").show();
            showName();
            reactivateLiTitle(step, step + 1)
            break;

        case 2:
            $("#step-2").hide();
            $("#step-3").show();
            reactivateLiTitle(step, step + 1)
            break;

        case 3:
            submitRegistration();
            break;
    }
}

function backStep(step) {
    switch (step) {
        case 1:
            window.location = '/';
            break;

        case 2:
            $("#step-2").hide();
            $("#step-1").show();
            reactivateLiTitle(step, step - 1)
            break;

        case 3:
            $("#step-3").hide();
            $("#step-2").show();
            reactivateLiTitle(step, step - 1)
            break;

        case 4:
            $("#step-4").hide();
            $("#step-3").show();
            reactivateLiTitle(step, step - 1)
            break;
    }
}

function reactivateLiTitle(deact, act) {
    $("#st" + deact).removeClass("active");
    $("#st" + act).addClass("active");
}

function showName() {
    if ($(".accountType:checked").val() == 'CUSTOMER') {
        $("#nameInput").hide();
    } else {
        $("#nameInput").show();
    }
}