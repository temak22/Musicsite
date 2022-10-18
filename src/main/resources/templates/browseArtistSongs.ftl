<#import "parts/common.ftl" as c>

<@c.page>
    <br>
    <hr>
    <div style="width: 100%">
        <div style="display: flex; align-items: center; justify-content: flex-end;">
            <div style="flex: 1">
                <h5>Songs</h5>
            </div>
        </div>
        <div class="row" style="">
            <div style="width: 33%">
                <#list songsInArtistBrowse as song>
                    <hr style="margin-inline: 10px">
                    <div style="margin: 5px">
                        <div class="row" style="margin: 10px">
                            <a style="color: crimson" href="/browse/albums/${song.album.album_id}">
                                <img src="/img/covers/${song.album.cover_file}" height="50" width="50" style="border-radius: 4px;">
                            </a>
                            <div style="margin-left: 10px; width: fit-content">
                                <h6 style="font-size: smaller; margin-bottom: 2px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${song.name}</h6>
                                <div class="row" style="margin-left: 0;">
                                    <h7 style="font-size: smaller; color: grey; margin-bottom: 0; font-weight: inherit; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${song.album.name} · ${song.album.release_date?string('yyyy')}</h7>
                                </div>
                            </div>
                        </div>
                    </div>
                <#else>
                    <h5>No items</h5>
                </#list>
            </div>
        </div>
    </div>
</@c.page>
