<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>JSP Site Lab</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/bootstrap-theme.css">
    </head>

    <body>
        <div class="container-fluid">
            <jsp:include page="navbar.jsp"/>
            <div class="row">
                <div class="col-xs-1"></div>
                <div class="jumbotron col-xs-10 text-center">
                    <h4 style="color:red">${errMessage}</h4>
                    <c:choose>
                        <c:when test="${empty convertedAmount}">
                            <h5>Choose a unit type and let's do some conversions!</h5>
                        </c:when>
                        <c:otherwise>
                            <h4>${amount} ${unit1type} = ${convertedAmount} ${unit2type}</h4>
                        </c:otherwise>
                    </c:choose>
                    <form action="UnitConv" class="form-horizontal" method="POST">
                        <div class="form-group">
                            <label class="col-xs-2 text-text-right">Unit type:</label>
                            <div class="col-xs-10 text-left">
                                <select id="dd1" name="dd1" onchange="configureDropDownLists(this, document.getElementById('dd2'), document.getElementById('dd3'))" style="width: 79%">
                                    <option value=""></option>
                                    <option value="Temperature">Temperature</option>
                                    <option value="Length">Length</option>
                                    <option value="Mass">Mass</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-2 text-right">Convert from</label>
                            <div class="col-xs-10 text-left">

                                <select id="dd2" name="dd2" style="width: 33%"></select>
                                to <select id="dd3" name="dd3" style="width: 33%"></select>
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="amount" class="col-xs-2 text-center">Amount to convert: </label>
                            <div class="col-xs-8">
                                <input type="text" class="form-control" id="amount" name="amount" placeholder="Enter amount" />
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <button type="submit" class="btn btn-primary">Convert!</button>
                        </div>
                    </form>
                </div>
                <div class="col-xs-1"></div>
            </div>

            <%@ include file="footer.jsp"%>
        </div>
        <script src="scripts/unitConv.js"></script>
        <script src="js/jquery-2.1.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>


</html>