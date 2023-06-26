$(document).ready( ()=>{
    toastr.options = {
        "closeButton": true,
        "debug": false,
        "positionClass": "toast-bottom-right",
        "onclick": null,
        "fadeIn": 300,
        "fadeOut": 1000,
        "timeOut": 5000,
        "extendedTimeOut": 1000
    }
    loadGender();
    loadRole();
    loadStatus();
    loadUser();
    addUser();
    deleteUser();
    updateUser();


});

function loadGender(){
    $.get("/apiUser/ListGender",(data)=>{
        $(".gender").empty();
        $.each(data, (index, genders)=>{
            let genderOption = '<option value="' + genders.id + '">' + genders.name + '</option>';
            $(".gender").append(genderOption);
        });
    });

    $.get("/apiUser/ListGender",(data)=>{
        $(".gender1").empty();
        $.each(data, (index, genders)=>{
            let genderOption = '<option value="' + genders.id + '">' + genders.name + '</option>';
            $(".gender1").append(genderOption);
        });
    });
}

function loadStatus(){
    $.get("/apiUser/ListStatus",(data)=>{
        $(".status").empty();
        $.each(data, (index, status)=>{
            let statusOption = '<option value="' + status.id + '">' + status.name + '</option>';
            $(".status").append(statusOption);
        });
    });

    $.get("/apiUser/ListStatus",(data)=>{
        $(".status1").empty();
        $.each(data, (index, status)=>{
            let statusOption = '<option value="' + status.id + '">' + status.name + '</option>';
            $(".status1").append(statusOption);
        });
    });
}

function loadRole(){
    $.get("/apiRole/ListRole",(data)=>{
        $(".role").empty();
        $.each(data, (index, role)=>{
            let roleOption = '<option value="' + role.id + '">' + role.name + '</option>';
            $(".role").append(roleOption);
        });
    });

    $.get("/apiRole/ListRole",(data)=>{
        $(".role1").empty();
        $.each(data, (index, role)=>{
            let roleOption = '<option value="' + role.id + '">' + role.name + '</option>';
            $(".role1").append(roleOption);
        });
    });
}


function loadUser() {
    $.ajax({
        url: "/apiUser/ListUser",
        method: "GET",
        success: function (users){
            $("#lst-user").empty();
            users.forEach(function(user) {
                let UserHTML = "<tr>" +
                    "<td>" + user.id + "</td>" +
                    "<td>" + user.name + "</td>" +
                    "<td>" + user.username + "</td>" +
                    "<td>" + user.password + "</td>" +
                    "<td>" + user.phoneNumber + "</td>" +
                    "<td>" + user.address + "</td>" +
                    "<td>" + user.genders.name + "</td>" +
                    "<td>" + user.birthday + "</td>" +
                    "<td>" + user.status.name + "</td>" +
                    "<td>" + user.role.name + "</td>" +
                    "<td>" +
                    '<button class="btn btn-warning delete-btn" data-toggle="modal" data-target="#confirmModal" data-id="' + user.id + '"><i class="fa fa-trash"></i></button>' +
                    '<button class="btn btn-primary update-btn" data-toggle="modal" data-target="#userModal" data-id="' + user.id + '"><i class="fa fa-edit"></i></button>' +
                    "</td>" +
                    "</tr>";
                $("#lst-user").append(UserHTML);
            });
        },
        error: function (){
            alert("error");
        }
    });
}
function addUser() {
    $("#frmUser").submit(function (event) {
        event.preventDefault();
        let name =        $('#name').val();
        let username =    $('#username').val();
        let password =    $('#password').val();
        let phoneNumber = $('#phonenumber').val();
        let birthday =    $('#birthday').val();
        let gender =      $('.gender').val();
        let status =      $('.status').val();
        let address =     $('#address').val();
        let role = $('.role').val();
        let userData = {
            name: name,
            username: username,
            password: password,
            phoneNumber: phoneNumber,
            birthday: birthday,
            genders: gender,
            status: status,
            address: address,
            role : role,
        };
        $.ajax({
            url: "/apiUser/saveUser",
            method: "POST",
            data: userData,
            success: function (response){
                toastr.success(response);
                $('#name').val('');
                $('#username').val('');
                $('#password').val('');
                $('#phonenumber').val('');
                $('#birthday').val('');
                $('#address').val('');
                $('.gender').prop("selectedIndex",0);
                $('.status').prop("selectedIndex",0);
                $('.role').prop("selectedIndex",0);
                loadUser();
            },
            error: function (response){
                toastr.error(response);
            }
        })
    })
}
function deleteUser() {
    $(document).on("click",".delete-btn",function () {
        let UserId = $(this).attr('data-id');
        $("#confirmAction").off().on("click",function () {
            $.ajax({
                url: "/apiUser/deleteUser/" + UserId,
                method: "GET",
                success: function (response) {
                    toastr.success(response);
                    loadUser();
                },
                error: function (response) {
                    toastr.error(response);
                }
            });

        });
    });
}
function updateUser() {
    $(document).on("click",".update-btn",function () {
        let UserId = $(this).attr('data-id');
        $.ajax({
            url: "/apiUser/GetUserById/"+UserId,
            method: "GET",
            success: function (user) {
                $('#name1').val(user.name);
                $('#username1').val(user.username);
                $('#password1').val(user.password);
                $('#phonenumber1').val(user.phoneNumber);
                $('#birthday1').val(user.birthday);
                $('.gender1').val(user.genders.id).prop("selected", true);
                $('.status1').val(user.status.id).prop("selected", true);
                $('#address1').val(user.address);
                $('.role1').val(user.role.id).prop("selected", true);
            },
            error: function (){
                alert("error");
            },
        });

        $("#update").off().on("click",function () {
            let name1 =        $('#name1').val();
            let username1 =    $('#username1').val();
            let password1 =    $('#password1').val();
            let phoneNumber1 = $('#phonenumber1').val();
            let birthday1 =    $('#birthday1').val();
            let gender1 =      $('.gender1').val();
            let status1 =      $('.status1').val();
            let address1 =     $('#address1').val();
            let role1 =        $('.role1').val();
            let userData = {
                name: name1,
                username: username1,
                password: password1,
                phoneNumber: phoneNumber1,
                birthday: birthday1,
                genders: gender1,
                status: status1,
                address: address1,
                role: role1
            };
            $.ajax({
                url: "/apiUser/updateUser/"+UserId,
                method: "PUT",
                data: userData,
                success: function (response) {
                    toastr.success(response);
                    loadUser();
                },
                error: function (response) {
                    toastr.error(response);
                }
            });
        });

    });
}
