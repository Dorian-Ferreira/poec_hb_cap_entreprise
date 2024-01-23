<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Games"/>
<jsp:include flush="true" page="../base.jsp"/>


<div class="container">
  <table class="table table-dark table-striped table-hover align-middle">
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
    <c:forEach items="${games}" var="game">
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
  <security:authorize access="hasRole('MODERATOR')">
    <a href="${UrlRoute.URL_ADMIN_GAME_NEW}" class="btn btn-light">
      Ajouter un jeu
    </a>
  </security:authorize>
</div>

<%@ include file="../footer.jsp" %>