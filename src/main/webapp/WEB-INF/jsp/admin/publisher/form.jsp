<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../tag.jsp" %>
<c:set var="title" scope="request" value="Formulaire éditeur"/>
<jsp:include flush="true" page="../../base.jsp"/>

<div class="container">
    <div class="row">
        <div class="py-2">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a class="link-if text-decoration-underline" href="/">Accueil</a></li>
                    <li class="breadcrumb-item"><a class="link-if text-decoration-underline" href="${UrlRoute.URL_ADMIN_GAME_NEW}">Ajouter un Jeu</a></li>
                    <li class="breadcrumb-item">Ajouter un éditeur</li>
                </ol>
            </nav>
        </div>
    </div>

    <div class="row mt-4">
        <div class="d-flex justify-content-center">
            <h1>Créer un Éditeur</h1>
        </div>
    </div>
    <f:form modelAttribute="publisher" method="post" action="${action}" cssClass="p-5">
        <div class="mb-3 row">
            <f:label path="name" class="col-sm-2 col-form-label">Nom : </f:label>
            <div class="col-sm-10">
                <f:input type="text" cssClass="form-control" path="name"/>
                <f:errors path="name" cssClass="invalid-feedback"/>
            </div>
        </div>
        <f:button class="btn btn-success">Soumettre</f:button>
        <f:button class="btn btn-danger" type="reset">Recommencer</f:button>
    </f:form>
</div>

<%@ include file="../../footer.jsp" %>
