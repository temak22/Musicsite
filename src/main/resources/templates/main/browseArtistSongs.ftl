<#import "../parts/common.ftl" as c>
<#import "../parts/addSong.ftl" as add>
<#import "../parts/playSong.ftl" as play>


<@c.page playing_song_src!"null" playing_song_name!"" playing_song_author!"">
    <br>
    <div style="width: 100%">
        <div style="display: flex; align-items: center; justify-content: flex-end; margin-left: 10px">
            <div style="flex: 1">
                <h5>Songs</h5>
            </div>
        </div>
        <hr style="margin-top: 2px; margin-left: 10px">

        <div style="display: flex; flex-direction: column">
            <#list songsInArtistBrowse as song>
                <#if song.song_id != songsInArtistBrowse[0].song_id>
                    <hr style="margin-inline: 10px">
                </#if>
                <div style="margin: 0px">
                    <div class="row" style="margin: 10px">
                        <div class="imgplay" style="width: 40px; display: flex; align-items: center; justify-content: center">
                            <div class="albumImg">
                                <img src="/img/covers/${song.album.cover_file}" height="50" width="50" style="border-radius: 4px;">
                            </div>
                            <div class="playButtonImg" style="height:50px; width:50px">
                                <div style="width: fit-content; display: flex; justify-content: flex-start">
                                    <@play.playsong song.song_id/>
                                </div>
                            </div>
                        </div>
                        <div style="margin-left: 20px; width: 60%; display: flex; justify-content: center; flex-direction: column">
                            <h6 style="font-size: smaller; margin-bottom: 2px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${song.name}</h6>
                            <div class="row" style="margin-left: 0;">
                                <h7 style="font-size: smaller; color: grey; margin-bottom: 0; font-weight: inherit; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
                                    <a style="color: grey" href="/browse/albums/${song.album.album_id}">
                                        ${song.album.name}
                                    </a>
                                     · ${song.album.release_date?string('yyyy')}
                                </h7>
                            </div>
                        </div>
                        <div style="width: 30%; display: flex; justify-content: flex-end; margin-bottom: 0">
                            <div style="width: fit-content; display: flex; justify-content: flex-end; margin-bottom: 0">
                                <div style="display: flex; justify-content: center; flex-direction: column">
                                    <@add.addsong song.song_id song.is_in_library/>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            <#else>
                <h5>No items</h5>
            </#list>
        </div>
    </div>


    <br><br>
</@c.page>
