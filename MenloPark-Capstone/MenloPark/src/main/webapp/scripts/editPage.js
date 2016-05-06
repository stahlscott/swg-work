/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var pageContext;

$(document).ready(function () {
    pageContext = $('#page-context').data("context");
    populatePages();
});
//chris' garbage - mind our mess
//$('#add-page-title').on("change paste keyup", function () {
//    checkFields();
//});
//$('#add-page-content').on("change paste keyup", function () {
//    checkFields();
//});


//function checkFields() {
////ignore this shit pls
//    if ($('#add-page-title').val !== '' && $('#add-page-content').val !== '') {
//        $('#add-edit-page').addClass("btn btn-primary enabled");
//        $('#add-edit-page').prop('disabled', false);
//
//    } else if ($('#add-page-title').val === '' && $('#add-page-content').val === '') {
//        $('#add-edit-page').addClass("btn btn-primary disabled");
//        $('#add-edit-page').prop('disabled', true);
//        return;
//    }
//}

function submitPage() {
//    checkFields();
//    var categoryArr = $("input[name='category']:checked").map(function () {
//        return this.value;
//    }).get();
//
//    var tagArr = $('.attached-tag').map(function () {
//        return $(this).data("tag-id");
//    }).get();
    $('#alertDiv').empty();

    var pageArr = $("input[name='page']:checked").data("parent-id");

    var errorCheck = false;

    if ($('#add-page-title').val() === '') {
        $('#alertDiv').append('<div class="alert alert-danger"><span class="glyphicon glyphicon-alert"></span><strong> Attention!</strong> Your post has not been submitted.'
                + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><br>Please be sure you provided a post title.</div>');
        $('#label-title').css("color", "red");
        errorCheck = true;
    }

    if (tinyMCE.activeEditor.getContent() === '') {
        $('#alertDiv').append('<div class="alert alert-danger"><span class="glyphicon glyphicon-alert"></span><strong> Attention!</strong> Your post has not been submitted.'
                + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><br>Please be sure you provided a post body.</div>');
        errorCheck = true;
    }

    if (errorCheck) {
        return;
    }

    $.ajax({
        type: 'POST',
        url: pageContext + '/editor/page',
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
        resetPage(true);
    }).error(function (data, status) {
        var errDiv = $('#alertDiv');
        errDiv.empty();

        $.each(data.responseJSON.fieldErrors, function (index, validationError) {
            errDiv.append('<div class="alert alert-danger"><span class="glyphicon glyphicon-alert"></span><strong> Attention!</strong> Your post has not been submitted.'
                    + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><br>'
                    + validationError.message + '</div>');
        });
    });
}

function populatePages() {
    $('#page-list').empty();
    $('#current-pages').empty();
    $.ajax({
        type: 'GET',
        url: pageContext + '/pages/all'
    }).success(function (data, status) {
        $.each(data, function (index, page) {
            if (page.parentId === 0) {
                $('#page-list').append('<input type="radio" name="page" id="pg-'
                        + page.pageId + '" data-parent-id="' + page.pageId + '" data-pg-name="' + page.pageName + '" value="'
                        + page.pageId + '"/>&nbsp;' + page.pageName + '<br>');
            }

            var editDeleteLink = '<dl><dt><a href="' + pageContext + '/page/display/' + page.pageId + '">' + page.pageName
                    + '</a></dt><dd><a onclick="insertPageLink(' + page.pageId + ', \'' + page.pageName + '\')">Insert Page Link</a>'
                    + ' | <span id="edit-page-index-' + page.pageId + '" data-display-index="' + page.displayIndex + '">'
                    + '<a onclick="generatePage(' + page.pageId + ')">Edit</a>'

            if ($('#role-admin').length) {
                editDeleteLink += ' | <a onclick="deletePage(' + page.pageId + ')">Delete</a>';
            }

            editDeleteLink += '</dd></dl>';
            $('#current-pages').append(editDeleteLink);

        });
    });
}


function clearPages() {
    clearRadioPage("page");
}

function insertPageLink(pageId, pageName) {
    tinyMCE.activeEditor.execCommand('mceInsertContent', false, ' <a href="' + pageId + '">' + pageName + '</a> ');
}

function clearRadioPage(pageName) {
    var ele = document.getElementsByName(pageName);
    for (var i = 0; i < ele.length; i++)
        ele[i].checked = false;
}

function deletePage(pageId) {
    if (confirm("Are you sure you want to delete this page?")) {
        $.ajax({
            type: 'DELETE',
            url: pageContext + '/admin/page/' + pageId
        }).success(function (status) {
            populatePages();
            populateNavbar();
        }).error(function (status) {
            alert("Cannot delete page. Check that it doesn't have child pages!");
        });
    }
}

function resetPage(skipConfirm) {
    if (skipConfirm || confirm('Are you sure you want to clear your current work?')) {
        tinyMCE.activeEditor.setContent('');
        $('#add-page-title').val('');
        $('#add-edit-page').text('Submit Page');
        $('#add-edit-page').attr('onclick', 'submitPage()');
        $("#page-list input:radio").attr('disabled', false);
    }
}

function generatePage(pageId) {
    resetPage(false);
    $.ajax({
        type: 'GET',
        url: $('#page-context').data("context") + '/page/' + pageId
    }).success(function (page, status) {
        tinyMCE.activeEditor.setContent(page.pageContent);
        $('#add-page-title').val(page.pageName);
        $('#add-edit-page').text('Edit Page');
        $('#add-edit-page').attr('onclick', 'editPage(' + page.pageId + ')');
        $("#page-list input:radio").attr('disabled', true);
        if (page.parentId > 0) {
            $('#pg-' + page.parentId).prop('checked', true);
        }
    });
}

function editPage(pageId) {

    $('#alertDiv').empty();

    var errorCheck = false;

    if ($('#add-page-title').val() === '') {
        $('#alertDiv').append('<div class="alert alert-danger"><span class="glyphicon glyphicon-alert"></span><strong> Attention!</strong> Your page has not been submitted.'
                + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><br>Please be sure you provided a page title.</div>');
        errorCheck = true;
    }

    if (tinyMCE.activeEditor.getContent() === '') {
        $('#alertDiv').append('<div class="alert alert-danger"><span class="glyphicon glyphicon-alert"></span><strong> Attention!</strong> Your page has not been submitted.'
                + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><br>Please be sure you provided a page body.</div>');
        errorCheck = true;
    }

    if (errorCheck) {
        return;
    }

    var pageArr = $("input[name='page']:checked").data("parent-id");
    var editIndex = $('#edit-page-index-' + pageId).data("display-index");

    $.ajax({
        type: 'PUT',
        url: $('#page-context').data("context") + '/editor/page/' + pageId,
        data: JSON.stringify({
            pageId: pageId,
            userId: 1,
            pageName: $('#add-page-title').val(),
            pageContent: tinyMCE.activeEditor.getContent(),
            displayIndex: editIndex, //not grabbing when it is a child. have to go back in and reassign it. :(
            parentId: pageArr
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        $('#alertDiv').html('<div class="alert alert-success"><span class="glyphicon glyphicon-ok"></span><strong> Success!</strong> Your page has been edited.'
                + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a></div>');
        resetPage(true);

    }).error(function (data, status) {
        var errDiv = $('#alertDiv');
        errDiv.empty();

        $.each(data.responseJSON.fieldErrors, function (index, validationError) {
            errDiv.append('<div class="alert alert-danger"><span class="glyphicon glyphicon-alert"></span><strong> Attention!</strong> Your post has not been submitted.'
                    + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><br>'
                    + validationError.message + '</div>');
        });
    });
}
