/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    loadPage();
});

function loadPage() {
    clearPageTable();

    var pageTable = $('#main-content');

    $.ajax({
        type: 'GET',
        url: $('#page-context').data("context") + '/page/' + $('#page-id').data('page-id')
    }).success(function (page, status) {

        document.title = page.pageName + " | CMS";
        
        var currentPage = "";

        currentPage += '<div class="panel panel-default">';
        currentPage += '<div class="panel-heading"><h3><strong>' + page.pageName + '</strong></h3>';
        currentPage += '</div>';
        currentPage += '<div class="panel-body">';
        currentPage += '<p>' + page.pageContent + '</p>';
        currentPage += '</div>';
        currentPage += '</div>';
        currentPage += '</div>';

        $('#page-id').val(page.pageId);

        pageTable.append(currentPage);
    });
}

function clearPageTable() {
    $('#main-content').empty();
}