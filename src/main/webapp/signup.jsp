<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
  <link rel="stylesheet" href =" login.css "/>
  <script src="js/jquery-1.10.2.js"></script>
  <script TYPE="text/javascript" src="js/login.js"></script>
  <title>Sign Up XMCDA1.0</title>
</head>
<body>
<div id="login-global" class=" login-global globis">
  		<div id="login-sous">
	  	    <div id="titi"><a>XMCDA</a></div>
		    <span id="descr">Join XMCDA.</span>
			<form method="POST"class="form-sign" id="form-sign2" action="javascript:(function(){return;}) ()" onsubmit="javascript:enregistre(this)">
				<p id="form-sign22"></p>
				<input type="text" class="login" name="firstname" placeholder="name" />
				<input type="text" class="login" name="name" placeholder="first name" />
				<!-- <input type="text" class="login" name="username" placeholder="Choose your username" > -->
				<input type= "mail" class="login" name="mail" placeholder="mail" />
				<input type="password" class="login" name="password" placeholder="Create a password" >
				
				<button class="" type="submit">Create my account</button>
			</form>
			<a href="login.jsp" class="signin">Have an account? Sign in</a>
	   	</div>
		<div class="login-footer">&copy; 2017 XMCDA</div>
	</div>
</body>
</html>