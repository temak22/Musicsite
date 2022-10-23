<#import "../parts/common.ftl" as c>

<@c.page>
    <hr>
    <div class="row" style="height: 300px">
        <div style="margin: 10px">
            <img src="/img/charts/${chart.cover_file}" height="280" width="280" style="border-radius: 8px;">
        </div>
        <div style="display: flex; height: 60%; flex-direction: column; justify-content: flex-end; margin-left: 20px">
            <h2 style="margin-bottom: 0">${chart.name}</h2>
            <a style="color: crimson; font-size: 22px;" href="/browse">MusicApp</a>
        </div>
    </div>
    <div>
        <br><br>
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
    </div>
    <div style="margin-top: 40px; margin-left: 40px; margin-bottom: 20px; display: flex; flex-direction: column">
        <h7 style="font-size: smaller; color: grey">Songs: ${songsInBrowse?size}</h7>
    </div>
</@c.page>
