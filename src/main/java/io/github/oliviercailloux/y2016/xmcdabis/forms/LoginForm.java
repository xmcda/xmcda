package io.github.oliviercailloux.y2016.xmcdabis.forms;

import java.util.HashMap;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import io.github.oliviercailloux.y2016.xmcdabis.dao.DAOException;
import io.github.oliviercailloux.y2016.xmcdabis.dao.UtilisateurDao;
import io.github.oliviercailloux.y2016.xmcdabis.entities.LoginUser;
import io.github.oliviercailloux.y2016.xmcdabis.entities.Utilisateur;

public class LoginForm {

	private static final String CHAMP_EMAIL  = "email"; 
	private static final String CHAMP_PASS   = "motdepasse"; 
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String,String>(); 
	private UtilisateurDao utilisateurDao1;

	public LoginForm (UtilisateurDao utilisateurDao ) {  
		
		this.utilisateurDao1 = utilisateurDao;
	}

	public String getResultat() { 
		return resultat; 
	}
	public Map<String, String> getErreurs() { 
		return erreurs;
	} 

	public Utilisateur login( HttpServletRequest request ) throws Exception {
		String email = getValeurChamp( request, CHAMP_EMAIL );
		String motDePasse = getValeurChamp( request, CHAMP_PASS );
		System.out.println("dans le loginForm methode login : "+email + motDePasse);

		Utilisateur login = new Utilisateur();
		try {       
			//traiterEmail( email, login );
			login.setEmail( email );
			login.setMotDePasse(motDePasse);
			System.out.println("je suis dans la methode login, voia l email et le mot de passe"+ login.getEmail()+login.getMotDePasse());
			if ( erreurs.isEmpty() ) { 
				System.out.println("je vais tester la trouver login dans le dao");
				if(utilisateurDao1.trouverLogin(login.getEmail(),login.getMotDePasse())!=0){  
					System.out.println("loginform oui j existe");
					return login; 
				}else{
					System.out.println("il n'existe pas");
					return null;
				} 
		}
			} catch ( DAOException e ) { 
			resultat = "echec de l'inscription : une erreur  est survenue.";
			e.printStackTrace(); 
		}   
		return null;
	} 
	
	/* * M�thode utilitaire qui retourne null si un champ est vide, et son contenu * sinon. */ 
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) { 
		String valeur = request.getParameter( nomChamp ); 
		if ( valeur == null || valeur.trim().length() == 0 ) { 
			return null;  
		} else {

			return valeur.trim();  
		}
	}

}
