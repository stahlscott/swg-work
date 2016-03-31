<%-- 
    Document   : newDvdFormNoAjax
    Created on : Mar 29, 2016, 3:34:49 PM
    Author     : Scott Stahl <stahl.scott@gmail.com>
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Dvd Library</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>Dvd Library</h1>
            <hr/>
        </div>
        <div class="container">
            <h1>New Dvd Form</h1>
            <a href="displayDvdListNoAjax">Dvd List (No Ajax)</a>
            <hr/>
            <sf:form class="form-horizontal" modelAttribute="dvd"
                  role="form"
                  action="addNewDvdNoAjax"
                  method="POST">
                <div class="form-group">
                    <label for="add-title" class="col-md-4 control-label">Title:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-title" path="title" placeholder="Title"/>
                        <sf:errors path="title" cssClass="text-danger"></sf:errors>
                    </div>
                </div>

                <div class="form-group">
                    <label for="add-release-date" class="col-md-4 control-label">Release Date:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-release-dat" path="releaseDate" placeholder="mm-dd-yyyy"/>
                        <sf:errors path="releaseDate" cssClass="text-danger"></sf:errors>
                    </div>
                </div>

                <div class="form-group">
                    <label for="add-mpaa-rating" class="col-md-4 control-label">MPAA Rating:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-mpaa-rating" path="mpaaRating" placeholder="G, PG, PG-13, R"/>
                        <sf:errors path="mpaaRating" cssClass="text-danger"></sf:errors>
                    </div>
                </div>

                <div class="form-group">
                    <label for="add-studio" class="col-md-4 control-label">Studio:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-studio" path="studio" placeholder="Producing Studio"/>
                        <sf:errors path="studio" cssClass="text-danger"></sf:errors>
                    </div>
                </div>

                <div class="form-group">
                    <label for="add-director" class="col-md-4 control-label">Director: </label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-director" path="director" placeholder="Steven Spielberg"/>
                        <sf:errors path="director" cssClass="text-danger"></sf:errors>
                    </div>
                </div>

                <div class="form-group">
                    <label for="add-reviewer-rating" class="col-md-4 control-label">Reviewer Rating:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-reviewer-rating" path="reviewerRating" placeholder="1-10"/>
                        <sf:errors path="reviewerRating" cssClass="text-danger"></sf:errors>
                    </div>
                </div>

                <div class="form-group">
                    <label for="add-notes" class="col-md-4 control-label">Notes:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-notes" path="notes" placeholder="Great flick!"/>
                        <sf:errors path="notes" cssClass="text-danger"></sf:errors>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <button type="submit" id="add-button" class="btn btn-primary">Add New Dvd</button>
                    </div>
                </div>
            </sf:form>
        </div>
    </body>
</html>
