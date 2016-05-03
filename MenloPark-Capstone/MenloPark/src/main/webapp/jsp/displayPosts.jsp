<%-- 
    Document   : displayPosts
    Created on : Apr 19, 2016, 1:40:55 PM
    Author     : Adam & Chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page | CMS</title>
        <!-- Bootstrap core CSS -->

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <span id="page-context" data-context="${pageContext.request.contextPath}"></span>
        <div class="container">
            <jsp:include page="headerFragment.jsp"/>

            <div class="row">
                <div id="main-content" class="col-sm-8"> 
                </div><!-- end of main content -->

                <div class="col-sm-4" id="side-bar">
                    <jsp:include page="sidebarFragment.jsp"/>

                    
                </div>
            </div> 
            <jsp:include page="footerFragment.jsp"/>

        </div>

        <p class="hidden" id="pc">${pageContext.request.contextPath}</p>

        <jsp:include page="postPageFragment.jsp"/>
        <script src="${pageContext.request.contextPath}/scripts/displayPosts.js"></script>
    </body>
</html>