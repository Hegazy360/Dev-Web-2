<%@ include file="partials/header.jsp" %>

<img src="${root}/uploads/${currentUser.id}${currentUser.image}"/> 
<c:out value="${currentUser.uname}"/>
<c:out value="${currentUser.email}"/>


<%@ include file="partials/footer.jsp" %>
