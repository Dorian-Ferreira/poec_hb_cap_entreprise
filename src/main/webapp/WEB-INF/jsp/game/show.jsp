<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Jeu ${game.name}"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container mt-2">
    <div class="row">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a class="link-if text-decoration-underline" href="/">Home</a></li>
                <li class="breadcrumb-item"><a class="link-if text-decoration-underline" href="${UrlRoute.URL_GAME}">Liste des Jeux</a></li>
                <li class="breadcrumb-item">${game.name}</li>
                <li class="breadcrumb-item"><a class="link-if text-decoration-underline" href="#reviewRow">Les Avis</a></li>
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
    <c:if test="${reviews.numberOfElements > 0}">
        <div class="row container" id="reviewRow">
            <c:set var="scroll" scope="request" value="reviewRow"/>
            <div class="row">
                <security:authorize access="hasRole('MODERATOR')">
                    <div>
                        <a href="${UrlRoute.URL_ADMIN_REVIEW}?search=${game.slug}" class="btn btn-success">
                            <i class="fa-solid fa-comment"></i> Modérer les avis
                        </a>
                    </div>
                </security:authorize>
                <security:authorize access="hasRole('GAMER')">
                    <div>
                        <button class="ms-2 btn btn-success"
                                title="Ajouter un commentaire"
                                data-hide-show-button="formReview"
                        >
                            <i class="fa-solid fa-comment"></i> Commenter
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
                </security:authorize>
                <div class="d-flex justify-content-center">
                    <div class="d-flex">
                        <h1 class="">Liste des avis</h1>
                    </div>
                </div>
                <div class="d-flex justify-content-center">
                    <div class="d-flex">
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

                        <span class="mt-auto mb-2">
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
