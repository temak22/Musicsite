<#import "../parts/common.ftl" as c>

<@c.page>
    <h4>User list</h4>
    <br>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Role</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user_edit>
            <tr>
                <td>${user_edit.username}</td>
                <td>
                    <#list user_edit.roles as role>
                        ${role}<#sep>,
                    </#list>
                </td>
                <td>
                    <a href="/admin/user/${user_edit.id}">edit</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>