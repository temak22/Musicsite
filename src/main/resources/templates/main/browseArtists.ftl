<#import "../parts/common.ftl" as c>

<@c.page playing_song_src!"null" playing_song_name!"" playing_song_author!"">
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
        <#list artists as artist>
            <div style="margin: 10px;">
                <a href="/browse/artists/${artist.artist_id}">
                    <img src="/img/avatars/${artist.avatar_file}" height="190" width="190" style="border-radius: 100%;">
                </a>
                <div style="width: 100%;">
                    <h6 style="font-size: smaller; margin-top: 5px;width: fit-content;margin-left: auto;margin-right: auto; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${artist.nickname}</h6>
                </div>
            </div>
        <#else>
            <div style="margin-top: 10px">
                <h7>No items</h7>
            </div>
        </#list>
    </div>
</@c.page>
