<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Review ${review.id}"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container mt-2">
    <div class="row">
        <h1>Jugement de ${review.writer.nickname} sur ${review.game.name}</h1>
        <security:authorize access="hasRole('MODERATOR')">
            <c:if test="${review.moderator == null}">
                <a class="col-1 btn btn-success" href="${UrlRoute.URL_ADMIN_REVIEW}/${review.id}/accept">Accepter</a>
                <a class="col-1 btn btn-danger" href="${UrlRoute.URL_ADMIN_REVIEW}/${review.id}/refuse">Refuser</a>
            </c:if>
        </security:authorize>
    </div>

    <div class="row">
        <div class="col-4">Posté le ${review.createdAt}</div>
        <div class="col-4">Note : ${review.rating}/20</div>
    </div>
    <div class="row">
        <h2 class="mt-5">Commentaire :</h2>
        <div>${review.description}</div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
