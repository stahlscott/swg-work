<%-- 
    Document   : loginPage
    Created on : 19-Apr-2016, 11:42:22 PM
    Author     : Adam Coate <adamcoate1@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <jsp:include page="headerFragment.jsp"/>
        <span id="page-context" data-context="${pageContext.request.contextPath}"></span>

            <div class="row">
                <div id="login-content" class="col-sm-8"> 
                    <div class ="panel panel-default">
                        <div class="panel-heading"><h4><strong>Login</strong></h4>

                        </div>
                        <div class="panel-body">
                            <div class="col-sm-4">
                                <div class="form-search">
                                    <br>
                                    <input type="text" class="form-control" id="login-name" placeholder="Username" /><br>
                                    <input type="password" class="form-control" id="login-password" placeholder="Password" /><br/>
                                    <button type="button" id="login-button" class="btn btn-success">Submit</button>

                                    <!--placeholder create/edit link until user needs to log in --><br>
                                    <em><a href="${pageContext.request.contextPath}/edit">Create A New Post</a></em><br>
                                    <em><a href="${pageContext.request.contextPath}/page/edit">Create A New Page</a></em><br>
                                    <em><a href="${pageContext.request.contextPath}/adminConsole">View Admin Console</a></em>
                                </div>

                            </div>
                        </div>
                    </div>
                </div><!-- end of main content -->

                <div class="col-sm-4" id="side-bar">
                    <jsp:include page="sidebarFragment.jsp"/>
                </div>
            </div>  <!-- footer here -->
            
            <jsp:include page="footerFragment.jsp"/>
        </div>
    </body>
</html>
