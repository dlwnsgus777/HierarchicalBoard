let main = {
	init : function() {
		this.userSignup();
		this.userLogin();
	},
	csrf: {
		token: $("meta[name='_csrf']").attr("content"),
		header: $("meta[name='_csrf_header']").attr("content")
	},
	validationSingup: function(userObj) {
		let userName = userObj.userName;
		let userId = userObj.userId;
		let passwd = userObj.password;
		let checkSpc = /[~!#$%^&*()_+|<>?:{}]/;	// @는 검사에서 제외
		console.log(userName.trim())
		if (!userName) {
			alert("유저 이름을 다시 정해주세요")
			return false;
		}
		
		if (!userId && checkSpc.test(userName)) {
			alert("아이디를 다시 입력해주세요")
			return false;
		}
		
		if (!passwd) {
			alert("비밀번호를 입력해주세요")
			return false;
		}
		return true;
	},
	userSignup: function() {
		let $this = this;
		let suerSignIn = document.querySelector("#btn-save");
		suerSignIn.addEventListener("click", function() {
			let user = {
					userName : document.querySelector("#userName").value.trim(),
					userId : document.querySelector("#userId").value.trim(),
					password: document.querySelector("#initPassword").value.trim()
			}
			
			if ($this.validationSingup(user)) {
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
			}
		})
	},
	userLogin : function() {
		let userSignIn = document.querySelector("#login-btn");

		userSignIn.addEventListener("click", function() {
			$("#login-frm").submit();
		});
	}
}

main.init();