$(document).ready(()=>{
    toastr.options = {
        "closeButton": true,
        "debug": false,
        "positionClass": "toast-bottom-right",
        "onclick": null,
        "fadeIn": 300,
        "fadeOut": 1000,
        "timeOut": 10000,
        "extendedTimeOut": 1000
    }

    loadGender();
    register();
    login();
    setTimeout(function() {
        $('#loading-icon').show();
    }, 20000);
});

function loadGender(){
    $.get("/apiUser/ListGender",(data)=>{
        $(".gender").empty();
        $.each(data, (index, genders)=>{
            let genderOption = '<option value="' + genders.id + '">' + genders.name + '</option>';
            $(".gender").append(genderOption);
        });
    });
}

function register(){
    $("#register").off().on("click",function (event) {
        event.preventDefault();
        let name =        $('#name').val();
        let username =    $('#username').val();
        let password =    $('#password').val();
        let phoneNumber = $('#phonenumber').val();
        let birthday =    $('#birthday').val();
        let gender =      $('.gender').val();
        let address =     $('#address').val();
        let email =       $('#email').val();
        let status =  "2"    ;
        let role = "1";
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
            email: email,
        };
        $.ajax({
            url: "/apiUser/saveUser",
            method: "POST",
            data: userData,
            success: function (){
                toastr.success("Check Your Email to register");
                $('#name').val('');
                $('#username').val('');
                $('#password').val('');
                $('#phonenumber').val('');
                $('#birthday').val('');
                $('#address').val('');
                $('.gender').prop("selectedIndex",0);
            },
            error: function (){
                toastr.error("Register Failed");
            }
        })
    })
}

function login() {
    const urlUser = "/";
    const urlAdmin = "/admin";
    $("#NameUser").empty();
    $("#login").submit(function (event) {
        event.preventDefault();
        $("#loading-icon").addClass("fa fa-spinner fa-spin");
        let userName = $("#usernameli").val();
        let password = $("#passwordli").val();
        $.ajax({
            url: "/apiUser/userLogin",
            method: "POST",
            data: {
                userName : userName,
                password: password,
            },
            success: function (response) {
                $("#loading-icon").removeClass("fa fa-spinner fa-spin");
                $.ajax({
                    url: "/apiUser/GetSessionUser",
                    method: "GET",
                    success: function (data) {
                        if(data.role.id === 1){
                            window.location.href = urlUser;
                        }else if(data.role.id === 2){
                            window.location.href = urlAdmin;
                        }
                    }
                });
                toastr.success(response);
            },
            error: function (response) {
                $("#loading-icon").removeClass("fa fa-spinner fa-spin");
                toastr.error(response);
            }
        });
    })
}
