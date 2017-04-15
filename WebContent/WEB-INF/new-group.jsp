<%@ include file="partials/header.jsp" %>

        <form method="POST" action='groups' name="newGroupForm">
            Title : <input
                type="text" name="title"
                value="" /> <br /> 
            Description : <input
                type="text" name="description"
                value=""/> <br /> 
            <input  type="submit" name ="submitGroupForm" value="Submit" />
        </form>




<%@ include file="partials/footer.jsp" %>