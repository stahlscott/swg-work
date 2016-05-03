<%-- 
    Document   : adminConsole
    Created on : 26-Apr-2016
    Author     : Chris
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Console Page | CMS</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <jsp:include page="headerFragment.jsp"/>
            <span id="page-context" data-context="${pageContext.request.contextPath}"></span>

            <div class="row">

                <div id="admin-content" class="col-sm-8"> 
                    <div class ="panel panel-default">
                        <div class="panel-heading"><h4><strong>Posts Awaiting Approval</strong></h4>
                        </div>
                        <div class="panel-body">
                            
                            <table class="table table-striped" id="unapproved-posts">
                                <thead>
                                    <tr><th>Post Title</th>
                                        <th>Author</th>
                                        <th>Date Submitted</th>
                                        <th>Returned Comments</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody id="unapproved-posts-body">
                                    
                                </tbody>
                            </table>
                            <!-- TODO: adding links to edit a post from this page, 
                            taking the user to the editPage.jsp. somehow -->

                            <div id="alertDivUpper">
                                
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4><strong>Navbar Appearance - Sortable Pages</strong></h4>
                        </div>
                        <div class="panel-body">

                            <ol class="sortable" id="page-sort">
                            </ol>
                            <br>
                            <button type="button" class="btn btn-primary" onclick="submitPages()">Submit</button>
                            <button type="button" class="btn btn-default" onclick="refreshPages()">Refresh</button>
                            <br>
                            <div id="alert-div">
                                
                            </div>
                        </div>
                    </div>
                </div><!-- end of main content -->

                <div class="col-sm-4" id="side-bar">
                    <div class="panel panel-danger">
                        <div class="panel-heading"><h4><strong>Welcome, <span id="the-username"><sec:authentication property="principal.username" /></span>!</strong></h4>
                        </div>
                        <div class="panel-body">
                            <ul style="list-style: none">
                                <li><a href="${pageContext.request.contextPath}/edit">Create/Edit a Blog Post</a></li>
                                <li><a href="${pageContext.request.contextPath}/page/edit">Create/Edit a Page</a></li>
                            </ul>
                        </div>
                    

                </div>
            </div>  <!-- footer here -->

            <jsp:include page="footerFragment.jsp"/>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery.mjs.nestedSortable.js"></script>
        <script src="${pageContext.request.contextPath}/scripts/adminConsole.js"></script>
    </body>
</html>
