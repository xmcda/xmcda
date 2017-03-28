
function connect(form){
	var email = form.mail.value;
	var password = form.mdp.value;
	if(verifier_login(email, password)){
	 connection(email, password);
	}	 
}

function verifier_login(mail, password){
	if(!validateEmail(mail)){
        creer_erreur("maill not good");
		return false;
	}else if(!password || (password.length >10 || password.length === 0 || password.length<3)){
		creer_erreur("Password required");
		return false;
	}
	return true;	
}
function creer_erreur(message){
  var balise='<p id="form-sign11" style="margin-left:15%;vertical-align:top; color:red;position:center;">'+message+'</p>';

  $("#form-sign11").replaceWith(balise);
		
}



// fonction de verification pour l'enregistrement

function enregistre(form){
	var password = form.password.value; // password
	var firstname = form.firstname.value; // prenom
	var name = form.name.value;          // nom
	var mail = form.mail.value;           // mail
	alert("je suis dans enregistre : "+password+firstname+name+mail);

 if(verifier_enregistrement(password, firstname, name, mail)){
	 
	 enregistrer(password, firstname, name, mail);
 }
}

function verifier_enregistrement(password, firstname, name, mail){
	alert("je suis dans verifier enregistrement : "+password+firstname+name+mail);

	 if(!firstname || firstname.length === 0 || !name || name.length === 0){
	    	creer_erreur_enregistrement("name and firstname required");
			return false;
	
	}else if(!password || (password.length > 10 || password.length < 3)){
		creer_erreur_enregistrement("Password required");	
		return false;
	}else if(!validateEmail(mail)){
	        creer_erreur_enregistrement("maill not good");
			return false;
	}else{
	return true;
	}
	 return false;
}

function creer_erreur_enregistrement(message){
	var balise='<p id="form-sign22" style="margin-left:15%;vertical-align:top; color:red;position:center;">'+message+'</p>';
		$("#form-sign22").replaceWith(balise);
  
}
// test mail avec une regulaire expression
function validateEmail(email) { 
     var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;   
     return re.test(email);
} 

function connection(mail, password){
	$.ajax({
		type:"POST",
		url:"login",
		data:{email : mail,
			motdepasse: password
			   },
		dataType:"text",
		success:function(data){
			
			if((new String(data).valueOf() == new String("n").valueOf()) || data.error!==undefined){
			
				//show_error_msg(data.error+" ("+data.code+")");
				
				window.location.href="/xmcda/login.jsp";
				alert("maill or password not good, veuillez vous inscrir ou recommencer");
			}else{
				
				$("#navconn").html('<li ><a href="javascript:deconnection()" ><i class="fa fa-sign-in"></i> Deconnexion</a></li>');
			
				window.location.href="/xmcda/index.jsp";
				alert("oui je suis dans modif");
				
				
				
			}	
		},
		error:"error connection"
	});
	
}

function enregistrer(password, firstname, name, mail){

	$.ajax({
		type:"POST",
		url:"inscription",
		data:{ motdepasse: password,
			   nom : name,
			   prenom: firstname, 
			   email : mail
			   },
		dataType:"text",
		success:function(data){
		alert("data normal"+data);
			if((new String(data).valueOf() == new String("n").valueOf()) || data.error!==undefined){
				alert("dattaaa error :"+ data.error);
			}else{
				window.location.href="/xmcda/index.jsp";
			}	
		},
		error:"error enregistrement"
	});
	
}

function deconnection(){
alert("je suis bien dans deconncetion");
	$.ajax({
		type:"POST",
		url:"logout",
		dataType:"text",
		success:function(data){
		alert("reponse serveur : " +data);
		alert("reponse serveur erreur  : " +data.error);
			if(data.error!==undefined){
				alert("erreur de deconnection");	
				}else{
				window.location.href="/xmcda/index.jsp";
			}	
		},
		error:"error enregistrement"
	});
}
