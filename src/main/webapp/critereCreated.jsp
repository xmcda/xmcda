<!DOCTYPE html>
<html>
<head>
	<title>xmcda project</title>
	<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>

<div class="wrapper">  
    <div id="notreformulaire">
    <form name="form1" id="formulairedecontact" method="POST" action="CreateCritereObject">	
    	<p>Votre Critere : </p> 
	    <p><strong> <%=request.getAttribute("result")%> </strong></p> 
	    <p>a �t� cr�e</p>
        <label>
        <span>Create an other crit�re </span> 	   
		<input type="text" placeholder="Tapez votre crit�re" name="critere" id="critere">
        </label>
        <label>
		<span>Pr�f�rence :</span>    
		<input type="text" placeholder="Tapez votre pr�f�rence" name="preference" id="preference">
        </label>
        
        <input class="sendButton" type="submit" name="Submit" value="Ajouter objets">
        <span><a href="index.jsp">Retour</a></span>
	</form>
	</div>
   </div>	
</body>
</html>
