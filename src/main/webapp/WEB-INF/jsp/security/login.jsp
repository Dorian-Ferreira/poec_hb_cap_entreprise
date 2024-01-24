<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container text-center">
  <div class="row mt-5">
    <div class="col-lg-3 col-md-2"></div>
    <form method="POST" action="${UrlRoute.URL_LOGIN}" class="form-signing col-lg-6 col-md-8 col-sm-12">
      <h2 class="form-heading">Se Connecter</h2>
      <div class="form-group ${error != null ? 'has-error' : ''}">
        <span>${message}</span>
        <input  name="username" type="text" class="form-control" placeholder="Nom d'utilisateur"
               autofocus="true"/>
        <input name="password" type="password" class="form-control" placeholder="Mot de passe"/>
        <p class="invalid-feedback">${error}</p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="btn btn-lg btn-primary btn-block mt-5" type="submit">Se Connecter</button>
        <h4 class="text-center mt-5">
          <a href="${contextPath}/register" class="btn-link">
            Créer un compte
          </a>
        </h4>
      </div>
    </form>
  </div>
</div>

<%@ include file="../footer.jsp" %>