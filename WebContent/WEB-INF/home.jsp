<%@ include file="partials/header.jsp"%>
<div class="container">
	<c:if test="${sessionScope.user != null}">
		<a class="button center" href="groups?action=newgroup">Create
			Group</a>
		<br>
	</c:if>

	<div class="cards col-xs-8 col-xs-offset-2">
		<c:forEach items="${groups}" var="group">
			<div class="card">
				<a href="groups?groupid=${group.id}">
					<div class="top-bar">
						<div class="title">${group.title}</div>
						<div class="creator">Creator : ${group.creatorName}</div>
					</div>
					<div class="description">${group.description}</div>
				</a>
			</div>
		</c:forEach>
	</div>
</div>
<%@ include file="partials/footer.jsp"%>
