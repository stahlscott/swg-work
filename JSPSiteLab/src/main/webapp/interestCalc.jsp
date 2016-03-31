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
                        <c:when test="${empty interests}">
                            <h5>Give us your starting principal, annual interest rate,</h5>
                            <h5>and number of years to invest, and we'll do the rest!</h5>
                            <form action="InterestCalc" class="form-horizontal" method="POST">
                                <div class="form-group">
                                    <label for="principal" class="col-xs-2 text-right">Principal: </label>
                                    <div class="col-xs-8">
                                        <input type="text" class="form-control" id="principal" name="principal" placeholder="Enter principal" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="interest" class="col-xs-2 text-right">Interest rate: </label>
                                    <div class="col-xs-8">
                                        <input type="text" class="form-control" id="interest" name="interest" placeholder="Enter annual interest rate" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="years" class="col-xs-2 text-right">Years: </label>
                                    <div class="col-xs-8">
                                        <input type="text" class="form-control" id="years" name="years" placeholder="Enter number of years" />
                                    </div>
                                </div>
                                <br/>
                                <br/>
                                <div class="row">
                                    <button type="submit" class="btn btn-primary">Calculate Interest</button>
                                </div>

                            </form>
                        </c:when>
                        <c:otherwise>

                            <div class="row">
                                <div class="col-xs-3">
                                    <h4>Year</h4>
                                </div>
                                <div class="col-xs-3">
                                    <h4>Beginning Principal</h4>
                                </div>
                                <div class="col-xs-3">
                                    <h4>Ending Principal</h4>
                                </div>
                                <div class="col-xs-3">
                                    <h4>Interest Earned</h4>
                                </div>
                            </div>
                            <c:forEach var="interest" items="${interests}">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <h5><c:out value="${interest.year}"/></h5>
                                    </div>
                                    <div class="col-xs-3">
                                        <h5><c:out value="${interest.beginningPrincipal}"/></h5>
                                    </div>
                                    <div class="col-xs-3">
                                        <h5><c:out value="${interest.endingPrincipal}"/></h5>
                                    </div>
                                    <div class="col-xs-3">
                                        <h5><c:out value="${interest.interestEarned}"/></h5>
                                    </div>
                                </div>
                            </c:forEach>

                            <br/>
                            <form action="InterestCalc">
                                <button type="submit" class="btn btn-primary">Click to Calculate Interest again</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="col-xs-1"></div>
            </div>
            <!--for some reason include didn't render right here--> 
            <div class="page-footer text-center">
                <small>Created by Scott Stahl 2016<br>
                    Powered by Java and Bootstrap!</small>
            </div>
        </div>
        <script src="js/jquery-2.1.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>
</html>