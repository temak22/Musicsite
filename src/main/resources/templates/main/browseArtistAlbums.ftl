<#import "../parts/common.ftl" as c>

<@c.page>
    <div style="display: flex; align-items: center; justify-content: flex-end;">
        <div style="flex: 1">
            <h5>Albums</h5>
        </div>
    </div>
    <hr style="margin-top: 2px">
    <div class="row">
        <#list albums as album>
            <div style="margin: 0px">
                <div style="margin: 10px">
                    <a href="/browse/albums/${album.album_id}">
                        <img src="/img/covers/${album.cover_file}" height="190" width="190" style="border-radius: 8px;">
                    </a>
                </div>
                <div style="width: 200px;margin: 10px;">
                    <h6 style="font-size: smaller; margin-bottom: 2px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${album.name}</h6>
                    <h6 style="font-size: small; color: grey; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${album.release_date?string('yyyy')}</h6>
                </div>
            </div>
        <#else>
            <h5>No items</h5>
        </#list>
    </div>
</@c.page>
