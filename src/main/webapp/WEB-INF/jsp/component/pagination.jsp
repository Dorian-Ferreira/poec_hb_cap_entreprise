<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<core:set var="currentPage" value="${page.number + 1}"/>
<core:set var="scrolling" value=""/>
<core:if test="${scroll != null}">
  <core:set var="scrolling" value="#${scroll}"/>
</core:if>
<div class="navigation d-flex justify-content-center mt-4">
  <div class="pagination">
    <core:if test="${!page.first && currentPage - 1 != 1}">
      <core:set var="firstPage" value="page=1"/>
      <a class="first" href="${jspUtils.generateUrlFrom(currentUrl, currentQuery, firstPage)}${scrolling}">
          1
      </a>
    </core:if>
    <core:if test="${currentPage - 2 > 1}">
      <core:set var="previousPreviousPage" value="page=${(currentPage - 2)}"/>
      <a class="previous" rel="prev" href="${jspUtils.generateUrlFrom(currentUrl, currentQuery, previousPreviousPage)}${scrolling}">
          ${currentPage - 2}
      </a>
    </core:if>
    <core:if test="${page.hasPrevious()}">
      <core:set var="previousPage" value="page=${(currentPage - 1)}"/>
      <a class="previous" rel="prev" href="${jspUtils.generateUrlFrom(currentUrl, currentQuery, previousPage)}${scrolling}">
          ${currentPage - 1}
      </a>
    </core:if>
    <a class="current disabled">${currentPage}</a>
    <core:if test="${page.hasNext() && currentPage + 1 != page.totalPages}">
      <core:set var="nextPage" value="page=${(currentPage + 1)}"/>
      <a class="next " href="${jspUtils.generateUrlFrom(currentUrl, currentQuery, nextPage)}${scrolling}">
          ${currentPage + 1}
      </a>
      <core:if test="${currentPage + 2 <= page.totalPages - 1}">
        <core:set var="nextNextPage" value="page=${(currentPage + 2)}"/>
        <a class="previous" rel="prev" href="${jspUtils.generateUrlFrom(currentUrl, currentQuery, nextNextPage)}${scrolling}">
            ${currentPage + 2}
        </a>
      </core:if>
    </core:if>
    <core:if test="${!page.last}">
      <core:set var="lastPage" value="page=${page.totalPages}"/>
      <a class="last" href="${jspUtils.generateUrlFrom(currentUrl, currentQuery, lastPage)}${scrolling}">
          ${page.totalPages}
      </a>
    </core:if>
  </div>
</div>