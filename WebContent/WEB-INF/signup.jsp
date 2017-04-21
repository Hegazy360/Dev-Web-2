<%@ include file="partials/header.jsp" %>
        <form method="POST" action='user' name="signUpForm" id="signUpForm">
            User Name : <input type="text" name="uname"
                               value="<c:out value="${user.uname}" />" /> <br />
            Password : <input
                type="password" name="pass"
                value="<c:out value="" />" /> <br /> 
            Email : <input
                type="text" name="email"
                value="<c:out value="" />" /> <br /> 
            <input  type="submit" name = "submitSignUpForm" value="Submit" />
        </form>
<%@ include file="partials/footer.jsp" %>