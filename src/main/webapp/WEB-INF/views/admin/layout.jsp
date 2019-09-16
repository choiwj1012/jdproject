<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">

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

	<title>Admin</title>

	<!-- Canonical SEO -->
    <!-- <link rel="canonical" href="http://www.creative-tim.com/product/paper-dashboard-pro"/> -->

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

	<!--  Social tags      -->
    <meta name="keywords" content="creative tim, html dashboard, html css dashboard, web dashboard, bootstrap dashboard, bootstrap, css3 dashboard, bootstrap admin, paper bootstrap dashboard, frontend, responsive bootstrap dashboard">
    <meta name="description" content="Paper Dashboard PRO is a beautiful Bootstrap admin dashboard with a large number of components, designed to look neat and organised. ">

    <!-- Schema.org markup for Google+ -->
    <meta itemprop="name" content="Paper Dashboard PRO by Creative Tim">
    <meta itemprop="description" content="Paper Dashboard PRO is a beautiful Bootstrap admin dashboard with a large number of components, designed to look neat and organised. If you are looking for a tool to manage and visualise data about your business, this dashboard is the thing for you.">

    <!-- <meta itemprop="image" content="../../s3.amazonaws.com/creativetim_bucket/products/47/original/opt_pdp_thumbnail.jpg"> -->
    
    <!-- Twitter Card data -->
    <!-- <meta name="twitter:card" content="summary_large_image">
    <meta name="twitter:site" content="@creativetim">
    <meta name="twitter:title" content="Paper Dashboard PRO by Creative Tim">
    <meta name="twitter:description" content="Paper Dashboard PRO is a beautiful Bootstrap admin dashboard with a large number of components, designed to look neat and organised.">
    <meta name="twitter:creator" content="@creativetim">
    <meta name="twitter:image" content="../../s3.amazonaws.com/creativetim_bucket/products/47/original/opt_pdp_thumbnail.jpg"> -->

    <!-- Open Graph data -->
    <!-- <meta property="og:title" content="Paper Dashboard PRO by Creative Tim" />
    <meta property="og:type" content="article" />
    <meta property="og:url" content="overview.html" />
    <meta property="og:image" content="../../s3.amazonaws.com/creativetim_bucket/products/47/original/opt_pdp_thumbnail.jpg"/>
    <meta property="og:description" content="Paper Dashboard PRO is a beautiful Bootstrap admin dashboard with a large number of components, designed to look neat and organised." />
    <meta property="og:site_name" content="Creative Tim" /> -->

     <!-- Bootstrap core CSS     -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" />

    <!--  Paper Dashboard core CSS    -->
    <link href="/resources/css/paper-dashboard.css" rel="stylesheet"/>

    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="/resources/css/demo.css" rel="stylesheet" />

    <!--  Fonts and icons     -->
    <!-- <link href="../../maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet"> -->
    <link href="/resources/fonts/css/font-awesome.min.css" rel="stylesheet">
    <!-- <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'> -->
    <link href="/resources/css/themify-icons.css" rel="stylesheet">
    <link href="/resources/css/jquery.modal.css" rel="stylesheet">
    <link href="/resources/css/waitMe.css" rel="stylesheet">
    
     <!--   Core JS Files. Extra: PerfectScrollbar + TouchPunch libraries inside jquery-ui.min.js   -->
    <script src="/resources/js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="/resources/js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/resources/js/layout.js" type="text/javascript" ></script>
    <script src="/resources/js/jquery.modal.min.js" type="text/javascript" ></script>
    <script src="/resources/js/waitMe.js" type="text/javascript" ></script>
    
</head>

<body>
	<div class="wrapper">
	
	<%@ include file="sidebar.jsp" %>
	
	    <div class="main-panel">
    		<%@ include file="header.jsp" %>
    		
    		<jsp:include page ="${body}"  flush="false"/>
	    	
	        <%@ include file="footer.jsp" %>
            
	    </div>
	</div>
</body>

	<!--  Forms Validations Plugin -->
	<script src="/resources/js/jquery.validate.min.js"></script>

	<!--  Plugin for Date Time Picker and Full Calendar Plugin-->
	<script src="/resources/js/moment.min.js"></script>

    <!--  Date Time Picker Plugin is included in this js file -->
    <script src="/resources/js/bootstrap-datetimepicker.js"></script>

    <!--  Select Picker Plugin -->
    <script src="/resources/js/bootstrap-selectpicker.js"></script>

	<!--  Checkbox, Radio, Switch and Tags Input Plugins -->
	<script src="/resources/js/bootstrap-checkbox-radio-switch-tags.js"></script>

	<!-- Circle Percentage-chart -->
	<script src="/resources/js/jquery.easypiechart.min.js"></script>

	<!--  Charts Plugin -->
	<script src="/resources/js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="/resources/js/bootstrap-notify.js"></script>

    <!-- Sweet Alert 2 plugin -->
	<script src="/resources/js/sweetalert2.js"></script>

    <!-- Vector Map plugin -->
	<script src="/resources/js/jquery-jvectormap.js"></script>

	<!-- Wizard Plugin    -->
    <script src="/resources/js/jquery.bootstrap.wizard.min.js"></script>

    <!--  Bootstrap Table Plugin    -->
    <script src="/resources/js/bootstrap-table.js"></script>

    <!--  Full Calendar Plugin    -->
    <script src="/resources/js/fullcalendar.min.js"></script>

    <!-- Paper Dashboard PRO Core javascript and methods for Demo purpose -->
	<script src="/resources/js/paper-dashboard.js"></script>

    <!--   Sharrre Library    -->
    <script src="/resources/js/jquery.sharrre.js"></script>

    <!-- Paper Dashboard PRO DEMO methods, don't include it in your project! -->
	<script src="/resources/js/demo.js"></script>

</html>
