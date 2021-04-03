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
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Title</th>
                    <th scope="col">Data</th>
                    <th scope="col">User</th>
                    <th scope="col">Answers</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">${theme.title}</th>
                        <td>${theme.date}</td>
                        <td>${theme.user.name}</td>
                        <td>${theme.answerCount}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <#if user??>
            <div class="user-answer">
                <div class="answer-btn-div">
                    <button type="button" class="btn answer-btn btn-success">Answer</button>
                </div>
                <div class="form-div h-100" style="display:none;">
                    <form class="user-answer-form">
                        <div class="input-group mb-3">
                            <#if _csrf??>
                                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                            </#if>
                            <input type="hidden" name="themeId" value="${theme.id}">
                            <textarea class="form-control" name="message" placeholder="Text here" aria-label="With textarea"></textarea>
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="submit">Send</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </#if>
        <div class="answers">
            <#if (answers?size > 0)>
                <#list answers as answer>
                    <div class="answer d-flex flex-column">
                        <div class="topic d-flex flex-row">
                            <div class="mr-3">
                                <p>${answer.user.name}</p>
                            </div>
                            <div>
                                <p>${answer.answerDate}</p>
                            </div>
                        </div>
                        <div>
                            <p>${answer.message}</p>
                        </div>
                    </div>
                </#list>
            </#if>
        </div>
        <#if (pages > 0)>
            <div class="pages">
                <#list 1..pages as x>
                    <a class="pageButton" href="?page=${x}&themeId=#{theme.id}">${x}</a>
                </#list>
            </div>
        </#if>
    </div>

    <script src="/js/main.js">
    </script>
</body>
</html>