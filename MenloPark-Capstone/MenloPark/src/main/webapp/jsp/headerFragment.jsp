<%-- 
    Document   : navFragment
    Created on : Apr 20, 2016, 12:44:47 PM
    Author     : Scott Stahl <stahl.scott@gmail.com>
--%>

<div class="row"  >
    <div class="col-sm-3" style="padding-right: 0px;">
        <a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/assets/mountains2.png" style="" id="header-img" alt="image" class="img-responsive" /></a>
        <link href='https://fonts.googleapis.com/css?family=Carter+One' rel='stylesheet' type='css/styles.css' />
        <link href='https://fonts.googleapis.com/css?family=Contrail+One' rel='stylesheet' type='css/styles.css'>
        <link href='https://fonts.googleapis.com/css?family=Cutive' rel='stylesheet' type='css/styles.css'>
        <link href='https://fonts.googleapis.com/css?family=Hanalei' rel='stylesheet' type='css/styles.css'>
        <link href='https://fonts.googleapis.com/css?family=Hanalei+Fill' rel='stylesheet' type='css/styles.css'>
        <link href='https://fonts.googleapis.com/css?family=Walter+Turncoat' rel='stylesheet' type='css/styles.css'>
<!--        <link href="${pageContext.request.contextPath}/css/sm-core-css.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/sm-simple.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/jquery.smartmenus.bootstrap.css" rel="stylesheet">-->
    </div>
    <div class="col-sm-8">
        <a href="${pageContext.request.contextPath}/"><h1>CMS Camping Store Ltd</h1></a>
    </div>
</div>

<hr/>
<nav id="nav" class="navbar navbar-default affix-top" data-spy="affix" data-offset-top="165">
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
        <div class="collapse navbar-collapse" id="navbar">
            <ul id="nav-main" class="nav navbar-nav sm sm-simple"></ul>
        </div>
    </div>
</nav>