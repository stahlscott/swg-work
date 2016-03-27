
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
                        <c:when test="${empty factors}">
                            <h5>Enter a number and we'll give you the factors,</h5>
                            <h5>as well as whether it's prime (no factors besides 1 and itself)</h5>
                            <h5>or perfect (sum of the factors equals the number).</h5>
                            <form action="Factorizor" method="POST">
                                <div class="form-group">
                                    <label for="num" class="col-xs-2 text-right">Number: </label>
                                    <div class="col-xs-8">
                                        <input type="text" class="form-control" id="num" name="num" placeholder="Enter number to factorize!" />
                                    </div>
                                    <br/>
                                    <br/>
                                    <div class="row">
                                        <button type="submit" class="btn btn-primary">Click to Factorize</button>
                                    </div>
                                </div>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <h4>The factors of ${num} are:</h4>
                            <h4><c:forEach var="factor" items="${factors}">
                                    <c:out value="${factor}"/>
                                </c:forEach></h4>
                                <c:if test="${perfect}">
                                <h4>${num} is a perfect number.</h4>
                            </c:if>
                            <c:if test="${prime}">
                                <h4>${num} is a prime number.</h4>
                            </c:if>
                            <br/>
                            <form action="Factorizor">
                                <button type="submit" class="btn btn-primary">Click to Factorize again</button>
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