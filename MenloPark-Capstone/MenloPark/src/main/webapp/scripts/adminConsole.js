/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    populateCurrentPages();
//    populateAwaitingApprovalPosts();
    loadSortablePages();
    populateUnapprovedPosts();
    $('.sortable').nestedSortable({
        handle: 'div',
        items: 'li',
        toleranceElement: '> div',
        maxLevels: 2

    });

});

var pageContext = $('#page-context').data("context");

function clearAwaitingApprovalBox() {
    $('#unapproved-posts-body').empty();
}

//function populateAwaitingApprovalPosts() {
//    var awaitingApproval = $('#awaiting-approval');
//
//    $.ajax({
//        type: 'GET',
//        url: $('#page-context').data("context") + '/editor/posts/review'
//    }).success(function (posts, status) {
//        $.each(posts, function (index, post) {
//            awaitingApproval.append('<li data-post-id="' + post.postId + '">"' + post.postTitle + '</a>"&nbsp;\n\
//                                        Posted by ' + post.userName + ' on ' + post.postDateTime[1] + '/' + post.postDateTime[2] + '/' + post.postDateTime[0] + ' |&nbsp;<a onclick="approvePost(' + post.postId + ')">Approve Post</a>&nbsp;|&nbsp;\n\
//                                    <a onclick="getReason(' + post.postId + ')">Reject Post</a></li>');
//        });
//    });
//}

function getReason(postId) {
    var reason = prompt("Please enter a rejection reason for the corresponding post and author. (REQUIRED)");

    if (reason === "" || reason === null) {
        getReason(postId);
    } else
        rejectPost(postId, reason);
}

function rejectPost(postId, reason) {

    $.ajax({// using PATCH because we're not updating the entire entry, just comments
        type: 'PATCH',
        url: $('#page-context').data("context") + '/editor/post/' + postId + '/' + reason + '/reject',
        data: JSON.stringify({
            postId: postId,
            editorComments: reason
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (status) {
        alert('it work');
        //getting back a 202 but it thinks it's an error. it's not an error. patch is weird apparently. thing works.
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
        $('#alertDivUpper').html('<div class="alert alert-success"><span class="glyphicon glyphicon-ok"></span><strong> Success!</strong> Post has been approved.<br>\n\
                <em>If the post does not disappear, it may also be listed as a Draft by the author.' + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a></div>');
        clearAwaitingApprovalBox();
        populateUnapprovedPosts();
    });
}


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

function loadSortablePages() {
    var pageSort = $('#page-sort');
    pageSort.empty();
    $('#alert-div').empty();

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

function submitPages() {
    var pageSort = $('#page-sort');
    var pages = $('.sortable').nestedSortable('toArray');

    for (var i = 1; i < pages.length; i++) {
        $.ajax({
            type: 'PUT',
            url: $('#page-context').data("context") + '/admin/page/index/' + pages[i].id,
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
        });
    }
    $('#alert-div').html('<div class="alert alert-success"><span class="glyphicon glyphicon-ok"></span><strong> Success!</strong> Your page order has been updated.'
            + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a></div>');
}

function refreshPages() {
    loadSortablePages();
    populateNavbar();
}

function populateUnapprovedPosts() {
    
    $.ajax({
        type: 'GET',
        url: $('#page-context').data("context") + '/editor/posts/review'
    }).success(function (posts, status) {
        $.each(posts, function (index, post) {
            $('#unapproved-posts-body').append('<tr><td>' + post.postTitle + '</td><td>' + post.userName + '</td><td>' + post.postDateTime[1] + '/' + post.postDateTime[2] + '/' + post.postDateTime[0] + 
                    '</td>' + '<td>' + post.editorComments + '</td>' + 
                    '<td><a onclick="approvePost(' + post.postId + ')">Approve Post</a></td>' + 
                    '<td><a onclick="getReason(' + post.postId + ')">Reject Post</a></td>');
        });
        
        
    }).error(function () {
        alert('Error loading draft posts.');
    });
}