<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
    <div class="d-flex bg-secondary flex-row">
        <div class="m-auto d-flex flex-row">
            <div class="mt-3 mr-5">
                <a href="/" class="text-decoration-none"><h3 class="text-dark">General</h3></a>
            </div>
            <div class="m-auto">
                <a href="/themes/create">
                    <button type="button" class="btn btn-primary">Create Theme</button>
                </a>
            </div>
        </div>

        <div class="m-auto d-flex flex-row">
            <#if user ??>
                <div class="mt-3">
                    <p>${user.name}</p>
                </div>
                <div class="m-auto">
                    <form action="/logout" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                        </#if>
                        <button type="submit" class="btn btn-danger">Logout</button>
                    </form>
                </div>
            <#else>
                <div>
                    <form action="/users/login" method="get">
                        <button type="submit" class="btn btn-primary">Login</button>
                    </form>
                </div>
            </#if>
        </div>
    </div>

    <div class="container">
        <div>
            <h3 class="text-muted">Creating Theme:</h3>
        </div>
        <div>
            <form action="/themes/create" method="post">
                <div class="input-group mb-3">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <input type="text" name="title" value="${(themeForm.title)!''}"
                           class="form-control" placeholder="Text Here" aria-label="With textarea" aria-describedby="button-addon2">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="submit">Submit</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>