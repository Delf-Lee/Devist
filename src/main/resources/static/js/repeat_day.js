$(document).ready(function () {
    var checkedId = $("#id-fixed-or-flexible").val();
    toggleRepeatDay(checkedId);

    if (checkedId === "FIXED")
        $("#btn-fixed").addClass('active');
    else if (checkedId === "FLEXIBLE")
        $("#btn-flexible").addClass('active');
});

$("input[name=repeat-day]").change(function () {
    var checkedId = $(this).val();
    $("#id-fixed-or-flexible").val(checkedId);

    toggleRepeatDay(checkedId);
});

function toggleRepeatDay(checkedId) {
    if (checkedId === "FIXED") {
        $("#div-fixedrepeatday").show();
        $("#div-flexiblerepeatday").hide();
    }
    else if (checkedId === "FLEXIBLE") {
        $("#div-fixedrepeatday").hide();
        $("#div-flexiblerepeatday").show();
    }
}