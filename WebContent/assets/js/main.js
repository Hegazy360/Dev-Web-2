$(document)
		.ready(
				function() {

					var id1 = getUrlParameter("id1");
					var id2 = getUrlParameter("id2");

					if( $('#chatLog').length )        
					{
						setInterval(function()
								{ 
								    $.ajax({
								      type:"post",
								      url:"chat?id1="+id1+"&id2="+id2,
								      datatype:"json",
								      success:function(responseJson)
								      {
									 		$("#chatLog").empty();
									        var $ul = $("<ul>").appendTo($("#chatLog")); 
									        $.each(responseJson, function(index, item) { 
												 console.log(item.content)
											    $("<li>").text(item.name).appendTo($ul);      
									            $("<li>").text(item.content).appendTo($ul); 
									            $("<li>").text(item.date).appendTo($ul);      

									        });
								      }
								    });
								}, 5000);


								
								 $("#messageSendForm").submit(function(e) {
								 $.ajax({
								 type : 'post',
								 url : $("#messageSendForm").attr("action"),
								 data : $("#messageSendForm").serialize(),
								 dataType: "json",
								 success : function(responseJson) {
									 		$("#chatLog").empty();
									 		$("#messageSendForm")[0].reset();
									        var $ul = $("<ul>").appendTo($("#chatLog")); 
									        $.each(responseJson, function(index, item) { 
												 console.log(item.content)
												    $("<li>").text(item.name).appendTo($ul);      
										            $("<li>").text(item.content).appendTo($ul); 
										            $("<li>").text(item.date).appendTo($ul); 									        });
								 },
								 error: function(err){
									 console.log(err);
									 console.log("ERROR");
								 }
								 
								 });
								 e.preventDefault();

								 });
					}
					
					if( $('#rightbar').length )         
					{
						console.log("right bar found");
						setInterval(function()
								{ 
								    $.ajax({
								      type:"post",
								      url:"user",
								      datatype:"json",
								      data: {
								    	  getOnlineFriends:true
								      },
								      success:function(responseJson)
								      {
								    	  
								    	  $("#online-friends-container").empty();
									        var $ul = $("<ul>").appendTo($("#online-friends-container")); 
									        $.each(responseJson, function(index, item) { 
												 console.log(item.uname)
											    $("<li>").append($("<a>").attr('href','user?userid='+item.id).text(item.uname)).appendTo($ul);      

									        });
								      }
								    });
								}, 10000);
					}
					
					

					$("#signUp").click(function() {
						$(".form").hide(250);
						$(".signUpForm").show(500);
					});
					$("#signIn").click(function() {
						$(".form").hide(250);
						$(".signInForm").show(500);
					});
					tinymce
							.init({
								selector : '#postContent',
								height : 300,
								theme : 'modern',
								plugins : [
										'advlist autolink lists link image charmap print preview hr anchor pagebreak',
										'searchreplace wordcount visualblocks visualchars code fullscreen',
										'insertdatetime media nonbreaking save table contextmenu directionality',
										'emoticons template paste textcolor colorpicker textpattern imagetools' ],
								toolbar1 : 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
								toolbar2 : 'print preview media | forecolor backcolor emoticons',
								image_advtab : true
							});
					$(document)
							.mouseup(
									function(e) {
										var container = $(".form");

										if (!container.is(e.target)
												&& container.has(e.target).length === 0) {
											container.hide(500);
										}
									});

					// $("#signInForm").submit(function(e) {
					// e.preventDefault();
					// $.ajax({
					// type : 'post',
					// url : 'user',
					// data : $("#signInForm").serialize()+"&submitSignInForm",
					// success : function(data) {
					// $("#header").load(location.href+" #header>*",""); }
					// });
					// });
					// $("#signUpForm").submit(function(e) {
					// e.preventDefault();
					// $.ajax({
					// type : 'post',
					// url : 'user',
					// data : $("#signUpForm").serialize()+"&submitSignUpForm",
					// success : function(data) {
					// $("#header").load(location.href+" #header>*","");
					// }
					// });
					// });

				});
function getUrlParameter(name) {
	name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
	var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
	var results = regex.exec(location.search);
	return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g,
			' '));
};

