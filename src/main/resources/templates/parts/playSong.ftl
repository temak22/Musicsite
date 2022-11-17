<#macro playsong song_id methodFrom>

    <form action="${methodFrom}/playSong" method="post">
        <input type="hidden" name="song_id" value="${song_id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary" style="background: rgba(255,255,255,0.5); border-color: white; color: #007bff">ᐅ</button>
    </form>
</#macro>