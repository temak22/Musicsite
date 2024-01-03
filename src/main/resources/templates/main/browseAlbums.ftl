<#import "../parts/common.ftl" as c>

<@c.page playing_song_src!"null" playing_song_name!"" playing_song_author!"">
    <hr>
    <div style="display: flex; align-items: center; justify-content: flex-end;">
        <div style="flex: 1">
            <h4>Albums</h4>
        </div>
        <hr>
    </div>
    <div class="row">
        <#list albumsInBrowse as albumDto>
            <div style="margin: 0px">
                <div style="margin: 10px">
                    <a href="/browse/albums/${albumDto.album.album_id}">
                        <img src="/img/covers/${albumDto.album.cover_file}" height="190" width="190" style="border-radius: 8px;">
                    </a>
                </div>
                <div style="width: 200px;margin: 10px;">
                    <h6 style="font-size: smaller; margin-bottom: 2px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${albumDto.album.name}</h6>
                    <a href="/browse/artists/${albumDto.artist.artist_id}" style="text-decoration-color: grey">
                        <h6 style="font-size: small; color: grey; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${albumDto.artist.nickname}</h6>
                    </a>
                </div>
            </div>
        <#else>
            <h5>No items</h5>
        </#list>
    </div>
</@c.page>
