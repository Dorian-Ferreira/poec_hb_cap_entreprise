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
    <div class="row">

    </div>
    <div class="row">
        <security:authorize access="hasRole('GAMER')">
            <a href="${UrlRoute.URL_REVIEW_NEW}/${game.id}" class="btn btn-light">
                Juger un jeu
            </a>
        </security:authorize>
    </div>
</div>

<%@ include file="../footer.jsp" %>
