<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title><tiles:getAsString name="title" /></title>
		<!-- Bootstrap core CSS -->
	    <link href="${pageContext.request.contextPath}/static/dist/css/bootstrap.min.css" rel="stylesheet">
		<tiles:insertAttribute name="includes"></tiles:insertAttribute>
	</head>
<body>
	<div class="content">
		<tiles:insertAttribute name="menu" />
		<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="afterfooter" />
	<script src="<c:url value="/static/script/jquery.min.js" />"></script>
</body>
</html>