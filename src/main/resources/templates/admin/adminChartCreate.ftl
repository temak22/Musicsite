<#import "../parts/common.ftl" as c>

<@c.page "null" "" "">
    <hr>
    <div><h4>New Chart</h4></div>
    <hr>
    <br>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="name" placeholder="Chart name" required>
            <input type="file" name="file" required>
            <br>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button type="submit" style="margin-top:5px">Create</button>
        </form>
    </div>
    <br>
    <hr>
    <div><h4>Charts</h4></div>
    <hr>
    <#list charts as chart>
    <div>
        <span>${chart.chart_id}</span>
        <b>${chart.name}</b>
        <div>
            <#if chart.cover_file??>
                <img src="/img/charts/${chart.cover_file}" height="100" width="100">
            </#if>
        </div>
    </div>
    <#else>
        <h5>No items</h5>
    </#list>
</@c.page>
