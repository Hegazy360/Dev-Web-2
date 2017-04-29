<%@ include file="partials/header.jsp" %>
        <form method="POST" action='user' name="userEditForm" id="signUpForm" enctype="multipart/form-data">
            User Name : <input type="text" name="uname"
                               value="${currentUser.uname}" /> <br />
            Password : <input
                type="password" name="pass"
                value="" /> <br /> 
            Email : <input
                type="text" name="email"
                value="${currentUser.email}"/> <br /> 
                <input type="file" name="file" />
            <input type="hidden" name="userid" value="${param.userid}"/>
            <input  type="submit" name = "submitSignUpForm" value="Submit" />
        </form>       
<%@ include file="partials/footer.jsp" %>