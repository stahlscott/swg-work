<%-- 
    Document   : vend-ajax
    Created on : Apr 6, 2016, 9:36:49 AM
    Author     : Scott Stahl <stahl.scott@gmail.com>
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
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
            <!--<h1>Vending Machine</h1>-->
            <hr/>

            <div id="contentRows"></div>

            <hr/><br/>
            <div class="vend-menu" style="display:none">
                <br/><br/><br/><br/><br/><br/><br/><br/>
            </div>
            <div class="text-center">
                <span class="btn btn-default nav-toggle">SHOW MENU</span><br/><br/>
                <span class="label label-default">
                    (c) 2016 scott stahl / the software guild
                </span>
            </div>
        </div>
        <div class="navbar navbar-inverse navbar-fixed-bottom vend-menu" style="display:none">
            <br/>
            <div class="container-fluid">
                <form role="form" action="vendItem" method="POST" class="text-center" id="submitSelection">
                    <div class="row">
                        <div class="col-xs-6 text-center selectionCol">
                            <div class="panel panel-default">
                                <div class="panel panel-body">
                                    <div id="vend-result">
                                        <br/><br/><br/><br/><br/><br/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6 text-center selectionCol">                      
                            <span class="btn btn-default addMoney" data-change-val="1.0">+ $1.00</span>
                            <span class="btn btn-default addMoney" data-change-val=".25">+ $0.25</span><br/><br/>
                            <span class="btn btn-default addMoney" data-change-val=".1">+ $0.10</span>
                            <span class="btn btn-default addMoney" data-change-val=".05">+ $0.05</span><br/><br/>
                            <span class="digitalText">DEPOSITED:</span>
                            <input type="text" id="totalDeposit" name="totalDeposit" size="10" readonly/> 
                        </div>
                        <hr/>
                    </div>
                    <hr/>   
                    <div class="text-center">
                        <span class="btn btn-danger coin-return" onclick="coinReturn()">COIN RETURN</span>
                        <span class="btn btn-default nav-toggle">HIDE MENU</span>
                    </div>
                </form>
                <br/>
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
        <script src="${pageContext.request.contextPath}/scripts/vendAjax.js"></script>
        <!--<script src="${pageContext.request.contextPath}/scripts/scripts.js"></script>-->

    </body>
</html>