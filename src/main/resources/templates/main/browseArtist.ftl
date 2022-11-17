<#import "../parts/common.ftl" as c>
<#import "../parts/addSond.ftl" as add>
<#import "../parts/playSong.ftl" as play>


<@c.page playing_song_src!"null" playing_song_name!"" playing_song_author!"">
    <#if artist.page_photo_file??>
        <div style="height: 500px; width: inherit;   background: linear-gradient(to bottom, rgba(255, 255, 255, .1), rgba(10, 10, 10, .5)), url('/img/pagePhotos/${artist.page_photo_file}');background-size: cover;">
            <div style="padding-top: 440px">
                <h2 style="margin-left: 80px; font-weight: bold; color: white">${artist.nickname}</h2>
            </div>
        </div>
    </#if>

    <br>
    <div style="width: 100%">
        <div style="display: flex; align-items: center; justify-content: flex-end;">
            <div style="flex: 1">
                <h5>Songs</h5>
            </div>
            <div style="">
                <a style="color: crimson" href="/browse/artists/${artist.artist_id}/songs">see all</a>
            </div>
        </div>
        <div class="row" style="">
            <div style="width: 33%">
                <#list songsInArtistBrowse[0..*3] as song>
                    <hr style="margin-inline: 10px">
                    <div style="margin: 5px">
                        <div class="row" style="margin: 10px">
                            <div class="imgplay" style="width: 40px; display: flex; align-items: center; justify-content: center">
                                <div class="albumImg">
                                    <a style="color: crimson" href="/browse/albums/${song.album.album_id}">
                                        <img src="/img/covers/${song.album.cover_file}" height="50" width="50" style="border-radius: 4px;">
                                    </a>
                                </div>
                                <div class="playButtonImg" style="height:50px; width:50px">
                                    <div style="width: fit-content; display: flex; justify-content: flex-start">
                                        <@play.playsong song.song_id "/browse"/>
                                    </div>
                                </div>
                            </div>
                            <div style="margin-left: 15px; width: 60%; display: flex; justify-content: center; flex-direction: column">
                                <h6 style="font-size: smaller; margin-bottom: 2px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${song.name}</h6>
                                <div class="row" style="margin-left: 0;">
                                    <h7 style="font-size: smaller; color: grey; margin-bottom: 0; font-weight: inherit; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${song.album.name} · ${song.album.release_date?string('yyyy')}</h7>
                                </div>
                            </div>
                            <div style="width: 20%; display: flex; justify-content: flex-end">
                                <@add.addsong song.song_id song.is_in_library/>
                            </div>
                        </div>
                    </div>
                <#else>
                    <h5>No items</h5>
                </#list>
            </div>
            <div style="width: 33%">
                <#if 3 < songsInArtistBrowse?size>
                    <#list songsInArtistBrowse[3..*3] as song>
                        <hr style="margin-inline: 10px">
                        <div style="margin: 5px">
                            <div class="row" style="margin: 10px">
                                <div class="imgplay" style="width: 40px; display: flex; align-items: center; justify-content: center">
                                    <div class="albumImg">
                                        <a style="color: crimson" href="/browse/albums/${song.album.album_id}">
                                            <img src="/img/covers/${song.album.cover_file}" height="50" width="50" style="border-radius: 4px;">
                                        </a>
                                    </div>
                                    <div class="playButtonImg" style="height:50px; width:50px">
                                        <div style="width: fit-content; display: flex; justify-content: flex-start">
                                            <@play.playsong song.song_id "/browse"/>
                                        </div>
                                    </div>
                                </div>
                                <div style="margin-left: 15px; width: 60%; display: flex; justify-content: center; flex-direction: column">
                                    <h6 style="font-size: smaller; margin-bottom: 2px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${song.name}</h6>
                                    <div class="row" style="margin-left: 0;">
                                        <h7 style="font-size: smaller; color: grey; margin-bottom: 0; font-weight: inherit; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${song.album.name} · ${song.album.release_date?string('yyyy')}</h7>
                                    </div>
                                </div>
                                <div style="width: 20%; display: flex; justify-content: flex-end">
                                    <@add.addsong song.song_id song.is_in_library/>
                                </div>
                            </div>
                        </div>
                    </#list>
                </#if>

            </div>
            <div style="width: 34%">
                <#if 6 < songsInArtistBrowse?size>
                    <#list songsInArtistBrowse[6..*3] as song>
                        <hr style="margin-inline: 10px">
                        <div style="margin: 5px">
                            <div class="row" style="margin: 10px">
                                <div class="imgplay" style="width: 40px; display: flex; align-items: center; justify-content: center">
                                    <div class="albumImg">
                                        <a style="color: crimson" href="/browse/albums/${song.album.album_id}">
                                            <img src="/img/covers/${song.album.cover_file}" height="50" width="50" style="border-radius: 4px;">
                                        </a>
                                    </div>
                                    <div class="playButtonImg" style="height:50px; width:50px">
                                        <div style="width: fit-content; display: flex; justify-content: flex-start">
                                            <@play.playsong song.song_id "/browse"/>
                                        </div>
                                    </div>
                                </div>
                                <div style="margin-left: 15px; width: 60%; display: flex; justify-content: center; flex-direction: column">
                                    <h6 style="font-size: smaller; margin-bottom: 2px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${song.name}</h6>
                                    <div class="row" style="margin-left: 0;">
                                        <h7 style="font-size: smaller; color: grey; margin-bottom: 0; font-weight: inherit; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${song.album.name} · ${song.album.release_date?string('yyyy')}</h7>
                                    </div>
                                </div>
                                <div style="width: 20%; display: flex; justify-content: flex-end">
                                    <@add.addsong song.song_id song.is_in_library/>
                                </div>
                            </div>
                        </div>
                    </#list>
                </#if>
            </div>
        </div>
    </div>

    <br>
    <hr>
    <div style="display: flex; align-items: center; justify-content: flex-end;">
        <div style="flex: 1">
            <h5>Albums</h5>
        </div>
        <div style="">
            <a style="color: crimson" href="/browse/artists/${artist.artist_id}/albums">see all</a>
        </div>
    </div>
    <div class="row">
        <#list albums[0..*5] as album>
            <div style="margin: 0px">
                <div style="margin: 10px">
                    <a href="/browse/albums/${album.album_id}">
                        <img src="/img/covers/${album.cover_file}" height="190" width="190" style="border-radius: 8px;">
                    </a>
                </div>
                <div style="width: 200px;margin: 10px;">
                    <h6 style="font-size: smaller; margin-bottom: 2px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${album.name}</h6>
                    <h6 style="font-size: small; color: grey; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${album.release_date?string('yyyy')}</h6>
                </div>
            </div>
        <#else>
            <h5>No items</h5>
        </#list>
    </div>
    <br><br>
</@c.page>
