<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Review"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container">
  <h1 class="mt-5">Juger un jeu</h1>
  <f:form modelAttribute="reviewDto" method="post" action="${action}" cssClass="p-5 col-md-8 col-sm-12 mx-auto">
    <div class="mb-3 row">
      <span class="col-sm-2 col-form-label">Game : </span>
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
      <f:label path="rating" class="col-sm-2 col-form-label">Rating :</f:label>
      <div class="col-sm-10">
        <f:input type="number" min="0" max="20" step="1" cssClass="form-control" path="rating"/>
        <f:errors path="rating" cssClass="invalid-feedback"/>
      </div>
    </div>
    <f:button class="btn btn-secondary" type="reset">Reset</f:button>
    <f:button class="btn btn-primary">Submit</f:button>
  </f:form>
</div>
<%@ include file="../footer.jsp" %>