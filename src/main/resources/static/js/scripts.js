function checkPasswordMatch() {
    var password = $("#txtNewPassword").val();
    var confirmPassword = $("#txtConfirmPassword").val();
    if(password == "" && confirmPassword == ""){
        $("#checkPasswordMatch").html("");
        $("#updateProfileButton").prop('disabled', false);
    } else {
        if(password != confirmPassword){
            $("#checkPasswordMatch").html("Passwords do not match!");
            $("#updateProfileButton").prop('disabled', true);
        } else {
            $("#checkPasswordMatch").html("Passwords match!");
            $("#updateProfileButton").prop('disabled', false);
        }
    }
}

function queueRowClicked(value) {
    location.href = "/queues/queue?id=" + value;
}