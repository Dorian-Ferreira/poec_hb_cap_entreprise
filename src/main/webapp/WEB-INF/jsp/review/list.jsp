<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Review"/>
<jsp:include flush="true" page="../base.jsp"/>


<div class="container">
  <%@ include file="../components/review_list.jsp" %>
</div>

<%@ include file="../footer.jsp" %>