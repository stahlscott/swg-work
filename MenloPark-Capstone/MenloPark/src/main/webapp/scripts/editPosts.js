/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    if ($('#add-post-title').val !== '' && $('#add-post-content').val !== '') {
        $('#add-edit-post').removeClass("disabled").addClass("enabled");
    }

    populateCategories();
    $('#post-date-picker').datepicker();
    $('#exp-date-picker').datepicker();
});

function submitPost() {

    $('#alertDiv').empty();

    var errorCheck = false;

    if ($('#add-post-title').val() === '') {
        $('#alertDiv').append('<div class="alert alert-danger"><span class="glyphicon glyphicon-alert"></span><strong> Attention!</strong> Your post has not been submitted.'
                + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><br>Please be sure you provided a post title.</div>');
        errorCheck = true;
    }

    if (tinyMCE.activeEditor.getContent() === '') {
        $('#alertDiv').append('<div class="alert alert-danger"><span class="glyphicon glyphicon-alert"></span><strong> Attention!</strong> Your post has not been submitted.'
                + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><br>Please be sure you provided a post body.</div>');
        errorCheck = true;
    }

    if ($('#post-date-picker').val() !== '') {
        try {
            var postDate = $.datepicker.parseDate("mm/dd/yy", $('#post-date-picker').val());
            var postDateText = $.datepicker.formatDate("yy-mm-dd", postDate) + 'T00:00:00';
        } catch (err) {
            $('#alertDiv').append('<div class="alert alert-danger"><span class="glyphicon glyphicon-alert"></span><strong> Bad Date! Been there... </strong> Your post has not been submitted.'
                    + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><br>Please be sure your Post Date is formatted with MM/DD/YYYY. </div>');
            errorCheck = true;
        }
    }

    if ($('#exp-date-picker').val() !== '') {
        try {
            var expDate = $.datepicker.parseDate("mm/dd/yy", $('#exp-date-picker').val());
            var expDateText = $.datepicker.formatDate("yy-mm-dd", expDate) + 'T00:00:00';
        } catch (err) {
            $('#alertDiv').append('<div class="alert alert-danger"><span class="glyphicon glyphicon-alert"></span><strong> Bad Date! Been there... </strong> Your post has not been submitted.'
                    + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><br>Please be sure your Expiration Date is formatted with MM/DD/YYYY. </div>');
            errorCheck = true;
        }
    }

    if (errorCheck) {
        return;
    }

    var categoryArr = $("input[name='category']:checked").map(function () {
        return this.value;
    }).get();

    var tagArr = $('.attached-tag').map(function () {
        return $(this).data("tag-id");
    }).get();

    $.ajax({
        type: 'POST',
        url: $('#page-context').data("context") + '/post',
        data: JSON.stringify({
            userId: 1,
            postTitle: $('#add-post-title').val(),
            postDateTime: postDateText,
            expDateTime: expDateText,
            postContent: tinyMCE.activeEditor.getContent(),
            flaggedForReview: 'false',
            draft: 'false',
            editorComments: '',
            tagIds: tagArr, //$('#tag-box').text().split(","),
            categoryIds: categoryArr,
            tagNames: [],
            categoryNames: []
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {

        $('#alertDiv').html('<div class="alert alert-success"><span class="glyphicon glyphicon-ok"></span><strong> Success!</strong> Your blog has been updated.'
                + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a></div>');
        tinyMCE.activeEditor.setContent('');
        $('#add-post-title').val('');
        $('#tag-box').text('');
        clearCategories();
        populateCategories();
        clearTags();

    }).error(function (data, status) {
        $('#alertDiv').html('<div class="alert alert-danger"><span class="glyphicon glyphicon-alert"></span><strong> Attention!</strong> Your post has not been submitted.'
                + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><br>Errors abound.</div>');
    });
}


function addTags() {
    var input = $('#tag-box-input').val();
    var tagArray = input.split(", ");
    tagArray.forEach(getAndDisplayTag);
    $('#tag-box-input').val('');
}

function clearTags() {
    $('#tag-box').empty();
}

function clearTag(tagId) {
    $('#tag' + tagId).remove();
}

function getAndDisplayTag(tag, index) {
    $.ajax({
        type: 'POST',
        url: 'tag',
        data: JSON.stringify({
            name: tag
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (tag, status) {
        if (!$('#tag' + tag.tagId).length) {
            $('#tag-box').append('<span class="attached-tag" id="tag' + tag.tagId + '" data-tag-id="' + tag.tagId + '">'
                    + '<span class="label label-default">#' + tag.name + ' '
                    + '<span onclick="clearTag(' + tag.tagId + ')">&times;</span>'
                    + '</span>&nbsp;</span>');
        }
    });
}

function populateCategories() {
    $.ajax({
        type: 'GET',
        url: 'categories/all'
    }).success(function (data, status) {
        $.each(data, function (index, cat) {
            $('#categories-list').append('<input type="checkbox" name="category" id="cat' + cat.categoryId + '" data-cat-name="' + cat.name + '" value="' + cat.categoryId
                    + '"/>&nbsp;' + cat.name + '&nbsp;:&nbsp;<a onclick="deleteCategory(' + cat.categoryId + ')">Delete</a> | <a onclick="editCategory(' + cat.categoryId + ')">Edit</a><br>');
        });
    });
}

function addCategory() {
    var input = $('#category-add-box').val();

    $.ajax({
        type: 'POST',
        url: 'category',
        data: JSON.stringify({
            name: input
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'datatype': 'json'
    }).success(function (data, status) {
        clearCategories();
        populateCategories();
        $('#category-add-box').val('');
    }).error(function (data, status) {
        alert("Error! Category has not been added.");
    });
}

function deleteCategory(id) {
    var ask = confirm("Are you sure you want to delete this category?");
    if (ask) {
        $.ajax({
            type: 'DELETE',
            url: 'category/' + id
        }).success(function (data, status) {
            clearCategories();
            populateCategories();
        }).error(function (data, status) {
            alert("Sorry, cannot delete category. This category likely has posts attached. ");
        });
    }
}

function clearCategories() {
    $('#categories-list').empty();
}

function editCategory(catId) {
    var currentName = $('#cat' + catId).data("cat-name");
    var newName = prompt('Please enter new name for "' + currentName + '"', currentName);

    if (newName !== "" && newName !== currentName) { //newName from prompt; if doesn't match blank or the old name
        $.ajax({
            type: 'PUT',
            url: 'category/' + catId,
            data: JSON.stringify({
                name: newName,
                categoryId: catId
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'datatype': 'json'
        }).success(function (data, status) {
            clearCategories();
            populateCategories();
        }).error(function (data, status) {
            alert("Error! Category name not changed.");
        });
    }
}


