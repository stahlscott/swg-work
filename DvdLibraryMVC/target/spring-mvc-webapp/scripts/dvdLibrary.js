/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    loadDvds();

    $('#add-button').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'dvd',
            data: JSON.stringify({
                title: $('#add-title').val(),
                releaseDate: $('#add-release-date').val(),
                mpaaRating: $('#add-mpaa-rating').val(),
                studio: $('#add-studio').val(),
                director: $('#add-director').val(),
                reviewerRating: $('#add-reviewer-rating').val(),
                notes: $('#add-notes').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#add-title').val('');
            $('#add-release-date').val('');
            $('#add-mpaa-rating').val('');
            $('#add-studio').val('');
            $('#add-director').val('');
            $('#add-reviewer-rating').val('');
            $('#add-notes').val('');
            loadDvds();
        }).error(function (data, status) {
            var errDiv = $('#add-validation-errors');
            errDiv.empty();

            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                errDiv.append(validationError.message).append('<br>');
            });
        });
    });

    $('#edit-button').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'PUT',
            url: 'dvd/' + $('#edit-dvd-id').text(),
            data: JSON.stringify({
                title: $('#edit-title').val(),
                releaseDate: $('#edit-release-date').val(),
                mpaaRating: $('#edit-mpaa-rating').val(),
                studio: $('#edit-studio').val(),
                director: $('#edit-director').val(),
                reviewerRating: $('#edit-reviewer-rating').val(),
                notes: $('#edit-notes').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            loadDvds();
        }).error(function (data, status) {
            var errDiv = $('#edit-validation-errors');
            errDiv.empty();

            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                errDiv.append(validationError.message).append('<br>');
            });
        });
    });

    $('#search-button').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'search/dvds',
            data: JSON.stringify({
                title: $('#search-title').val(),
                releaseDate: $('#search-release-date').val(),
                mpaaRating: $('#search-mpaa-rating').val(),
                studio: $('#search-studio').val(),
                director: $('#search-director').val(),
                reviewerRating: $('#search-reviewer-rating').val(),
                notes: $('#search-notes').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            fillDvdForm(data, status);
        });
    });

});

function loadDvds() {
    $.ajax({
        type: 'GET',
        url: 'dvds'
    }).success(function (data, status) {
        fillDvdForm(data, status);
    });
}

function fillDvdForm(dvdList, status) {
    clearDvdTable();

    var dTable = $('#contentRows');

    $.each(dvdList, function (index, dvd) {
        dTable.append($('<tr>')
                .append($('<td>').append($('<a>')
                        .attr({
                            'data-dvd-id': dvd.dvdId,
                            'data-toggle': 'modal',
                            'data-target': '#detailsModal'
                        }).text(dvd.title)
                        )
                        )
                .append($('<td>').text(dvd.releaseDate))
                .append($('<td>').append($('<a>')
                        .attr({
                            'data-dvd-id': dvd.dvdId,
                            'data-toggle': 'modal',
                            'data-target': '#editModal'
                        }).text('Edit')
                        )
                        )
                .append($('<td>').append($('<a>')
                        .attr({
                            'onclick': 'deleteDvd(' + dvd.dvdId + ')'
                        })
                        .text('Delete'))));
    });
}

function clearDvdTable() {
    $('#contentRows').empty();
}

$('#detailsModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);

    var dvdId = element.data('dvd-id');

    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId
    }).success(function (dvd, status) {
        modal.find('#dvd-id').text(dvd.dvdId);
        modal.find('#dvd-title').text(dvd.title);
        modal.find('#dvd-release-date').text(dvd.releaseDate);
        modal.find('#dvd-mpaa-rating').text(dvd.mpaaRating);
        modal.find('#dvd-studio').text(dvd.studio);
        modal.find('#dvd-director').text(dvd.director);
        modal.find('#dvd-reviewer-rating').text(dvd.reviewerRating);
        modal.find('#dvd-notes').text(dvd.notes);
    });
});

$('#editModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);

    var dvdId = element.data('dvd-id');

    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId
    }).success(function (dvd, status) {
        modal.find('#edit-dvd-id').text(dvd.dvdId);
        modal.find('#edit-title').val(dvd.title);
        modal.find('#edit-release-date').val(dvd.releaseDate);
        modal.find('#edit-mpaa-rating').val(dvd.mpaaRating);
        modal.find('#edit-studio').val(dvd.studio);
        modal.find('#edit-director').val(dvd.director);
        modal.find('#edit-reviewer-rating').val(dvd.reviewerRating);
        modal.find('#edit-notes').val(dvd.notes);
    });
});


function deleteDvd(dvdId) {
    var answer = confirm("Do you really want to delete this dvd?");
    if (answer) {
        $.ajax({
            type: 'DELETE',
            url: 'dvd/' + dvdId
        }).success(function () {
            if (dvdId === $('#edit-dvd-id')) {
                hideEditForm();
            }
            ;
            loadDvds();
        });
    }
}