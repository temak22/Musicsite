<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl">

<@c.page>
    <h4>User editor</h4>
    <hr>

    <form action="/admin/user" method="post">
        <input type="text" value="${user_edit.username}" name="username">
        <input type="text" value="${user_edit.password}" name="password">
        <#list roles as role>
            <#if name != user_edit.username>
                <#if role == roles[0]>
                    <div>
                        <label><input type="checkbox" disabled="disabled" name="${role}" checked>${role}</label>
                    </div>
                <#else>
                    <div>
                        <label><input type="checkbox" name="${role}" ${user_edit.roles?seq_contains(role)?string("checked", "")}>${role}</label>
                    </div>
                </#if>
                <input type="hidden" value="false" name="isMainAdmin">
            <#else>
                <input type="hidden" value="true" name="isMainAdmin">
            </#if>
        </#list>
        <input type="hidden" value="${user_edit.user_id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit">Save</button>
    </form>
</@c.page>