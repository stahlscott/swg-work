/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var POSTS_PER_PAGE = 5;

$(document).ready(function () {
    loadPosts(1);
});

function loadPosts(startOfRange) {
    clearPostTable();

    var postTable = $('#main-content');

    $.ajax({
        type: 'GET',
        url: $('#page-context').data("context") + '/posts/visible/' + startOfRange + '/' + POSTS_PER_PAGE
    }).success(function (data, status) {
        $.each(data, function (index, post) {
            var currentPost = "";

            currentPost += '<div class ="panel panel-default">';
            currentPost += '<div class="panel-heading"><h3><strong>' + post.postTitle + '</strong></h3>';
            currentPost += '<br/>';
            currentPost += '<h4>' + post.postDateTime[1] + '-' + post.postDateTime[2] + '-' + post.postDateTime[0];
            currentPost += ' | Posted by: ' + post.userName;

            if (post.categoryNames.length > 0) {
                currentPost += ' in ';
                for (var i = 0; i < post.categoryNames.length; i++) {
                    currentPost += '<a href="' + $('#page-context').data("context") + '/category/display/' + post.categoryIds[i] + '">' + post.categoryNames[i] + '</a>';
                    if (i < post.categoryNames.length - 1) {
                        currentPost += ', ';
                    }
                }
            }

            currentPost += '</h4>';
            currentPost += '</div>';
            currentPost += '<div class="panel-body">';
            currentPost += '<p>' + post.postContent + '</p>';
            currentPost += '<p style="text-align: right;">' + '<a data-identifier="' + post.postId + '" data-toggle="modal" data-post-id="'
                    + post.postId + '" data-target="#postPageModal">Read More...</a></p>';
            currentPost += '</div>';
            currentPost += '<div class = "panel-footer"';
            currentPost += '<h4>';
            currentPost += '<small>';

            for (var i = 0; i < post.tagNames.length; i++) {
                currentPost += '<span class="attached-tag" id="tag' + post.tagNames[i] + '"><span class="label label-default"><a href="'
                        + $('#page-context').data("context") + '/tag/display/' + post.tagIds[i] + '">#' + post.tagNames[i] + '</a></span></span>&nbsp;';

            }
            //take this one out: 
            currentPost += '</small>';
            currentPost += '</h4>';
//            currentPost += '<h3 id="post-id-' + post.postId + '" data-post-id="' + post.postId + '">' + post.postId + '</h3>';
            currentPost += '</div>';
            currentPost += '</div>';

            $('#post-id').val(post.postId);

            postTable.append(currentPost);
        });
        pagination(startOfRange);
    });
}

function pagination(currentStart) {
    $('#side-pagination').empty();
    $.ajax({
        type: 'GET',
        url: $('#page-context').data("context") + '/posts/visible/size'
    }).success(function (size, status) {
        var numberOfPages = (size / POSTS_PER_PAGE);
        var pageList = '<ul class="pagination">';

        for (i = 0; i < numberOfPages; i++) {
            var startOfRange = (i * POSTS_PER_PAGE) + 1;
            var endOfRange = (i + 1) * POSTS_PER_PAGE;
            if (endOfRange > size) {
                endOfRange = size;
            }
            pageList += '<li';
            if (startOfRange === currentStart) {
                pageList += ' class="active"';
            }
            pageList += '><a onclick="loadPosts(' + startOfRange + ')">' + startOfRange + '-' + endOfRange + '</a></li>';
        }
        pageList += '</ul>';
        $('#side-pagination').append(pageList);
    });
}

function clearPostTable() {
    $('#main-content').empty();
}

$('#postPageModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var id = element.data('post-id');
    var modal = $(this);

    $.ajax({
        url: 'post/visible/' + id
    }).success(function (post) {
        modal.find('#post-id').text(post.postId); //won't need this
        modal.find('#post-title').text(post.postTitle);
        modal.find('#post-month').text(post.postDateTime[1]);
        modal.find('#post-date').text(post.postDateTime[2]);
        modal.find('#post-year').text(post.postDateTime[0]);
        modal.find('#post-body').html(post.postContent); //display in html

        var categoryList = "";
        if (post.categoryNames.length > 0) {
            categoryList += ' in ';
            for (var i = 0; i < post.categoryNames.length; i++) {
                categoryList += '<a href="' + $('#page-context').data("context") + '/category/display/' + post.categoryIds[i] + '">' + post.categoryNames[i] + '</a>';
                if (i < post.categoryNames.length - 1) {
                    categoryList += ', ';
                }
            }
        }
        modal.find('#post-categories').html(categoryList);

        modal.find('#post-user').text(post.userName);
        var tagList = "";
        for (var i = 0; i < post.tagNames.length; i++) {
            tagList += '<span class="attached-tag" id="tag' + post.tagNames[i] + '"><span class="label label-default"><a href="'
                    + $('#page-context').data("context") + '/tag/display/' + post.tagIds[i] + '">#' + post.tagNames[i] + '</a></span></span>&nbsp;';
        }
        modal.find('#post-tags').html(tagList);

        modal.find('#bookmark').html('<a href="' + $('#page-context').data("context") + '/post/display/' + post.postId + '">bookmark this post</a>');

    }).error(function () {
        alert("wha happen");
    });
});

$(".modal-wide").on("show.bs.modal", function () {
    var height = $(window).height() - 200;
    $(this).find(".modal-body").css("max-height", height);
});
