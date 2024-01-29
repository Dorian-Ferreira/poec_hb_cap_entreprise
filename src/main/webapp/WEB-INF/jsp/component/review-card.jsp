<%@ page contentType="text/html;charset=UTF-8" %>

<div class="col-lg-4 col-md-6 col-sm-12 mt-4">
  <div class="main-review-card w-100 ${jspUtils.getBorderCssClass(review.moderator != null)}">
    <p class="text-center text-white">
      Le ${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy")}
      par ${review.writer.nickname}
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