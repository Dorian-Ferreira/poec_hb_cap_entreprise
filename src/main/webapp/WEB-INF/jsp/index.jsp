<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="Home"/>
<jsp:include flush="true" page="base.jsp"/>

<div class="container">
    <h1>Home</h1>
    <table class="table table-dark table-striped table-hover align-middle">
        <thead>
            <tr>
                <th>
                    Jeu
                </th>
                <th>
                    Date de soumission
                </th>
                <th>
                    Note
                </th>
                <th>
                    Auteur
                </th>
                <th>
                    Statut
                </th>
                <th>
                    Opérations
                </th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${reviews}" var="review">
                <tr>
                    <th>
                            ${review.game.name}
                    </th>
                    <th>
                            ${review.createdAt}
                    </th>
                    <th>
                            ${review.rating}
                    </th>
                    <th>
                            ${review.writer.nickname}
                    </th>
                    <th>
                        <c:choose>
                            <c:when test="${review.moderator != null}">
                                Modéré par ${review.moderator.nickname}
                            </c:when>
                            <c:otherwise>
                                En Attente
                            </c:otherwise>
                        </c:choose>
                    </th>
                    <th>
                        <a class="btn btn-light" href="${UrlRoute.URL_REVIEW}/${review.id}">Voir</a>
                        <security:authorize access="hasRole('MODERATOR')">
                            <c:if test="${review.moderator == null}">
                                <a class="btn btn-success" href="${UrlRoute.URL_ADMIN_REVIEW}/${review.id}/accept">Accepter</a>
                                <a class="btn btn-danger" href="${UrlRoute.URL_ADMIN_REVIEW}/${review.id}/refuse">Refuser</a>
                            </c:if>
                        </security:authorize>
                    </th>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <security:authorize access="hasRole('GAMER')">
        <a href="${UrlRoute.URL_REVIEW_NEW}" class="btn btn-light">
            Juger un jeu
        </a>
    </security:authorize>
</div>

<%@ include file="footer.jsp" %>
