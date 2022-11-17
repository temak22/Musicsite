<#macro player song_src song_name song_author>
    <div class="container-player">
        <div class="podcast">
            <h5 class="podcast__title">

                ${song_author!""}
                <#if song_name != "">
                    <span style="margin-left: 5px; margin-right: -5px">-</span>
                </#if>
                <i>${song_name!""}</i>
            </h5>

            <div class="podcast__meta">
                <audio controls width="100%" src="${song_src!"null"}"></audio>
            </div>
        </div>
    </div>
</#macro>