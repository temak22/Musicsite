<#import "../parts/common.ftl" as c>
<#import "../parts/addToLibrary.ftl" as add>
<#import "../parts/playSong.ftl" as play>



<@c.page playing_song_src!"null" playing_song_name!"" playing_song_author!"">
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
                        <div style="margin-left: 15px; width: 60%; display: flex; justify-content: center; flex-direction: column">
                            <h6 style="font-size: smaller; margin-bottom: 0px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${song.name}</h6>
                            <div style="margin:0">
                                <h7 style="font-size: smaller; color: grey; margin-bottom: 0; font-weight: inherit; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${song.artist.nickname}</h7>
                            </div>
                        </div>
                        <div style="width: 30%; display: flex; justify-content: flex-end; margin-bottom: 0">
                            <div style="display: flex; justify-content: center; flex-direction: column">
                                <@add.addSong song.song_id song.is_in_library/>
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
