function loginRegisterSwitch() {
	$('form').animate({
		height : "toggle",
		opacity : "toggle"
	}, "slow");
}

function showAlertAfterRegistration() {
	$('div.alert.alert-success').show();
	$('div.alert.alert-success').removeClass("fade");
}

$('.message a').click(function() {
	loginRegisterSwitch();
});

$('button.register').click(function() {
	var firstName = $("form.register-form input.firstName").val();
	var lastName = $("form.register-form input.lastName").val();
	var email = $("form.register-form input.email").val();
	var password = $("form.register-form input.password").val();
	var cpassword = $("form.register-form input.confirmPassword").val();

	if (firstName == '' || lastName == '' || email == '' || password == '' || cpassword == '') {
		alert("Не заповнені усі поля!");
	} else if ((password.length) < 8) {
		alert("Пароль повинен містити не менше 8 символів");
	} else if (password != cpassword) {
		alert("Паролі не співпадають");
	} else {
		var userRegistration = {
			firstName : firstName,
			lastName : lastName,
			email : email,
			password : password
		};

	$.post("registration", userRegistration, function(data) {
		if (data == "Success") {
			showAlertAfterRegistration();
			$("form")[0].reset();
			$("form")[1].reset();
			loginRegisterSwitch();	
			}
		});
	}
});

$("button.login").click(function() {
	var email = $("form.login-form input.email").val();
	var password = $("form.login-form input.password").val();

	if (email == '' || password == '') {
		alert("Не заповнені усі поля!");
	} else {
		var userLogin = {
			email : email,
			password : password
		};

		$.post("login", userLogin, function(data) {
			if(data !== ''){
				var customUrl = '';
				var urlContent = window.location.href.split('/');
				for (var i = 0; i < urlContent.length-1; i++) {
					customUrl+=urlContent[i]+'/'
				}
				customUrl+=data.destinationUrl;
				window.location = customUrl;
			}
			$("form")[1].reset();
		});
	}
});