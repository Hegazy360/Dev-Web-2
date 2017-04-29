<%@ include file="partials/header.jsp"%>
<div class="container">
	<a class="button center" href="groups?action=newgroup">Create Group</a> <br>
	<div class="group-cards col-xs-8 col-xs-offset-2">
		<c:forEach items="${groups}" var="group">
		<div class="group-card">
			<a href="groups?groupid=${group.id}"> 
			<div class="top-bar">
				<div class="group-title">${group.title}</div>
				<div class="group-creator">Creator : ${group.creatorName}</div>
			</div>
			<div class="group-description">${group.description}</div>
			</a>
		</div>
		</c:forEach>
	</div>
</div>
<%@ include file="partials/footer.jsp"%>
