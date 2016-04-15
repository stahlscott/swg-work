<%-- 
    Document   : home
    Created on : Mar 28, 2016, 7:24:45 PM
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
        <title>Dvd Library</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>Dvd Library</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/home">Home</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/search">Search</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/stats">Stats</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayDvdListNoAjax">Dvd List (No Ajax)</a>
                    </li>
                </ul>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <h2>Dvd Library</h2>
                    <jsp:include page="dvdSummaryTableFragment.jsp"/>
                    <div id="edit-validation-errors" style="color: red"></div>
                </div>
                <div class="col-md-6">
                    <h2>Add New Dvd</h2>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="add-title" class="col-md-4 control-label">Title: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-title" placeholder="Title"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-release-date" class="col-md-4 control-label">Release Date: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-release-date" placeholder="Release Date"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-mpaa-rating" class="col-md-4 control-label">MPAA Rating: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-mpaa-rating" placeholder="MPAA Rating"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-studio" class="col-md-4 control-label">Studio: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-studio" placeholder="Studio"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-director" class="col-md-4 control-label">Director: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-director" placeholder="Director"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-reviewer-rating" class="col-md-4 control-label">Reviewer Rating: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-reviewer-rating" placeholder="1-10"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-notes" class="col-md-4 control-label">Notes: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-notes" placeholder="Notes"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button type="button"
                                        id="add-button"
                                        class="btn btn-primary">Create Dvd</button>
                            </div>
                        </div>
                    </form>
                    <div id="add-validation-errors" style="color: red"></div>
                </div>
            </div>
        </div>

        <jsp:include page="detailsEditModalFragment.jsp"/>

        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/scripts/dvdLibrary.js"></script>
    </body>
</html>
