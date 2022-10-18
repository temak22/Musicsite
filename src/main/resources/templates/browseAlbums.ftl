<#import "parts/common.ftl" as c>

<@c.page>
    <hr>
    <div style="display: flex; align-items: center; justify-content: flex-end;">
        <div style="flex: 1">
            <h4>Albums</h4>
        </div>
        <hr>
    </div>
    <div class="row">
        <#list albumsInBrowse as albumInBrowse>
            <div style="margin: 0px">
                <div style="margin: 10px">
                    <a href="/browse/albums/${albumInBrowse.album.album_id}">
                        <img src="/img/covers/${albumInBrowse.album.cover_file}" height="190" width="190" style="border-radius: 8px;">
                    </a>
                </div>
                <div style="width: 200px;margin: 10px;">
                    <h6 style="font-size: smaller; margin-bottom: 2px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${albumInBrowse.album.name}</h6>
                    <a href="/browse/artists/${albumInBrowse.artist.artist_id}" style="text-decoration-color: grey">
                        <h6 style="font-size: small; color: grey; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${albumInBrowse.artist.nickname}</h6>
                    </a>
                </div>
            </div>
        <#else>
            <h5>No items</h5>
        </#list>
    </div>
</@c.page>
