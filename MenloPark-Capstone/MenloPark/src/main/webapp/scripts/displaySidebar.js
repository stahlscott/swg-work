/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    generateTagCloud();
    generatePostSummary();
});

//$(window).scroll(function () {
//    $('#sidebar').width($('#tag-cloud').width());
//});

function generateTagCloud() {
    var tagCloud = $('#tag-cloud');
    tagCloud.empty();
    $.ajax({
        type: 'GET',
        url: $('#page-context').data("context") + '/tags/top/'
    }).success(function (tags, status) {

        var topOccurences = 0;

        $.each(tags, function (index, tag) {
            topOccurences = (tag.numberOfOccurences > topOccurences) ? tag.numberOfOccurences : topOccurences;
        });

        $.each(tags, function (index, tag) {
            var qty = tag.numberOfOccurences;
            if (qty > 0) {
                var size = '';
                if (qty >= topOccurences) {
                    size = 42;
                } else if (qty > (topOccurences * .9)) {
                    size = 36;
                } else if (qty > (topOccurences * .7)) {
                    size = 28;
                } else if (qty > (topOccurences * .5)) {
                    size = 24;
                } else if (qty > (topOccurences * .3)) {
                    size = 20;
                } else {
                    size = 16;
                }
                tagCloud.append(' <span style="font-size:' + size + 'px"><a href="' + $('#page-context').data("context") + '/tag/display/' + tag.tagId + '">' + tag.name);
            }
        });
    });
}

function generatePostSummary() {
    var postSummary = $('#accordion');
    postSummary.empty();
    var months = ["January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"];
    $.ajax({
        type: 'GET',
        url: $('#page-context').data("context") + '/posts/visible/headers'
    }).success(function (postHeaders, status) {
        $.each(postHeaders, function (index, header) {

            var dateId = header.postDateTime[0] + header.postDateTime[1];

            if (!$('#body-' + dateId).length) {
                var basePanel = '';
                basePanel += '<div class="panel panel-default">';
                basePanel += '<div class="panel-heading" role="tab" id="heading' + dateId + '">';
                basePanel += '<h4 class="panel-title">';
                basePanel += '<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse'
                        + dateId + '" aria-expanded="false" aria-controls="collapse' + dateId + '">';
                basePanel += months[header.postDateTime[1] - 1] + ' ' + header.postDateTime[0];
                basePanel += '</a>';
                basePanel += '</h4>';
                basePanel += '</div>';
                basePanel += '<div id="collapse' + dateId + '" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading' + dateId + '">';
                basePanel += '<div class="panel-body text-left" id="body-' + dateId + '">';
                basePanel += '</div>';
                basePanel += '</div>';
                basePanel += '</div>';
                postSummary.append(basePanel);
            }

            var monthPanel = $('#body-' + dateId);

            var postLink = '';
            postLink += '<li><a href="' + $('#page-context').data("context") + '/post/display/' + header.postId + '">'
                    + header.postTitle + '</a></li>';
            monthPanel.append(postLink);

        });
    });
}