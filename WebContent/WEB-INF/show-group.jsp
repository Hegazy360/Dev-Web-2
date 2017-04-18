<%@ include file="partials/header.jsp" %>
<br>

<a href="posts?action=newpost&groupid=${group.id}" }>Create Post</a>

<c:out value="${group.title}"/>
<c:out value="${group.description}"/>
<br>
<c:forEach items="${posts}" var="post">
<a href="posts?groupid=${group.id}&postid=${post.id}">
    <c:out value="${post.title}"/>
    <c:out value="${post.description}"/>
    <c:out value="${post.content}"/>
</a>
</c:forEach>
<br>
<%@ include file="partials/footer.jsp" %>