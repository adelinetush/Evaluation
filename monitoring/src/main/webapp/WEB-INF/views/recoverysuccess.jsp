<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<% 
request.setAttribute("baseUrl", 
	 StringUtils.replace(request.getRequestURL().toString(), request.getRequestURI(), request.getContextPath()));
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>PERFNET - eMonitoring and Evaluation</title>
<link rel="shortcut icon" href="<%= request.getAttribute("baseUrl") %>/static/images/favicon.ico" />
<link type="text/css" rel="stylesheet" href="<%= request.getAttribute("baseUrl") %>/static/js/jquery-ui/custom-theme/jquery-ui-1.8.14.custom.css" />
<link type="text/css" rel="stylesheet" href="<%= request.getAttribute("baseUrl") %>/static/css/system.css" />
</head>
<body>
	<div id="wrapper" class="group">
		<div id="header" class="group">
			<div class="header-logo">
				<img alt="logo" src="<%= request.getAttribute("baseUrl") %>/static/images/procnet-logo.png">
			</div>
		</div>
		<div id="content-container" class="group" style="border: 0px;">
			<div>
				<h3>An email with your new password has been sent to your account</h3>
			</div>
		</div>
		<div id="footer" class="group">
			<ul id="">
				<li>&copy; Planet Systems Ltd, 2014</li>
			</ul>
		</div>
		<div style="height: 20px;"></div>
	</div>
</body>
</html>