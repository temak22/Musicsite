<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl">


<@c.page playing_song_src!"null" playing_song_name!"" playing_song_author!"">
    <hr>
    <div style="display: flex; align-items: center; justify-content: flex-end;">
        <div style="flex: 1">
            <h4>Charts</h4>
        </div>
        <div style="">
            <a style="color: crimson" href="/browse/charts">see all</a>
        </div>
        <hr>
    </div>
    <div class="row">
        <#list charts[0..*5] as chart>
            <div style="margin: 0px">
                <div style="margin: 10px">
                    <a href="/browse/charts/${chart.chart_id}">
                        <img src="/img/charts/${chart.cover_file}" height="190" width="190" style="border-radius: 8px;">
                    </a>
                </div>
                <div style="width: 200px;margin: 10px;">
                    <h6 style="font-size: smaller; margin-bottom: 2px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${chart.name}</h6>
                </div>
            </div>
        <#else>
            <h5>No items</h5>
        </#list>
    </div>

    <#if isUser = true>
        <#if recommendAlbums?size != 0>
            <hr>
            <div style="display: flex; align-items: center; justify-content: flex-end;">
                <div style="flex: 1">
                    <h4>${recommendStyle} albums for you</h4>
                </div>
                <hr>
            </div>
            <div class="row">
                <#list recommendAlbums[0..*5] as recommendAlbum>
                    <div style="margin: 0px">
                        <div style="margin: 10px">
                            <a href="/browse/albums/${recommendAlbum.album.album_id}">
                                <img src="/img/covers/${recommendAlbum.album.cover_file}" height="190" width="190" style="border-radius: 8px;">
                            </a>
                        </div>
                        <div style="width: 200px;margin: 10px;">
                            <h6 style="font-size: smaller; margin-bottom: 2px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${recommendAlbum.album.name}</h6>
                            <a href="/browse/artists/${recommendAlbum.artist.artist_id}" style="text-decoration-color: grey">
                                <h6 style="font-size: small; color: grey; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${recommendAlbum.artist.nickname}</h6>
                            </a>
                        </div>
                    </div>
                </#list>
            </div>
        </#if>
    </#if>
</@c.page>
