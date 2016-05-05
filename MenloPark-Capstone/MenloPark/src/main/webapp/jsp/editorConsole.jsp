<%-- 
    Document   : adminConsole
    Created on : 28-Apr-2016
    Author     : Chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editor Console Page | CMS</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <span style="display: none;" id="logged"><sec:authentication property="principal.username" /></span>
        <div class="container">
            <jsp:include page="headerFragment.jsp"/>
            <span id="page-context" data-context="${pageContext.request.contextPath}"></span>

            <div class="row">

                <div id="user-content" class="col-sm-8"> 

                    <div class ="panel panel-default">
                        <div class="panel-heading"><h4><strong>Drafts</strong></h4>
                        </div>
                        <div class="panel-body">
                            <p class="text-muted" style="font-size: small;">
                                User-created Drafts will appear here. Drafts are posts that have not been published nor have they been submitted for approval.
                                A draft will only be submitted for approval when the author de-selects "Save as Draft" on the edit screen.
                            </p>
                            <table class="table table-striped" id="draft-posts">
                                <thead>
                                    <tr><th>Post Title</th>
                                        <th>Author</th>
                                        <th>Date</th>
                                        <th>Comments</th>
                                        <th>Actions</th>   
                                    </tr>
                                </thead>
                                <tbody id="draft-posts-body">

                                </tbody>
                            </table>

                        </div>
                    </div>


                    <div class ="panel panel-default">
                        <div class="panel-heading"><h4><strong>Posts Awaiting Approval</strong></h4>
                        </div>
                        <div class="panel-body">
                            <p class="text-muted" style="font-size: small;">
                                Submitted posts that have not been administrator-approved will appear here. <br>
                                To edit or delete a post, select "Create/Edit a Blog Post" from the User Console panel. 
                            </p>
                            <table class="table table-striped" id="unapproved-posts">
                                <thead>
                                    <tr><th>Post Title</th>
                                        <th>Author</th>
                                        <th>Date Submitted</th>
                                        <th>Returned Comments</th>
                                    </tr>
                                </thead>
                                <tbody id="unapproved-posts-body">

                                </tbody>
                            </table>

                            <div id="alert-div-lower">

                            </div>
                        </div>
                    </div>


                </div><!-- end of main content -->

                <div class="col-sm-4" id="side-bar">
                    <div class="panel panel-info">

                        <div class="panel-heading"><h4><strong>User Console</strong></h4>
                            Hello, <sec:authentication property="principal.username" />!
                            </h4>
                        </div>
                        <div class="panel-body">
                            <ul style="list-style: none">
                                <li><a href="${pageContext.request.contextPath}/edit">Create/Edit a Blog Post</a></li>
                                <li><a href="${pageContext.request.contextPath}/page/edit">Create/Edit a Page</a></li>
                            </ul>
                        </div>
                    </div>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <div class="panel panel-danger">
                            <div class="panel-heading text-center">Logged In As <sec:authentication property="principal.username" /></div>
                            <div class="panel-body">
                                <ul style="list-style: none">
                                    <li><a href="${pageContext.request.contextPath}/admin/console">Go To Admin Console</a>
                                </ul>
                            </div>
                        </div>
                    </sec:authorize> 


                </div>
            </div>  <!-- footer here -->

            <jsp:include page="footerFragment.jsp"/>
        </div>
        <script src="${pageContext.request.contextPath}/scripts/editorConsole.js"></script>
    </body>
</html>
