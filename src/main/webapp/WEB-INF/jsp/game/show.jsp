<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Jeu ${game.name}"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container mt-2">
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
                    <p>${game.getAverageRating()}</p>
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
                    <div class="col-3 mx-4">
                        <span>${review.writer.nickname} :</span>
                        <span class="${jspUtils.getCssClas(review.rating)}">${review.rating}</span>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </c:if>

</div>

<%@ include file="../footer.jsp" %>
