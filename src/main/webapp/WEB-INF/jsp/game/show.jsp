<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Jeu ${game.name}"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container mt-2">
    <div class="row">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/">Home</a></li>
                <li class="breadcrumb-item"><a href="${UrlRoute.URL_GAME}">Liste des jeux</a></li>
                <li class="breadcrumb-item">${game.name}</li>
            </ol>
        </nav>
    </div>
    <div class="row">
        <div class="col-lg-6 col-sm-12">
            <div class="container-img">
                <img src="${game.image}" alt="${game.name}">
            </div>
        </div>
        <div class="col-lg-6 col-sm-12">
            <div class="justify-content-between">
                <h1>${game.name}</h1>
                <security:authorize access="hasRole('MODERATOR')">
                    <a href="${UrlRoute.URL_ADMIN_GAME_EDIT}/${game.id}" class="btn btn-success">
                        Modifier le jeu
                    </a>
                    <a href="${UrlRoute.URL_ADMIN_GAME_DELETE}/${game.id}" class="btn btn-danger">
                        Supprimer le jeu
                    </a>
                    <a href="${UrlRoute.URL_ADMIN_GAME_IMAGE}/${game.id}" class="btn btn-success">
                        Téléverser une image
                    </a>
                </security:authorize>
                <security:authorize access="hasRole('GAMER')">
                    <a href="${UrlRoute.URL_REVIEW_NEW}/${game.id}" class="btn btn-secondary">
                        Donner son avis sur le jeu
                    </a>
                </security:authorize>
            </div>
            <div>
                <c:if test="${game.reviews.size() > 0}">
                    <h5 class="mt-4">Note moyenne : </h5>
                    <p>${game.getAverageRating()}/20</p>
                </c:if>

                <h5 class="mt-4">Sortie le : </h5>
                <p>${dateUtils.getDateFormat(game.publishedAt, "dd/MM/yyyy")}</p>

                <h5 class="mt-4">Éditeur : </h5>
                <p>${game.publisher.name}</p>

                <h5 class="mt-4">Genre : </h5>
                <p>${game.genre.name}</p>

                <h5 class="mt-4">Modèle économique  : </h5>
                <p>${game.businessModel.name}</p>

                <h5 class="mt-4">Classification : </h5>
                <p>${game.classification.name}</p>

                <h5 class="mt-4">Platformes : </h5>
                <p>${jspUtils.getPlatformsString(game.platforms)}</p>
            </div>
        </div>
    </div>
    <div class="row container">
        <h5>Description :</h5>
        <p>${game.description}</p>
    </div>
    <c:if test="${game.reviews.size() > 0}">
        <div class="row container">
            <h5 class="mt-4">Avis :</h5>
            <c:forEach items="${game.reviews}" var="review">
                <c:if test="${review.moderator != null}">
                    <%@ include file="../component/review-card.jsp" %>
                </c:if>
            </c:forEach>
        </div>
    </c:if>

</div>

<%@ include file="../footer.jsp" %>
