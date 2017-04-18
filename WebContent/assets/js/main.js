$(document).ready(function() {
	$("#signInForm").submit(function(e) {
		e.preventDefault();
		$.ajax({
			type : 'post',
			url : 'user',
			data : $("#signInForm").serialize()+"&submitSignInForm",
			success : function(data) {
				$("#header").load(location.href+" #header>*","");			}
		});
	});
	$("#signUpForm").submit(function(e) {
		e.preventDefault();
		$.ajax({
			type : 'post',
			url : 'user',
			data : $("#signUpForm").serialize()+"&submitSignUpForm",
			success : function(data) {
				$("#header").load(location.href+" #header>*","");
			}
		});
	});
});