<%@ include file="partials/header.jsp" %>
        <form method="POST" action='user' name="signUpForm" id="signUpForm">
            <% String action = request.getParameter("action");
                System.out.println(action+"signup");
            %>
            <% if (action.equalsIgnoreCase("edit")) {%>
            User Name : <input type="text" name="uname"
                               value="<c:out value="${user.uname}" />" readonly="readonly"/> (You Can't Change this)<br /> 
            <%} else {%>
            User Name : <input type="text" name="uname"
                               value="<c:out value="${user.uname}" />" /> <br />
            <%}%>
            Password : <input
                type="password" name="pass"
                value="<c:out value="${user.password}" />" /> <br /> 
            Email : <input
                type="text" name="email"
                value="<c:out value="${user.email}" />" /> <br /> 
            <input  type="submit" name = "submitSignUpForm" value="Submit" />
        </form>
<%@ include file="partials/footer.jsp" %>