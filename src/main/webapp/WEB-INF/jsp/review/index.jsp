<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Home"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container">
    <div class="row">
        <div class="d-flex justify-content-between py-2">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a class="link-if text-decoration-underline" href="/">Accueil</a></li>
                    <li class="breadcrumb-item">Liste des Avis</li>
                    <li class="breadcrumb-item">Avis ${reviews.numberOfElements == 0 ? 0 : ((reviews.number * reviews.size) + 1)} à ${(reviews.number * reviews.size) + reviews.numberOfElements} sur ${reviews.totalElements}</li>
                </ol>
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="d-flex justify-content-center">
            <div class="d-flex">
                <h1 class="">Liste des avis</h1>
            </div>
            <security:authorize access="hasRole('GAMER')">
                <a href="${UrlRoute.URL_REVIEW_NEW}" title="Ajouter un Avis" class="btn-green ms-3 pt-3" title="Ajouter un avis">
                    <i class="fa-solid fa-comment-medical fs-3"></i>
                </a>
            </security:authorize>
        </div>
        <div class="d-flex justify-content-center">
            <div class="d-flex">
                <div class="d-flex main-container mx-5 my-auto px-3">
                    <input type="text"
                           class="form-control"
                           placeholder="Starcraft, Chloe, ..."
                           data-filter
                    >
                    <a class="my-auto me-3">
                        <i class="fa fa-magnifying-glass"></i>
                    </a>
                </div>

                <!-- Label à afficher -->
                <c:set var="label" scope="request" value="Date"/>
                <!-- Sur quelle propriété de l'objet on souhaite trier -->
                <c:set var="sortable" value="createdAt"/>
                <%@ include file="../component/sortable.jsp" %>

                <c:set var="label" scope="request" value="Note"/>
                <c:set var="sortable" value="rating"/>
                <%@ include file="../component/sortable.jsp" %>

                <c:set var="label" scope="request" value="Jeu"/>
                <c:set var="sortable" value="game.name"/>
                <%@ include file="../component/sortable.jsp" %>

                <c:set var="label" scope="request" value="Joueur"/>
                <c:set var="sortable" value="writer.nickname"/>
                <%@ include file="../component/sortable.jsp" %>

                <security:authorize access="hasRole('MODERATOR')">
                    <select class="m-auto px-3" aria-label="Default select example" moderationFilter>
                        <option selected>Status</option>
                        <option value="1">Tous</option>
                        <option value="2">Non Modéré</option>
                        <option value="3">Modéré</option>
                    </select>
                </security:authorize>

                <span class="mt-auto mb-2 ps-4">
                    <a href="${UrlRoute.URL_REVIEW}" class="btn-link" title="Réinitialiser les filtres">
                        <i class="fa-solid fa-filter-circle-xmark"></i>
                    </a>
                </span>
            </div>
        </div>
    </div>
    <div class="row" id="reviewRow">
        <c:set var="scroll" scope="request" value="reviewRow"/>
        <c:forEach items="${reviews.content}" var="review">
            <%@ include file="../component/review-card.jsp" %>
        </c:forEach>
    </div>

    <c:set var="page" scope="request" value="${reviews}"/>
    <c:set var="url" scope="request" value="/review"/>
    <%@ include file="../component/pagination.jsp" %>
</div>

<%@ include file="../footer.jsp" %>
