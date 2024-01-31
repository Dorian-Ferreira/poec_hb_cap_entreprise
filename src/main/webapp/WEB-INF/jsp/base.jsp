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
        <script type="text/javascript" src="../../js/page/hide-form.js"></script>
        <script type="text/javascript" src="../../js/page/alert.js"></script>
    </head>
    <body class="background">
        <security:authorize access="isAuthenticated()">
            <nav class="navbar navbar-expand-lg">
                <div class="row w-100">
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="mx-3">
                            <a class="navbar-brand" href="/" title="Page d'accueil">
                                <i class="text-white fa-solid fa-gamepad fs-1"></i>
                            </a>
                            <a class="link-if" href="${UrlRoute.URL_GAME_RANDOM}" title="Jeu aléatoire">
                                <i class="fa-solid fa-dice fs-3"></i>
                            </a>
                        </div>

                        <div class="d-flex align-items-center">
                            <div>
                                <security:authorize access="hasRole('GAMER')">
                                    <i class="fa-solid fa-user"></i>
                                </security:authorize>
                                <security:authorize access="hasRole('MODERATOR')">
                                    <i class="fa-solid fa-user-shield"></i>
                                </security:authorize>
                                <span class="txt-primary">${userLogged.nickname}</span>
                                <form class="navbar-brand m-0 p-2" method="POST" action="${UrlRoute.URL_LOGOUT}" autocomplete="off">
                                    <button type="submit" tabindex="3" class="btn btn-link" title="Se Déconnecter"><i class="fa-solid fa-right-from-bracket"></i></button>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>
        </security:authorize>

        <c:if test="${not empty flashMessage.message}">
            <div class="container">
                <div class="alert alert-${flashMessage.type}">
                        ${flashMessage.message}
                </div>
            </div>
        </c:if>
