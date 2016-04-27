<%-- 
    Document   : editPosts
    Created on : Apr 19, 2016, 2:19:47 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create-Edit Post Page | CMS</title>
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

                            <form class="form-inline" id="add-post-form" method="POST">

                                <label for="add-post-title">New Post Title:</label>
                                <strong><input id="add-post-title" placeholder="Post Title"/></strong>
                                <br/>

                        </div>

                        <div class="panel-body">

                            <!-- tiny MCE goes here -->

                            <textarea id="add-post-content" name="add-post-content">This is where you can add a post! <b>Style it</b> <u>however you</u> <i>like.</i></textarea>


                            <!-- tag box well, where tags appear -->
                            <div class="well" style="padding: 5px;">
                                <span class="text-left text-muted tag" id="tag-box"></span>
                            </div>


                            <!-- tag box input -->

                            <input id="tag-box-input" placeholder="Separate, tags, by commas" />&nbsp;<button id="add-tag-button" class="btn btn-group-sm" type="button" onclick="addTags()">Add Tag</button>
                            <button id="clear-tags-button" class="btn btn-group-sm" type="button" onclick="clearTags()">Clear Tag List</button>

                            <br><br>
                            <!-- hidden success / fail bootstrap alerts -->
                            <div id="alertDiv"></div>
                            <br>
                            <!-- date inputs-->
                            <div class="col-sm-4">
                                <label for="post-date-picker">Post Date: </label><input id="post-date-picker" placeholder="MM/DD/YYYY"/>
                            </div>
                            <div class="col-sm-4">
                                <label for="exp-date-picker">Expiration Date: </label><input id="exp-date-picker" placeholder="MM/DD/YYYY"/>
                            </div>

                            <input type="text" class="hidden" id="post-id" />

                            <div class="form-group pull-right">
                                <a href="${pageContext.request.contextPath}/"<button type="button" id="cancel" class="btn btn-default">Cancel</button></a>
                                <button type="button" onclick="submitPost()" id="add-edit-post" class="btn btn-primary disabled">Post</button>

                            </div>


                            </form>
                        </div>

                    </div>

                </div><!-- end of main content -->


                <div class="col-sm-4" id="side-bar">
                    <div class="well text-center">
                        <h3>Categories</h3>
                        <h4><small>Select one or more for your post:</small></h4>
                        <p id="categories-list" class="text-left">

                        </p>

                        <input id="category-add-box" type="text" placeholder="Add a Category" />&nbsp;<button type="button" id="add-category-button" class="btn btn-group-xs" onclick="addCategory()">Add Category</button>

                    </div>
                    <div class="well text-center">
                        <h3>Posts by Month / Year</h3>

                        <ul style="list-style-type: none; text-align: left;">
                            <li>March 2016  </li>
                            <li>February 2016 </li>
                            <li>December 2015 </li>

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
                                selector: '#add-post-content',
                                plugins: "media image"
                                        //                menubar: "insert",
                                        //                toolbar: "media image"
                            });
        </script>
        <script src="${pageContext.request.contextPath}/scripts/editPosts.js"></script>

    </body>
</html>
