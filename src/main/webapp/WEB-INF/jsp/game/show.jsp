<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Jeu ${game.name}"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container mt-2">
    <div class="row">
        <div class="col-6">
            <img src="${game.image}" alt="${game.name}">
        </div>
        <div class="col-6">
            <h1>${game.name}</h1>
            <h5 class="mt-4">Sortie le : </h5>
            <ul>
                <li>${dateUtils.getDateFormat(game.publishedAt, "dd/MM/yyyy")}</li>
            </ul>
            <h5 class="mt-4">Éditeur : </h5>
            <ul>
                <li>${game.publisher.name}</li>
            </ul>
            <h5 class="mt-4">Genre : </h5>
            <ul>
                <li>${game.genre.name}</li>
            </ul>
            <h5 class="mt-4">Modèle économique  : </h5>
            <ul>
                <li >${game.businessModel.name}</li>
            </ul>
            <h5 class="mt-4">Classification : </h5>
            <ul>
                <li>${game.classification.name}</li>
            </ul>
            <h5 class="mt-4">Platformes : </h5>
            <ul>
                <c:forEach items="${game.platforms}" var="platform">
                    <li>
                        ${platform.name}
                    </li>
                </c:forEach>
            </ul>
            </p>
        </div>
    </div>
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
    <div class="col-2 row">
        <security:authorize access="hasRole('GAMER')">
            <a href="${UrlRoute.URL_REVIEW_NEW}/${game.id}" class="btn btn-light">
                Juger le jeu
            </a>
        </security:authorize>
    </div>
</div>

<%@ include file="../footer.jsp" %>
