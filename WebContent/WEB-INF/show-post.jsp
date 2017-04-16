<%@ include file="partials/header.jsp"%>
<br>

<c:out value="${post.title}" />
<c:out value="${post.description}" />
<c:out value="${post.content}" />

<br>
<c:forEach items="${comments}" var="comment">
	<c:out value="${comment.userName}" />
	<c:out value="${comment.content}" />
	<br>
</c:forEach>

<form method="POST" action='posts' name="newCommentForm">
	Title : <input type="text" name="content" value="" /> <br /> <input
		type="hidden" name="postid" value="${param.postid}" /> <input
		type="hidden" name="groupid" value="${param.groupid}" /> <input
		type="submit" name="submitCommentForm" value="Submit" />
</form>
<br>
<%@ include file="partials/footer.jsp"%>