// Using an object literal for a jQuery feature
var page = {

	init : function() {
		page.registerEvents();
	},

	registerEvents : function() {

		$(document).on("click", ".tb tr", function(e) {
			/*var cell = $(this).parent("td");
			var row = $(cell).parent("tr");
			var rowTr = $(this).closest("tr");
			var rowIndx = $(this).closest('tr').attr('idx');
			
			page.rowSelect(rowIndx);*/
		});

	},

	rowSelect : function(rowIndx) {

		var params = "idx=" + rowIndx;
		var URL = "/admin?"+params;
		
		window.location = URL;
		
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
	}
};

$(document).ready(function() {
	page.init();
});
