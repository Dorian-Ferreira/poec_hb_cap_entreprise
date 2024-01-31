<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Avis ${review.id}"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container mt-2">
    <div class="row">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a class="link-if text-decoration-underline" href="/">Accueil</a></li>
                <li class="breadcrumb-item"><a class="link-if text-decoration-underline" href="${UrlRoute.URL_REVIEW}">Liste des Avis</a></li>
            </ol>
        </nav>
    </div>
    <div class="row">
        <div class="col-6">
            <h1>Avis de
                <a class="link-if" href="${UrlRoute.URL_REVIEW}?search=${review.writer.nickname}">${review.writer.nickname}</a>
            </h1>

            <c:if test="${review.moderator == null}">
                <security:authorize access="hasRole('MODERATOR')">
                    <a class="col-1 btn btn-success" title="Accepter le commentaire" href="${UrlRoute.URL_ADMIN_REVIEW}/${review.id}/accept"><i class="fa-regular fa-square-check"></i></a>
                    <a class="col-1 btn btn-danger" title="Refuser le commentaire" href="${UrlRoute.URL_ADMIN_REVIEW}/${review.id}/refuse"><i class="fa-regular fa-circle-xmark"></i></a>
                </security:authorize>
            </c:if>
            <div class="col-4">
                Posté le ${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy à hh:mm")}
            </div>
            <c:if test="${review.moderator != null}">
                <div>
                    Modéré par ${review.moderator.nickname} le ${dateUtils.getDateFormat(review.moderatedAt, "dd/MM/yyyy")}
                </div>
            </c:if>
            <div class="col-4">
                Note :
                <span class="col-4 ${jspUtils.getCssClas(review.rating)}">
                ${review.rating}/20
            </span>
            </div>
        </div>
        <div class="col-6">
            <c:set var="game" scope="request" value="${review.game}"/>
            <%@ include file="../component/game-card.jsp" %>
        </div>
    </div>

    <div class="row">
    </div>
    <div class="row">
        <h2 class="mt-5">Commentaire :</h2>
        <div>${review.description}</div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
