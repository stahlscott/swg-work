<%-- 
    Document   : singlePostPage
    Created on : Apr 24, 2016, 9:07:53 PM
    Author     : Chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Posts by Category | CMS</title>
        <!-- Bootstrap core CSS -->

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <span id="page-context" data-context="${pageContext.request.contextPath}"></span>
        <span id="post-id" data-post-id="${postId}"></span>
        <span id="category-id" data-category-id="${categoryId}"></span>

        <div class="container">
            <jsp:include page="headerFragment.jsp"/>
<!--            <div class="alert alert-info text-left">
                Viewing all posts within "<span id="category-id" style="font-weight: bold;" data-category-id="${categoryId}"></span>"

            </div>-->
            <div class="row">

                <div id="main-content" class="col-sm-8"> 

                </div>

                <div class="col-sm-4" id="side-bar">
                    <jsp:include page="sidebarFragment.jsp"/>
                </div>
            </div> 
            <jsp:include page="footerFragment.jsp"/>
        </div>

        <jsp:include page="postPageFragment.jsp"/>
        
        <p class="hidden" id="pc">${pageContext.request.contextPath}</p>
        <script src="${pageContext.request.contextPath}/scripts/singleCategory.js"></script>
    </body>
</html>