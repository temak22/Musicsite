<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isAdmin = user.isAdmin()
        isUser = true
    >
<#else>
    <#assign
        name = "unknown"
        isAdmin = false
        isUser = false
    >
</#if>