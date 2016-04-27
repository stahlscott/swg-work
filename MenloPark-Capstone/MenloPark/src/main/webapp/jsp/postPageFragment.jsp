<%-- 
    Document   : postPageFragment
    Created on : Apr 19, 2016, 9:16:23 PM
    Author     : Chris
--%>
<span id="page-context" data-context="${pageContext.request.contextPath}"></span>

<div class="modal fade modal-wide" id="postPageModal" tabindex="-1" role="dialog" aria-labelledby="postPageModalLabel" aria-hidden="true" style="display:none;">
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <strong><h2 id="post-title"></h2></strong>
            </div>

            <div class="modal-body" >

                <!-- start actual blog post -->

                <div class="panel panel-default">
                    <div class="panel-heading">

                        <h5>
                            <span id="post-month"></span>-<span id="post-date"></span>-<span id="post-year"></span> | Posted by <span id="post-user"></span>
                            <span id="post-categories"></span><span class="pull-right" id="bookmark"></span>
                        </h5>
                    </div>
                    <div class="panel-body" >


                        <div class="row">
                            <div id="main-content" style="padding: 20px;" > 
                                <span id="post-body"></span>
                            </div><!-- end of main content -->
                        </div><!--end row-->

                    </div>

                </div>

                <div class="modal-footer">
                    <div class="text-left"><h4><small><span id="post-tags"></span></small></h4></div>
                    <div class="pull-right"><button type="button" class="btn btn-default" data-dismiss="modal">Close</button></div>
                    <p id="data-post-id" class="hidden"></p>
                </div>
            </div>
        </div>
    </div>










    <!-- footer here -->

</div>
