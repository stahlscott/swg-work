<%-- 
    Document   : customError
    Created on : May 3, 2016, 10:36:49 AM
    Author     : Scott Stahl <stahl.scott@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page | CMS</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <span id="page-context" data-context="${pageContext.request.contextPath}"></span>
        <div class="container">
            <jsp:include page="headerFragment.jsp"/>
            <div>
                <h1>An error has occurred...</h1>
                <h3>${errorMessage}</h3>
            </div> 
            <jsp:include page="footerFragment.jsp"/>
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
        </div>
    </body>
</html>