<%@ include file="partials/header.jsp" %>
<br>



<div class="container">
<div>
<c:out value="${group.title}"/>
</div>
<div>
<c:out value="${group.description}"/>
</div>
	<a class="button center" href="posts?action=newpost&groupid=${group.id}">Create Post</a> <br>
	<div class="group-cards col-xs-8 col-xs-offset-2">
		<c:forEach items="${posts}" var="post">
		<div class="group-card">
			<a href="posts?groupid=${group.id}&postid=${post.id}">
			<div class="top-bar">
				<div class="group-title">${group.title}</div>
				<div class="group-creator">By : ${group.creatorName}</div>
			</div>
			<div class="group-description">${post.description}</div>
			<div class="group-description">${post.content}</div>
			
			</a>
		</div>
		</c:forEach>
	</div>
</div>

<%@ include file="partials/footer.jsp" %>