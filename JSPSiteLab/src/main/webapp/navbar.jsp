<%-- 
    Document   : navbar
    Created on : Mar 25, 2016, 2:06:14 PM
    Author     : Scott Stahl <stahl.scott@gmail.com>
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="page-header text-center">
    <h4>Software Guild Java Cohort</h4>
    <h4>JSP Site Lab</h4>
</div>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#tsg-jsp-navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse" id="tsg-jsp-navbar">
            <ul class="nav navbar-nav">
                <li class="${pageContext.request.requestURI eq pageContext.request.contextPath.concat('/') ? 'active' : 'none'}"><a href="/JSPSiteLab">Home</a></li>
                <li class="${pageContext.request.requestURI eq pageContext.request.contextPath.concat('/luckySevens.jsp') ? 'active' : 'none'}"><a href="LuckySevens">Lucky Sevens</a></li>
                <li class="${pageContext.request.requestURI eq pageContext.request.contextPath.concat('/factorizor.jsp') ? 'active' : 'none'}"><a href="Factorizor">Factorizor</a></li>
                <li class="${pageContext.request.requestURI eq pageContext.request.contextPath.concat('/interestCalc.jsp') ? 'active' : 'none'}"><a href="InterestCalc">Interest Calculator</a></li>
                <li class="${pageContext.request.requestURI eq pageContext.request.contextPath.concat('/flooringCalc.jsp') ? 'active' : 'none'}"><a href="FlooringCalc">Flooring Calculator</a></li>
                <li class="${pageContext.request.requestURI eq pageContext.request.contextPath.concat('/tipCalc.jsp') ? 'active' : 'none'}"><a href="TipCalc">Tip Calculator</a></li>
                <li class="${pageContext.request.requestURI eq pageContext.request.contextPath.concat('/unitConv.jsp') ? 'active' : 'none'}"><a href="UnitConv">Unit Converter</a></li>
            </ul>
        </div>
    </div>
</nav>