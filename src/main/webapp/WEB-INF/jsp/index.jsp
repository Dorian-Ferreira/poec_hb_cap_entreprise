<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="Home"/>
<jsp:include flush="true" page="base.jsp"/>

<div class="container">
    <div class="row">
        <div class="d-flex justify-content-center">
            <h1 class="mt-2">Les meilleurs avis</h1>
        </div>
        <div class="d-flex justify-content-end">
            <a class="mt-4 link-if" href="${UrlRoute.URL_REVIEW}">Voir tous les avis</a>
        </div>
        <c:forEach items="${reviews}" var="review">
            <%@ include file="component/review-card.jsp" %>
        </c:forEach>
    </div>

    <div class="row my-5">
        <div class="d-flex justify-content-center">
            <h1 class="mt-4">Propositions de jeux</h1>
        </div>
        <div class="d-flex justify-content-end">
            <a class="mt-4 link-if" href="${UrlRoute.URL_GAME}">Voir tous les jeux</a>
        </div>

        <div class="col-lg-3 col-md-2">
        </div>
        <div id="carouselExampleCaptions" class="carousel slide col-lg-6 col-md-8 col-sm-12" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="3" aria-label="Slide 4"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="4" aria-label="Slide 5"></button>
            </div>
            <div class="carousel-inner">
                <a class="carousel-item active" data-bs-interval="3000" href="${UrlRoute.URL_GAME}/${games.get(0).slug}">
                    <img src="${games.get(0).image}" class="d-block w-100" alt="${games.get(0).name}">
                    <div class="carousel-caption d-flex d-md-block">
                        <div class="w-100">
                            <div class="d-flex justify-content-center">
                                <div class="fs-4 carousel-div align-middle">${games.get(0).name}</div>
                            </div>
                            <div class="d-flex justify-content-center">
                                <div class="fs-6 mt-1 carousel-div align-middle">${games.get(0).publisher.name}</div>
                            </div>
                        </div>
                    </div>
                </a>
                <c:forEach items="${games.subList(1, games.size())}" var="game">
                    <a class="carousel-item" data-bs-interval="3000" href="${UrlRoute.URL_GAME}/${game.slug}">
                        <img src="${game.image}" class="d-block w-100" alt="${game.name}">
                        <div class="carousel-caption d-flex d-md-block">
                            <div class="w-100">
                                <div class="d-flex justify-content-center">
                                    <div class="fs-4 carousel-div align-middle">${game.name}</div>
                                </div>
                                <div class="d-flex justify-content-center">
                                    <div class="fs-6 mt-1 carousel-div align-middle">${game.publisher.name}</div>
                                </div>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
