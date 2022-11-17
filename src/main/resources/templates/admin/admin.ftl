<#import "../parts/common.ftl" as c>

<@c.page "null" "" "">
    <div class="row">
        <div style="display: flex; flex-direction: column; width: fit-content; margin-right: 20px">
            <div style="padding: 10px; margin-bottom: 10px; background-color: #cccccc; border: solid grey; width: 100% ">
                <a href="/admin/user" style="text-decoration: none; color: grey">Userlist</a>
            </div>
        </div>

        <div style="display: flex; flex-direction: column; width: fit-content; margin-right: 20px">
            <div style="padding: 10px; margin-bottom: 10px; background-color: #cccccc; border: solid grey; width: 100% ">
                <a href="/admin/createAlbum" style="text-decoration: none; color: grey">Create Album</a>
            </div>
            <div style="padding: 10px; margin-bottom: 10px; background-color: #cccccc; border: solid grey; width: 100% ">
                <a href="/admin/createChart" style="text-decoration: none; color: grey">Create Chart</a>
            </div>
        </div>

        <div style="display: flex; flex-direction: column; width: fit-content; margin-right: 20px">
            <div style="padding: 10px; margin-bottom: 10px; background-color: #cccccc; border: solid grey; width: 100% ">
                <a href="/admin/updateAlbum" style="text-decoration: none; color: grey">Update Album</a>
            </div>
            <div style="padding: 10px; margin-bottom: 10px; background-color: #cccccc; border: solid grey; width: 100% ">
                <a href="/admin/updateChart" style="text-decoration: none; color: grey">Update Chart</a>
            </div>
            <div style="padding: 10px; margin-bottom: 10px; background-color: #cccccc; border: solid grey; width: 100% ">
                <a href="/admin/updateArtist" style="text-decoration: none; color: grey">Update Artist</a>
            </div>
        </div>

        <div style="display: flex; flex-direction: column; width: fit-content; margin-right: 20px">
            <div style="padding: 10px; margin-bottom: 10px; background-color: #cccccc; border: solid grey; width: 100% ">
                <a href="/admin/deleteAlbum" style="text-decoration: none; color: grey">Delete Album</a>
            </div>
            <div style="padding: 10px; margin-bottom: 10px; background-color: #cccccc; border: solid grey; width: 100% ">
                <a href="/admin/deleteChart" style="text-decoration: none; color: grey">Delete Chart</a>
            </div>
        </div>
    </div>
</@c.page>