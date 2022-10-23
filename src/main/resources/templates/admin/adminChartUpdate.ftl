<#import "../parts/common.ftl" as c>

<@c.page>
    <hr>
    <div><h4>Update Chart</h4></div>
    <hr>
    <br>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="number" name="chart_id" placeholder="Chart id">
            <input type="text" name="name" placeholder="Chart name" required>
            <input type="file" name="file" required>
            <br>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button type="submit" style="margin-top:5px">Update</button>
        </form>
    </div>
    <br>
</@c.page>
