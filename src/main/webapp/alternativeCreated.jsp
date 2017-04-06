<!DOCTYPE html>
<html>
<head>
	<title>xmcda project</title>
	<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>

<div class="wrapper">  
    <div id="notreformulaire">
    <p>Votre alternative : </p> 
    <p><strong> <%=request.getAttribute("result")%> </strong></p> 
    <p>a été créee</p>
    <form name="form1" id="formulairedecontact" method="GET" action="CreateAlternative">	
        <label>
		<span>Create an other alternative :</span>
        <input type="text" placeholder="Tapez votre alternative" name="alternative" id="alternative">
        </label>
        		
		<input class="sendButton" type="submit" name="Submit" value="Ajouter alternative">
		<span><a href="createObject.jsp">Retour</a></span>
	</form>
	</div>
   </div>	
</body>
</html>
