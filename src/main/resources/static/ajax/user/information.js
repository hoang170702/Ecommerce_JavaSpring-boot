$(document).ready(function() {
    toastr.options = {
        "closeButton": true,
        "debug": false,
        "positionClass": "toast-bottom-right",
        "onclick": null,
        "fadeIn": 300,
        "fadeOut": 1000,
        "timeOut": 3000,
        "extendedTimeOut": 1000
    }

    $('#change-password-form').submit(function(e) {
        e.preventDefault();
        var currentUsername = $('#current-username').val();
        var currentPassword = $('#current-password').val();
        var newPassword = $('#new-password').val();
        var confirmPassword = $('#confirm-password').val();
        if (newPassword !== confirmPassword) {
            toastr.error('Mật khẩu mới và xác nhận mật khẩu không khớp!');
            return;
        }
        var requestData = {
            userName: currentUsername,
            password: currentPassword,
            changePassword: newPassword
        };

        $.ajax({
            type: 'PUT',
            url: '/apiUser/changeInformUser',
            data: requestData,
            success: function(response) {
                toastr.success(response);
                const url = "/";
                window.location.href = url;
                $('#current-username').val('');
                $('#current-password').val('');
                $('#new-password').val('');
                $('#confirm-password').val('');
            },
            error: function(xhr, status, error) {
                toastr.success('Đã xảy ra lỗi: ' + error);
            }
        });
    });
});