<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container text-center">
  <div class="row mt-3">
    <div class="col-lg-4 col-md-3"></div>
    <form method="POST" action="${UrlRoute.URL_LOGIN}" class="form-signing col-lg-4 col-md-6 col-sm-12">
      <h2 class="form-heading">Se Connecter</h2>
      <div class="form-group ${error != null ? 'has-error' : ''}">
        <span>${message}</span>
        <input name="username" type="text" class="form-control mt-4" placeholder="Nom d'utilisateur"
               autofocus="true"/>
        <input name="password" type="password" class="form-control mt-2" placeholder="Mot de passe"/>
        <p class="invalid-feedback">${error}</p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="btn btn-lg btn-primary btn-block mt-4" type="submit">Se Connecter</button>
        <div class="mt-4">
          <a class="link-if" data-toggle="tooltip" title="C'est 12345">Mot de passe oublié</a>
        </div>
        <h4 class="text-center mt-4">
          <a href="${UrlRoute.URL_REGISTER}" class="btn-link">
            Créer un compte
          </a>
        </h4>
      </div>
    </form>
  </div>
</div>

<%@ include file="../footer.jsp" %>