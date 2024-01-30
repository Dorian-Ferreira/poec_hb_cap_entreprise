<%@ page contentType="text/html;charset=UTF-8" %>

<div class="col-lg-4 col-md-6 col-sm-12 mt-4">
  <div class="main-review-card w-100 ${jspUtils.getBorderCssClass(review.moderator != null)}">
    <p class="text-center text-white">
      Rédigé le ${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy")}
      par <a class="link-if" href="${UrlRoute.URL_REVIEW}?search=${review.writer.nickname}">${review.writer.nickname}</a>
      <figcaption class="blockquote-footer text-center">
        <c:if test="${not empty review.moderator}">
          Modéré par <cite title="Source Title">${review.moderator.nickname}</cite> -
          le ${dateUtils.getDateFormat(review.moderatedAt, "dd/MM/yyyy")}
        </c:if>
        <c:if test="${empty review.moderator}">
          <cite title="Source Title">En attente de moderation ⌛</cite>
          <c:if test="${userLogged.moderator}">
            <a class="btn btn-link rating-20"
               href="${UrlRoute.URL_ADMIN_REVIEW}/${review.id}/accept"
               title="Accepter"
            >
              <i class="fa fa-check fa-2x"></i>
            </a>
            /
            <a class="btn btn-link rating-5"
               href="${UrlRoute.URL_ADMIN_REVIEW}/${review.id}/refuse"
               title="Refuser"
            >
              <i class="fa-solid fa-xmark fa-2x"></i></i>
            </a>
          </c:if>
        </c:if>
      </figcaption>
    </p>
    <div class="review-card w-100">
      <p class="review-description h-75">
        <a class="link-if" href="${UrlRoute.URL_REVIEW}/${review.id}">
          ${jspUtils.excerpt(review.description, 209)}
        </a>
      </p>
      <div class="d-flex justify-content-between">
        <p class="${jspUtils.getCssClas(review.rating)}">
          ${jspUtils.getStringRating(review.rating)} / 20
        </p>
        <a class="btn-link" href="${UrlRoute.URL_GAME}/${review.game.slug}">
          ${review.game.name}
        </a>
      </div>
    </div>
  </div>
</div>