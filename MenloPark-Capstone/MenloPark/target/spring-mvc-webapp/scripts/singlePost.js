/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    loadPosts();
});

function loadPosts(startOfRange) {
    clearPostTable();

    var postTable = $('#main-content');


    $.ajax({
        type: 'GET',
        url: $('#page-context').data("context") + '/post/' + $('#post-id').data('post-id')
    }).success(function (post, status) {
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
//                $('#category-id').text(post.categoryIds[i]);
                if (i < post.categoryNames.length - 1) {
                    currentPost += ', ';
                }
            }
        }
        currentPost += '</h4>';
        currentPost += '</div>';
        currentPost += '<div class="panel-body">';
        currentPost += '<p>' + post.postContent + '</p>';
//        currentPost += '<p style="text-align: right;">' + '<a data-identifier="' + post.postId + '" data-toggle="modal" data-post-id="' + post.postId + '" data-target="#postPageModal">Read More...</a></p>';
        currentPost += '</div>';
        currentPost += '<div class = "panel-footer" >';
        currentPost += '<h4>';
        currentPost += '<small>';

        for (var i=0; i < post.tagNames.length; i++) {

            currentPost += '<span class="attached-tag" id="tag"' + post.tagNames[i] + '"><span class="label label-default"><a href="' + $('#page-context').data("context") + '/tag/display/' + post.tagIds[i] + '">#' + post.tagNames[i] + '</a></span></span>&nbsp;';

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
}


function clearPostTable() {
    $('#main-content').empty();
}