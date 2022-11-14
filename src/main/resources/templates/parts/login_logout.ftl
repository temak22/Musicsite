<#macro login path>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" name="username" placeholder="User name">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" name="password" placeholder="Password">
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Sign in</button>
    </form>
</#macro>

<#macro logout_button>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Sign Out</button>
    </form>
</#macro>

<#macro login_button>
    <form action="/login" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
</#macro>