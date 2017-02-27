<!DOCTYPE html>
<html>
<head>
	<title>xmcda project</title>
	<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>

<div class="wrapper">  
    <div id="notreformulaire">
    <p>Votre mesurement : <strong> <%=request.getAttribute("result")%> </strong> a été créee</p>
    <form name="form1" id="formulairedecontact" method="post" action="CreateAlternativeObject">	
        <label>
		<span>Create an other alternative :</span>
        <input type="text" placeholder="Tapez votre alternative" name="alternative" id="alternative">
        </label>
        		
		<input class="sendButton" type="submit" name="Submit" value="Ajouter alternative">
		<span><a href="index.jsp">Retour</a></span>
	</form>
	</div>
   </div>	
</body>
</html>
