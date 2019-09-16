<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">

<!-- Mirrored from demos.creative-tim.com/paper-dashboard-pro/examples/pages/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 21 Nov 2016 01:37:44 GMT -->
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="57x57" href="/resources/favicon/apple-icon-57x57.png">
	<link rel="apple-touch-icon" sizes="60x60" href="/resources/favicon/apple-icon-60x60.png">
	<link rel="apple-touch-icon" sizes="72x72" href="/resources/favicon/apple-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="76x76" href="/resources/favicon/apple-icon-76x76.png">
	<link rel="apple-touch-icon" sizes="114x114" href="/resources/favicon/apple-icon-114x114.png">
	<link rel="apple-touch-icon" sizes="120x120" href="/resources/favicon/apple-icon-120x120.png">
	<link rel="apple-touch-icon" sizes="144x144" href="/resources/favicon/apple-icon-144x144.png">
	<link rel="apple-touch-icon" sizes="152x152" href="/resources/favicon/apple-icon-152x152.png">
	<link rel="apple-touch-icon" sizes="180x180" href="/resources/favicon/apple-icon-180x180.png">
	<link rel="icon" type="image/png" sizes="192x192"  href="/resources/favicon/android-icon-192x192.png">
	<link rel="icon" type="image/png" sizes="32x32" href="/resources/favicon/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="96x96" href="/resources/favicon/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="16x16" href="/resources/favicon/favicon-16x16.png">
	<link rel="manifest" href="/resources/favicon/manifest.json">
	<meta name="msapplication-TileColor" content="#ffffff">
	<meta name="msapplication-TileImage" content="/resources/favicon/ms-icon-144x144.png">
	<meta name="theme-color" content="#ffffff">

	<title>최고의투자</title>

	<!-- Canonical SEO -->
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

     <!-- Bootstrap core CSS     -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" />

    <!--  Paper Dashboard core CSS    -->
    <link href="/resources/css/paper-dashboard.css" rel="stylesheet"/>


    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="/resources/css/demo.css" rel="stylesheet" />

    <!--  Fonts and icons     -->
    <link href="/resources/fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="/resources/css/themify-icons.css" rel="stylesheet">
    <link href="/resources/css/waitMe.css" rel="stylesheet">
    
     <!--   Core JS Files. Extra: PerfectScrollbar + TouchPunch libraries inside jquery-ui.min.js   -->
    <script src="/resources/js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="/resources/js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/resources/js/layout.js" type="text/javascript" ></script>
    <script src="/resources/js/waitMe.js" type="text/javascript" ></script>
    
</head>

<body>
    <nav class="navbar navbar-transparent navbar-absolute">
        <div class="container">
            <div class="navbar-header">
                <!-- <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-example-2">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button> -->
                <a class="navbar-brand" href="/">
                <img src="/resources/img/logo.png" style="height:45px;"/>
                </a>
            </div>
            <!-- <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                       <a href="register.html">
                            Register
                        </a>
                    </li>
					<li>
                       <a href="../dashboard/overview.html">
                            Dashboard
                        </a>
                    </li>
                </ul>
            </div> -->
        </div>
    </nav>

    <div class="wrapper wrapper-full-page">
        <div class="full-page login-page" data-color="" data-image="/resources/img/background/background-2.jpg">
        <!--   you can change the color of the filter page using: data-color="blue | azure | green | orange | red | purple" -->
			
			<jsp:include page ="${body}"  flush="false"/>
			
        </div>
    </div>
</body>


	<!--  Forms Validations Plugin -->
	<script src="/resources/js/jquery.validate.min.js"></script>

	<!--  Plugin for Date Time Picker and Full Calendar Plugin-->
	<script src="/resources/js/moment.min.js"></script>

	<!-- Paper Dashboard PRO DEMO methods, don't include it in your project! -->
	<script src="/resources/js/demo.js"></script>

	<script type="text/javascript">
        $().ready(function(){
            demo.checkFullPageBackgroundImage();
        });
	</script>
	
</html>
