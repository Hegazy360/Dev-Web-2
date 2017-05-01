$(document).ready(function() {
	
	$("#signUp").click(function(){
		$(".form").hide(250);
	    $(".signUpForm").show(500);
	});
	$("#signIn").click(function(){
		$(".form").hide(250);
	    $(".signInForm").show(500);
	});
	$(document).mouseup(function (e)
			  {
			    var container = $(".form");

			    if (!container.is(e.target) && container.has(e.target).length === 0) 
			    {
			        container.hide(500);
			    }
			});
	
	
//	$("#signInForm").submit(function(e) {
//		e.preventDefault();
//		$.ajax({
//			type : 'post',
//			url : 'user',
//			data : $("#signInForm").serialize()+"&submitSignInForm",
//			success : function(data) {
//				$("#header").load(location.href+" #header>*","");			}
//		});
//	});
//	$("#signUpForm").submit(function(e) {
//		e.preventDefault();
//		$.ajax({
//			type : 'post',
//			url : 'user',
//			data : $("#signUpForm").serialize()+"&submitSignUpForm",
//			success : function(data) {
//				$("#header").load(location.href+" #header>*","");
//			}
//		});
//	});

});


