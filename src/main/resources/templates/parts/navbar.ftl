<#include "security.ftl">
<#import "login_logout.ftl" as l>
<#import "audioplayer.ftl" as pl>


<#macro nav song_src song_name song_author>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/project_DB">MusicApp</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/library">Library</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/browse">Browse</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/search">Search</a>
                </li>
            </ul>

            <#if isAdmin = true>
                <div style="width: 60%; margin-right: 55px">
                    <@pl.player song_src song_name song_author/>
                </div>

                <div class="navbar-text mr-3">
                    <a href="/admin">Admin Panel</a>
                </div>
            <#else>
                <div style="width: 60%; margin-right: 180px">
                    <@pl.player song_src song_name song_author/>
                </div>
            </#if>

            <#if name != "unknown">
                <div class="navbar-text" style="margin-right: 10px">${name}</div>
                <div class="navbar-item"><@l.logout_button /></div>
            <#else>
                <div class="navbar-item"><@l.login_button /></div>
            </#if>

        </div>
    </nav>
</#macro>