function check() {
var username =  document.getElementById('user').value ;
var pass =  document.getElementById('pass').value ;
var error = '';

var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
if (username === null || username.length < 1) {
			error = 'Необходимо ввести имя пользователя.';
			document.getElementById('error').innerHTML = error;
			event.preventDefault();
            return;
        }
        else {
            if (reg.test(username) === false  & username.length > 
            1 & username != null){
            error = 'Необходимо ввести имя пользователя в формате email';
			document.getElementById('error').innerHTML = error;
			event.preventDefault();
            return; 
            }
        }
        if (pass === null || pass.length < 1) {
            error ='Необходимо ввести пароль.';
			document.getElementById('error').innerHTML = error;
			event.preventDefault();
            return;
        }
	 
}