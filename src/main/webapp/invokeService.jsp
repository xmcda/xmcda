<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<p><b>Invoked service is: </b></p>
		<p><%= (((session.getAttribute("serviceName")) == null) ? "" : (session.getAttribute("serviceName"))) %></p>
		<p><b>Provided by: </b></p>
		<p><%= (((session.getAttribute("providerName")) == null) ? "" : (session.getAttribute("providerName"))) %></p>
	
	
	
	
		<p><b>Inputs are: </b></p>
		<p><%= (((session.getAttribute("input1")) == null) ? "" : (session.getAttribute("input1"))) %></p>
		<p><%= (((session.getAttribute("input2")) == null) ? "" : (session.getAttribute("input2"))) %></p>
		<p><%= (((session.getAttribute("input3")) == null) ? "" : (session.getAttribute("input3"))) %></p>
		<p><%= (((session.getAttribute("input4")) == null) ? "" : (session.getAttribute("input4"))) %></p>
		<p><%= (((session.getAttribute("input5")) == null) ? "" : (session.getAttribute("input5"))) %></p>
		<p><%= (((session.getAttribute("input6")) == null) ? "" : (session.getAttribute("input6"))) %></p>
		<p><%= (((session.getAttribute("input7")) == null) ? "" : (session.getAttribute("input7"))) %></p>
		<p><%= (((session.getAttribute("input8")) == null) ? "" : (session.getAttribute("input8"))) %></p>
		<p><%= (((session.getAttribute("input9")) == null) ? "" : (session.getAttribute("input9"))) %></p>
		<p><%= (((session.getAttribute("input10")) == null) ? "" : (session.getAttribute("input10"))) %></p>
		<p><%= (((session.getAttribute("input11")) == null) ? "" : (session.getAttribute("input11"))) %></p>
		<p><%= (((session.getAttribute("input12")) == null) ? "" : (session.getAttribute("input12"))) %></p>
		<p><%= (((session.getAttribute("input13")) == null) ? "" : (session.getAttribute("input13"))) %></p>
		<p><%= (((session.getAttribute("input14")) == null) ? "" : (session.getAttribute("input14"))) %></p>
		<p><%= (((session.getAttribute("input15")) == null) ? "" : (session.getAttribute("input15"))) %></p>
	</body>
</html>