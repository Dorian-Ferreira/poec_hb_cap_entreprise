<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../tag.jsp" %>
<c:set var="title" scope="request" value="Form Game"/>
<jsp:include flush="true" page="../admin_base.jsp"/>

<div class="container col-9">

    <c:if test="${isEdit}">
        <h1>Edit Game ${game.name}</h1>
    </c:if>
    <c:if test="${!isEdit}">
        <h1>Create Game</h1>
    </c:if>
    <f:form modelAttribute="game" method="post" action="${action}" cssClass="p-5">
        <div class="mb-3 row">
            <f:label path="name" class="col-sm-2 col-form-label">Name : </f:label>
            <div class="col-sm-10">
                <f:input type="text" cssClass="form-control" path="name"/>
                <f:errors path="name" cssClass="invalid-feedback"/>
            </div>
        </div>
        <div class="mb-3 row">
            <f:label path="publishedAt" class="col-sm-2 col-form-label">Publication Date : </f:label>
            <div class="col-sm-10">
                <f:input type="date" cssClass="form-control" path="publishedAt"/>
                <f:errors path="publishedAt" cssClass="invalid-feedback"/>
            </div>
        </div>

        <div class="mb-3 row">
            <f:label path="image" class="col-sm-2 col-form-label">Image : </f:label>
            <div class="col-sm-10">
                <f:input type="text" cssClass="form-control" path="image"/>
                <f:errors path="image" cssClass="invalid-feedback"/>
            </div>
        </div>

        <div class="mb-3 row">
            <f:label path="description" class="col-sm-2 col-form-label">Description : </f:label>
            <div class="col-sm-10">
                <f:textarea cssClass="form-control review-content" path="description"/>
                <f:errors path="description" cssClass="invalid-feedback"/>
            </div>
        </div>

        <div class="mb-3 row">
            <f:label path="publisher" class="col-sm-2 col-form-label">Publisher : </f:label>
            <div class="col-sm-10">
                <f:select path="publisher"
                          items="${publishers}"
                          cssClass="form-select"
                          itemLabel="name"
                >
                </f:select>
            </div>
        </div>

        <div class="mb-3 row">
            <f:label path="platforms" class="col-sm-2 col-form-label">Platforms : </f:label>
            <div class="col-sm-10">
                <input class="form-control" data-multiple-select-input="platform"/>
                <f:select path="platforms"
                          multiple="multiple"
                          items="${platforms}"
                          cssClass="form-select"
                          itemLabel="name"
                          data-multiple-select="platform"
                >
                </f:select>
            </div>
        </div>

        <div class="mb-3 row">
            <f:label path="genre" class="col-sm-2 col-form-label">Genre : </f:label>
            <div class="col-sm-10">
                <input class="form-control"/>
                <f:select path="genre"
                          items="${genres}"
                          cssClass="form-select"
                          itemLabel="name"
                >
                </f:select>
            </div>
        </div>

        <div class="mb-3 row">
            <f:label path="businessModel" class="col-sm-2 col-form-label">Business Model : </f:label>
            <div class="col-sm-10">
                <input class="form-control"/>
                <f:select path="businessModel"
                          items="${businessModels}"
                          cssClass="form-select"
                          itemLabel="name"
                >
                </f:select>
            </div>
        </div>

        <div class="mb-3 row">
            <f:label path="classification" class="col-sm-2 col-form-label">Classification : </f:label>
            <div class="col-sm-10">
                <input class="form-control"/>
                <f:select path="classification"
                          items="${classifications}"
                          cssClass="form-select"
                          itemLabel="name"
                >
                </f:select>
            </div>
        </div>

        <f:button class="btn btn-secondary" type="reset">Reset</f:button>
        <f:button class="btn btn-primary">Submit</f:button>
    </f:form>
</div>

<%@ include file="../../footer.jsp" %>
