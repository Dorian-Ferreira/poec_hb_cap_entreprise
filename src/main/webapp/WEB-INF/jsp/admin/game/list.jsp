<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../tag.jsp" %>
<c:set var="title" scope="request" value="Jeu Admin"/>
<jsp:include flush="true" page="../../base.jsp"/>

<div class="container">
  <div class="row">
    <div class="d-flex justify-content-between py-2">
      <nav aria-label="breadcrumb py-2">
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a class="link-if text-decoration-underline" href="/">Home</a></li>
          <li class="breadcrumb-item">BO Admin</li>
          <li class="breadcrumb-item">Liste des Jeux</li>
          <li class="breadcrumb-item">Jeux ${games.numberOfElements == 0 ? 0 : (games.number * games.size) + 1} à ${(games.number * games.size) + games.numberOfElements} sur ${games.totalElements}</li>
        </ol>
      </nav>
      <a href="${UrlRoute.URL_ADMIN_GAME_NEW}" class="btn btn-success">
        Ajouter un jeu
      </a>
    </div>
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
        <span class="mt-auto mb-2">
          <a href="${currentUrl}" class="btn-link" title="Réinitialiser les filtres">
                <i class="fa-solid fa-filter-circle-xmark"></i>
          </a>
        </span>
      </div>
    </div>
  </div>

  <table class="table table-dark table-striped table-hover align-middle mt-4">
    <thead>
      <tr>
        <th>
          <c:set var="label" scope="request" value="Nom"/>
          <c:set var="sortable" value="name"/>
          <%@ include file="../../component/sortable.jsp" %>
        </th>
        <th>
          <c:set var="label" scope="request" value="Date de Sortie"/>
          <c:set var="sortable" value="publishedAt"/>
          <%@ include file="../../component/sortable.jsp" %>
        </th>
        <th>
          <c:set var="label" scope="request" value="Éditeur"/>
          <c:set var="sortable" value="publisher.name"/>
          <%@ include file="../../component/sortable.jsp" %>
        </th>
        <th>
          Opérations
        </th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${games.content}" var="game">
        <tr>
          <th>
            <div>
                ${game.name}
            </div>
          </th>
          <th>
            <div>
                ${game.publishedAt}
            </div>
          </th>
          <th>
            <div>
                ${game.publisher.name}
            </div>
          </th>
          <th>
            <a class="btn btn-light" title="Voir le jeu" href="${UrlRoute.URL_GAME}/${game.slug}"><i class="fa-regular fa-eye"></i></a>
            <a class="btn btn-success" title="Modifier le jeu" href="${UrlRoute.URL_ADMIN_GAME_EDIT}/${game.id}"><i class="fa-solid fa-pen"></i></a>
            <a class="btn btn-success" title="Ajouter une image au jeu" href="${UrlRoute.URL_ADMIN_GAME_IMAGE}/${game.id}"><i class="fa-regular fa-image"></i></a>
            <a class="btn btn-danger" title="Supprimer le jeu" href="${UrlRoute.URL_ADMIN_GAME_DELETE}/${game.id}"><i class="fa-solid fa-trash"></i></a>
          </th>
        </tr>
        </c:forEach>
    </tbody>
  </table>
  <c:set var="page" scope="request" value="${games}"/>
  <%@ include file="../../component/pagination.jsp" %>
</div>

<%@ include file="../../footer.jsp" %>