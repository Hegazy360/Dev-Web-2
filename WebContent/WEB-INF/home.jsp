<%@ include file="partials/header.jsp" %>
  <br> HOME
  <br> hello ${user.uname}
  <br>

  <a href="groups?action=newgroup">Create Group</a>
  <br>
  <c:forEach items="${groups}" var="group">
    <a href="groups?groupid=${group.id}">
      <c:out value="${group.title}" />
      <c:out value="${group.description}" />
      <c:out value="${group.creator_id}" />
    </a>
  </c:forEach>
  <br>
  <%@ include file="partials/footer.jsp" %>
