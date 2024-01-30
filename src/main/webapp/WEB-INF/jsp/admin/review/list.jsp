<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../tag.jsp" %>
<c:set var="title" scope="request" value="Review Admin"/>
<jsp:include flush="true" page="../../base.jsp"/>

<div class="container">
  <div class="row">
    <div class="py-2">
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a class="link-if" href="/">Home</a></li>
          <li class="breadcrumb-item">BO Admin</li>
          <li class="breadcrumb-item">Liste des avis</li>
          <li class="breadcrumb-item">Avis ${reviews.numberOfElements == 0 ? 0 : ((reviews.number * reviews.size) + 1)} à ${(reviews.number * reviews.size) + reviews.numberOfElements} sur ${reviews.totalElements}</li>
        </ol>
      </nav>
    </div>
  </div>
  <div class="row">
    <div class="d-flex justify-content-center">
      <div class="d-flex">
        <h1 class="mt-2">Liste des avis</h1>
      </div>
    </div>
    <div class="d-flex justify-content-center">
      <div class="d-flex">
        <div class="d-flex main-container mx-5">
          <input type="text"
                 class="form-control"
                 placeholder="Starcraft, Chloe, ..."
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
        <c:set var="label" scope="request" value="Jeu"/>
        <c:set var="sortable" value="game.name"/>
        <%@ include file="../../component/sortable.jsp" %>
      </th>
      <th>
        <c:set var="label" scope="request" value="Date de soumission"/>
        <c:set var="sortable" value="createdAt"/>
        <%@ include file="../../component/sortable.jsp" %>
      </th>
      <th>
        <c:set var="label" scope="request" value="Note"/>
        <c:set var="sortable" value="rating"/>
        <%@ include file="../../component/sortable.jsp" %>
      </th>
      <th>
        <c:set var="label" scope="request" value="Joueur"/>
        <c:set var="sortable" value="writer.nickname"/>
        <%@ include file="../../component/sortable.jsp" %>
      </th>
      <th>
        <select class="form-select" aria-label="Default select example" moderationFilter>
          <option selected>Status</option>
          <option value="1">Tous</option>
          <option value="2">Non Modéré</option>
          <option value="3">Modéré</option>
        </select>
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
          <a class="btn btn-light" title="Voir le commentaire" href="${UrlRoute.URL_REVIEW}/${review.id}"><i class="fa-regular fa-eye"></i></a>
          <c:if test="${review.moderator == null}">
            <a class="btn btn-success" title="Accepter le commentaire" href="${UrlRoute.URL_ADMIN_REVIEW}/${review.id}/accept"><i class="fa-regular fa-square-check"></i></a>
          </c:if>
          <a class="btn btn-danger" title="Refuser le commentaire" href="${UrlRoute.URL_ADMIN_REVIEW}/${review.id}/refuse"><i class="fa-regular fa-circle-xmark"></i></a>
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
  <%@ include file="../../component/pagination.jsp" %>
</div>

<%@ include file="../../footer.jsp" %>