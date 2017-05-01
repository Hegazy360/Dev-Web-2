<%@ include file="partials/header.jsp"%>
<br>


<div class="container">
<div class="post">
<div class="title">${post.title}</div>
<div class="description">${post.description}</div>
<div class="content">${post.content}</div>
</div>
<div class="comments">
<c:forEach items="${comments}" var="comment">
	<div class="comment">
	<p>${comment.userName}</p>
	<p>${comment.content}</p>
	</div>
</c:forEach>

<div class="new-comment">
<form method="POST" action='posts' name="newCommentForm">
		<input type="text" name="content" value="" /> <br /> <input
		type="hidden" name="postid" value="${param.postid}" /> <input
		type="hidden" name="groupid" value="${param.groupid}" /> <input
		type="submit" name="submitCommentForm" value="Comment" />
</form>
</div>
</div>

</div>




<br>
<%@ include file="partials/footer.jsp"%>