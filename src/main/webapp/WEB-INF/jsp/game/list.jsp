<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Jeux"/>
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

  <div class="row">
    <c:forEach items="${games.content}" var="game">
      <a class="col-4 mt-2 main-game-card" href="${UrlRoute.URL_GAME}/${game.id}">
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