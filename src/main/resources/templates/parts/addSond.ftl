<#include "security.ftl">
<#macro addsong song_id is_in_library>
    <#if known>
        <#if is_in_library = 0>
            <form action="/browse/addSong" method="post">
                <input type="hidden" name="song_id" value="${song_id}">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">+</button>
            </form>
        <#else>
            <div style="width: 36.95px; height: 38px; border: 1px solid #007bff; border-radius: 0.25rem; display: flex; flex-direction: row; justify-content: center">
                <div style="display: flex; flex-direction: column; justify-content: center">
                    <h7 style="color: #007bff; ">&#10003;</h7>
                </div>
            </div>
        </#if>
    </#if>


</#macro>