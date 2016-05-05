<%-- 
    Document   : postPreviewFragment
    Created on : May 3, 2016, 2:26:19 PM
    Author     : Scott Stahl <stahl.scott@gmail.com>
--%>

<div class="modal fade modal-wide" id="postPreviewModal" tabindex="-1" role="dialog" aria-labelledby="postPageModalLabel" aria-hidden="true" style="display:none;">
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <!--                <strong><h2 id="post-title"></h2></strong>-->
            </div>

            <div class="modal-body" >

                <!-- start actual blog post -->
                <div class="row"  >
                    <div class="col-sm-3" style="padding-right: 0px;">
                        <a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/assets/mountains2.png" style="" alt="image" class="img-responsive" /></a>
                    </div>
                    <div class="col-sm-8">
                        <a href="${pageContext.request.contextPath}/"><h1>CMS Camping Store Ltd</h1></a>
                    </div>
                </div>
                <div class="container">
                    <nav id="nav-preview" class="navbar navbar-default affix-top" data-spy="affix" data-offset-top="165">
                        <div class="container-fluid">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                                <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                                    <span class="glyphicon glyphicon-home" style="font-size: 18px;"></span>
                                </a>
                            </div>
                            <div class="collapse navbar-collapse" id="navbar-preview">
                                <ul id="nav-main" class="nav navbar-nav sm sm-simple">
                                    <li id="nav-page-none"><a href="#">About Us</a></li>
                                    <li id="nav-page-none1"><a href="#">Hours</a></li>
                                    <li id="nav-page-none2"><a href="#">Contact Us</a></li>
                                </ul>
                            </div>
                        </div>
                    </nav>

                    <div class="row">
                        <div id="preview-content" class="col-sm-8"> 

                        </div>

                        <!--                        <div class="col-sm-4" id="side-bar">
                        <%--<jsp:include page="sidebarFragment.jsp"/>--%>
                    </div>-->
                    </div> 
                    <nav class="footer navbar-fixed-bottom">
                        <div class="container">
                            <br><p class="text-center">
                                &copy; 2016 Les Sportinggoods | 
                            <sec:authorize access="isAuthenticated()">
                                <a href="${pageContext.request.contextPath}/editor/console">User Console</a> | 
                                <a href="${pageContext.request.contextPath}/j_spring_security_logout">Log Out</a> 
                            </sec:authorize>
                            <%--<sec:authorize access="isAnonymous()">--%>
                            | <a href= "${pageContext.request.contextPath}/login">Login</a>
                            <%--</sec:authorize>--%>
                            </p>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>