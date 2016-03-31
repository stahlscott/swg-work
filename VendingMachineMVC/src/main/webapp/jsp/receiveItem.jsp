<%-- 
    Document   : receiveItem
    Created on : Mar 31, 2016, 11:29:50 AM
    Author     : Scott Stahl <stahl.scott@gmail.com>
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Vending Machine</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/stylesheet.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container-fluid">
            <hr/>
            <div class="jumbotron text-center">
                <h2>
                    <c:if test="${!empty successMsg}">
                        <span class="label label-success">
                            ${successMsg}
                        </span><br/><br/>
                        <img src="${pageContext.request.contextPath}${imgUrl}" width="250"/><br/><br/>
                    </c:if>
                    <c:if test="${!empty errMsg}">
                        <span class="label label-danger">
                            ${errMsg}
                        </span><br/><br/>
                    </c:if>
                </h2>
                <h4>
                    <span class="label label-info">
                        You got back: 
                        <c:if test="${quarters > 0}">
                            <span class="badge">${quarters}</span> quarter(s) 
                        </c:if>
                        <c:if test="${dimes > 0}">
                            <span class="badge">${dimes}</span> dime(s) 
                        </c:if>
                        <c:if test="${nickels > 0}">
                            <span class="badge">${nickels}</span> nickel
                        </c:if>
                        <c:if test="${quarters == 0 && dimes == 0 && nickels == 0}">
                            no change at all.
                        </c:if>
                    </span>
                </h4>
            </div>

        </div>
        <div class="navbar navbar-inverse navbar-fixed-bottom">
            <br/>
            <div class="container-fluid">
                <form role="form" action="vend" class="text-center" id="vend">
                    <button class="btn btn-primary">HAVE ANOTHER <span class="glyphicon glyphicon-thumbs-up"></span></button>
                </form>

                <br/><br/>
                <div class="text-center">
                    <span class="label label-default">
                        (c) 2016 scott stahl / the software guild
                    </span>
                </div>
            </div>
        </div>
        <br/>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/scripts/scripts.js"></script>
    </body>
</html>
