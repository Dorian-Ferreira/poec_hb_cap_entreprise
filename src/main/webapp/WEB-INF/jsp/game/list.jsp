<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Games"/>
<jsp:include flush="true" page="../base.jsp"/>


<div class="container">
  <div class="row d-flex justify-content-between">
    <div class="d-flex">

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
          <a href="${currentUrl}" class="btn-link">
              Reset
          </a>
      </span>
    </div>
  </div>

  <table class="table table-dark table-striped table-hover align-middle mt-2">
    <thead>
    <tr>
      <th>
        Nom
      </th>
      <th>
        Date de sortie
      </th>
      <th>
        Éditeur
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
            <a class="btn btn-light" href="${UrlRoute.URL_GAME}/${game.id}">Voir</a>
            <security:authorize access="hasRole('MODERATOR')">
              <a class="btn btn-success" href="${UrlRoute.URL_ADMIN_GAME_EDIT}/${game.id}">Modifier</a>
              <a class="btn btn-success" href="${UrlRoute.URL_ADMIN_GAME_IMAGE}/${game.id}">Téléverser l'image</a>
              <a class="btn btn-danger" href="${UrlRoute.URL_ADMIN_GAME_DELETE}/${game.id}">Supprimer</a>
            </security:authorize>
          </th>
        </tr>
    </c:forEach>
    </tbody>
  </table>

<%--  <div class="row">--%>
<%--    <c:forEach items="${games.content}" var="game">--%>
<%--      <div class="col-lg-4 col-md-6 col-sm-12 mt-4">--%>
<%--        <div class="main-game-card w-100">--%>
<%--          <div class="game-card w-100">--%>

<%--          </div>--%>
<%--        </div>--%>
<%--      </div>--%>
<%--    </c:forEach>--%>
<%--  </div>--%>

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