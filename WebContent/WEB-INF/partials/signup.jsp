<div class="form signUpForm">
<form method="POST" action='user' name="signUpForm" id="signUpForm" enctype="multipart/form-data" class="col-xs-6 col-xs-offset-3">
            User Name : <br> <input type="text" name="uname"
                               value="<c:out value="" />" /> <br />
            Password : <br><input
                type="password" name="pass"
                value="<c:out value="" />" /> <br /> 
            Email : <br><input
                type="text" name="email"
                value="" /> <br /> 
                <input type="file" name="file" />
            <input  type="submit" name = "submitSignUpForm" value="Submit" />
</form>       
</div>