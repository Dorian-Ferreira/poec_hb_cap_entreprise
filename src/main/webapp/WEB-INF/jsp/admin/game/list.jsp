<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../tag.jsp" %>
<c:set var="title" scope="request" value="Jeu Admin"/>
<jsp:include flush="true" page="../../base.jsp"/>

<div class="container">
  <div class="row">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/">Home</a></li>
        <li class="breadcrumb-item">BO Admin</li>
        <li class="breadcrumb-item">Liste des Jeux</li>
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
            <a class="btn btn-light" href="${UrlRoute.URL_GAME}/${game.slug}"><i class="fa-regular fa-eye"></i></a>
            <a class="btn btn-success" href="${UrlRoute.URL_ADMIN_GAME_EDIT}/${game.id}"><i class="fa-solid fa-pen"></i></a>
            <a class="btn btn-success" href="${UrlRoute.URL_ADMIN_GAME_IMAGE}/${game.id}"><i class="fa-regular fa-image"></i></a>
            <a class="btn btn-danger" href="${UrlRoute.URL_ADMIN_GAME_DELETE}/${game.id}"><i class="fa-solid fa-trash"></i></a>
          </th>
        </tr>
        </c:forEach>
    </tbody>
  </table>
  <c:set var="page" scope="request" value="${games}"/>
  <c:set var="url" scope="request" value="${UrlRoute.URL_ADMIN_GAME}"/>
  <%@ include file="../../component/pagination.jsp" %>

  <a href="${UrlRoute.URL_ADMIN_GAME_NEW}" class="btn btn-light">
    Ajouter un jeu
  </a>
</div>

<%@ include file="../../footer.jsp" %>