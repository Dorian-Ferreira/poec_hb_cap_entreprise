<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Home"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container">
    <div class="row">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/">Home</a></li>
                <li class="breadcrumb-item">Liste des Avis</li>
            </ol>
        </nav>
    </div>
    <div class="row">
        <div class="d-flex justify-content-center">
            <div class="d-flex">
                <h1 class="">Liste des avis</h1>
            </div>
        </div>
        <div class="d-flex justify-content-center">
            <div class="d-flex">
                <div class="d-flex main-container mx-5">
                    <input type="text"
                           class="form-control"
                           placeholder="Starcraft, Blizzard, ..."
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

                <span class="mt-auto mb-2">
                    <a href="${UrlRoute.URL_REVIEW}" class="btn-link" title="Réinitialiser les filtres">
                        <i class="fa-solid fa-filter-circle-xmark"></i>
                    </a>
                </span>
            </div>
        </div>
    </div>
    <div class="row">
        <c:forEach items="${reviews.content}" var="review">
            <%@ include file="../component/review-card.jsp" %>
        </c:forEach>
    </div>

    <c:set var="page" scope="request" value="${reviews}"/>
    <c:set var="url" scope="request" value="/review"/>
    <%@ include file="../component/pagination.jsp" %>
    <security:authorize access="hasRole('GAMER')">
        <a href="${UrlRoute.URL_REVIEW_NEW}" class="btn btn-light">
            Ajouter un avis
        </a>
    </security:authorize>
</div>

<%@ include file="../footer.jsp" %>
