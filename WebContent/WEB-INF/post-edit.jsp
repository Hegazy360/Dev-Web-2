<%@ include file="partials/header.jsp" %>

        <form method="POST" action='posts' name="newPostForm">
            Title : <input
                type="text" name="title"
                value="${post.title}" /> <br /> 
            Description : <input
                type="text" name="description"
                value="${post.description}"/> <br /> 
            Content : <input
                type="text" name="content"
                value="${post.content}"/> <br /> 
             <input type="hidden" name="groupid" value="${param.groupid}"/>
            <input  type="submit" name ="submitPostForm" value="Submit" />
        </form>

<%@ include file="partials/footer.jsp" %>