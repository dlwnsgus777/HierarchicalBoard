let main = {
	init : function() {
		this.userSignup();
		this.userLogin();
	},
	csrf: {
		token: $("meta[name='_csrf']").attr("content"),
		header: $("meta[name='_csrf_header']").attr("content")
	},
	userSignup: function() {
		let $this = this;
		let suerSignIn = document.querySelector("#btn-save");
		suerSignIn.addEventListener("click", function() {
			let user = {
					userName : document.querySelector("#userName").value,
					userId : document.querySelector("#userId").value,
					password: document.querySelector("#initPassword").value
			}

	        $.ajax({
	            type: 'POST',
	            url: '/users/signup',
	            dataType: 'json',
	            contentType:'application/json; charset=utf-8',
	            data: JSON.stringify(user),
	            beforeSend: function(xhr) {
    				xhr.setRequestHeader($this.csrf.header, $this.csrf.token);
    			}
	        }).done(function(result) {
	        	if (result.msg === "save") {
		        	alert("회원가입에 성공했습니다.");
		        	location.reload();
	        	} else {
	        		alert("회원가입에 실패했습니다. 다시 가입해주세요");
	        		location.reload();
	        	}
	        }).fail(function (error) {
	            console.log(error);
	        });
		})
	},
	userLogin : function() {
		let $this = this;
		let suerSignIn = document.querySelector("#login-btn");

		suerSignIn.addEventListener("click", function() {
			let data = {
					username : $("#username").val(),
					password : $("#password").val()
				}
			console.log(data)
	        $.ajax({
	            type: 'POST',
	            url: '/',//user/login
	            //dataType: 'html',
	            //contentType:'application/x-www-form-urlencoded; charset=utf-8',
	            data: JSON.stringify(data),
	            beforeSend: function(xhr) {
					xhr.setRequestHeader($this.csrf.header, $this.csrf.token);
				}
	        }).done(function(result) {
	        	console.log(result)
	        }).fail(function (error) {
	            console.log(error);
	        });
		});
	}
}

main.init();