<#import "parts/common.ftl" as c>
<#import "parts/login_logout.ftl" as l>

<@c.page>
    Login page
    <hr>
    <@l.login "/login" />
    <br>
    <a href="/registration">Add new user</a>
</@c.page>