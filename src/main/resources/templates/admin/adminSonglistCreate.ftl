<#import "../parts/common.ftl" as c>

<@c.page "null" "" "">
    <hr>
    <div><h4>Create "${album.name}" (${album.album_id}) Songlist</h4></div>
    <hr>
    <div>
        <form method="post">
            <div style="color: red; margin-bottom: 5px"><h7>!!! Input "[song name(String)] [is lead song(0/1)] [serial number(int)] [feat. artist id(int) 1..*]" !!!</h7></div>
            <div style="color: red; margin-bottom: 5px"><h7>Example: song_name 1 1 2_5</h7></div>
            <label>
                <textarea name="songlist" cols="40" rows="5"></textarea>
            </label>
            <br>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button type="submit">Save</button>
        </form>
    </div>
</@c.page>
