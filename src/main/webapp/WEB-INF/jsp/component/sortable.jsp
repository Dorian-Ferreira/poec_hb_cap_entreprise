<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="sort-filter d-flex">
  ${label}
  <div class="ms-1 me-2 row">
    <c:set var="sortAsc" scope="request" value="sort=${sortable},asc"/>
    <a href="${jspUtils.generateUrlFrom(currentUrl, currentQuery, sortAsc)}" data-my-sortable="${sortAsc}">
      <i class="fa-solid fa-sort-up"></i>
    </a>
    <c:set var="sortDesc" scope="request" value="sort=${sortable},desc"/>
    <a href="${jspUtils.generateUrlFrom(currentUrl, currentQuery, sortDesc)}" data-my-sortable="${sortDesc}">
      <i class="fa-solid fa-sort-down"></i>
    </a>
  </div>
</div>