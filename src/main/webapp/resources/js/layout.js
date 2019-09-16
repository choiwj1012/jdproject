// Using an object literal for a jQuery feature
var layout = {
	init : function() {

		layout.registerEvents();

	},

	registerEvents : function() {
		$(document).on("input", ".numberic", function() {
			this.value = this.value.replace(/[^0-9]/g, '');
		});

		$(document).on("input", ".numbericFloat", function() {
			this.value = this.value.replace(/[^0-9.]/g, '');
		});

		$(document).on("input", ".engOnly", function() {
			this.value = this.value.replace(/[0-9]|[^\!-z]/g, '');
		});

		$(document).on("input", ".engNum", function() {
			this.value = this.value.replace(/[^a-z^A-Z^0-9]/g, '');
		});

		$(document).on("input", ".engNumEtc", function() {
			this.value = this.value.replace(/[^a-z^A-Z^0-9^-]/g, '');
		});

		$(document).on("input", ".notKor", function() {
			this.value = this.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, '');
		});

	},

	loading : function(flag) {

		// http://www.jqueryscript.net/demo/jQuery-Plugin-For-Creating-Loading-Overlay-with-CSS3-Animations-waitMe/
		if (flag) {
			$('body').waitMe({
				effect : 'bounce',
				text : 'Please waiting...',
				bg : 'rgba(255,255,255,0.7)',
				color : '#000'
			});
		} else {
			$('body').waitMe('hide');
		}
	},
	
	loadingSet : function(flag, id) {

		// http://www.jqueryscript.net/demo/jQuery-Plugin-For-Creating-Loading-Overlay-with-CSS3-Animations-waitMe/
		if (flag) {
			$(id).waitMe({
				effect : 'bounce',
				text : 'Please waiting...',
				bg : 'rgba(255,255,255,0.7)',
				color : '#000'
			});
		} else {
			$('body').waitMe('hide');
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
			return val.replace(/(\s*)/g, "");
		} else {
			return str;
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

	getUrlParams : function() {
		var params = {};
		window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str,
				key, value) {
			params[key] = value;
		});
		return params;
	},
	
	getParameters :function (paramName) {
	    // 리턴값을 위한 변수 선언
	    var returnValue;

	    // 현재 URL 가져오기
	    var url = location.href;

	    // get 파라미터 값을 가져올 수 있는 ? 를 기점으로 slice 한 후 split 으로 나눔
	    var parameters = (url.slice(url.indexOf('?') + 1, url.length)).split('&');

	    // 나누어진 값의 비교를 통해 paramName 으로 요청된 데이터의 값만 return
	    for (var i = 0; i < parameters.length; i++) {
	        var varName = parameters[i].split('=')[0];
	        if (varName.toUpperCase() == paramName.toUpperCase()) {
	            returnValue = parameters[i].split('=')[1];
	            return decodeURIComponent(returnValue);
	        }
	    }
	},

	setCookie : function(name, value, expiredays) {
		var todayDate = new Date();
		todayDate.setDate(todayDate.getDate() + expiredays);
		document.cookie = name + "=" + escape(value) + "; path=/; expires="
				+ todayDate.toGMTString() + ";"
	},

	getCookie : function(name) {
		var arg = name + "=";
		var alen = arg.length;
		var clen = document.cookie.length;
		var i = 0;
		while (i < clen) {
			var j = i + alen;
			if (document.cookie.substring(i, j) == arg)
				return getCookieVal(j);
			i = document.cookie.indexOf(" ", i) + 1;
			if (i == 0)
				break;
		}
		return null;
	},

	getCookieVal : function(offset) {
		var endstr = document.cookie.indexOf(";", offset);
		if (endstr == -1)
			endstr = document.cookie.length;
		return unescape(document.cookie.substring(offset, endstr));
	},

	copyToClipboardl : function() {
		$temp.val('aaa').select();
		document.execCommand("copy");
		$temp.remove();
	},

	go_year : function(targetYear) {

		var currentYear = new Date().getFullYear();

		var targetObj = document.getElementById(targetYear);

		while (targetObj.length > 0)
			targetObj.remove(0);

		for (var i = currentYear; i >= currentYear - 2; i--) {

			var objOption = document.createElement("option");
			if (i < 10) {
				objOption.text = "0" + i;
				objOption.value = "0" + i;

			} else {
				objOption.text = i;
				objOption.value = i;

			}
			targetObj.options.add(objOption); // IE, FF 둘 다에서 된다.
		}
	},

	go_month : function(targetMonth) {

		var currentMonth = new Date().getMonth() + 1;

		var targetObj = document.getElementById(targetMonth);

		while (targetObj.length > 0)
			targetObj.remove(0);

		for (var i = 1; i <= 12; i++) {

			var objOption = document.createElement("option");
			if (i < 10) {
				objOption.text = "0" + i;
				objOption.value = "0" + i;

			} else {
				objOption.text = i;
				objOption.value = i;

			}

			if (currentMonth == i) {
				objOption.selected = true;
			}

			targetObj.options.add(objOption); // IE, FF 둘 다에서 된다.
		}
	},

	go_dd : function(targetYear, targetMonth, targetDay) {

		var input_year = document.getElementById(targetYear).value;
		var input_month = document.getElementById(targetMonth).value;

		var newDay = new Date(input_year, input_month, "");
		var lastDay = newDay.getDate();

		var targetObj = document.getElementById(targetDay);

		while (targetObj.length > 0)
			targetObj.remove(0);

		for (var i = 1; i <= lastDay; i++) {

			var objOption = document.createElement("option");
			if (i < 10) {
				objOption.text = "0" + i;
				objOption.value = "0" + i;

			} else {
				objOption.text = i;
				objOption.value = i;

			}
			targetObj.options.add(objOption); // IE, FF 둘 다에서 된다.
		}

	},

	go_day_first : function(targetYear, targetMonth, targetDay, flag) {

		var input_year = document.getElementById(targetYear).value;
		var input_month = document.getElementById(targetMonth).value;

		var newDay = new Date(input_year, input_month, "");
		var lastDay = newDay.getDate();

		var targetObj = document.getElementById(targetDay);

		while (targetObj.length > 0)
			targetObj.remove(0);

		for (var i = 1; i <= lastDay; i++) {

			var objOption = document.createElement("option");
			if (i < 10) {
				objOption.text = "0" + i;
				objOption.value = "0" + i;

			} else {
				objOption.text = i;
				objOption.value = i;

			}

			if (flag == "first") {
				if (i == 0) {
					objOption.selected = true;
				}
			} else {
				if (i == lastDay) {
					objOption.selected = true;
				}
			}

			targetObj.options.add(objOption); // IE, FF 둘 다에서 된다.
		}

	}

};

$(document).ready(function() {
	layout.init();
});
