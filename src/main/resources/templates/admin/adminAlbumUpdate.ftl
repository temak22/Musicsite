<#import "../parts/common.ftl" as c>

<@c.page>
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
            <button type="submit" style="margin-top:5px">Update</button>
        </form>
    </div>
    <br>
</@c.page>
