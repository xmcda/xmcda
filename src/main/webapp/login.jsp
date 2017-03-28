<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login XMCDA</title>
	<link rel="stylesheet" href =" login.css "/>
	<script src="js/jquery-1.10.2.js"></script>	
	<script TYPE="text/javascript" src="js/login.js"></script>
</head>
<body>
<div id="login-global" class="login-global">
  		<div id="login-sous" class="login-sous">
	  	    <div id="titi" class="titi"><a href="">XMCDA</a></div>
		    <span id="descr" class="descr">your knowledge, contacts, it's your future.</span>
		   
			<form method="POST" class="form-sign" id="form-sign1" action="javascript:(function(){return;}) ()" onsubmit="javascript:connect(this)">
				 <p id="form-sign11"></p>
				<!-- <input type="text" class="login" name="login" placeholder="Username" > -->
				<input type="mail" class="login" name="mail" placeholder="mail" />
				<input type="password" class="login" name="mdp" placeholder="Password" >
				<button class="" type="submit">Sign in</button>
				<a href="signup.jsp" class="signup">New to XMCDA? Sign up</a>
			</form>
	   	</div>
		<div class="login-footer">&copy; 2017 XMCDAc</div>
	</div>
</body>
</html>