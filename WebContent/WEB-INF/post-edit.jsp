<%@ include file="partials/header.jsp"%>

<form method="POST" action='posts' name="editPostForm">
	Title : <input type="text" name="title" value="${post.title}" /> <br />
	Description : <input type="text" name="description"
		value="${post.description}" /> <br /> Content :
	<textarea name="content" id="postContent">${post.content}</textarea>
	<br /> <input type="hidden" name="groupid" value="${param.groupid}" />
	<input type="hidden" name="postid" value="${param.postid}" /> <input
		type="submit" name="submitEditPostForm" value="Submit" />
</form>

<%@ include file="partials/footer.jsp"%>