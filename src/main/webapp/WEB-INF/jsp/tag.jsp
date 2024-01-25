<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page import="fr.dorian_ferreira.cap_entreprise.mapping.UrlRoute" %>

<%-- Imports nécessaires pour récupérer le UserService dans les JSP --%>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="fr.dorian_ferreira.cap_entreprise.service.UserService" %>
<%@ page import="fr.dorian_ferreira.cap_entreprise.entity.User" %>
<%@ page import="fr.dorian_ferreira.cap_entreprise.utils.DateUtils" %>
<%@ page import="fr.dorian_ferreira.cap_entreprise.utils.JspUtils" %>

<%
  String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
  String path = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI).toString();
  Object query = request.getAttribute(RequestDispatcher.FORWARD_QUERY_STRING);
  String queryString = "";
  if (query != null) {
    queryString = query.toString();
  }
  request.setAttribute("currentQuery", queryString);
  request.setAttribute("currentPath", path);
  request.setAttribute("currentUrl", url + path);

  WebApplicationContext ctx = RequestContextUtils.findWebApplicationContext(request);
  if (ctx != null) {
    UserService userService = ctx.getBean(UserService.class);
    if (request.getUserPrincipal() != null) {
      User user = userService.findByName(request.getUserPrincipal().getName());
      request.setAttribute("userLogged", user);
    }

    request.setAttribute("dateUtils", ctx.getBean(DateUtils.class));
    request.setAttribute("jspUtils", ctx.getBean(JspUtils.class));
  }
%>