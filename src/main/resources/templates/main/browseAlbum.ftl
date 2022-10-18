<#import "../parts/common.ftl" as c>

<@c.page>
    <hr>
    <div class="row" style="height: 300px">
        <div style="margin: 10px">
            <img src="/img/covers/${albumInBrowse.album.cover_file}" height="280" width="280" style="border-radius: 8px;">
        </div>
        <div style="display: flex; height: 60%; flex-direction: column; justify-content: flex-end; margin-left: 20px">
            <h2 style="margin-bottom: 0">${albumInBrowse.album.name}</h2>
            <a style="color: crimson; font-size: 22px;" href="/browse/artists/${albumInBrowse.artist.artist_id}">${albumInBrowse.artist.nickname}</a>

            <div class="row" style="margin-left: 0">
                <h7 style="margin-bottom: 0; font-weight: inherit;">${albumInBrowse.album.style}</h7>
                <h6 style="margin-inline: 4px">.</h6>
                <h7 style="margin-bottom: 0">${albumInBrowse.album.release_date?string('yyyy')}</h7>
            </div>
        </div>
    </div>
    <div>
        <br><br>
        <#list songsInBrowse as songInBrowse>
            <hr style="margin: 0">
            <div style="margin: 0px">
                <div class="row" style="height: 40px; margin: 6px;">
                    <div style="width: 20px; display: flex; align-items: center; justify-content: center">
                        <#if songInBrowse.is_lead_song = 1>
                            <h7 style="margin-bottom: 0; color: grey">*</h7>
                        </#if>
                    </div>
                    <div style="width: 40px; display: flex; align-items: center; justify-content: center">
                        <h7 style="margin-bottom: 0; color: grey">${songInBrowse.serial_number}</h7>
                    </div>
                    <div style="width: fit-content; margin: 0; display: flex; align-items: center; justify-content: center">
                        <h7 style="margin-bottom: 0">${songInBrowse.name}</h7>
                    </div>
                </div>
            </div>
        <#else>
            <h5>No items</h5>
        </#list>
    </div>
    <div style="margin-top: 40px; margin-left: 40px; margin-bottom: 20px; display: flex; flex-direction: column">
        <h7 style="font-size: smaller; color: grey">${albumInBrowse.album.release_date}</h7>
        <h7 style="font-size: smaller; color: grey">Songs: ${songsInBrowse?size}</h7>
    </div>

</@c.page>
