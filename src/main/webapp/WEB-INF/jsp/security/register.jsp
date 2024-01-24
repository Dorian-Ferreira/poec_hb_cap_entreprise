<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container">
  <div class="row mt-5 align-items-center">
    <div class="col-lg-3 col-md-2"></div>
    <f:form cssClass="col-lg-6 col-md-8 col-sm-12" method="POST" modelAttribute="userForm" class="form-signin">
      <h2 class="form-signing-heading text-center">Créer un compte</h2>
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <f:input type="text" path="username" class="form-control" placeholder="Pseudo"
                    autofocus="true"/>
        <f:errors path="username"/>
      </div>
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <f:input type="text" path="email" class="form-control" placeholder="Email"
                    autofocus="true"/>
        <f:errors path="email"/>
      </div>
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <f:input type="date" path="birthAt" class="form-control" placeholder="Date de naissance"
                 autofocus="true"/>
        <f:errors path="birthAt"/>
      </div>
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <f:input type="password" path="password" class="form-control" placeholder="Mot de Passe"/>
        <f:errors path="password"/>
      </div>
      <div class="row">
        <div class="col-5"></div>
        <div class="align-self-center col-2">
          <button class="btn btn-primary " type="submit">Submit</button>
        </div>
      </div>
    </f:form>
  </div>
</div>

<%@ include file="../footer.jsp" %>