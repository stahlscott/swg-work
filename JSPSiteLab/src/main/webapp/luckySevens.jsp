
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
                        <c:when test="${empty rolls}">
                            <h5>Roll the dice! When you roll a 7, you win $4. Anything else, you lose $1.</h5>
                            <h5>We'll roll until you are out of money.</h5>
                            <form action="LuckySevens" method="POST">
                                <div class="form-group">
                                    <label for="dollars" class="col-xs-2 text-right">Starting bet: </label>
                                    <div class="col-xs-8">
                                        <input type="text" class="form-control" id="dollars" name="dollars" placeholder="Enter starting bet" />
                                    </div>
                                    <br/>
                                    <br/>
                                    <div class="row">
                                        <button type="submit" class="btn btn-primary">Click to play Lucky Sevens!</button>
                                    </div>
                                </div>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <h4>You started with $${startingBet}...</h4>
                            <h4>It took you ${rolls} rolls to run out of money.</h4>
                            <h4>You should've stopped at ${rollsAtMax} when you had $${maxMoney}!</h4>
                            <br/>
                            <form action="LuckySevens" method="GET">
                                <button type="submit" class="btn btn-primary">Click to play again!</button>
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