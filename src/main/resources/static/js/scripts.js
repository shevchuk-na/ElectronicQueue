function checkPasswordMatch() {
    var password = $("#txtNewPassword").val();
    var confirmPassword = $("#txtConfirmPassword").val();
    if (password == "" && confirmPassword == "") {
        $("#checkPasswordMatch").html("");
        $("#updateProfileButton").prop('disabled', false);
    } else {
        if (password != confirmPassword) {
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

// function sleep(ms) {
//     return new Promise(resolve => setTimeout(resolve, ms));
// }

$(document).ready(async function () {
        setInterval("getAjaxTicketUpdate()", 5000);
    }
)
;

function getAjaxTicketUpdate() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/ajax/getTicketUpdate",
        dataType: 'json',
        cache: false,
        timeout: 3000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            $('#userTicketList').bootstrapTable({
                data: data.result
            });
        },

    });

}