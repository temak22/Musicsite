<#import "../parts/common.ftl" as c>

<@c.page "null" "" "">
    <hr>
    <div><h4>Update Song</h4></div>
    <hr>
    <br>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="number" name="song_id" placeholder="Song id" required>
            <input type="text" name="name" placeholder="Song name" required>
            <input type="number" list="number01" max="1" name="is_lead_song" placeholder="Is lead song?" required>
            <datalist id="number01">
                <option value="0">
                <option value="1">
            </datalist>
            <input type="number" name="serial_number" placeholder="Serial number" required>
            <input type="number" name="order_number" placeholder="Order in album" required>
            <br><br>
            <div style="color: red; margin-bottom: 5px"><h7>!!! Input "[feat. artist id(int) 1..*]" !!!</h7></div>
            <div style="color: red; margin-bottom: 5px"><h7>Example: 2_5_7</h7></div>
            <label>
                <textarea name="featlist" cols="40" rows="5" placeholder="Feat Artists"></textarea>
            </label>

            <div style="margin-top: 5px; margin-left: 5px" class="row">
                <h7 style="margin-right: 10px">Song file</h7>
                <input type="file" name="file" required>
            </div>
            <br>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button type="submit" style="margin-top: 5px">Update</button>
        </form>
    </div>
</@c.page>
