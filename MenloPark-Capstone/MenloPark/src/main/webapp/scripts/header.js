/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
//    $('#nav-main').smartmenus();
    populateNavbar();
    spinnaz();
    noSpinnaz();
});

function populateNavbar() {
    var navbar = $('#nav-main');
    navbar.empty();

    $.ajax({
        type: 'GET',
        url: $('#page-context').data("context") + '/pages/all/'
    }).success(function (pages, status) {
        var currentPageId = $('#page-id').data('page-id');
        $.each(pages, function (index, page) {
            var navlink = '';

            // if page has no parents, set as top-level menu item
            if (page.parentId === 0) {
                navlink += '<li id="nav-page-' + page.pageId + '"';
                // if current displaying page = page we're iterating on, mark as active
                if (page.pageId === currentPageId) {
                    navlink += ' class="active"';
                }
                navlink += '><a id="page-link-' + page.pageId+'" href="' + $('#page-context').data("context") + '/page/display/' + page.pageId + '">'; //  class="dropdown-toggle" data-hover="dropdown" <-- tried to work with plugin, couldn't get it working
                navlink += page.pageName + '</a>';
                navlink += '</li>';
                navbar.append(navlink);
            } else {
                // if it has parents, check to see if nav-dropdown needs to be created
                if (!$('#nav-dropdown-' + page.parentId).length) {
                    $('#nav-page-' + page.parentId).append('<ul class="dropdown-menu" id="nav-dropdown-' + page.parentId + '">');
                    $('#nav-page-' + page.parentId).attr('class', function (i, origValue) {
                        return origValue + ' dropdown';
                    });
                    // comment these out and uncomment styles.css to use hover functionality
                    $('#page-link-' + page.parentId).attr('class', 'dropdown-toggle');
                    $('#page-link-' + page.parentId).attr('data-toggle', 'dropdown');
                }

                // add child to parent nav-dropdown list
                var parentDropdown = $('#nav-dropdown-' + page.parentId);

                var navlink = '<li id="nav-page-' + page.pageId + '"';
                if (page.pageId === currentPageId) {
                    navlink += ' class="active"';
                }
                navlink += '><a href="' + $('#page-context').data("context") + '/page/display/' + page.pageId + '">' + page.pageName + '</a></li>';
                navlink += '</ul>';

                parentDropdown.append(navlink);
            }
        });
    });
}

//<editor-fold desc="Super Secret Stuff">

function spinnaz() {
    $('#header-img').mouseenter(function () {
        $(this).addClass('spinnaz');
    });
}

function noSpinnaz() {
    $('#header-img').mouseleave(function () {
        $(this).removeClass('spinnaz');
    });
}

//</editor-fold>