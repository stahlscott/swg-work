<%-- 
    Document   : singlePage
    Created on : Apr 25, 2016, 5:53:40 PM
    Author     : Scott Stahl <stahl.scott@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title></title>
        <!-- Bootstrap core CSS -->

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <span id="page-context" data-context="${pageContext.request.contextPath}"></span>
        <span id="page-id" data-page-id="${pageId}"></span>
        <div class="container">
            <jsp:include page="headerFragment.jsp"/>

            <div class="row">
                <div id="main-content" class="col-sm-8"> 

                </div>

                <div class="col-sm-4" id="side-bar">
                    <jsp:include page="sidebarFragment.jsp"/>
                </div>
            </div> 
            <jsp:include page="footerFragment.jsp"/>
        </div>
        <script src="${pageContext.request.contextPath}/scripts/singlePage.js"></script>
    </body>
</html>