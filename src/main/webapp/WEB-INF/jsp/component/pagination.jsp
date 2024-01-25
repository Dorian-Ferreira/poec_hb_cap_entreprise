<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<core:set var="currentPage" value="${page.number + 1}"/>
<div class="navigation d-flex justify-content-center my-4">
  <div class="pagination">
    <core:if test="${!page.first && currentPage - 1 != 1}">
      <a class="first" href="${url}">
          1
      </a>
    </core:if>
    <core:if test="${currentPage - 2 > 1}">
      <a class="previous" rel="prev" href="${url}?page=${currentPage - 2}">
          ${currentPage - 2}
      </a>
    </core:if>
    <core:if test="${page.hasPrevious()}">
      <a class="previous" rel="prev" href="${url}?page=${currentPage - 1}">
          ${currentPage - 1}
      </a>
    </core:if>
    <a class="current disabled">${currentPage}</a>
    <core:if test="${page.hasNext() && currentPage + 1 != page.totalPages}">
      <a class="next " href="${url}?page=${currentPage + 1}">
          ${currentPage + 1}
      </a>
      <core:if test="${currentPage + 2 <= page.totalPages - 1}">
        <a class="previous" rel="prev" href="${url}?page=${currentPage + 2}">
            ${currentPage + 2}
        </a>
      </core:if>
    </core:if>
    <core:if test="${!page.last}">
      <a class="last" href="${url}?page=${page.totalPages}">
          ${page.totalPages}
      </a>
    </core:if>
  </div>
</div>