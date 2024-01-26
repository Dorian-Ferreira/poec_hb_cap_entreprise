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
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="row w-100">
            <div class="col-2">
                <a class="navbar-brand ms-3" href="${contextPath}/">
                    <span>Page d'accueil</span>
                </a>
            </div>
            <div class="col-2">
                <security:authorize access="hasRole('GAMER')">
                    <a href="${UrlRoute.URL_REVIEW_NEW}" class="btn btn-secondary">
                        Donner un avis sur un jeu
                    </a>
                </security:authorize>
            </div>
            <div class="col-2">
                <div class="navbar-nav">
                    <a class="btn btn-secondary" href="${UrlRoute.URL_GAME}">Liste de jeu</a>
                </div>
            </div>
            <div class="col-2">

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
                        <security:authorize access="hasRole('MODERATOR')">
                          <a class="logged-user btn-link" href="${UrlRoute.URL_ADMIN}">
                              BO Admin
                          </a>
                        </security:authorize>
                        <security:authorize access="hasRole('GAMER')">
                          Bienvenue <span class="txt-primary">${userLogged.nickname}</span>
                        </security:authorize>
                      </span>
                    </div>
                    <div class="d-flex justify-content-end">
                        <form method="POST" action="${UrlRoute.URL_LOGOUT}" autocomplete="off">
                            <button type="submit" tabindex="3" class="btn btn-link">Se Déconnecter</button>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </form>
                    </div>
                </security:authorize>
            </div>
        </div>
    </nav>