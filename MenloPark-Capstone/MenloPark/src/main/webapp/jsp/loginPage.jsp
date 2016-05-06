<%-- 
    Document   : loginPage
    Created on : 19-Apr-2016, 11:42:22 PM
    Author     : Adam Coate <adamcoate1@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <div class="col-md-4">
                                <c:if test="${param.login_error == 1}">
                                    <h4 style="color: red;">Username or password incorrect.</h4>
                                </c:if>
                                <br>
                                <form method="POST" action="j_spring_security_check">
                                    <fieldset>
                                        <div class="inner-addon left-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                            <input type="text" name="j_username" class="form-control" id="login-name" placeholder="Username" />
                                        </div><br/>
                                        <div class="inner-addon left-addon">
                                            <span class="glyphicon glyphicon-lock"></span>
                                            <input type="password" name="j_password" class="form-control" id="login-password" placeholder="Password" />
                                        </div><br/>
                                        <input type="submit" name="commit" id="login-button" class="btn btn-success" value="Log In" />
                                    </fieldset>
                                </form>
                            </div>



                        </div>
                    </div>
                </div><!-- end of main content -->

                <div class="col-sm-4" id="side-bar">
                    <%--<jsp:include page="sidebarFragment.jsp"/>--%>
                </div>
            </div>  <!-- footer here -->

            <jsp:include page="footerFragment.jsp"/>
        </div>
    </body>
</html>
