@import gg.jte.Content
@import hexlet.code.utils.NamedRoutes
@import hexlet.code.dto.BasePage

@param BasePage page = null
@param Content content

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Анализатор страниц</title>
</head>
<body>
    @if(page != null && page.getFlash() != null)
            <div class="rounded-0 m-0 alert alert-dismissible fade show alert-${page.getFlashType()}" role="alert">
                <p class="m-0">${page.getFlash()}</p>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
    @endif

    <div class="col-lg-8 mx-auto p-4 py-md-5">
        <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
            <nav class="nav">
                <a href="${NamedRoutes.rootPath()}" class="nav-link d-flex align-items-center text-body-emphasis text-decoration-none">
                    <span class="fs-4">Анализатор страниц</span>
                </a>
                <a href="${NamedRoutes.urlsPath()}" class="nav-link d-flex align-items-center text-body-emphasis text-decoration-none">
                    <span class="fs-4">Страницы</span>
                </a>
            </nav>
        </header>
        <main>
            ${content}
        </main>
        <footer class="pt-5 my-5 text-body-secondary border-top">
            <p>Created by <a href="https://ru.hexlet.io/">Hexlet</a> · © 2024</p>
        </footer>
    </div>
</body>
</html>