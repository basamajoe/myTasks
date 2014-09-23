<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en" ng-app="taskApp">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title><tiles:getAsString name="title" /></title>

<!-- Bootstrap core CSS -->
<link href="<c:url value="/static/dist/css/bootstrap.min.css"/>"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/static/css/main.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/static/css/default.css"/>">
<!-- Angularjs from Google CND -->
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0/angular.min.js"></script>
<tiles:insertAttribute name="includes"></tiles:insertAttribute>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
</head>
<body>
	<tiles:insertAttribute name="header" />
	<div class="content">
		<tiles:insertAttribute name="menu" />
		<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="footer" />

	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<c:url value="/static/script/jquery.min.js" />"></script>
	<script src="<c:url value="/static/script/bootstrap.min.js"/>"></script>
	<tiles:insertAttribute name="afterfooter" />
</body>
</html>