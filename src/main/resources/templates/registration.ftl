<#import "parts/common.ftl" as c>
<#import "parts/login_logout.ftl" as l>

<@c.page "null" "" "">
    Add new user
    <hr>
    <p style="color: red">${message!' '}</p>
    <@l.login "/registration" />
    <br>
    <a href="/login">Return to log in</a>
</@c.page>