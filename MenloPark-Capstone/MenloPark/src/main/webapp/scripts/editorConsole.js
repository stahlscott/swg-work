/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    populateDrafts();
    populateUnapprovedPosts();
});


function populateDrafts() {

    $.ajax({
        type: 'GET',
        url: $('#page-context').data("context") + '/editor/posts/draft'
    }).success(function (posts, status) {
        $.each(posts, function (index, post) {
            $('#draft-posts-body').append('<tr><td>' + post.postTitle + '</td><td>' + post.userName + '</td><td>' + post.postDateTime[1] + '/' + post.postDateTime[2] + '/' + post.postDateTime[0] +
                    '</td>' + '<td>' + post.editorComments + '</td>' +
                    '<td><a onclick="deletePost(' + post.postId + ')">Delete Draft</a></td>');
        });


    }).error(function () {
        alert('Error loading draft posts.');
    });
}


function approvePost(postId) {

    $.ajax({
        type: 'PATCH',
        url: $('#page-context').data("context") + '/editor/post/' + postId + '/approve',
        data: JSON.stringify({
            postId: postId
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (status) {
        //alert('it work'); //could add an alert div here
        $('#alert-div-lower').html('<div class="alert alert-success"><span class="glyphicon glyphicon-ok"></span><strong> Success!</strong> Post has been approved.<br>\n\
                <em>If the post does not disappear, it may also be listed as a Draft by the author.' + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a></div>');
        clearAwaitingApprovalBox();
        populateUnapprovedPosts();
    });


}

function clearAwaitingApprovalBox() {
    $('#unapproved-posts-body').empty();
}

function deletePost(postId) {

    var ask = confirm("Are you sure you want to delete this draft?");
    if (ask) {
        $.ajax({
            type: 'DELETE',
            url: $('#page-context').data("context") + '/editor/post/' + postId
        }).success(function (data, status) {
            alert("Draft deleted.");
            populateDrafts();
        }).error(function (data, status) {
            alert("Sorry, cannot delete draft.");
        });
    }
}

function populateUnapprovedPosts() {

    $.ajax({
        type: 'GET',
        url: $('#page-context').data("context") + '/editor/posts/review'
    }).success(function (posts, status) {
        $.each(posts, function (index, post) {
            $('#unapproved-posts-body').append('<tr><td>' + post.postTitle + '</td><td>' + post.userName + '</td><td>' + post.postDateTime[1] + '/' + post.postDateTime[2] + '/' + post.postDateTime[0] +
                    '</td>' + '<td>' + post.editorComments + '</td>');
        });

    }).error(function () {
        alert('Error loading draft posts.');
    });
}