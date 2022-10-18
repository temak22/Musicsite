<#import "../parts/common.ftl" as c>

<@c.page>
    <br>
    <form method="get" action="/search">
        <input type="text" name="filter" value="${filter!}">
        <button type="submit">Search</button>
    </form>

    <#if artists?size + albumsInBrowse?size + songsInBrowse?size = 0>
        <#if filter??>
            <div style="margin-top: 10px">
                <h7>No results</h7>
            </div>
        <#else>
            <div style="margin-top: 10px">
                <h7>Waiting for your request!</h7>
            </div>
        </#if>
    <#else>
        <#if 0 < artists?size>
            <hr>
            <div style="display: flex; align-items: center; justify-content: flex-end;">
                <div style="flex: 1">
                    <h4>Artists</h4>
                </div>
                <div style="">
                    <a style="color: crimson" href="/search/filter=${filter!}/artists">see all</a>
                </div>
                <hr>
            </div>
            <div class="row">
                <#list artists[0..*5] as artist>
                        <div style="margin: 10px;">
                            <a href="/browse/artists/${artist.artist_id}">
                                <img src="/img/avatars/${artist.avatar_file}" height="190" width="190" style="border-radius: 100%;">
                            </a>
                            <div style="width: 100%;">
                                <h6 style="font-size: smaller; margin-top: 5px;width: fit-content;margin-left: auto;margin-right: auto; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${artist.nickname}</h6>
                            </div>
                        </div>
                </#list>
            </div>
        </#if>

        <#if 0 < albumsInBrowse?size>
            <hr>
            <div style="display: flex; align-items: center; justify-content: flex-end;">
                <div style="flex: 1">
                    <h4>Albums</h4>
                </div>
                <div style="">
                    <a style="color: crimson" href="/search/filter=${filter!}/albums">see all</a>
                </div>
                <hr>
            </div>
            <div class="row">
                <#list albumsInBrowse[0..*5] as albumInBrowse>
                    <div style="margin: 0px;">
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
                </#list>
            </div>
        </#if>

        <#if 0 < songsInBrowse?size>
            <hr>
            <div style="display: flex; align-items: center; justify-content: flex-end;">
                <div style="flex: 1">
                    <h4>Songs</h4>
                </div>
                <div style="">
                    <a style="color: crimson" href="/search/filter=${filter!}/songs">see all</a>
                </div>
                <hr>
            </div>
            <div class="row">
                <#list songsInBrowse[0..*5] as song>
                    <div style="margin: 0px; width: 20%; height: 60px">
                        <div class="row" style="margin: 10px">
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
                </#list>
            </div>
        </#if>
    </#if>
    <br><br>
</@c.page>