<#import "../parts/common.ftl" as c>

<@c.page>
    <hr>
    <div style="display: flex; align-items: center; justify-content: flex-end;">
        <div style="flex: 1">
            <h4>Charts</h4>
        </div>
        <hr>
    </div>
    <div class="row">
        <#list charts as chart>
            <div style="margin: 0px">
                <div style="margin: 10px">
                    <a href="/browse/charts/${chart.chart_id}">
                        <img src="/img/charts/${chart.cover_file}" height="190" width="190" style="border-radius: 8px;">
                    </a>
                </div>
                <div style="width: 200px;margin: 10px;">
                    <h6 style="font-size: smaller; margin-bottom: 2px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${chart.name}</h6>
                </div>
            </div>
        <#else>
            <h5>No items</h5>
        </#list>
    </div>
</@c.page>
