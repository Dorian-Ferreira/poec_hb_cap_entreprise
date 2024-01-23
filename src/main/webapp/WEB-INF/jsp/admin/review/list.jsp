<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../tag.jsp" %>
<c:set var="title" scope="request" value="Review"/>
<jsp:include flush="true" page="../admin_base.jsp"/>


<div class="container col-9">
  <%@ include file="../../components/review_list.jsp" %>
</div>

<%@ include file="../admin_footer.jsp" %>