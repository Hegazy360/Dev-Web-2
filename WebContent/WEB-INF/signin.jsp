<%@ include file="partials/header.jsp" %>

        <form method="POST" action='UserController' name="signInForm">
            Email : <input
                type="text" name="email"
                value="" /> <br /> 
            Password : <input
                type="password" name="password"
                value=""/> <br /> 
            <input  type="submit" name = "submitSignInForm" value="Submit" />
        </form>
<%@ include file="partials/footer.jsp" %>