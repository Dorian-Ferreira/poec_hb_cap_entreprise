<%@ page contentType="text/html;charset=UTF-8" %>

<table class="table table-dark table-striped table-hover align-middle">
  <thead>
  <tr>
    <th>
      Game
    </th>
    <th>
      Publication Date
    </th>
    <th>
      Rating
    </th>
    <th>
      Writer
    </th>
    <th>
      Status
    </th>
    <th>
      Operations
    </th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${reviews}" var="review">
    <tr>
      <th>
        ${review.game.name}
      </th>
      <th>
          ${review.createdAt}
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
          <security:authorize access="hasRole('MODERATOR')">
            <c:if test="${review.moderator == null}">
              <a class="btn btn-success" href="">Accepter</a>
              <a class="btn btn-danger" href="">Refuser</a>
            </c:if>
          </security:authorize>
      </th>
    </tr>
  </c:forEach>
  </tbody>
</table>