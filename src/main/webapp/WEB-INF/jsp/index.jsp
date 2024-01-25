<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="Home"/>
<jsp:include flush="true" page="base.jsp"/>

<div class="container">
    <h1>Home</h1>

    <div class="row mt-5">
        <div class="col-lg-3 col-md-2">
        </div>
        <div id="carouselExampleCaptions" class="carousel slide col-lg-6 col-md-8 col-sm-12" data-bs-ride="false">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="3" aria-label="Slide 4"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="4" aria-label="Slide 5"></button>
            </div>
            <div class="carousel-inner">
                <a class="carousel-item active" data-bs-interval="2000" href="${UrlRoute.URL_GAME}/${games.get(0).id}">
                    <img src="${games.get(0).image}" class="d-block w-100" alt="${games.get(0).name}">
                    <div class="carousel-caption d-flex d-md-block">
                        <div class="d-flex justify-content-center">
                            <div class="fs-4 m-2 carousel-div align-middle">${games.get(0).name}</div>
                        </div>
                        <div class="d-flex justify-content-center">
                            <div class="fs-6 m-2 carousel-div align-middle">${games.get(0).publisher.name}</div>
                        </div>
                    </div>
                </a>
                <c:forEach items="${games.subList(1, games.size())}" var="game">
                    <a class="carousel-item" data-bs-interval="2000" href="${UrlRoute.URL_GAME}/${game.id}">
                        <img src="${game.image}" class="d-block w-100" alt="${game.name}">
                        <div class="carousel-caption d-flex d-md-block">
                            <div class="d-flex justify-content-center">
                                <div class="fs-4 m-2 carousel-div align-middle">${game.name}</div>
                            </div>
                            <div class="d-flex justify-content-center">
                                <div class="fs-6 m-2 carousel-div align-middle">${game.publisher.name}</div>
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

    <div class="row mt-5">
        <c:forEach items="${reviews.content}" var="review">
            <div class="col-lg-4 col-md-6 col-sm-12 mt-4">
                <div class="main-review-card w-100">
                    <p class="text-center">
                        Le ${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy")}
                        par <a class="btn-link" href="#">${review.writer.nickname}</a>
                    </p>
                    <div class="review-card w-100">
                        <p class="review-description h-75">
                            <a class="link-if" href="${UrlRoute.URL_REVIEW}/${review.id}">
                                ${jspUtils.excerpt(review.description, 209)}
                            </a>
                        </p>
                        <div class="d-flex justify-content-between">
                            <p class="${jspUtils.getCssClas(review.rating)}">
                                    ${jspUtils.getStringRating(review.rating)} / 20
                            </p>
                            <a class="btn-link" href="${UrlRoute.URL_GAME}/${review.game.id}">
                                    ${review.game.name}
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <c:set var="page" scope="request" value="${reviews}"/>
    <c:set var="url" scope="request" value="/"/>
    <%@ include file="component/pagination.jsp" %>
    <security:authorize access="hasRole('GAMER')">
        <a href="${UrlRoute.URL_REVIEW_NEW}" class="btn btn-light">
            Juger un jeu
        </a>
    </security:authorize>
</div>

<%@ include file="footer.jsp" %>
