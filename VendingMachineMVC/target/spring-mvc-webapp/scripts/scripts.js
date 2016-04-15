/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(".item-sel").click(function () {
    var id = $(this).data("vend-id");
    var cost = parseFloat($(this).data("vend-cost"));
    $("#selection").val(id);
    $("#cost").val("$ " + cost.toFixed(2));
    $(".vend-menu").show();
});

var change = 0;
var currentTotal = 0;

$("span.addMoney").click(function () {
    change = parseFloat($(this).data("change-val"));
    currentTotal += change;
    $("#totalDeposit").val("$ " + currentTotal.toFixed(2));
});

$(".nav-toggle").click(function () {
    $(".vend-menu").toggle();
});

$("#product").hover(
        function () {
            $("#ilya-left").attr("src", "img/vendingmachiner.jpg");
            $("#ilya-right").attr("src", "img/vendingmachine.jpg");
            $("#das").show();
        },
        function () {
            $("#ilya-left").attr("src", "img/vendingmachine.jpg");
            $("#ilya-right").attr("src", "img/vendingmachiner.jpg");
            $("#das").hide();
});