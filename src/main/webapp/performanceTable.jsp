<!DOCTYPE html>
<html>
<head>
	<title>xmcda project</title>
	<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>

<div class="wrapper">  
    <div id="notreformulaire">
    <form name="form1" id="formulairedecontact" method="POST" action="CreatePerformanceTableObject">	
        <label>
		<span>PerformanceTable :
			<select name="mesurementOrCat">
				<option>Choisir</option>
				<option>Mesurement</option>
				<option>Categorie</option>
			</select>
		</span>
        <input type="tel" placeholder="Entrez votre utilit� (entier)" name="utilite" id="utilite">
        </label>
        		
		<input class="sendButton" type="submit" name="Submit" value="Ajouter objets">
		<span><a href="createObject.jsp">Retour</a></span>
	</form>
	</div>
   </div>	
</body>
</html>
