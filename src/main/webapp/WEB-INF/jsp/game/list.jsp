<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Jeux"/>
<jsp:include flush="true" page="../base.jsp"/>


<div class="container">
  <div class="row">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/">Home</a></li>
        <li class="breadcrumb-item">Liste des jeux</li>
      </ol>
    </nav>
  </div>
  <div class="row">
    <div class="d-flex justify-content-center">
      <div class="d-flex">
        <h1 class="mt-2">Liste des jeux</h1>
      </div>
    </div>
  </div>
  <div class="row">
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

        <c:set var="label" scope="request" value="Nom"/>
        <c:set var="sortable" value="name"/>
        <%@ include file="../component/sortable.jsp" %>

        <c:set var="label" scope="request" value="Date"/>
        <c:set var="sortable" value="publishedAt"/>
        <%@ include file="../component/sortable.jsp" %>

        <c:set var="label" scope="request" value="Éditeur"/>
        <c:set var="sortable" value="publisher.name"/>
        <%@ include file="../component/sortable.jsp" %>

        <span class="mt-auto mb-2">
            <a href="${UrlRoute.URL_GAME}" class="btn-link" title="Réinitialiser les filtres">
                <i class="fa-solid fa-filter-circle-xmark"></i>
            </a>
        </span>
      </div>
    </div>
  </div>

  <div class="row mt-2">
    <c:forEach items="${games.content}" var="game">
      <a class="col-4 mt-2 main-game-card" href="${UrlRoute.URL_GAME}/${game.slug}">
        <div class="game-card">
          <div class="game-card-img">
            <img alt="${game.name}" src="${game.image}">
          </div>
          <div class="d-flex justify-content-between">
            <p>${game.name}</p>
            <p>${game.publisher.name}</p>
          </div>
        </div>
      </a>
    </c:forEach>
  </div>

  <c:set var="page" scope="request" value="${games}"/>
  <c:set var="url" scope="request" value="/game"/>
  <%@ include file="../component/pagination.jsp" %>
  <security:authorize access="hasRole('MODERATOR')">
    <a href="${UrlRoute.URL_ADMIN_GAME_NEW}" class="btn btn-light">
      Ajouter un jeu
    </a>
  </security:authorize>
</div>

<%@ include file="../footer.jsp" %>