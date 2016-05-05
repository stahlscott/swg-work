/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var pageContext;

$(document).ready(function () {
    populateCategories();
    getTags();
    $('#post-date-picker').datepicker();
    $('#exp-date-picker').datepicker();

    pageContext = $('#page-context').data("context");
});

// <editor-fold desc="SUBMIT POST function">

function submitPost() {

    $('#alertDiv').empty();

    var errorCheck = false;

    if ($('#add-post-title').val() === '') {
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

    if ($('#post-date-picker').val() !== '') {
        try {
            var postDate = $.datepicker.parseDate("mm/dd/yy", $('#post-date-picker').val());
            var postDateText = $.datepicker.formatDate("yy-mm-dd", postDate) + 'T00:00:00';
        } catch (err) {
            $('#alertDiv').append('<div class="alert alert-danger"><span class="glyphicon glyphicon-alert"></span><strong> Bad Date! Been there... </strong> Your post has not been submitted.'
                    + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><br>Please be sure your Post Date is formatted with MM/DD/YYYY. </div>');
            $('#label-post-date').css("color", "red");
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
            $('#label-exp-date').css("color", "red");
            errorCheck = true;
        }
    }

    if (errorCheck) {
        return;
    }

    var flagForReview;

    var editorComments = '';

    if ($('#role-admin').length && !$('#flagged').prop('checked')) {

        flagForReview = false;
    } else {
        flagForReview = true;
        editorComments = $('#editor-comments-text').val();
    }

    var categoryArr = $("input[name='category']:checked").map(function () {
        return this.value;
    }).get();

    var tagArr = $('.attached-tag').map(function () {
        return $(this).data("tag-id");
    }).get();

    $.ajax({
        type: 'POST',
        url: pageContext + '/editor/post',
        data: JSON.stringify({
            userName: $('#the-username').text(),
            postTitle: $('#add-post-title').val(),
            postDateTime: postDateText,
            expDateTime: expDateText,
            postContent: tinyMCE.activeEditor.getContent(),
            flaggedForReview: flagForReview,
            draft: $('#draft').is(':checked'),
            editorComments: editorComments,
            tagIds: tagArr,
            categoryIds: categoryArr,
            tagNames: [],
            categoryNames: []
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (post, status) {
        $('#alertDiv').html('<div class="alert alert-success"><span class="glyphicon glyphicon-ok"></span><strong> Success!</strong> Your blog has been updated.'
                + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a></div>');
        resetPage(true);
        return post.postId;
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

//</editor-fold>

// <editor-fold desc="Tag functions">

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
        url: 'editor/tag',
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

function getTags() {
    var allTheTags = [];
    $.ajax({
        type: 'GET',
        url: $('#page-context').data("context") + '/tags/all'
    }).success(function (data, status) {
        $.each(data, function (index, tag) {

            allTheTags.push(tag.name);
        });
        $('#tag-box-input').autocomplete({
            source: allTheTags,
            minLength: 2
        });
    });
}
// </editor-fold>

// <editor-fold desc="CATEGORY functions">
function populateCategories() {
    $.ajax({
        type: 'GET',
        url: $('#page-context').data("context") + '/categories/all'
    }).success(function (data, status) {
        $.each(data, function (index, cat) {
            $('#categories-list').append('<input type="checkbox" name="category" id="cat' + cat.categoryId + '" class="category-checkbox" data-cat-name="' + cat.name + '" value="' + cat.categoryId
                    + '"/>&nbsp;' + cat.name + '&nbsp;:&nbsp;<a onclick="deleteCategory(' + cat.categoryId + ')">Delete</a> | <a onclick="editCategory(' + cat.categoryId + ')">Edit</a><br>');
        });
    });
}

function addCategory() {
    var input = $('#category-add-box').val();

    $.ajax({
        type: 'POST',
        url: $('#page-context').data("context") + '/editor/category',
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
            url: $('#page-context').data("context") + '/editor/category/' + id
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
            url: $('#page-context').data("context") + '/editor/category/' + catId,
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

// </editor-fold>

// <editor-fold desc="Generate POST functions">

function generatePostSummary() {
    var postSummary = $('#edit-accordion');
    postSummary.empty();
    var months = ["January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"];
    $.ajax({
        type: 'GET',
        url: $('#page-context').data("context") + '/posts/all/headers'
    }).success(function (postHeaders, status) {
        $.each(postHeaders, function (index, header) {
            var tabId, prefix = '', suffix = '';
            if (header.flaggedForReview) {
                tabId = 'flagged';
//                prefix = '<i style="color:red">';
//                suffix = '</i>';
            } else if (header.draft) {
                tabId = 'draft';
            } else {
                tabId = header.postDateTime[0] + header.postDateTime[1];
            }

            if (!$('#body-' + tabId).length) {
                var basePanel = '';
                basePanel += '<div class="panel panel-default">';
                basePanel += '<div class="panel-heading" role="tab" id="heading-' + tabId + '">';
                basePanel += '<h4 class="panel-title">';
                basePanel += '<a class="collapsed" role="button" data-toggle="collapse" data-parent="#edit-accordion" href="#collapse-'
                        + tabId + '" aria-expanded="false" aria-controls="collapse-' + tabId + '">';

                if (tabId === 'flagged') {
                    basePanel += 'Flagged For Review';
                } else if (tabId === 'draft') {
                    basePanel += 'Drafts';
                } else {
                    basePanel += months[header.postDateTime[1] - 1] + ' ' + header.postDateTime[0];
                }

                basePanel += '</a>';
                basePanel += '</h4>';
                basePanel += '</div>';
                basePanel += '<div id="collapse-' + tabId + '" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading-' + tabId + '">';
                basePanel += '<div class="panel-body text-left" id="body-' + tabId + '">';
                basePanel += '</div>';
                basePanel += '</div>';
                basePanel += '</div>';
                postSummary.append(basePanel);
            }

            var monthPanel = $('#body-' + tabId);

            var postLink = '';
            postLink += '<li><a href="' + $('#page-context').data("context") + '/editor/post/display/' + header.postId + '">'
                    + header.postTitle + '</a> | <a onclick="generatePost(' + header.postId + ')">Edit</a>';

            if ($('#role-admin').length) {
                postLink += '&nbsp;|&nbsp;<a onclick="deletePost(' + header.postId + ')" style="color: red;">Delete</a>';
            }

            if (header.flaggedForReview && header.draft) {
                postLink += ' <i class="text-muted">Draft</i>';
            }

            postLink += '</li>';
            monthPanel.append(postLink);
        });
    });
}

function generatePost(postId) {
    resetPage(false);
    $.ajax({
        type: 'GET',
        url: $('#page-context').data("context") + '/editor/post/' + postId
    }).success(function (post, status) {
        tinyMCE.activeEditor.setContent(post.postContent);
        $('#add-post-title').val(post.postTitle);
        var postMonth = (post.postDateTime[1] > 9) ? post.postDateTime[1] : '0' + post.postDateTime[1];
        var postDay = (post.postDateTime[2] > 9) ? post.postDateTime[2] : '0' + post.postDateTime[2];
        $('#post-date-picker').val(postMonth + '/' + postDay + '/' + post.postDateTime[0]);
        var expMonth = (post.expDateTime[1] > 9) ? post.expDateTime[1] : '0' + post.expDateTime[1];
        var expDay = (post.expDateTime[2] > 9) ? post.expDateTime[2] : '0' + post.expDateTime[2];
        $('#exp-date-picker').val(expMonth + '/' + expDay + '/' + post.expDateTime[0]);
        post.tagNames.forEach(getAndDisplayTag);
        post.categoryIds.forEach(checkCategories);
        $('#add-edit-post').text('Edit Post');
        $('#add-edit-post').css('width', '95px');
        $('#add-edit-post').attr('onclick', 'submitPostEdit(' + post.postId + ')');
//        $('#preview').show();
//        $('#preview').attr('onclick', 'previewPost(' + post.postId + ')');
        $('#draft').prop('checked', post.draft);
        $('#flagged').prop('checked', post.flaggedForReview);
        if (post.flaggedForReview && post.editorComments !== '') {
            $('#alertDiv').append('<div class="alert alert-info"><span class="glyphicon glyphicon-alert"></span><strong> Editor comments: </strong>'
                    + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><br>' + post.editorComments + '</div>');
            $('#editor-comments.text').val(post.editorComments);
        }
    });
}
//</editor-fold>

function checkCategories(catId) {
    $('#cat' + catId).prop('checked', true);
}

function uncheckAllCategories() {
    $('.category-checkbox').prop('checked', false);
}

// <editor-fold desc="EDIT POST functions">

function submitPostEdit(postId) {
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

    var flagForReview;

    if ($('#role-admin').length) {
        flagForReview = false;
    } else {
        flagForReview = true;
    }

    var categoryArr = $("input[name='category']:checked").map(function () {
        return this.value;
    }).get();

    var tagArr = $('.attached-tag').map(function () {
        return $(this).data("tag-id");
    }).get();

    $.ajax({
        type: 'PUT',
        url: $('#page-context').data("context") + '/editor/post/' + postId,
        data: JSON.stringify({
            postId: postId,
            userName: $('#the-username').text(),
            postTitle: $('#add-post-title').val(),
            postDateTime: postDateText,
            expDateTime: expDateText,
            postContent: tinyMCE.activeEditor.getContent(),
            flaggedForReview: flagForReview,
            draft: $('#draft').is(':checked'),
            editorComments: '',
            tagIds: tagArr,
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

//</editor-fold>

//<editor-fold desc="DELETE POST function">
function deletePost(postId) {
    if (confirm("Are you sure you want to delete this post?")) {
        $.ajax({
            type: 'DELETE',
            url: pageContext + '/editor/post/' + postId
        }).success(function (data, status) {
            alert("Post deleted.");
            generatePostSummary();
        }).error(function (data, status) {
            alert("Post not deleted.");
        });
    }
}


//</editor-fold>

function resetPage(skipConfirm) {
    if (skipConfirm || confirm('Are you sure you want to clear your current work?')) {
        tinyMCE.activeEditor.setContent('');
        $('#add-post-title').val('');
        $('#tag-box').text('');
        clearCategories();
        populateCategories();
        clearTags();
        $('#add-edit-post').text('Submit Post');
        $('#add-edit-post').attr('onclick', 'submitPost()');
        $('#draft').prop('checked', false);
        $('#label-exp-date').css("color", "black");
        $('#label-post-date').css("color", "black");
//        $('#preview').hide();
        generatePostSummary();
    }
}

$('#flagged').click(function () {
    if (this.checked) {
        $("#editor-comments").show();
    } else {
        $("#editor-comments").hide();
    }
});


// <editor-fold desc="Preview Modal">
$('#postPreviewModal').on('show.bs.modal', function (event) {
    $('#preview-content').empty();
    var element = $(event.relatedTarget);
    var id = element.data('post-id');
    var modal = $(this);

    var currentPost = "";

    currentPost += '<div class ="panel panel-default">';
    currentPost += '<div class="panel-heading"><h3><strong>' + $('#add-post-title').val() + '</strong></h3>';
    currentPost += '<br/>';
    currentPost += '<h4>MM-DD-YYYY';
    currentPost += ' | Posted by: ' + $('#the-username').text();

    var categoryArr = $("input[name='category']:checked").map(function () {
        return $(this).data("cat-name");
    }).get();


    if (categoryArr.length > 0) {
        currentPost += ' in ';
        for (var i = 0; i < categoryArr.length; i++) {
            currentPost += '<a href="#">' + categoryArr[i] + '</a>';
            if (i < categoryArr.length - 1) {
                currentPost += ', ';
            }
        }
    }

    currentPost += '</h4>';
    currentPost += '</div>';
    currentPost += '<div class="panel-body">';
    currentPost += '<p>' + tinyMCE.activeEditor.getContent() + '</p>';
    currentPost += '<p style="text-align: right;">' + '<a data-identifier="#" data-toggle="modal" data-post-id="#" data-target="#postPageModal">Read More...</a></p>';
    currentPost += '</div>';
    currentPost += '<div class = "panel-footer" >';
    currentPost += '<h4>';
    currentPost += '<small>';

    var tagArr = $('.attached-tag').map(function () {
        return $(this).text().substring(1);
    }).get();

    for (var i = 0; i < tagArr.length; i++) {
        currentPost += '<span class="attached-tag" id="tag' + tagArr[i] + '"><span class="label label-default"><a href="#">#' + tagArr[i] + '</a></span></span>&nbsp;';

    }
    //take this one out: 
    currentPost += '</small>';
    currentPost += '</h4>';
//            currentPost += '<h3 id="post-id-' + post.postId + '" data-post-id="' + post.postId + '">' + post.postId + '</h3>';
    currentPost += '</div>';
    currentPost += '</div>';

    $('#preview-content').append(currentPost);
});

$(".modal-wide").on("show.bs.modal", function () {
    var height = $(window).height() - 200;
    $(this).find(".modal-body").css("max-height", height);
});

//</editor-fold>
