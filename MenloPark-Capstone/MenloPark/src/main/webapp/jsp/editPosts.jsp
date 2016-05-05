<%-- 
    Document   : editPosts
    Created on : Apr 19, 2016, 2:19:47 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Create-Edit Post Page | CMS</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/jquery-ui.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body><span id="the-username" style="display: none;"><sec:authentication property="principal.username" /></span>

        <div class="container">
            <jsp:include page="headerFragment.jsp"/>
            <div class="row">
                <div id="main-content" class="col-sm-8">
                    <div class ="panel panel-default">
                        <div class="panel-heading">
                            <!-- post title -->

                            <form class="form-inline" id="add-post-form" method="POST">
                                <label id="label-title" for="add-post-title">New Post Title:</label>
                                <strong><input id="add-post-title" placeholder="Post Title"/></strong>
                                <br/>
                        </div>

                        <div class="panel-body">

                            <!-- tiny MCE goes here -->

                            <textarea id="add-post-content" name="add-post-content" placeholder="This is where you can add a post! "></textarea>


                            <!-- tag box well, where tags appear -->
                            <div class="well" style="padding: 5px; height: 32px;">
                                <span class="text-left text-muted tag" id="tag-box"></span>
                            </div>

                            <!-- tag box input -->

                            <input class="typeahead tt-query" id="tag-box-input" autocomplete="off" spellcheck="false" placeholder="Separate, tags, by commas" />&nbsp&nbsp;<button id="add-tag-button" class="btn btn-group-sm" type="button" onclick="addTags()">Add Tag</button>&nbsp
                            <button id="clear-tags-button" class="btn btn-group-sm" type="button" onclick="clearTags()">Clear Tag List</button>

                            <div class="pull-right">
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <span><input id="flagged" type="checkbox" class="a-checkbox" name="check-if-draft" />&nbsp;Flag For Review</span>
                                    </sec:authorize>
                                <span><input id="draft" type="checkbox" class="a-checkbox" name="check-if-draft" />&nbsp;Save As Draft</span>
                            </div>
                            <br><br>
                            <!-- hidden success / fail bootstrap alerts -->
                            <div id="editor-comments" style="display:none">
                                <textarea id="editor-comments-text" placeholder="Editor comments..." style="width:100%"></textarea>
                            </div>
                            <div id="alertDiv"></div>
                            <br>
                            <!-- date inputs-->
                            <div class="row">
                                <div class="col-sm-4">
                                    <label for="post-date-picker" id="label-post-date">Post Date: </label> <input size="10" id="post-date-picker" placeholder="MM/DD/YYYY"/>
                                </div>
                                <div class="col-sm-4">
                                    <label for="exp-date-picker" id="label-exp-date">Expiration Date: </label> <input size="10" id="exp-date-picker" placeholder="MM/DD/YYYY"/>
                                </div>

                                <input type="text" class="hidden" id="post-id" />

                                <div class="col-sm-4 text-right">
                                    <button type="button" id="preview" class="btn btn-info" data-toggle="modal" data-target="#postPreviewModal">Save & Preview</button><br>
                                    <button type="button" onclick="resetPage(false)" id="cancel" class="btn btn-primary">Cancel</button>
                                    <button type="button" onclick="submitPost()" id="add-edit-post" class="btn btn-success">Submit Post</button>

                                </div>
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
                        <div id="post-summary">
                            <h3>Edit Posts</h3>
                            <h4><small>Posts by Month, Draft, or In-Review.</small></h4>
                            <div class="panel-group" id="edit-accordion" role="tablist" aria-multiselectable="true">
                            </div>
                        </div>
                        </ul>
                    </div>
                </div>
            </div>
            <jsp:include page="footerFragment.jsp"/>
            <jsp:include page="postPreviewFragment.jsp"/>
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

<!--    <script src="${pageContext.request.contextPath}/scripts/typeahead.min.js"></script>-->
<!--        <script src="${pageContext.request.contextPath}/css/jquery-ui.min.css"></script>-->
        <script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
        <script src="${pageContext.request.contextPath}/scripts/editPosts.js"></script>


    </body>
</html>
