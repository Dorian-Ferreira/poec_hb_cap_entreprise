<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<%
    Object title = request.getAttribute("title");
    if (title == null) {
        title = "Home";
    }
    request.setAttribute("title", title);
%>

<html>
    <head>
        <title>${title}</title>
        <link href="${contextPath}/css/main.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
        <script type="text/javascript" src="../../js/page/search-bar.js"></script>
        <script type="text/javascript" src="../../js/page/multiple-select.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="row w-100">
            <div class="col-2">
                <a class="navbar-brand ms-3" href="${contextPath}/">
                    <span>Home</span>
                </a>
            </div>
            <div class="col-2">
                <div class="navbar-nav">
                    <a class="btn btn-secondary" href="${UrlRoute.URL_GAME}">Liste de jeu</a>
                </div>
            </div>
            <div class="col-4">
                <div class="main-container p-2">
                    <div class="d-flex">
                        <input type="text"
                               class="form-control"
                               placeholder="Starcraft, FPS, ..."
                               data-search-bar-games
                        >
                        <a class="my-auto me-3">
                            <i class="fa fa-magnifying-glass"></i>
                        </a>
                    </div>
                    <div class="search-response-container">
                    </div>
                </div>
            </div>
            <div class="col-4">
                <security:authorize access="!isAuthenticated()">
                    <div class="d-flex justify-content-end">
                        <a class="nav-link" href="${UrlRoute.URL_REGISTER}">S'inscrire</a>
                    </div>
                    <div class="d-flex justify-content-end">
                        <a class="nav-link" href="${UrlRoute.URL_LOGIN}">Se Connecter</a>
                    </div>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <div class="d-flex justify-content-end">
                            <span class="ms-2">
                                Bienvenue
                                <a class="logged-user btn-link" href="${UrlRoute.URL_USER}/${userLogged.nickname}">
                                        ${userLogged.nickname}
                                </a>
                            </span>
                    </div>
                    <div class="d-flex justify-content-end">
                        <form method="POST" action="${UrlRoute.URL_LOGOUT}" autocomplete="off">
                            <button type="submit" tabindex="3" class="btn btn-link">Logout</button>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </form>
                    </div>
                </security:authorize>
            </div>
        </div>
    </nav>