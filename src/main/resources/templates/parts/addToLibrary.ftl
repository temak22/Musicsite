<#include "security.ftl">
<#macro addSong song_id is_in_library>
    <#if known>
        <#if is_in_library = 0>
            <form action="/addSong" method="post">
                <input type="hidden" name="song_id" value="${song_id}">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">+</button>
            </form>
        <#else>
            <form action="/checkoutSong" method="post">
                <input type="hidden" name="song_id" value="${song_id}">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary" style="background-color: white">
                    <h7 style="color: #007bff;">&#10003;</h7>
                </button>
            </form>
        </#if>
    </#if>
</#macro>

<#macro addAlbum album_id is_in_library>
    <#if known>
        <#if is_in_library = 0>
            <form action="/addAlbum" method="post">
                <input type="hidden" name="album_id" value="${album_id}">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">+</button>
            </form>
        <#else>
            <form action="/checkoutAlbum" method="post">
                <input type="hidden" name="album_id" value="${album_id}">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary" style="background-color: white">
                    <h7 style="color: #007bff;">&#10003;</h7>
                </button>
            </form>
        </#if>
    </#if>
</#macro>