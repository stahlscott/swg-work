<%-- 
    Document   : adminConsole
    Created on : 26-Apr-2016
    Author     : Chris
--%>

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
                            <p class="text-muted">Populate posts with confirm / reject buttons; reject button pulls up a comment box</p>


                        </div>
                    </div>
                    <div class ="panel panel-default">
                        <div class="panel-heading"><h4><strong>Navbar Appearance</strong></h4>
                        </div>
                        <div class="panel-body">
                            <p class="text-muted"> // not yet. use ints for now; won't work for child pages. use draggable/sortable jquery UI</p>

                            <div class="col-sm-4">

                                <ul id="nav-index">


                                    <br><br>
                                    <button class="btn btn-primary" id="navbar-submit" type="submit">Confirm</button>
                                    </form>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4><strong>Sortable Pages</strong></h4>
                        </div>
                        <div class="panel-body">

                            <ol class="sortable" id="page-sort">
                            </ol>

                            <button type="button" class="btn btn-primary" onclick="submitPageOrder()">Submit</button>
                            <button type="button" class="btn btn-default" onclick="loadSortablePages()">Reset</button>
                        </div>
                    </div>
                </div><!-- end of main content -->

                <div class="col-sm-4" id="side-bar">
                    <jsp:include page="sidebarFragment.jsp"/>
                </div>
            </div>  <!-- footer here -->

            <jsp:include page="footerFragment.jsp"/>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery.mjs.nestedSortable.js"></script>
        <script src="${pageContext.request.contextPath}/scripts/adminConsole.js"></script>
    </body>
</html>
