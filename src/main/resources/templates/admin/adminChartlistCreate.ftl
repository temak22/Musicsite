<#import "../parts/common.ftl" as c>

<@c.page>
    <hr>
    <div><h4>Create "${chart.name}" (${chart.chart_id}) Chartlist</h4></div>
    <hr>
    <div>
        <form method="post">
            <div style="color: red; margin-bottom: 5px"><h7>!!! Input "[song id(int)] [serial_number(int)]" !!!</h7></div>
            <div style="color: red; margin-bottom: 5px"><h7>Example: 1 1</h7></div>
            <label>
                <textarea name="chartlist" cols="40" rows="5"></textarea>
            </label>
            <br>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button type="submit">Save</button>
        </form>
    </div>
</@c.page>
