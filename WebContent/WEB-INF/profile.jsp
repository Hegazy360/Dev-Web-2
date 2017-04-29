<%@ include file="partials/header.jsp" %>

<div class="container">
	<div id="informations" class="col-xs-12">
		<img class="profile-img" src="${root}/uploads/${currentUser.id}${currentUser.image}"/>
	
		<p class="champ">
			<span class="titre">Name :</span> 
			<span class="contenu">${currentUser.uname}</span>
		</p>
		
		<p class="champ">
			<span class="titre">Email :</span> 
			<span class="contenu">${currentUser.email}</span>
		</p>
		
		<a href="user?userid=${ currentUser.id }&&action=edit" title="Modify my informations">Modify my informations</a>
	</div>
	<div class="friend-cards cards">
	<h2>Friends</h2>
	<c:forEach items="${friends}" var="friend">
		<div class="friend-card card">
			<a href="user?userid=${ friend.id }"> 
				<div class="friend-name">${friend.uname}</div>
			</a>
		</div>
		</c:forEach>
	</div>
	<div class="posts-cards cards">
	<h2>Created Posts</h2>
	<c:forEach items="${createdPosts}" var="post">
		<div class="post-card card">
			<a href="posts?groupid=${post.group_id}&postid=${post.id}">
				<div class="post-title">${post.title}</div>
				<div class="post-description">${post.description}</div>
			</a>
		</div>
		</c:forEach>
	</div>
	
	
</div>
<%@ include file="partials/footer.jsp" %>
