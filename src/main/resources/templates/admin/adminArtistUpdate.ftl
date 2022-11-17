<#import "../parts/common.ftl" as c>

<@c.page "null" "" "">
    <hr>
    <div><h4>Update Artist</h4></div>
    <hr>
    <br>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="number" name="artist_id" placeholder="Artist id">
            <input type="text" name="nickname" placeholder="Artist nickname" required>
            <input type="text" name="email" placeholder="Email">
            <input type="text" name="phone" placeholder="Phone">
            <div style="margin-top: 5px; margin-left: 5px" class="row">
                <h7 style="margin-right: 10px">Avatar</h7>
                <input type="file" name="avatarFile" required>
            </div>
            <div style="margin-top: 5px; margin-left: 5px" class="row">
                <h7 style="margin-right: 10px">Page photo</h7>
                <input type="file" name="pageFile" required>
            </div>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button type="submit" style="margin-top: 5px">Update</button>
        </form>
    </div>
</@c.page>
