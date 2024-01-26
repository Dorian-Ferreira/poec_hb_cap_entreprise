<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../tag.jsp" %>
<c:set var="title" scope="request" value="Review Admin"/>
<jsp:include flush="true" page="../../base.jsp"/>

<div class="container">
  <div class="row">
    <div class="d-flex justify-content-center">
      <div class="d-flex">
        <h1 class="mt-2">Liste des avis</h1>
      </div>
    </div>
    <div class="d-flex justify-content-center">
      <div class="d-flex">
        <!-- Label à afficher -->
        <c:set var="label" scope="request" value="Date"/>
        <!-- Sur quelle propriété de l'objet on souhaite trier -->
        <c:set var="sortable" value="createdAt"/>
        <%@ include file="../../component/sortable.jsp" %>

        <c:set var="label" scope="request" value="Note"/>
        <c:set var="sortable" value="rating"/>
        <%@ include file="../../component/sortable.jsp" %>

        <c:set var="label" scope="request" value="Jeu"/>
        <c:set var="sortable" value="game.name"/>
        <%@ include file="../../component/sortable.jsp" %>

        <c:set var="label" scope="request" value="Joueur"/>
        <c:set var="sortable" value="writer.nickname"/>
        <%@ include file="../../component/sortable.jsp" %>

        <span class="mt-auto mb-2">
          <a href="${currentUrl}" class="btn-link">
              Reset
          </a>
        </span>
      </div>
    </div>
  </div>
  <table class="table table-dark table-striped table-hover align-middle mt-4">
    <thead>
    <tr>
      <th>
        Jeu
      </th>
      <th>
        Date de soumission
      </th>
      <th>
        Note
      </th>
      <th>
        Auteur
      </th>
      <th>
        Statut
      </th>
      <th>
        Opérations
      </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${reviews.content}" var="review">
      <tr>
        <th>
            ${review.game.name}
        </th>
        <th>
            ${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy hh:mm")}
        </th>
        <th>
            ${review.rating}
        </th>
        <th>
            ${review.writer.nickname}
        </th>
        <th>
          <c:choose>
            <c:when test="${review.moderator != null}">
              Modéré par ${review.moderator.nickname}
            </c:when>
            <c:otherwise>
              En Attente
            </c:otherwise>
          </c:choose>
        </th>
        <th>
          <a class="btn btn-light" href="${UrlRoute.URL_REVIEW}/${review.id}">Voir</a>
          <c:if test="${review.moderator == null}">
            <a class="btn btn-success" href="${UrlRoute.URL_ADMIN_REVIEW}/${review.id}/accept">Accepter</a>
          </c:if>
          <a class="btn btn-danger" href="${UrlRoute.URL_ADMIN_REVIEW}/${review.id}/refuse">Refuser</a>
        </th>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <div>
    <a href="${UrlRoute.URL_EXPORT}" class="btn btn-link">
      <i class="fa-solid fa-file-excel me-1"></i>
      Télécharger export Excel
    </a>
  </div>
  <c:set var="page" scope="request" value="${reviews}"/>
  <c:set var="url" scope="request" value="${UrlRoute.URL_ADMIN_REVIEW}"/>
  <%@ include file="../../component/pagination.jsp" %>
</div>

<%@ include file="../../footer.jsp" %>