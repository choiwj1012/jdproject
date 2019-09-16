function check_key() {

	var char_ASCII = event.keyCode;
	 if (char_ASCII >= 48 && char_ASCII <= 57 ){
		   return 1;
		}
}

function numberkey() {
	if(check_key()!=1) {
		event.returnValue= false;
		alert("숫자만 입력할 수 있습니다.")
		return;
	}

}

	$(document).ready(function(){
		
		$('#freebutton').on('click', function() {

			var reg1= /^[가-힣]{2,5}$/;
			var reg2= /^(010|011|016)\d{3,4}\d{4}$/;

		var name = $('#inName').val();
		var tel = $('#inTel').val();
		var result1 = reg1.test(name);
		var result2 = reg2.test(tel);
		if (name == '') {
				alert('빈공간이 있으면 안됩니다. 이름을 입력해주세요');
				$('#inName').focus();
				return false;
			} else if (tel == '') {
				alert('빈공간이 있으면 안됩니다. 전화번호를 입력해주세요');
				$('#inTel').focus();
				return false;
			} else if(!result1) {
				alert("이름 입력이 잘못되었습니다.");
				$('#inName').focus();
				$('#inName').val("");
				return false;
			} else if(!result2) {
				alert("연락처 입력이 잘못되었습니다.");
				tel = "";
				$('#inTel').focus();
				$('#inTel').val("");
				return false;
			}
		$.ajax({
			url : "/post/goodLikerRandingPage",
			type : "post",
			data : {
				"Name" : name,
				"Tel" : tel
			},
			success : function(data) {
				if(data.code != null) {
					$('#inName').val("");
					$('#inTel').val("");
					alert(data.code);	
					
				}else if (data.message != null){
					$('#inName').val("");
					$('#inTel').val("");
					alert(data.message);
					
				}
			}
		});
		});
	});
	

