<%@ include file="partials/header.jsp" %>
<br>



<div class="container">
<div>
<c:out value="${group.title}"/>
</div>
<div>
<c:out value="${group.description}"/>
</div>
	<c:if test="${sessionScope.user != null}">
	<a class="button center" href="posts?action=newpost&groupid=${group.id}">Create Post</a> <br>
	</c:if>
	<div class="cards col-xs-8 col-xs-offset-2">
		<c:forEach items="${posts}" var="post">
		<div class="card">
			<a href="posts?groupid=${group.id}&postid=${post.id}">
			<div class="top-bar">
				<div class="title">${post.title}</div>
				<div class="creator">By : ${post.authorName}</div>
			</div>
			<div class="description">${post.description}</div>
			<div class="content">${post.content}</div>
			
			</a>
		</div>
		</c:forEach>
	</div>
</div>

<%@ include file="partials/footer.jsp" %>