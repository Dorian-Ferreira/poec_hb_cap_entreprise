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
        <script type="text/javascript" src="../../js/page/multiple-select.js"></script>
        <script type="text/javascript" src="../../js/lib/bootstrap/bootstrap.js"></script>
        <script type="text/javascript" src="../../js/page/sortable.js"></script>
    </head>
    <body class="background">
        <security:authorize access="isAuthenticated()">
            <nav class="navbar navbar-expand-lg">
                <div class="row w-100">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="navbar-collapse collapse navbar-nav justify-content-between " id="navbarSupportedContent">
                        <div class="navbar-item">
                            <a class="navbar-brand ms-3 px-3" href="${contextPath}/">
                                <span class="text-white">Page d'accueil</span>
                            </a>
                        </div>

                        <div class="navbar-item">
                            <security:authorize access="hasRole('MODERATOR')">
                                <a class="btn btn-secondary" href="${UrlRoute.URL_ADMIN}">
                                    BO Admin
                                </a>
                            </security:authorize>
                            <security:authorize access="hasRole('GAMER')">
                                <span>Bienvenue <span class="txt-primary">${userLogged.nickname}</span></span>
                            </security:authorize>
                        </div>

                        <security:authorize access="hasRole('GAMER')">
                            <div class="navbar-item">
                                <a href="${UrlRoute.URL_REVIEW_NEW}" class="btn btn-secondary">
                                    Donner un avis sur un jeu
                                </a>
                            </div>
                        </security:authorize>

                        <div class="navbar-item">
                            <a class="btn btn-secondary" href="${UrlRoute.URL_GAME}">Liste des jeux</a>
                        </div>

                        <div class="navbar-item">
                            <a class="btn btn-secondary" href="${UrlRoute.URL_REVIEW}">Liste des avis</a>
                        </div>

                        <div class="navbar-item">
                            <form method="POST" action="${UrlRoute.URL_LOGOUT}" autocomplete="off">
                                <button type="submit" tabindex="3" class="btn btn-danger">Se Déconnecter</button>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form>
                        </div>
                    </div>
                </div>
            </nav>
        </security:authorize>

