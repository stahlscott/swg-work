<%-- 
    Document   : singleTags
    Created on : 23-Apr-2016, 7:43:55 PM
    Author     : Adam Coate <adamcoate1@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Posts by Tag | CMS</title>
        <!-- Bootstrap core CSS -->

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <span id="page-context" data-context="${pageContext.request.contextPath}"></span>
        <span id="post-id" data-post-id="${postId}"></span>
        <span id="tag-id" data-tag-id="${tagId}" data-tag-name="${tagName}"></span>

        <div class="container">
            <jsp:include page="headerFragment.jsp"/>
            <div class="alert alert-info text-left">
                Viewing all posts within tag "<span id="display-tag-name" style="font-weight: bold;">${tagName}</span>"
            </div>

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

        <script src="${pageContext.request.contextPath}/scripts/singleTag.js"></script>

    </body>
</html>