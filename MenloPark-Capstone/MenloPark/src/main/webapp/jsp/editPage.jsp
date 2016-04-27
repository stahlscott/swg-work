<%-- 
    Document   : editPage
    Created on : Apr 25, 2016, 6:18:10 PM
    Author     : Scott Stahl <stahl.scott@gmail.com>
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create-Edit Pages | CMS</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/jquery-ui.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <span id="page-context" data-context="${pageContext.request.contextPath}"></span>
        <div class="container">
            <jsp:include page="headerFragment.jsp"/>
            <div class="row">
                <div id="main-content" class="col-sm-8">
                    <div class ="panel panel-default">
                        <div class="panel-heading">
                            <!-- post title -->

                            <form class="form-inline" id="add-page-form" method="POST">

                                <label for="add-page-title">New Page Title:</label>
                                <strong><input class="required" id="add-page-title" placeholder="Page Title"/></strong>
                                <br/>

                        </div>

                        <div class="panel-body">

                            <!-- tiny MCE goes here -->

                            <textarea id="add-page-content" class="required" name="add-page-content">This is where you can add a page! <b>Style it</b> <u>however you</u> <i>like.</i></textarea>


                            <!-- tag box well, where tags appear -->
<!--                            <div class="well" style="padding: 5px;">
                                <span class="text-left text-muted tag" id="tag-box"></span>
                            </div>-->


                            <br><br>
                            <!-- hidden success / fail bootstrap alerts -->
                            <div id="alertDiv"></div>
                            <br>

                            <input type="text" class="hidden" id="page-id" />

                            <div class="form-group pull-right">
                                <a href="${pageContext.request.contextPath}/"<button type="button" id="cancel" class="btn btn-default">Cancel</button></a>
                                <button type="button" onclick="submitPage()" id="add-edit-page" class="btn btn-primary disabled">Submit</button>

                            </div>
                            </form>
                        </div>

                    </div>
                    
                </div><!-- end of main content -->

                <div class="col-sm-4" id="side-bar">
                    <div class="well">
                        <h3 class="text-center">Parent Pages</h3>
                        <p id="page-list" class="text-left">
                        </p>
                        <button id="clear-pages-button" class="btn btn-default" type="button" onclick="clearPages()">Clear</button>

                    </div>
                    <div class="well text-center">
                        <h3>Current Pages</h3>
                        <p>
                        <ul style="list-style-type: none; text-align: left;" id="current-pages">
                            <c:forEach var="page" items="${pageList}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/page/display/${page.pageId}">${page.pageName}</a> - 
                                    <a onclick="insertPageLink(${page.pageId}, '${page.pageName}')">Insert Page Link</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <jsp:include page="footerFragment.jsp"/>
        </div>

        <p class="hidden" id="pc">${pageContext.request.contextPath}</p>

        <script src="${pageContext.request.contextPath}/js/tinymce.min.js"></script>
        <script>
                            tinymce.init({
                                selector: '#add-page-content',
                                plugins: "media image"
                                        //                menubar: "insert",
                                        //                toolbar: "media image"
                            });
        </script>
        <script src="${pageContext.request.contextPath}/scripts/editPage.js"></script>

    </body>
</html>

