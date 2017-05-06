<%@ include file="partials/header.jsp"%>


<div id="chatLog">
		
</div>

<form method="POST" action='chat?id1=${param.id1}&id2=${param.id2}'
	name="messageSendForm" id="messageSendForm" class="col-xs-6 col-xs-offset-3">
	Message : <br> <input type="text" name="message" id="message" value="" /> <br />
	<input type="hidden" name="id1" value="${param.id1}"/>
	<input type="hidden" name="id2" value="${param.id2}"/>
	<input type="submit" name="sendMessage" value="Send" />
</form>







<%@ include file="partials/footer.jsp"%>