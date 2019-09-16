// Using an object literal for a jQuery feature
var page = {

	init : function() {

		var cookie_userId = page.getLogin("userId");
		var cookie_pwd = page.getLogin("pwd");

		if (cookie_userId != "") {
			$("#userId").val(cookie_userId);
			$("#pwd").val(cookie_pwd);
			$("#rememberChk").attr("checked", true);
		}
		page.registerEvents();

	},

	registerEvents : function() {

		$(document)
				.on(
						'change',
						'#rememberChk',
						function(e) {

							var rememberChk = $('#rememberChk').is(":checked");

							if (rememberChk) {

								var isRemember = confirm("이 PC에 로그인 정보를 저장하시겠습니까?\n	PC방등의 공공장소에서는 개인정보가 유출될 수 있으니 주의해주십시오.");
								if (!isRemember) {
									$('#rememberChk').attr("checked", false);
								}
							}
						});

		$(document).on('click', '#login', function(e) {

			layout.loading(true);

			page.remember();

			var userId = $("#userId").val();
			var pwd = $("#pwd").val();

			var params = "userId=" + userId;
			params += "&pwd=" + pwd;

			/*if(!page.validateEmail(email)){
				alert("이메일을 확인해주세요.");
				layout.loading(false);
				$("#email").focus();
				return;
			}*/

			if (userId == "") {
				alert("전화번호를 입력해주세요.");
				layout.loading(false);
				$("#userId").focus();
				return;
			}

			if (pwd == "") {
				alert("비밀번호를 입력해주세요.");
				layout.loading(false);
				$("#pwd").focus();
				return;
			}

			var URL = "/auth/login";

			$.ajax({
				url : URL,
				type : "POST",
				data : params,
				dataType : 'json',
				success : function(data) {
					if (data.code == '0') {
						page.remember();
						$(location).attr('href', '/auth');
					} else {
						alert(data.message);
						layout.loading(false);
					}
				}
			});
		});

		$(document).on('keydown', '#pwd', function(e) {

			if (e.keyCode == 13) {

				$(this).blur();

				layout.loading(true);

				page.remember();

				var userId = $("#userId").val();
				var pwd = $("#pwd").val();

				var params = "userId=" + userId;
				params += "&pwd=" + pwd;

				//			  	if(!page.validateEmail(email)){
				//			  		alert("이메일을 확인해주세요.");
				//			  		layout.loading(false);
				//			  		$("#email").focus();
				//			  		return;
				//			  	}
					
				if (userId == "") {
					alert("전화번호를 입력해주세요.");
					layout.loading(false);
					$("#userId").focus();
					return;
				}
				
				if (pwd == "") {
					alert("비밀번호를 입력해주세요.");
					layout.loading(false);
					$("#pwd").focus();
					return;
				}

				var params = "userId=" + userId;
				params += "&pwd=" + pwd;

				var URL = "/auth/login";

				$.ajax({
					url : URL,
					type : "POST",
					data : params,
					dataType : 'json',
					success : function(data) {
						if (data.code == '0') {
							page.remember();
							$(location).attr('href', '/auth');
						} else {
							alert(data.message);
							layout.loading(false);
						}
					}
				});
			}
		});
	},

	remember : function() {

		var rememberChk = $('#rememberChk').is(":checked");

		if (rememberChk) {
			page.saveLogin("userId", $("#userId").val());
			page.saveLogin("pwd", $("#pwd").val());
		} else {
			page.saveLogin("userId", "");
			page.saveLogin("pwd", "");
		}
	},

	nullCheck : function(val, str) {
		if (str == undefined) {
			str = "";
		}

		if (val == "undefined") {
			return str;
		} else if (val == undefined) {
			return str;
		}

		if (typeof (val) != 'undefined' && val != null && val != '') {
			return val;
		} else {
			return str;
		}
	},

	validateEmail : function(email) {
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		return re.test(email);
	},

	/**
	 * getLogin
	 * 쿠키값을 가져온다.
	 * @returns {String}
	 */
	getLogin : function(id) {

		// userid 쿠키에서 id 값을 가져온다.
		var cook = document.cookie + ";";
		var idx = cook.indexOf(id, 0);
		var val = "";

		if (idx != -1) {
			cook = cook.substring(idx, cook.length);
			begin = cook.indexOf("=", 0) + 1;
			end = cook.indexOf(";", begin);
			val = unescape(cook.substring(begin, end));
		}

		return val;

	},

	/**
	 * setSave
	 * Cookie에 user_id를 저장
	 * @param name
	 * @param value
	 * @param expiredays
	 */
	setSave : function(name, value, expiredays) {

		var today = new Date();
		today.setDate(today.getDate() + expiredays);
		document.cookie = name + "=" + escape(value) + "; path=/; expires="
				+ today.toGMTString() + ";"

	},

	/**
	 * saveLogin
	 * 로그인 정보 저장
	 * @param id
	 */
	saveLogin : function(id, val) {

		if (val != "") {
			// userid 쿠키에 id 값을 7일간 저장
			page.setSave(id, val, 7);
		} else {
			// userid 쿠키 삭제
			page.setSave(id, val, -1);
		}

	},

	/* 폼: 생성 */
	formCreate : function(nm, mt, at, tg) {
		var f = document.createElement("form");
		f.name = nm;
		f.method = mt;
		f.action = at;
		f.target = tg ? tg : "_self";
		return f;
	},

	/* 폼: 인풋 생성 */
	formInput : function(f, n, v) {
		var i = document.createElement("input");
		i.type = "hidden";
		i.name = n;
		i.value = v;
		//f.appendChild(i);
		f.insertBefore(i, null);
		//f.insertBefore(i);
		return f;
	},

	/* 폼: 전송 */
	formSubmit : function(f) {
		document.body.appendChild(f);
		f.submit();
	},

};

$(document).ready(function() {
	page.init();
});
