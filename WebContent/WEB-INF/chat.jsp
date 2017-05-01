<%@ include file="partials/header.jsp"%>



  <div id="actions" data-peerid="${sessionScope.user.id}">
    Your PeerJS ID is <span id="pid"></span><br>
    Connect to a peer: <input type="text" id="rid" placeholder="Someone else's id"><input class="button" type="button" value="Connect" id="connect"><br><br>

    <form id="send">
      <input type="text" id="text" placeholder="Enter message"><input class="button" type="submit" value="Send to selected peers">
    </form>
    <button id="close">Close selected connections</button>
    <div id="wrap"><div id="connections"><span class="filler">You have not yet
        made any connections.</span></div>
  </div>




  
  



<%@ include file="partials/footer.jsp"%>