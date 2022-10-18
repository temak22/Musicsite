<#import "parts/common.ftl" as c>

<@c.page>
    <hr>
    <div style="display: flex; align-items: center; justify-content: flex-end;">
        <div style="flex: 1">
            <h4>Songs</h4>
        </div>
        <hr>
    </div>
    <div style="display: flex; flex-direction: column">
        <#list songsInBrowse as song>
            <hr style="margin-inline: 10px">
            <div style="margin: 5px">
                <div class="row" style="margin-inline: 10px">
                    <a style="color: crimson" href="/browse/albums/${song.album.album_id}">
                        <img src="/img/covers/${song.album.cover_file}" height="50" width="50" style="border-radius: 4px;">
                    </a>
                    <div style="margin-left: 10px; width: 60%; display: flex; justify-content: center; flex-direction: column">
                        <h6 style="font-size: smaller; margin-bottom: 0px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${song.name}</h6>
                        <div style="margin:0">
                            <h7 style="font-size: smaller; color: grey; margin-bottom: 0; font-weight: inherit; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${song.artist.nickname}</h7>
                        </div>
                    </div>
                </div>
            </div>
        <#else>
            <h5>No items</h5>
        </#list>
    </div>
    <br><br>

</@c.page>
