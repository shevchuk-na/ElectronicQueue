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

$(document).ready(async function () {
        setInterval("getAjaxTicketUpdate()", 5000);
    }
)
;

function getAjaxTicketUpdate() {

    var date_options = {
        day: 'numeric',
        month: 'long',
        year: 'numeric',
        hour: 'numeric',
        minute: 'numeric',
        second: 'numeric'
    };

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/ajax/getTicketUpdate",
        dataType: 'json',
        cache: false,
        timeout: 3000,
        success: function (data) {
            let i = 0;
            let tbl_body = "";
            $.each(data.result, function () {
                let tbl_row = "";
                $.each(this, function (k, v) {
                    if (k != "id") {
                        if (k == "created") {
                            let date = new Date(v)
                            let date_string = date.toLocaleString("ru", date_options);
                            date_string = date_string.replace(',', '');
                            tbl_row += "<td>" + date_string + "</td>";
                        } else {
                            tbl_row += "<td>" + v + "</td>";
                        }
                    }
                })
                switch (data.result[i]['place']) {
                    case 1:
                        tbl_body += "<tr class=\"table-success\" onclick=\"javascript:queueRowClicked(\'" + data.result[i]['id'] + "\');\">" + tbl_row + "</tr>";
                        break;
                    case 2:
                        tbl_body += "<tr class=\"table-success\" onclick=\"javascript:queueRowClicked(\'" + data.result[i]['id'] + "\');\">" + tbl_row + "</tr>";
                        break;
                    case 3:
                        tbl_body += "<tr class=\"table-warning\" onclick=\"javascript:queueRowClicked(\'" + data.result[i]['id'] + "\');\">" + tbl_row + "</tr>";
                        break;
                    case 4:
                        tbl_body += "<tr class=\"table-warning\" onclick=\"javascript:queueRowClicked(\'" + data.result[i]['id'] + "\');\">" + tbl_row + "</tr>";
                        break;
                    case 5:
                        tbl_body += "<tr class=\"table-warning\" onclick=\"javascript:queueRowClicked(\'" + data.result[i]['id'] + "\');\">" + tbl_row + "</tr>";
                        break;
                    default:
                        tbl_body += "<tr onclick=\"javascript:queueRowClicked(\'" + data.result[i]['id'] + "\');\">" + tbl_row + "</tr>";
                        break;
                }
                i++;
            });
            $("#userTicketList").find("tbody").html(tbl_body);

        },

    });

}