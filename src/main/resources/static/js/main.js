var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(function () {
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

$(document).ready(function () {
    $(document).on("click", ".btn-complete-todo", function () {
        var div_id = $(this).closest("div").attr('id');
        var is_done;
        var a = $(this).closest("a").remove();

        if (div_id === "todo-list") {
            $("#completed-todo-list").append(a);
            is_done = "true";
        } else {
            $("#todo-list").append(a);
            is_done = "false";
        }

        $.ajax({
            type: "POST",
            url: "/todo/" + $(this).data("id") + "/do",
            data: {
                'isDone': is_done
            }
        });
    });
});

$("#btn-showing-completed-todo-list").click(function () {
    var completed_todo_list = $("#completed-todo-list");
    if (completed_todo_list.is(":visible"))
        completed_todo_list.hide();
    else
        completed_todo_list.show();
});

$(document).ready(function () {
    $('table tr').click(function () {
        var id = $(this).closest("tr").attr('value');
        location.href = "todo/" + id;
    });
});