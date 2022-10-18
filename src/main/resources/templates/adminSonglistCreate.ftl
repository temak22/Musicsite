<#import "parts/common.ftl" as c>

<@c.page>
    <hr>
    <div><h4>Create "${album.name}" (${album.album_id}) Songlist</h4></div>
    <hr>
    <div>
        <form method="post">
            <div style="color: red; margin-bottom: 5px"><h7>!!! Input "[song name(String)] [is_lead_song(0/1)] [serial_number(int)]" !!!</h7></div>
            <div style="color: red; margin-bottom: 5px"><h7>Example: song_name 1 1</h7></div>
            <label>
                <textarea name="songlist" cols="40" rows="5"></textarea>
            </label>
            <br>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button type="submit">Send</button>
        </form>
    </div>
</@c.page>
