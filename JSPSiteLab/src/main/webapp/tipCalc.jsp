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
                        <c:when test="${empty totalAmt}">
                            <h5>Enter the following information to calculate a tip.</h5>
                            <form action="TipCalc" class="form-horizontal" method="POST">
                                <div class="form-group">
                                    <label for="billAmt" class="col-xs-2 text-right">Amount: </label>
                                    <div class="col-xs-8">
                                        <input type="text" class="form-control" id="billAmt" name="billAmt" placeholder="Enter bill amount" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="tipPct" class="col-xs-2 text-right">Tip %: </label>
                                    <div class="col-xs-8">
                                        <input type="text" class="form-control" id="tipPct" name="tipPct" placeholder="Enter tip as a %" />
                                    </div>
                                </div>
                                <br/>
                                <br/>
                                <div class="row">
                                    <button type="submit" class="btn btn-primary">Calculate Tip</button>
                                </div>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <h4>Bill amount: $${billAmt}</h4>
                            <h4>Tip %: ${tipPct}%</h4>
                            <h4>Tip $${tipAmt}</h4>
                            <h4>Total $${totalAmt}</h4>
                            <br/>
                            <form action="TipCalc">
                                <button type="submit" class="btn btn-primary">Click to calculate another tip</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-xs-1"></div>
            </div>

            <%@ include file="footer.jsp"%>
        </div>
        <script src="js/jquery-2.1.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>


</html>