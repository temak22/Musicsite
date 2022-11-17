<#import "../parts/common.ftl" as c>

<@c.page "null" "" "">
    <hr>
    <div><h4>New Album</h4></div>
    <hr>
    <br>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="name" placeholder="Album name" required>
            <input type="date" name="release_date" placeholder="Release date" required>
            <input type="text" name="style" placeholder="Style" required>
            <input type="number" name="artist_id" placeholder="Artist id" required>
            <input type="file" name="file" required>
            <br>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button type="submit" style="margin-top:5px">Create</button>
        </form>
    </div>
    <br>
    <hr>
    <div><h4>Albums</h4></div>
    <hr>
    <#list albums as album>
    <div>
        <span>${album.album_id}</span>
        <b>${album.name}</b>
        <i>${album.release_date}</i>
        <span>${album.style}</span>
        <i>${album.artist_id}</i>
        <div>
            <#if album.cover_file??>
                <img src="/img/covers/${album.cover_file}" height="100" width="100">
            </#if>
        </div>
    </div>
    <#else>
        <h5>No items</h5>
    </#list>
</@c.page>
