<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../tag.jsp" %>
<c:set var="title" scope="request" value="Image Jeu"/>
<jsp:include flush="true" page="../../base.jsp"/>

<div class="container">

    <h1>Ajouter une image au Jeu ${game.name}</h1>
    <form method="POST" action="${UrlRoute.URL_ADMIN_GAME_IMAGE}/${game.id}?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
        <label>Choisir un fichier:</label>
        <input type="file" name="file" />
        <input type="submit" value="Soumettre"/>
    </form>

</div>

<%@ include file="../../footer.jsp" %>
