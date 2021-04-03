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

    <div class="container d-flex flex-column justify-content-center align-items-center">
        <div>
            <h3 class="text-muted">Themes</h3>
        </div>
        <#if (themes?size > 0)>
            <div>
                <div class="list-group">
                    <a href="/" class="list-group-item list-group-item-action active">
                        <div class="d-flex flex-row justify-content-center align-items-center">
                            <div>
                                <p>Themes</p>
                            </div>
                        </div>
                    </a>
                    <#list themes as theme>
                        <a href="/themes/precise?themeId=${theme.id}" class="list-group-item list-group-item-action">
                            <div class="d-flex flex-row">
                                <div class="mr-4">
                                    <p>${theme.title}</p>
                                </div>
                                <div class="mr-4">
                                    <p>${theme.date}</p>
                                </div>
                                <div class="mr-4">
                                    <p>${theme.user.name}</p>
                                </div>
                                <div class="mr-4">
                                    <#if (theme.answerCount > 0)>
                                        <p>${theme.answerCount}</p>
                                    </#if>
                                </div>
                            </div>
                        </a>
                    </#list>
                </div>
            </div>
        </#if>
        <#if (pages > 0)>
            <div class="pages">
                <#list 1..pages as x>
                    <a class="pageButton" href="?page=${x}">${x}</a>
                </#list>
            </div>
        </#if>
    </div>
</body>
</html>