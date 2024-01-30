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
        <script type="text/javascript" src="../../js/lib/bootstrap/bootstrap.bundle.min.js"></script>
        <script type="text/javascript" src="../../js/page/sortable.js"></script>
        <script type="text/javascript" src="../../js/page/filter.js"></script>
    </head>
    <body class="background">
        <security:authorize access="isAuthenticated()">
            <nav class="navbar navbar-expand-lg">
                <div class="row w-100 ">
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="mx-3">
                            <a class="navbar-brand" href="${contextPath}/">
                                <span class="text-white">Page d'accueil</span>
                            </a>
                        </div>

                        <div class="d-flex align-items-center">
                            <security:authorize access="hasRole('MODERATOR')">
                                <div class="dropdown">
                                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        BO Admin
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="${UrlRoute.URL_ADMIN_REVIEW}">Liste des Avis</a></li>
                                        <li><a class="dropdown-item" href="${UrlRoute.URL_ADMIN_GAME}">Liste des Jeux</a></li>
                                        <li><a class="dropdown-item" href="${UrlRoute.URL_ADMIN_GAME_NEW}">Ajouter un jeu</a></li>
                                    </ul>
                                </div>
                            </security:authorize>

                            <span class="mx-3 txt-primary">${userLogged.nickname}</span>
                            <form class="navbar-brand m-0 p-2" method="POST" action="${UrlRoute.URL_LOGOUT}" autocomplete="off">
                                <button type="submit" tabindex="3" class="btn btn-danger"><i class="fa-solid fa-user-slash"></i></button>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form>
                        </div>
                    </div>
                </div>
            </nav>
        </security:authorize>

