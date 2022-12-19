<#import "../parts/common.ftl" as c>
<#import "../parts/playSong.ftl" as play>


<@c.page playing_song_src!"null" playing_song_name!"" playing_song_author!"">
    <br>
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
                    <div class="imgplay" style="width: 40px; display: flex; align-items: center; justify-content: center">
                        <div class="albumImg">
                            <img src="/img/covers/${song.album.cover_file}" height="50" width="50" style="border-radius: 4px;">
                        </div>
                        <div class="playButtonImg" style="height:50px; width:50px">
                            <div style="width: fit-content; display: flex; justify-content: flex-start">
                                <@play.playsong song.song_id "/library"/>
                            </div>
                        </div>
                    </div>
                    <div style="margin-left: 15px; width: 60%; display: flex; justify-content: center; flex-direction: column">
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