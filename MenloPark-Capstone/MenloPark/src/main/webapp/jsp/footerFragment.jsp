<%-- 
    Document   : footerFragment
    Created on : Apr 20, 2016, 12:44:38 PM
    Author     : Scott Stahl <stahl.scott@gmail.com>
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="footer navbar-fixed-bottom">
    <div class="container">
        <br><p class="text-center" style="text-align: match-parent;">
            <a href="${pageContext.request.contextPath}/assets/LESSPORTINGGOODSresize.jpg">&copy;</a> 2016 Les Sportinggoods | 
            <sec:authorize access="isAuthenticated()">
                <a href="${pageContext.request.contextPath}/editor/console">User Console</a> | 
                <a href="${pageContext.request.contextPath}/j_spring_security_logout">Log Out</a> | 
            </sec:authorize>
            <%--<sec:authorize access="isAnonymous()">--%>
           <a href= "${pageContext.request.contextPath}/login">Login</a>
            <%--</sec:authorize>--%>
            </p>
    </div>
</nav>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <span id="role-admin"></span>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_EDITOR')">
    <span id="role-editor"></span>
</sec:authorize>
<span id="page-context" data-context="${pageContext.request.contextPath}"></span>

<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/scripts/header.js"></script>
<script src="${pageContext.request.contextPath}/scripts/displaySidebar.js"></script>