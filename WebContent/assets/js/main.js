$(document).ready(function() {
	
	$("#signUp").click(function(){
		$(".form").hide(250);
	    $(".signUpForm").show(500);
	});
	$("#signIn").click(function(){
		$(".form").hide(250);
	    $(".signInForm").show(500);
	});
	tinymce.init({
	    selector: '#postContent',
	    height: 300,
	    theme: 'modern',
	    plugins: [
	      'advlist autolink lists link image charmap print preview hr anchor pagebreak',
	      'searchreplace wordcount visualblocks visualchars code fullscreen',
	      'insertdatetime media nonbreaking save table contextmenu directionality',
	      'emoticons template paste textcolor colorpicker textpattern imagetools'
	    ],
	    toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
	    toolbar2: 'print preview media | forecolor backcolor emoticons',
	    image_advtab: true
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


