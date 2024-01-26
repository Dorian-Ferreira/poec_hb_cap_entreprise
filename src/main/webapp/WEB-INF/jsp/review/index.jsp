<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Home"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container">
    <div class="row">
        <div class="d-flex justify-content-center">
            <div class="d-flex">
                <h1 class="mt-2">Liste des avis</h1>
            </div>
        </div>
        <div class="d-flex justify-content-center">
            <div class="d-flex">
                <!-- Label à afficher -->
                <c:set var="label" scope="request" value="Date"/>
                <!-- Sur quelle propriété de l'objet on souhaite trier -->
                <c:set var="sortable" value="createdAt"/>
                <%@ include file="../component/sortable.jsp" %>

                <c:set var="label" scope="request" value="Note"/>
                <c:set var="sortable" value="rating"/>
                <%@ include file="../component/sortable.jsp" %>

                <c:set var="label" scope="request" value="Jeu"/>
                <c:set var="sortable" value="game.name"/>
                <%@ include file="../component/sortable.jsp" %>

                <c:set var="label" scope="request" value="Joueur"/>
                <c:set var="sortable" value="writer.nickname"/>
                <%@ include file="../component/sortable.jsp" %>

                <span class="mt-auto mb-2">
                    <a href="${currentUrl}" class="btn-link">
                        Reset
                    </a>
                </span>
            </div>
        </div>
        <c:forEach items="${reviews.content}" var="review">
            <%@ include file="../component/review-card.jsp" %>
        </c:forEach>
    </div>

    <c:set var="page" scope="request" value="${reviews}"/>
    <c:set var="url" scope="request" value="/review"/>
    <%@ include file="../component/pagination.jsp" %>

</div>

<%@ include file="../footer.jsp" %>
