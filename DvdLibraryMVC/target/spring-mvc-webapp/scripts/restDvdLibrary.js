/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    loadContacts();
});

function loadContacts() {
    clearDvdTable();
    var contentRows = $('#contentRows');
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/DvdLibraryMVC/dvds' //could just be 'contacts' because its within the same app
    }).success(function (data, status) { 
        $.each(data, function (index, dvd) {
            var title = dvd.title;
            var releaseDate = dvd.releaseDate;
            var id = dvd.dvdId;

            var row = '<tr>';
            row += '<td>' + title + '</td>';
            row += '<td>' + releaseDate + '</td>';
            row += '<td><a onclick="showEditForm(' + id + ')">Edit</a></td>';
            row += '<td><a onclick="deleteAddress(' + id + ')">Delete</a></td>';
            row += '</tr>';
            contentRows.append(row);
        });
    });
}

function clearDvdTable() {
    $('#contentRows').empty();
}