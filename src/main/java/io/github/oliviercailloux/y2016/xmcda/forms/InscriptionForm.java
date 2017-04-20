package io.github.oliviercailloux.y2016.xmcda.forms;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.github.oliviercailloux.y2016.xmcda.dao.DAOException;
import io.github.oliviercailloux.y2016.xmcda.dao.UserDao;
import io.github.oliviercailloux.y2016.xmcda.entities.User;

public final class InscriptionForm { 

	private static final String CHAMP_EMAIL  = "email"; 
	private static final String CHAMP_PASS   = "motdepasse"; 
	private static final String CHAMP_PRENOM   = "prenom";  
	private static final String CHAMP_NOM    = "nom";  


	private String resultat;
	private Map<String, String> erreurs = new HashMap<String,String>(); 
	private UserDao utilisateurDao;
	
	
	public InscriptionForm( UserDao utilisateurDao ) {  
		this.utilisateurDao = utilisateurDao;
	}


	public String getResultat() { 
		return resultat; 
	}
	public Map<String, String> getErreurs() { 
		return erreurs;
	} 

	public User inscrireUtilisateur( HttpServletRequest request ) throws Exception {
		String email = getValeurChamp( request, CHAMP_EMAIL );
		String motDePasse = getValeurChamp( request, CHAMP_PASS );
		String prenom = getValeurChamp( request, CHAMP_PRENOM );
		String nom = getValeurChamp( request, CHAMP_NOM );
		Timestamp date = new Timestamp( System.currentTimeMillis() );

		User utilisateur = new User();
		try {       
			traiterEmail( email, utilisateur );
			utilisateur.setMotDePasse(motDePasse);
			utilisateur.setDateInscription( date );
			utilisateur.setNom(nom); 
			utilisateur.setPrenom(prenom);
			if ( erreurs.isEmpty() ) { 

				utilisateurDao.creer(utilisateur);
				System.out.println("good creation reussi");
				resultat = "Succes de l'inscription.";
			} else {  
				resultat = "echec de l'inscription.";
			}  
		} catch ( DAOException e ) { 
			resultat = "echec de l'inscription : une erreur  est survenue.";
			e.printStackTrace(); 
		}  
		return utilisateur; 
	} 

	/* * Ajoute un message correspondant au champ sp�cifi� � la map des erreurs. */
	private void setErreur( String champ, String message ) {  
		erreurs.put( champ, message ); 
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
	/*verification de l existance de l email dans la base */ 
	private void traiterEmail( String email, User utilisateur ) throws Exception { 
		try {       
			validationEmail( email ); 
		} catch ( FormValidationException e ) {      
			setErreur( CHAMP_EMAIL, e.getMessage() ); 
		}
		utilisateur.setEmail( email );
	}
	/* Validation de l'adresse email */
	private void validationEmail( String email ) throws FormValidationException {  
		if ( email != null ) {    
			if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {  
				throw new FormValidationException( "Merci de saisir mail valide." );    
			} else if ( utilisateurDao.trouver( email ) != null ) {    
				throw new FormValidationException( " email existe." );   
			}   
		} else {   
			throw new FormValidationException( "Merci de saisir un mail." );  
		}
	}

}

