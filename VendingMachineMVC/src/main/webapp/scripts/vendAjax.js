/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var change = 0;
var currentTotal = 0;

$(document).ready(function () {
    loadItems();

    $(".addMoney").click(function () {
        resetErrMsgsCost();
        change = parseFloat($(this).data("change-val"));
        currentTotal += change;
        $("#totalDeposit").val("$ " + currentTotal.toFixed(2));
    });

    $(".nav-toggle").click(function () {
        $(".vend-menu").toggle();
    });
});

function loadItems() {
    clearItemTable();
    clearDepositAmt();

    var iTable = $('#contentRows');

    $.ajax({
        type: 'GET',
        url: 'items'
    }).success(function (data, status) {
        $.each(data, function (index, item) {
            var row = "";

            if (index % 3 === 2) {
                row += '<div class="row">';
            }

            row += '<div class="col-md-4 text-center">';
            row += '<div class="panel panel-default">';
            row += '<div class="panel-heading">' + item.name + '</div>';
            row += '<div class="panel-body">';
            row += '<div class="row">';
            row += '<div class="col-xs-6">';
            row += '<b>$' + item.cost.toFixed(2) + '</b> each <br/>';
            row += '<span class="itemQty" id="itemQty' + item.position + '">qty: [' + item.quantity + ']</span>';
            row += '<span class="errMsg errMsgCost digitalText" id="errMsgCost' + item.position + '" style="display:none">INSERT COINS</span><br/><br/>';
            row += '<span class="btn btn-default item-sel" id="' + item.position + '" onclick="vendItem(this)" data-vend-id="' + item.position +
                    '" data-vend-cost="' + item.cost + '" data-vend-qty="' + item.quantity + '">' + item.position + '</span>';
            row += '<span class="errMsg errMsgQty digitalText" id="errMsgQty' + item.position + '" style="display:none">OUT OF STOCK</span>';
            row += '</div>';
            row += '<div class="col-xs-6"><img src="' + item.imgUrl + '" width="100"/>';
            row += '</div>';
            row += '</div>';
            row += '</div>';
            row += '</div>';
            row += '</div>';

            if (index % 3 === 0) {
                row += '</div>';
            }
            iTable.append(row);
        });
    });
}

function loadItemQty(itemPos) {

    $.ajax({
        type: 'GET',
        url: 'item/' + itemPos
    }).success(function (item, status) {
        $('#itemQty' + itemPos).text('qty: [' + item.quantity + ']');
        $('#' + itemPos).data('vend-qty', item.quantity);
    });
}

function clearItemTable() {
    $('#contentRows').empty();
}

function clearDepositAmt() {
    $('#totalDeposit').val('');
    change = 0;
    currentTotal = 0;
}

function resetErrMsgsCost() {
    $('.errMsgCost').hide();
    $('.itemQty').show();
}

function toggleErrMsgCost(itemPosition) {
    $('#errMsgCost' + itemPosition).toggle();
    $('#itemQty' + itemPosition).toggle();
}

function toggleErrMsgQty(itemPosition) {
    $('#errMsgQty' + itemPosition).toggle();
    $('#' + itemPosition).toggle();
}

function coinReturn() {
    var deposited = $('#totalDeposit').val().substring(2);
    var vTable = $('#vend-result');
//    vTable.empty();

    $.ajax({
        type: 'GET',
        url: 'coinReturn/' + deposited
    }).success(function (result, status) {
        clearDepositAmt();
        var txt = "";
//        txt += ('<div class="panel panel-default"><div class="panel panel-body">');
        txt += ('<h4>Coins Returned</h4><br>');
        txt += ('Quarters: ' + result.quarters + '<br>');
        txt += ('Dimes: ' + result.dimes + '<br>');
        txt += ('Nickels: ' + result.nickels);
//        txt += ('</div></div>');
        vTable.html(txt);
    });
}

function vendItem(item) {
    var deposited = $('#totalDeposit').val().substring(2);

    var itemPos = $(item).data("vend-id");
    var itemCost = parseFloat($(item).data("vend-cost"));
    var itemQty = parseInt($(item).data("vend-qty"));

    var vTable = $('#vend-result');
//    vTable.empty();

    if (itemQty <= 0) {
        resetErrMsgsCost();
        toggleErrMsgQty(itemPos);
        vTable.html('<br/><br/><br/><br/><br/><br/>');
    } else if (deposited < itemCost) {
        resetErrMsgsCost();
        toggleErrMsgCost(itemPos);
        vTable.html('<br/><br/><br/><br/><br/><br/>');
        $(".vend-menu").show();
    } else {
        $("#selection").val(itemPos);
        $("#cost").val("$ " + itemCost.toFixed(2));
        $(".vend-menu").show();
        $.ajax({
            type: 'PUT',
            url: 'item/' + itemPos + '/' + deposited
        }).success(function (result, status) {

            if (result.error) {
                resetErrMsgsCost();
                toggleErrMsgCost(itemPos);
            } else {
                loadItemQty(itemPos);
                clearDepositAmt();
                var txt = "";
//                txt += ('<div class="panel panel-default"><div class="panel panel-body">');
                txt += ('<h4>Enjoy your nice, cold ' + result.name + '!!</h4><br>');
                txt += ('Quarters: ' + result.quarters + '<br>');
                txt += ('Dimes: ' + result.dimes + '<br>');
                txt += ('Nickels: ' + result.nickels);
//                txt += ('</div></div>');
                vTable.html(txt);
            }
        });
    }
}