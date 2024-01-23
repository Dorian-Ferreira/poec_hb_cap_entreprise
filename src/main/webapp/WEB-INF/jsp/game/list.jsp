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
        Genre
      </th>
      <th>
        Modèle économique
      </th>
      <th>
        Classification
      </th>
      <th>
        Platformes
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
            <div>
              ${game.genre.name}
            </div>
          </th>
          <th>
            <div>
              ${game.businessModel.name}
            </div>
          </th>
          <th>
            <div>
              ${game.classification.name}
            </div>
          </th>
          <th>
            <c:forEach items="${game.platforms}" var="platform">
              <div>
                  ${platform.name}
              </div>
            </c:forEach>
          </th>
          <th>
            <a class="btn btn-light" href="${UrlRoute.URL_GAME}/${game.id}">Voir</a>
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