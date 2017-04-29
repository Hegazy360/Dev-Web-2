<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<jsp:include page="partials/header.jsp">
	<jsp:param value="pageStyle" name="profile"/>
</jsp:include>

<section>
	<img class="profile-img" src="${root}/uploads/${currentUser.id}${currentUser.image}"/>
	
	<div id="informations">
		<p class="champ">
			<span class="titre">Nom d'utilisateur :</span> 
			<span class="contenu"><c:out value="${currentUser.uname}"/></span>
		</p>
		
		<p class="champ">
			<span class="titre">email :</span> 
			<span class="contenu"><c:out value="${currentUser.email}"/></span>
		</p>
		
		<a href="user?userid=${ currentUser.id }&&action=edit" title="Modify my informations">Modify my informations</a>
	</div>
</section>

<%@ include file="partials/footer.jsp" %>