<#import "../parts/common.ftl" as c>

<@c.page "null" "" "">
    <hr>
    <div><h4>Update Album</h4></div>
    <hr>
    <br>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="number" name="album_id" placeholder="Album id">
            <input type="text" name="name" placeholder="Album name" required>
            <input type="date" name="release_date" placeholder="Release date" required>
            <input type="text" name="style" placeholder="Style" required>
            <input type="number" name="artist_id" placeholder="Artist id" required>
            <input type="file" name="file" required>
            <br>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <div style="margin-top: 5px; margin-left: 5px" class="row">
                <input type="checkbox" name="edit_songs">
                <h7 style="margin-right: 10px">Create new songs</h7>
            </div>
            <button type="submit" style="margin-top:5px">Update</button>
        </form>
    </div>
    <br>
</@c.page>
