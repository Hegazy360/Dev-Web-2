<%@ include file="partials/header.jsp" %>
<br>

<c:out value="${group.title}"/>
<c:out value="${group.description}"/>
<br>
<c:forEach items="${posts}" var="post">    
    <c:out value="${post.title}"/>
    <c:out value="${post.description}"/>
    <c:out value="${post.content}"/>
</c:forEach>
<br>
<%@ include file="partials/footer.jsp" %>