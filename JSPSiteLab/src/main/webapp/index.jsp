
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

                    <h2>Click above to get started!</h2>

                </div>
                <div class="col-xs-1"></div>
            </div>

            <jsp:include page="footer.jsp"/>
        </div>
        <script src="js/jquery-2.1.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>


</html>