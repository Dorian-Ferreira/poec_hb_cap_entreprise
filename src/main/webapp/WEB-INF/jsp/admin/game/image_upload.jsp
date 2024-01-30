<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../tag.jsp" %>
<c:set var="title" scope="request" value="Image Jeu"/>
<jsp:include flush="true" page="../../base.jsp"/>

<div class="container">
    <div class="row">
        <div class="py-2">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a class="link-if" href="/">Home</a></li>
                    <li class="breadcrumb-item"><a class="link-if" href="${UrlRoute.URL_ADMIN_GAME}">Liste des jeux</a></li>
                    <li class="breadcrumb-item">Téléverser une image</li>
                </ol>
            </nav>
        </div>
    </div>

    <h1>Ajouter une image au Jeu ${game.name}</h1>
    <form method="POST" action="${UrlRoute.URL_ADMIN_GAME_IMAGE}/${game.id}" enctype="multipart/form-data">
        <label>Choisir un fichier:</label>
        <input type="file" name="file" />
        <input type="submit" value="Soumettre"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>

</div>

<%@ include file="../../footer.jsp" %>
