<%@ include file="partials/header.jsp" %>


<img src="${root}/uploads/${currentUser.id}${currentUser.image}"/>

<p class="champ">
	<span class="titre">Nom d'utilisateur :</span>
	<span class="contenu"><c:out value="${currentUser.uname}"/></span>
</p>

<p class="champ">
	<span class="titre">email :</span>
	<span class="contenu"><c:out value="${currentUser.email}"/></span>
</p>

<a href="user?userid=${ currentUser.id }&&action=edit" title="Modify my informations">Modify my informations</a>

<%@ include file="partials/footer.jsp" %>
