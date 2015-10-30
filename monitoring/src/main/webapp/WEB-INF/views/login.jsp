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

		</div>
		<div id="content-container" class="group">
			<div class="login-box-title"><span style="display: block; padding-top: 5px;">PerfNet Version 1- User Login</span></div>
			<div class="login-box-logo">
					<img alt="logo" src="<%= request.getAttribute("baseUrl") %>/static/images/perfnet-logo.png">
			</div>
			<div id="logincontent">
				<form id="loginform" name="loginForm" class="loginForm"
					action="<%= request.getAttribute("baseUrl") %>/j_spring_security_check" method="post">
					<div>
						<label class="uiLabel">Username:</label>
						<input name="j_username" id="j_username" type="text"
							class="textbox" />
					</div>
					<div style="padding-top: 10px;">
						<label id="passwordLabel" class="uiLabel">Password:</label>
						<input name="j_password" id="j_password" type="password"
							class="textbox" />
					</div>
					<div style="padding-top: 10px;" id="remember-me">
						<input id="_spring_security_remember_me"
							name="_spring_security_remember_me" type="checkbox"
							class="uiCheckbox" value="true" /> <label>Remember Me</label>
					</div>
					<div style="padding-top: 5px;" class="error">
						<% if(request.getAttribute("errorMessage") != null && 
								StringUtils.isNotBlank((String)request.getAttribute("errorMessage"))){ %>
							<%= request.getAttribute("errorMessage") %>
						<%} %>
					</div>
					<p style="padding-top: 5px;">
						<a href="<%= request.getAttribute("baseUrl") %>/Recovery">Forgot your Password?</a>
					<p>
					<div style="padding-top: 5px;" class="group">
						<input name="btnSubmit" id="btnSubmit" type="submit"
							value="Sign In" class="button" style="float: right;"/>
					</div>
					<p>
					<%-- <h4>
						Don't have an account? <a href="<%= request.getParameter("baseUrl") %>/SignUp">Register</a>
					</h4> --%>
					</p>
					<div style="clear:both; height: 5px;"></div>
				</form>
			</div>
		</div>
		<div id="footer" class="group">
			<ul id="">
				<li>&copy; Planet Systems Ltd, 2015</li>
			</ul>
		</div>
		<div style="height: 20px;"></div>
	</div>
</body>
</html>