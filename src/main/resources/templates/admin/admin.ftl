<#import "../parts/common.ftl" as c>

<@c.page>
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
                <a href="/admin/updateArtist" style="text-decoration: none; color: grey">Update Artist</a>
            </div>
        </div>
    </div>
</@c.page>