<!DOCTYPE html>
<html>
<head>
	<title>xmcda project</title>
	<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>

<div class="wrapper">  
    <div id="notreformulaire">
    <form name="form1" id="formulairedecontact" method="post" action="CreatePerformanceTableObject">	
        <label>
		<span>PerformanceTable :
			<select name="mesurementOrCat">
				<option name="">Choisir</option>
				<option>Mesurement</option>
				<option>Categorie</option>
			</select>
		</span>
        <input type="tel" placeholder="Entrez votre utilité (entier)" name="utilite" id="utilite">
        </label>
        		
		<input class="sendButton" type="submit" name="Submit" value="Ajouter objets">
		<span><a href="index.jsp">Retour</a></span>
	</form>
	</div>
   </div>	
</body>
</html>
