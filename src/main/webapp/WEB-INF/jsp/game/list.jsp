<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Jeux"/>
<jsp:include flush="true" page="../base.jsp"/>


<div class="container">
  <div class="row">
    <div class="d-flex justify-content-between py-2">
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a class="link-if text-decoration-underline" href="/">Accueil</a></li>
          <li class="breadcrumb-item">Liste des Jeux</li>
          <li class="breadcrumb-item">Jeux ${games.numberOfElements == 0 ? 0 : (games.number * games.size) + 1} à ${(games.number * games.size) + games.numberOfElements} sur ${games.totalElements}</li>
        </ol>
      </nav>
    </div>
  </div>
  <div class="row">
    <div class="d-flex justify-content-center">
      <div class="d-flex">
        <h1 class="mt-2">Liste des jeux</h1>
      </div>
      <security:authorize access="hasRole('MODERATOR')">
        <a href="${UrlRoute.URL_ADMIN_GAME_NEW}" class="btn-green ms-3 pt-4" title="Ajouter un jeu">
          <i class="fa-solid fa-square-plus fs-3"></i>
        </a>
      </security:authorize>
    </div>
  </div>
  <div class="row">
    <div class="d-flex justify-content-center">
      <div class="d-flex">
        <div class="d-flex main-container mx-5 my-auto px-3">
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

        <span class="mt-auto mb-auto px-4">
            <a href="${UrlRoute.URL_GAME}" class="btn-link" title="Réinitialiser les filtres">
                <i class="fa-solid fa-filter-circle-xmark"></i>
            </a>
        </span>
      </div>
    </div>
  </div>

  <div class="row mt-2">
    <c:forEach items="${games.content}" var="game">
      <a class="col-lg-4 col-md-6 mt-2 main-game-card" href="${UrlRoute.URL_GAME}/${game.slug}">
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
  <%@ include file="../component/pagination.jsp" %>
</div>

<%@ include file="../footer.jsp" %>