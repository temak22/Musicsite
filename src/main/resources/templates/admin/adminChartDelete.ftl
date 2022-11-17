<#import "../parts/common.ftl" as c>

<@c.page "null" "" "">
    <hr>
    <div><h4>Delete Chart</h4></div>
    <hr>
    <br>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="chart_id" placeholder="Chart id" required>
            <br>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button type="submit" style="margin-top:5px">Delete</button>
        </form>
    </div>
    <br>
</@c.page>
