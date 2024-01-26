<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="BO Admin"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container">

  <h1 class="row">Bo Admin</h1>

  <a class="row link-if mt-2 h3" href="${UrlRoute.URL_ADMIN_GAME}">Liste des jeux</a>
  <a class="row link-if mt-2 h3" href="${UrlRoute.URL_ADMIN_REVIEW}">Liste des avis</a>

</div>

<%@ include file="../footer.jsp" %>