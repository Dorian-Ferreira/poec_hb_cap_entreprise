<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Formulaire Avis"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container">
  <div class="row">
    <div class="d-flex justify-content-between py-2">
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a class="link-if text-decoration-underline" href="/">Accueil</a></li>
          <li class="breadcrumb-item"><a class="link-if text-decoration-underline" href="${UrlRoute.URL_REVIEW}">Liste des Avis</a></li>
          <li class="breadcrumb-item">Donner un Avis</li>
        </ol>
      </nav>
    </div>
  </div>
  <div class="row">
    <div class="d-flex justify-content-center">
      <div class="d-flex">
        <h1 class="mt-2">Donner son avis sur un jeu</h1>
      </div>
    </div>
  </div>
  <f:form modelAttribute="reviewDto" method="post" action="${action}" cssClass="p-5 col-md-8 col-sm-12 mx-auto">
    <div class="mb-3 row">
      <span class="col-sm-2 col-form-label">Jeu : </span>
      <div class="col-sm-10">
        <f:select path="game"
                  items="${games}"
                  cssClass="form-select"
                  itemLabel="name"
        >
        </f:select>
      </div>
    </div>
    <div class="mb-3 row">
      <f:label path="description" class="col-sm-2 col-form-label">Description :</f:label>
      <div class="col-sm-10">
        <f:textarea cssClass="form-control review-content" path="description"/>
        <f:errors path="description" cssClass="invalid-feedback"/>
      </div>
    </div>
    <div class="mb-3 row">
      <f:label path="rating" class="col-sm-2 col-form-label">Note :</f:label>
      <div class="col-sm-10">
        <f:input type="number" min="0" max="20" step="0.5" cssClass="form-control" path="rating"/>
        <f:errors path="rating" cssClass="invalid-feedback"/>
      </div>
    </div>

    <f:button class="btn btn-success">Soumettre</f:button>
    <f:button class="btn btn-danger" type="reset">Recommencer</f:button>
  </f:form>
</div>
<%@ include file="../footer.jsp" %>