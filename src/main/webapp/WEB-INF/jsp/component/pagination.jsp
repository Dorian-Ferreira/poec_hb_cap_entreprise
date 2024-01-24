<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<core:set var="currentPage" value="${page.number + 1}"/>
<div class="navigation d-flex justify-content-center my-4">
  <div class="pagination">
    <core:if test="${!page.first}">
      <a class="first" href="${url}">
        &lt;&lt;
      </a>
    </core:if>
    <core:if test="${page.hasPrevious()}">
      <a class="previous" rel="prev" href="${url}?page=${currentPage - 1}">
        &lt;
      </a>
    </core:if>
    <span class="current">${currentPage}</span>
    <core:if test="${page.hasNext()}">
      <a class="next" href="${url}?page=${currentPage + 1}">
        &gt;
      </a>
    </core:if>
    <core:if test="${!page.last}">
      <a class="last" href="${url}?page=${page.totalPages}">
        &gt;&gt;
      </a>
    </core:if>
  </div>
</div>