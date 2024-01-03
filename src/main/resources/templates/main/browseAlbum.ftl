<#import "../parts/common.ftl" as c>
<#import "../parts/addSond.ftl" as add>
<#import "../parts/playSong.ftl" as play>


<@c.page playing_song_src!"null" playing_song_name!"" playing_song_author!"">
    <hr>
    <div class="row" style="height: 300px">
        <div style="margin: 10px">
            <img src="/img/covers/${albumDto.album.cover_file}" height="280" width="280" style="border-radius: 8px;">
        </div>
        <div style="display: flex; height: 60%; flex-direction: column; justify-content: flex-end; margin-left: 20px">
            <h2 style="margin-bottom: 0">${albumDto.album.name}</h2>
            <a style="color: crimson; font-size: 22px;" href="/browse/artists/${albumDto.artist.artist_id}">${albumDto.artist.nickname}</a>

            <div class="row" style="margin-left: 0">
                <h7 style="margin-bottom: 0; font-weight: inherit;">${albumDto.album.style}</h7>
                <h6 style="margin-inline: 4px">.</h6>
                <h7 style="margin-bottom: 0">${albumDto.album.release_date?string('yyyy')}</h7>
            </div>
        </div>
    </div>
    <div>
        <br><br>
        <#list songsInBrowse as song>
            <hr style="margin: 0">
            <div style="margin: 0px">
                <div class="row" style="height: 40px; margin: 6px;">
                    <div style="width: 20px; display: flex; align-items: center; justify-content: center">
                        <#if song.is_lead_song = 1>
                            <h7 style="margin-bottom: 0; color: grey">*</h7>
                        </#if>
                    </div>
                    <div class="numberplay" style="width: 40px; display: flex; align-items: center; justify-content: center">
                        <div class="serialNumber">
                            <h7 style="margin-bottom: 0; color: grey">${song.serial_number}</h7>
                        </div>
                        <div class="playButton">
                            <div style="width: fit-content; display: flex; justify-content: flex-start">
                                <@play.playsong song.song_id "/browse"/>
                            </div>
                        </div>
                    </div>
                    <div style="width: 82%; margin: 0; display: flex; justify-content: flex-start">
                        <div style="display: flex; flex-direction: column; justify-content: center">
                            <h7 style="margin-bottom: 0">${song.name}</h7>
                        </div>
                    </div>
                    <div style="width: fit-content; display: flex; justify-content: flex-end">
                        <@add.addsong song.song_id song.is_in_library/>
                    </div>
                </div>
            </div>
        <#else>
            <h5>No items</h5>
        </#list>
    </div>
    <div style="margin-top: 40px; margin-left: 40px; margin-bottom: 20px; display: flex; flex-direction: column">
        <h7 style="font-size: smaller; color: grey">${albumDto.album.release_date}</h7>
        <h7 style="font-size: smaller; color: grey">Songs: ${songsInBrowse?size}</h7>
    </div>

</@c.page>
