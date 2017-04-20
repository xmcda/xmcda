<!DOCTYPE html>
<html>
<head>
	<title>xmcda project</title>
	<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>

<div class="wrapper">  
    <div id="notreformulaire">
    	<p>Votre PerformanceTable catégorie : </p> 
	    <p><strong> <%=request.getAttribute("result")%> </strong></p> 
	    <p>a été crée</p>	
		<form name="form1" id="formulairedecontact" method="post" action="CreatePerformanceTableObject">	
	        <label>
	        <span>Create an other performanceTable :</span>
			<span>
				<select name="mesurementOrCat">
					<option>Choisir</option>
					<option>Mesurement</option>
					<option>Categorie</option>
				</select>
			</span>
	        <input type="tel" placeholder="Entrez votre utilité (entier)" name="utilite" id="utilite">
	        </label>
	        		
			<input class="sendButton" type="submit" name="Submit" value="Ajouter objets">
			<span><a href="createObject.jsp">Retour</a></span>
		</form>
	</div>
   </div>	
</body>
</html>
