/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    populateCurrentPages();

    loadSortablePages();

    $('.sortable').nestedSortable({
        handle: 'div',
        items: 'li',
        toleranceElement: '> div',
        maxLevels: 2

    });

});

function populateCurrentPages() {
    var navSelect = $('#nav-index');
    navSelect.empty();
    $.ajax({
        type: 'GET',
        url: $('#page-context').data("context") + '/pages/parents'
    }).success(function (pages, status) {
        $.each(pages, function (index, page) {
            navSelect.append('<li data-display-index="' + page.displayIndex + '" id="listItem' + page.pageId + '" data-page-id="' + page.pageId + '">'
                    + page.pageName + '&nbsp;<input type="number" id="new-display-index' + page.pageId + '" class="admin-box" placeholder="' + page.displayIndex + '"/></li>');
        });
    }).error(function () {
        alert('oops');
    });
}

//function confirmNewOrder() { //for navbar
//    $.each(li, function (index, page) {
//    var li = $("ul#nav-index li");



//        $.ajax({
//            type: 'PUT',
//            url: $('#page-context').data("context") + '/page/' + $('#listItem' + page.pageId).data("page-id")
//        }).success(function (status) {
//            $('#new-display-index').val('');
//        });
//    }

//    $.ajax({
//        type: 'PUT',
//        url: $('#page-context').data("context") + '/page/' + $('#new-display-index').data("page-id")
//    }).success(function (status) {
//        $('#new-display-index').val('');
//    });
//}


function loadSortablePages() {
    var pageSort = $('#page-sort');
    pageSort.empty();

    $.ajax({
        type: 'GET',
        url: $('#page-context').data("context") + '/pages/all'
    }).success(function (pages, status) {
        $.each(pages, function (index, page) {
            if (page.parentId === 0) {
                var pageLink = '<li id="list_' + page.pageId + '"><div class="list-group-item">' + page.pageName + '</div></li>';
                pageSort.append(pageLink);
            } else {

                if (!$('#child-' + page.parentId).length) {
                    $('#list_' + page.parentId).append('<ol id="child-' + page.parentId + '"></ol>');
                }

                var pageLink = '<li id="list_' + page.pageId + '"><div class="list-group-item">' + page.pageName + '</div></li>';
                $('#child-' + page.parentId).append(pageLink);
            }
        });
    });
}

function submitPageOrder() {
    var pageSort = $('#page-sort');
    var pages = $('.sortable').nestedSortable('toArray');

    for (var i = 0; i < pages.length; i++) {
        $.ajax({
            type: 'PUT',
            url: $('#page-context').data("context") + '/page/index/' + pages[i].id,
            data: JSON.stringify({
                pageId: pages[i].id,
                pageName: null,
                pageContent: null,
                displayIndex: i,
                parentId: pages[i].parent_id
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            pageSort.append(pages[i].id);
            pageSort.append(pages[i].parent_id);
        });
    }

    alert("Looks good!");
}