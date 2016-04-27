/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    populatePage();

});

function submitPage() {

//    var categoryArr = $("input[name='category']:checked").map(function () {
//        return this.value;
//    }).get();
//
//    var tagArr = $('.attached-tag').map(function () {
//        return $(this).data("tag-id");
//    }).get();

var pageArr = $("input[name='page']:checked").data("parent-id");
    //TODO add validation on title and content
    
    $.ajax({
        type: 'POST',
        url: $('#page-context').data("context") + '/page',
        data: JSON.stringify({
            userId: 1,
            pageName: $('#add-page-title').val(),
            pageContent: tinyMCE.activeEditor.getContent(),
            displayIndex: 0,
            parentId: pageArr
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {

        $('#alertDiv').html('<div class="alert alert-success"><span class="glyphicon glyphicon-ok"></span><strong> Success!</strong> Your page has been created.'
                + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a></div>');
        tinyMCE.activeEditor.setContent('');
        $('#add-page-title').val('');

    }).error(function (data, status) {
        $('#alertDiv').html('<div class="alert alert-danger"><span class="glyphicon glyphicon-alert"></span><strong> Attention!</strong> Your page has not been submitted.'
                + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><br>Errors abound.</div>');
    });
}

function populatePage() {
    $.ajax({
        type: 'GET',
        url: $('#page-context').data("context") + '/pages/all'
    }).success(function (data, status) {
        $.each(data, function (index, page) {
            $('#page-list').append('<input type="radio" name="page" id="pg"' + page.pageId + '" data-parent-id="' + page.pageId + '" data-pg-name="' + page.pageName + '" value="' + page.pageId +
                    '"/>&nbsp;' + page.pageName + '&nbsp;:&nbsp;<a onclick="deletePage(' + page.pageId + ')">Delete</a> | <a onclick="editPage(' + page.pageId + ')">Edit</a><br>');
        });
    });
}
function clearPages()
{
    clearRadioPage("page");
}

function insertPageLink(pageId, pageName) {
    tinyMCE.activeEditor.execCommand('mceInsertContent', false, ' <a href="' + pageId + '">' + pageName + '</a> ');
}

function clearRadioPage(PageName)
{
    var ele = document.getElementsByName(PageName);
    for (var i = 0; i < ele.length; i++)
        ele[i].checked = false;
}
