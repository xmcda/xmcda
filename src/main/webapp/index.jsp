<!DOCTYPE html>
<html>
<head>
	<title>xmcda project</title>
	<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>

<div class="wrapper">  
    <div id="notreformulaire">
    <form name="form1" id="formulairedecontact" method="post" action="">	
        <label>
		<span>Alternative :</span>
        <input type="text" placeholder="Tapez votre alternative" name="alternative" id="name">
        </label>
        
        <label>
		<span>Crit�re :</span>    
		<input type="text" placeholder="Tapez votre crit�re" name="critere" id="city">
        </label>
        
        <label>
		<span>PerformanceTable :
			<select>
				<option name="mesurement">Mesurement :</option>
				<option name="categorie">Cat�gorie :</option>
			</select>
		</span>
        <input type="tel" placeholder="Entrez votre utilit� (entier)" name="phone" id="phone">
        </label>
        		
		<input class="sendButton" type="submit" name="Submit" value="Ajouter objets">
	</form>
	</div>
   </div>	
</body>
</html>
