<%@ include file="partials/header.jsp" %>

        <form method="POST" action='posts' name="newPostForm">
            Title : <input
                type="text" name="title"
                value="" /> <br /> 
            Description : <input
                type="text" name="description"
                value=""/> <br /> 
            Content : <input
                type="text" name="content"
                value=""/> <br /> 
             <input type="hidden" name="groupid" value="${param.groupid}"/>
            <input  type="submit" name ="submitPostForm" value="Submit" />
        </form>

<%@ include file="partials/footer.jsp" %>