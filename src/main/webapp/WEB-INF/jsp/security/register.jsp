<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container">
  <f:form method="POST" modelAttribute="userForm" class="form-signin">
    <h2 class="form-signin-heading">Create your account</h2>
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <f:input type="text" path="username" class="form-control" placeholder="Email"
                    autofocus="true"/>
        <f:errors path="username"/>
      </div>
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <f:input type="text" path="name" class="form-control" placeholder="Account name"
                    autofocus="true"/>
        <f:errors path="name"/>
      </div>
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <f:input type="password" path="password" class="form-control" placeholder="Password"/>
        <f:errors path="password"/>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
  </f:form>
</div>

<%@ include file="../footer.jsp" %>