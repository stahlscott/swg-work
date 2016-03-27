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
                        <c:when test="${empty totalCost}">
                            <h5>Enter the following information to calculate flooring cost.</h5>
                            <form action="FlooringCalc" class="form-horizontal" method="POST">
                                <div class="form-group">
                                    <label for="width" class="col-xs-2 text-right">Width (ft): </label>
                                    <div class="col-xs-8">
                                        <input type="text" class="form-control" id="width" name="width" placeholder="Enter width" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="length" class="col-xs-2 text-right">Length (ft): </label>
                                    <div class="col-xs-8">
                                        <input type="text" class="form-control" id="length" name="length" placeholder="Enter length" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="cost" class="col-xs-2 text-right">Cost per square foot: </label>
                                    <div class="col-xs-8">
                                        <input type="text" class="form-control" id="cost" name="cost" placeholder="Enter cost" />
                                    </div>
                                </div>
                                <br/>
                                <br/>
                                <div class="row">
                                    <button type="submit" class="btn btn-primary">Calculate Flooring Cost</button>
                                </div>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <h4>Material cost: $${materialCost}</h4>
                            <h4>Labor cost: $${laborCost}</h4>
                            <h4>Subtotal: $${totalCost}</h4>
                            <br/>
                            <form action="FlooringCalc">
                                <button type="submit" class="btn btn-primary">Click to Calculate Flooring Cost again</button>
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