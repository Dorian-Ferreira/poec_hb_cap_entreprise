<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../tag.jsp" %>
<c:set var="title" scope="request" value="Formulaire Jeu"/>
<jsp:include flush="true" page="../../base.jsp"/>

<div class="container">
    <div class="row">
        <div class="py-2">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a class="link-if text-decoration-underline" href="/">Accueil</a></li>
                    <li class="breadcrumb-item"><a class="link-if text-decoration-underline" href="${UrlRoute.URL_GAME}">Liste des Jeux</a></li>
                    <li class="breadcrumb-item">Ajouter un Jeu</li>
                </ol>
            </nav>
        </div>
    </div>

    <div class="row mt-4">
        <div class="d-flex justify-content-center">
            <c:if test="${isEdit}">
                <h1>Modifier le Jeu ${game.name}</h1>
            </c:if>
            <c:if test="${!isEdit}">
                <h1>Créer un Jeu</h1>
            </c:if>
        </div>
    </div>
    <f:form modelAttribute="game" method="post" action="${action}" cssClass="p-5">
        <div class="mb-3 row">
            <f:label path="name" class="col-md-12 col-lg-2 col-form-label">Nom : </f:label>
            <div class="col-lg-10 col-md-12">
                <f:input type="text" cssClass="form-control" path="name"/>
                <f:errors path="name" cssClass="invalid-feedback"/>
            </div>
        </div>
        <div class="mb-3 row">
            <f:label path="publishedAt" class="col-md-12 col-lg-2 col-form-label">Date de sortie : </f:label>
            <div class="col-lg-10 col-md-12">
                <f:input type="date" cssClass="form-control" path="publishedAt"/>
                <f:errors path="publishedAt" cssClass="invalid-feedback"/>
            </div>
        </div>

        <div class="mb-3 row">
            <f:label path="description" class="col-md-12 col-lg-2 col-form-label">Description : </f:label>
            <div class="col-lg-10 col-md-12">
                <f:textarea cssClass="form-control review-content" path="description"/>
                <f:errors path="description" cssClass="invalid-feedback"/>
            </div>
        </div>

        <div class="mb-3 row">
            <f:label path="publisher" class="col-md-12 col-lg-2 col-form-label">Éditeur :
                <a class="btn-green" href="${UrlRoute.URL_ADMIN_PUBLISHER_NEW}" title="Ajouter un éditeur">
                    <i class="fa-solid fa-square-plus fs-3"></i>
                </a>
            </f:label>
            <div class="col-lg-10 col-md-12">
                <f:select path="publisher"
                          items="${publishers}"
                          cssClass="form-select"
                          itemLabel="name"
                >
                </f:select>
                <f:errors path="publisher" cssClass="invalid-feedback"/>
            </div>
        </div>

        <div class="mb-3 row">
            <f:label path="platforms" class="col-md-12 col-lg-2 col-form-label">Platformes : </f:label>
            <div class="col-lg-10 col-md-12">
                <input class="form-control" data-multiple-select-input="platform"/>
                <f:select path="platforms"
                          multiple="multiple"
                          items="${platforms}"
                          cssClass="form-select"
                          itemLabel="name"
                          data-multiple-select="platform"
                >
                </f:select>
                <f:errors path="platforms" cssClass="invalid-feedback"/>
            </div>
        </div>

        <div class="mb-3 row">
            <f:label path="genre" class="col-md-12 col-lg-2 col-form-label">Genre : </f:label>
            <div class="col-lg-10 col-md-12">
                <f:select path="genre"
                          items="${genres}"
                          cssClass="form-select"
                          itemLabel="name"
                >

                </f:select>
                <f:errors path="genre" cssClass="invalid-feedback"/>
            </div>
        </div>

        <div class="mb-3 row">
            <f:label path="businessModel" class="col-md-12 col-lg-2 col-form-label">Modèle Économique : </f:label>
            <div class="col-lg-10 col-md-12">
                <f:select path="businessModel"
                          items="${businessModels}"
                          cssClass="form-select"
                          itemLabel="name"
                >
                </f:select>
                <f:errors path="businessModel" cssClass="invalid-feedback"/>
            </div>
        </div>

        <div class="mb-3 row">
            <f:label path="classification" class="col-md-12 col-lg-2 col-form-label">Classification : </f:label>
            <div class="col-lg-10 col-md-12">
                <f:select path="classification"
                          items="${classifications}"
                          cssClass="form-select"
                          itemLabel="name"
                >
                </f:select>
                <f:errors path="classification" cssClass="invalid-feedback"/>
            </div>
        </div>

        <f:button class="btn btn-success">Soumettre</f:button>
        <f:button class="btn btn-danger" type="reset">Recommencer</f:button>
    </f:form>
</div>

<%@ include file="../../footer.jsp" %>
