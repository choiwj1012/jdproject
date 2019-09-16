// Using an object literal for a jQuery feature
var page = {
		
    init: function( ) {
    	
        page.registerEvents();
      
    },
    
    registerEvents: function() {
    	
		$(document).on('click', '#register', function(e) {
			
			layout.loading(true);
			
			var email = $("#email").val();
			var pwd = $("#pwd").val();
			var pwdrepeat = $("#pwdrepeat").val();
			var name = $("#name").val();
			var phone = $("#phone").val();
			var career = $("#career").val();
			var amount = $("#amount").val();
			
			var params  = "email="+email;
			params += "&pwd="+pwd;
			
			if(!page.validateEmail(email)){
				alert("이메일을 확인해주세요.");
				layout.loading(false);
				$("#email").focus();
				return;
			}
			
			if(pwd==""){
				alert("비밀번호를 입력해주세요.");
				layout.loading(false);
				$("#pwd").focus();
				return;
			}
			
			if(pwdrepeat==""){
				alert("비밀번호확인을 입력해주세요.");
				layout.loading(false);
				$("#pwdrepeat").focus();
				return;
			}
			
			if(pwd!=pwdrepeat){
				alert("비밀번호확인이 일치하지 않습니다.");
				layout.loading(false);
				$("#pwdrepeat").focus();
				return;
			}
			
			if(name==""){
				alert("이름을 입력해주세요.");
				layout.loading(false);
				$("#name").focus();
				return;
			}
			
			if(phone==""){
				alert("전화번호를 입력해주세요.");
				layout.loading(false);
				$("#phone").focus();
				return;
			}
			
			if(career==""){
				alert("투자경력을 입력해주세요.");
				layout.loading(false);
				$("#career").focus();
				return;
			}
			
			if(amount==""){
				alert("투자예상급액을 입력해주세요.");
				layout.loading(false);
				$("#amount").focus();
				return;
			}
			
			var URL = "/auth/login";
			
			$.ajax({
				url : URL,
				type : "POST",
				data : params,
				dataType:'json',
				success : function(data) {
					if(data.code == '0'){
						$(location).attr('href','/auth');
					}else{
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
				
				var email = $("#email").val();
			  	var pwd = $("#pwd").val();
			  	
			  	var params   = "email="+email;
			  		  params += "&pwd="+pwd;
			  	
			  	if(!page.validateEmail(email)){
			  		alert("이메일을 확인해주세요.");
			  		layout.loading(false);
			  		$("#email").focus();
			  		return;
			  	}
			  	
			  	if(pwd==""){
			  		alert("비밀번호를 입력해주세요.");
			  		layout.loading(false);
			  		$("#pwd").focus();
			  		return;
			  	}
			  	
			  	var params   = "email="+email;
			  		  params += "&pwd="+pwd;
			  	
		  		var URL = "/auth/login";
		  		
		  		$.ajax({
					url : URL,
					type : "POST",
					data : params,
			        dataType:'json',
					success : function(data) {
						if(data.code == '0'){
							$(location).attr('href','/auth');
						}else{
							alert(data.message);
							layout.loading(false);
						}
					}
				});
			}
		});
  	},
  	
    nullCheck: function(val, str) {
		if(str==undefined){
			str = "";  	
		}
		  
		if(val=="undefined"){
			return str;
		}else if(val==undefined){
			return str;
		}
		  
		if (typeof(val) != 'undefined' && val != null && val != ''){
			return val;
		}else{
			return str;
		}
	},
	
	validateEmail: function(email) {
		  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		  return re.test(email);
	},
	
	/**
	* getLogin
	* 쿠키값을 가져온다.
	* @returns {String}
	*/
	getLogin: function(id) {
		
		// userid 쿠키에서 id 값을 가져온다.
		var cook = document.cookie + ";";
		var idx = cook.indexOf(id, 0);
		var val = "";
		
		if(idx != -1) {
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
	setSave: function(name, value, expiredays) {
		
		var today = new Date();
		today.setDate( today.getDate() + expiredays );
		document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + today.toGMTString() + ";"
		
	},
	
	/**
	* saveLogin
	* 로그인 정보 저장
	* @param id
	*/
	saveLogin: function(id, val) {
		
		if(val != "") {
			// userid 쿠키에 id 값을 7일간 저장
			page.setSave(id, val, 7);
		}else{
			// userid 쿠키 삭제
			page.setSave(id, val, -1);
		}
		
	},
	
	/* 폼: 생성 */
	formCreate:function (nm,mt,at,tg) {
		 var f = document.createElement("form");
		 f.name = nm;
		 f.method = mt;
		 f.action = at;
		 f.target = tg ? tg : "_self";
		 return f;
	},
	
	/* 폼: 인풋 생성 */
	formInput:function(f,n,v) {
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
	formSubmit:function(f) {
		 document.body.appendChild(f);
		 f.submit();
	},
	
};

$(document).ready(function () {
	page.init();
});
