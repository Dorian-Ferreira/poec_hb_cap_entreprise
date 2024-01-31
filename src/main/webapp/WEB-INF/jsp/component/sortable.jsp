<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<core:set var="scrolling" value=""/>
<core:if test="${scroll != null}">
  <core:set var="scrolling" value="#${scroll}"/>
</core:if>

<div class="sort-filter d-flex align-items-center">
  <div class="d-flex">
    ${label}
  </div>
  <div class="ms-1 me-2 row">
    <c:set var="sortAsc" scope="request" value="sort=${sortable},asc"/>
    <a href="${jspUtils.generateUrlFrom(currentUrl, currentQuery, sortAsc)}${scrolling}" data-my-sortable="${sortAsc}">
      <i class="fa-solid fa-sort-up"></i>
    </a>
    <c:set var="sortDesc" scope="request" value="sort=${sortable},desc"/>
    <a href="${jspUtils.generateUrlFrom(currentUrl, currentQuery, sortDesc)}${scrolling}" data-my-sortable="${sortDesc}">
      <i class="fa-solid fa-sort-down"></i>
    </a>
  </div>
</div>