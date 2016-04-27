<!DOCTYPE html>
<html>
    <head>
        <title>Dvd Library</title>
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.css"
              rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>Dvd Library</h1>
            <hr/>
            <div class="row">
                <div class="col-md-6">
                    <div id="dvdTableDiv">
                        <h2>Dvd Library</h2>

                        <table id="dvdTable" class="table table-hover">
                            <tr>
                                <th width="40%">Title</th>
                                <th width="30%">Release Date</th>
                                <th width="15%"></th>
                                <th width="15%"></th>
                            </tr>
                            <tbody id="contentRows"></tbody>
                        </table>
                    </div>
                </div>
                <div class="col-md-6">

                    <div id="editFormDiv" style="display: none">
                        <h2 onclick="hideEditForm()">Edit Dvd</h2>

                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="add-title" class="col-md-4 control-label">
                                    Title:
                                </label>

                                <div class="col-md-8">
                                    <input type="text"
                                           class="form-control"
                                           id="edit-title"
                                           placeholder="Title"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-release-date" class="col-md-4 control-label">
                                    Release Date:
                                </label>

                                <div class="col-md-8">
                                    <input type="text"
                                           class="form-control"
                                           id="edit-release-date"
                                           placeholder="Release Date"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-mpaa-rating" class="col-md-4 control-label">
                                    MPAA Rating:</label>

                                <div class="col-md-8">
                                    <input type="text"
                                           class="form-control"
                                           id="edit-mpaa-rating"
                                           placeholder="MPAA Rating"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-studio" class="col-md-4 control-label">Studio:</label>

                                <div class="col-md-8">
                                    <input type="email"
                                           class="form-control"
                                           id="edit-studio"
                                           placeholder="Studio"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-director" class="col-md-4 control-label">Director:</label>

                                <div class="col-md-8">
                                    <input type="tel"
                                           class="form-control"
                                           id="edit-director"
                                           placeholder="Director"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-reviewer-rating" class="col-md-4 control-label">Reviewer Rating:</label>

                                <div class="col-md-8">
                                    <input type="tel"
                                           class="form-control"
                                           id="edit-reviewer-rating"
                                           placeholder="1-10"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-notes" class="col-md-4 control-label">Notes:</label>

                                <div class="col-md-8">
                                    <input type="tel"
                                           class="form-control"
                                           id="edit-notes"
                                           placeholder="Notes"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-4">
                                    <input type="hidden" id="edit-dvd-id">
                                    <button type="button"
                                            id="edit-cancel-button"
                                            class="btn btn-default"
                                            onclick="hideEditForm()">
                                        Cancel
                                    </button>
                                </div>
                                <div class="col-md-4">
                                    <button type="submit"
                                            id="edit-button"
                                            class="btn btn-default">
                                        Update Dvd
                                    </button>
                                </div>
                            </div>

                        </form>
                    </div>
                    <div id="addFormDiv">

                        <h2>Add New Dvd</h2>
                        <!-- THIS NEEDS TO BE EDITED IF USING -->
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="add-title" class="col-md-4 control-label">
                                    Title:
                                </label>

                                <div class="col-md-8">
                                    <input type="text"
                                           class="form-control"
                                           id="add-title"
                                           placeholder="Title"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-release-date" class="col-md-4 control-label">
                                    Release Date:
                                </label>

                                <div class="col-md-8">
                                    <input type="text"
                                           class="form-control"
                                           id="add-release-date"
                                           placeholder="Release Date"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-mpaa-rating" class="col-md-4 control-label">
                                    MPAA Rating:</label>

                                <div class="col-md-8">
                                    <input type="text"
                                           class="form-control"
                                           id="add-mpaa-rating"
                                           placeholder="MPAA Rating"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-studio" class="col-md-4 control-label">Studio:</label>

                                <div class="col-md-8">
                                    <input type="email"
                                           class="form-control"
                                           id="add-studio"
                                           placeholder="Studio"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-director" class="col-md-4 control-label">Director:</label>

                                <div class="col-md-8">
                                    <input type="tel"
                                           class="form-control"
                                           id="add-director"
                                           placeholder="Director"/>
                                </div>
                            </div>
                            <div class="form-reviewer-rating">
                                <label for="add-zip" class="col-md-4 control-label">Reviewer Rating:</label>

                                <div class="col-md-8">
                                    <input type="tel"
                                           class="form-control"
                                           id="add-reviewer-rating"
                                           placeholder="1-10"/>
                                </div>
                            </div>
                            <div class="form-notes">
                                <label for="add-zip" class="col-md-4 control-label">Notes:</label>

                                <div class="col-md-8">
                                    <input type="tel"
                                           class="form-control"
                                           id="add-notes"
                                           placeholder="Notes"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button type="submit"
                                            id="add-button"
                                            class="btn btn-default">
                                        Create Dvd
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div> <!-- End col div -->
            </div> <!-- End row div -->
        </div>
        <!-- #5: Placed at the end of the document so the pages load faster -->
        <script src="js/jquery-1.11.1.min.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="scripts/restDvdLibrary.js"></script>
    </body>
</html>