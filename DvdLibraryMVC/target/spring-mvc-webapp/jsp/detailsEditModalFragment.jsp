<%-- 
    Document   : detailsEditModalFragment
    Created on : Apr 7, 2016, 1:39:42 PM
    Author     : Scott Stahl <stahl.scott@gmail.com>
--%>
<div class="modal fade" id="detailsModal" tabindex="-1" role="dialog" aria-labelledby="detailsModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
            </div>
            <div class="modal-body">
                <h3 id="dvd-id"></h3>
                <table class="table table-bordered">
                    <tr>
                        <th>Title: </th>
                        <td id="dvd-title"></td>
                    </tr>
                    <tr>
                        <th>Release Date: </th>
                        <td id="dvd-release-date"></td>
                    </tr>
                    <tr>
                        <th>MPAA Rating: </th>
                        <td id="dvd-mpaa-rating"></td>
                    </tr>
                    <tr>
                        <th>Studio: </th>
                        <td id="dvd-studio"></td>
                    </tr>
                    <tr>
                        <th>Director: </th>
                        <td id="dvd-director"></td>
                    </tr>
                    <tr>
                        <th>Reviewer Rating: </th>
                        <td id="dvd-reviewer-rating"></td>
                    </tr>
                    <tr>
                        <th>Notes: </th>
                        <td id="dvd-notes"></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="editModalLabel">Edit Dvd</h4>
            </div>
            <div class="modal-body">
                <h3 id="edit-dvd-id"></h3>
                <form class="horizontal" role="form">
                    <div class="form-group">
                        <label for="edit-title" class="col-md-4 control-label">Title: </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-title" placeholder="Title"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-release-date" class="col-md-4 control-label">Release Date: </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-release-date" placeholder="Release Date"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-mpaa-rating" class="col-md-4 control-label">MPAA Rating: </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-mpaa-rating" placeholder="MPAA Rating"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-studio" class="col-md-4 control-label">Studio: </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-studio" placeholder="Studio"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-director" class="col-md-4 control-label">Director: </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-director" placeholder="Director"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-reviewer-rating" class="col-md-4 control-label">Reviewer Rating: </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-reviewer-rating" placeholder="1-10"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-ntoes" class="col-md-4 control-label">Notes: </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-notes" placeholder="Notes"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button type="button" id="edit-button" class="btn btn-primary" data-dismiss="modal">Edit Dvd</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer"></div>
        </div>
    </div>
</div>