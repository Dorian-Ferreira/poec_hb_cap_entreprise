<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Jeu ${game.name}"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container mt-2">
    <div class="row">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a class="link-if text-decoration-underline" href="/">Accueil</a></li>
                <li class="breadcrumb-item"><a class="link-if text-decoration-underline" href="${UrlRoute.URL_GAME}">Liste des Jeux</a></li>
                <li class="breadcrumb-item">${game.name}</li>
                <c:if test="${game.reviews.size() > 0}">
                    <li class="breadcrumb-item"><a class="link-if text-decoration-underline" href="#reviewRow">Les Avis</a></li>
                </c:if>
            </ol>
        </nav>
    </div>
    <div class="row">
        <div class="col-md-4 col-sm-12">
            <div class="container-img">
                <img class="d-block" src="${game.image}" alt="${game.name}">
            </div>
        </div>
        <div class="col-md-8 col-sm-12">
            <div class="justify-content-between">
                <h1>${game.name}</h1>
                <security:authorize access="hasRole('MODERATOR')">
                    <a href="${UrlRoute.URL_ADMIN_GAME_EDIT}/${game.id}" class="btn btn-success">
                        <i class="fa-solid fa-pen"></i> Modifier
                    </a>
                    <a href="${UrlRoute.URL_ADMIN_GAME_IMAGE}/${game.id}" class="btn btn-success">
                        <i class="fa-regular fa-image"></i> Ajouter une Image
                    </a>
                    <a href="${UrlRoute.URL_ADMIN_GAME_DELETE}/${game.id}" class="btn btn-danger">
                        <i class="fa-solid fa-trash"></i> Supprimer
                    </a>
                </security:authorize>
            </div>
            <div class="mt-4">
                <c:if test="${game.reviews.size() > 0}">
                    <div class="mt-4">
                        <span class="h5">Note moyenne : </span>
                        <span>${averageRating}/20</span>
                    </div>
                </c:if>

                <div class="mt-4">
                    <span class="h5">Sortie le : </span>
                    <span>${dateUtils.getDateFormat(game.publishedAt, "dd/MM/yyyy")}</span>
                </div>

                <div class="mt-4">
                    <span class="h5">Éditeur : </span>
                    <a class="link-if" title="Chercher les jeux de l'éditeur" href="${UrlRoute.URL_GAME}?search=${game.publisher.slug}">${game.publisher.name}</a>
                </div>

                <div class="mt-4">
                    <span class="h5">Genre : </span>
                    <a class="link-if" title="Chercher les jeux du genre" href="${UrlRoute.URL_GAME}?search=${game.genre.slug}">${game.genre.name}</a>
                </div>

                <div class="mt-4">
                    <span class="h5">Modèle économique  : </span>
                    <a class="link-if" title="Chercher les jeux du modèle économique" href="${UrlRoute.URL_GAME}?search=${game.businessModel.slug}">${game.businessModel.name}</a>
                </div>

                <div class="mt-4">
                    <span class="h5">Classification : </span>
                    <a class="link-if" title="Chercher les jeux de la classification" href="${UrlRoute.URL_GAME}?search=${game.classification.slug}">${game.classification.name}</a>
                </div>

                <div class="mt-4 d-flex">
                    <span class="h5">Platformes : </span>
                    ${jspUtils.getPlatformsString(game.platforms)}
                </div>
            </div>
        </div>
    </div>
    <div class="row container mt-5">
        <h5>Description :</h5>
        <p>${game.description}</p>
    </div>
    <security:authorize access="hasRole('GAMER')">
        <div class="row">
            <div>
                <button class="ms-2 btn btn-success"
                        title="Ajouter un commentaire"
                        data-hide-show-button="formReview"
                >
                    <i class="fa-solid fa-file-pen me-2"></i>Ajouter un commentaire
                </button>
            </div>
            <div class="my-3 d-none"
                       data-hide-show-container="formReview"
            >
                <f:form cssClass="col-md-8 col-sm-12 mx-auto"
                        action="${UrlRoute.URL_GAME}/${game.slug}"
                        method="post"
                        modelAttribute="reviewDTO"
                >
                    <div class="mb-3 row">
                        <f:label path="description" class="col-sm-2 col-form-label">Description</f:label>
                        <div class="col-sm-10">
                            <f:textarea cssClass="form-control" path="description"/>
                            <f:errors path="description" cssClass="invalid-feedback"/>
                        </div>
                    </div>
                    <div class="my-3 row">
                        <f:label path="rating" class="col-sm-2 col-form-label">Note</f:label>
                        <div class="col-sm-10">
                            <f:input type="number" step="0.5" cssClass="form-control" path="rating"/>
                            <f:errors path="rating" cssClass="invalid-feedback"/>
                        </div>
                    </div>
                    <f:button type="submit" class="btn btn-success">
                        <i class="fa fa-check"></i> Ajouter
                    </f:button>
                </f:form>
            </div>
        </div>
    </security:authorize>
    <security:authorize access="hasRole('MODERATOR')">
        <div class="row">
            <div class="ms-5 mt-2">
                <a href="${UrlRoute.URL_REVIEW}?search=${game.slug}&moderation=2" class="btn btn-danger">
                    <i class="fa-solid fa-comment-slash"></i> Modérer les avis en attente
                </a>
            </div>
        </div>
    </security:authorize>
    <c:if test="${reviews.numberOfElements > 0}">
        <div class="row container" id="reviewRow">
            <c:set var="scroll" scope="request" value="reviewRow"/>
            <div class="row">
                <div class="d-flex justify-content-center">
                    <h1 class="">Liste des avis</h1>
                    </div>
                <div class="d-flex justify-content-center">
                    <div class="d-flex">
                        <p class="mt-3 me-5">
                            Avis ${reviews.numberOfElements == 0 ? 0 : ((reviews.number * reviews.size) + 1)} à ${(reviews.number * reviews.size) + reviews.numberOfElements} sur ${reviews.totalElements}
                        </p>

                        <!-- Label à afficher -->
                        <c:set var="label" scope="request" value="Date"/>
                        <!-- Sur quelle propriété de l'objet on souhaite trier -->
                        <c:set var="sortable" value="createdAt"/>
                        <%@ include file="../component/sortable.jsp" %>

                        <c:set var="label" scope="request" value="Note"/>
                        <c:set var="sortable" value="rating"/>
                        <%@ include file="../component/sortable.jsp" %>

                        <c:set var="label" scope="request" value="Joueur"/>
                        <c:set var="sortable" value="writer.nickname"/>
                        <%@ include file="../component/sortable.jsp" %>

                        <span class="mt-auto mb-auto">
                            <a href="${UrlRoute.URL_GAME}/${game.slug}#reviewRow" class="btn-link" title="Réinitialiser les filtres">
                                <i class="fa-solid fa-filter-circle-xmark"></i>
                            </a>
                        </span>
                    </div>
                </div>
            </div>
            <c:forEach items="${reviews.content}" var="review">
                <c:if test="${review.moderator != null}">
                    <%@ include file="../component/review-card.jsp" %>
                </c:if>
            </c:forEach>
        </div>

        <c:set var="page" scope="request" value="${reviews}"/>
        <%@ include file="../component/pagination.jsp" %>
    </c:if>

</div>

<%@ include file="../footer.jsp" %>
