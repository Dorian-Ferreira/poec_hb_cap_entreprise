<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Avis ${review.id}"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container mt-2">
    <div class="row">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a class="link-if" href="/">Home</a></li>
                <li class="breadcrumb-item"><a class="link-if" href="${UrlRoute.URL_REVIEW}">Liste des avis</a></li>
            </ol>
        </nav>
    </div>
    <div class="row">
        <h1>Avis de <span class="txt-primary">${review.writer.nickname}</span> sur <a class="link-if" href="${UrlRoute.URL_GAME}/${review.game.slug}">${review.game.name}</a></h1>
        <security:authorize access="hasRole('MODERATOR')">
            <c:if test="${review.moderator == null}">
                <a class="col-1 btn btn-success" title="Accepter le commentaire" href="${UrlRoute.URL_ADMIN_REVIEW}/${review.id}/accept"><i class="fa-regular fa-square-check"></i></a>
                <a class="col-1 btn btn-danger" title="Refuser le commentaire" href="${UrlRoute.URL_ADMIN_REVIEW}/${review.id}/refuse"><i class="fa-regular fa-circle-xmark"></i></a>
            </c:if>
        </security:authorize>
    </div>

    <div class="row">
        <div class="col-4">
            Posté le ${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy à hh:mm")}
        </div>
        <div class="col-4">
            Note :
            <span class="col-4 ${jspUtils.getCssClas(review.rating)}">
                ${review.rating}/20
            </span>
        </div>
    </div>
    <div class="row">
        <h2 class="mt-5">Commentaire :</h2>
        <div>${review.description}</div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
