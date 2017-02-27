package forms;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import dao.DAOException;
import dao.UtilisateurDao;
import entities.Utilisateur;


public final class InscriptionForm { 
	private static final String CHAMP_EMAIL  = "email"; 
	private static final String CHAMP_PASS   = "motdepasse"; 
	private static final String CHAMP_CONF   = "confirmation";  
	private static final String CHAMP_NOM    = "nom";  
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String,String>(); 


	private UtilisateurDao utilisateurDao;
	public InscriptionForm( UtilisateurDao utilisateurDao ) {  
		this.utilisateurDao = utilisateurDao;
	}

	public String getResultat() { 
		return resultat; 
	}
	public Map<String, String> getErreurs() { 
		return erreurs;
	} 

	public Utilisateur inscrireUtilisateur( HttpServletRequest request ) throws Exception {
		String email = getValeurChamp( request, CHAMP_EMAIL );
		String motDePasse = getValeurChamp( request, CHAMP_PASS );
		String confirmation = getValeurChamp( request, CHAMP_CONF );
		String nom = getValeurChamp( request, CHAMP_NOM );
		Timestamp date = new Timestamp( System.currentTimeMillis() );

		Utilisateur utilisateur = new Utilisateur();
		try {       
			traiterEmail( email, utilisateur );
			traiterMotsDePasse( motDePasse, confirmation, utilisateur );
			traiterDate( date, utilisateur );
			//traiterNom( nom, utilisateur ); 
			if ( erreurs.isEmpty() ) { 
				utilisateurDao.creer( utilisateur );
				resultat = "Succès de l'inscription.";
			} else {  
				resultat = "Échec de l'inscription.";
			}  
		} catch ( DAOException e ) { 
			resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace(); 
		}  
		return utilisateur; 
	} 

	private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception {  
		if ( motDePasse != null && confirmation != null ) {    
			if ( !motDePasse.equals( confirmation ) ) {     
				throw new Exception( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );  
			} else if ( motDePasse.length() < 3 ) {    
				throw new Exception( "Les mots de passe doivent contenir au moins 3 caractères." );
			}  
		} else { 
			throw new Exception( "Merci de saisir et confirmer votre mot de passe." );  
		} 
	} 
	private void validationNom( String nom ) throws Exception {   
		if ( nom != null && nom.length() < 3 ) {
			throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caractères." );   
		}
	}
	/* * Ajoute un message correspondant au champ spécifié à la map des erreurs. */
	private void setErreur( String champ, String message ) {  
		erreurs.put( champ, message ); 
	} 
	/* * Méthode utilitaire qui retourne null si un champ est vide, et son contenu * sinon. */ 
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) { 
		String valeur = request.getParameter( nomChamp ); 
		if ( valeur == null || valeur.trim().length() == 0 ) { 
			return null;  
		} else {

			return valeur.trim();  
		}
	}
	/* * Appel à la validation de l'adresse email reçue et initialisation de la * propriété email du bean */ 
	private void traiterEmail( String email, Utilisateur utilisateur ) throws Exception { 
		try {       
			validationEmail( email ); 
		} catch ( FormValidationException e ) {      
			setErreur( CHAMP_EMAIL, e.getMessage() ); 
		}
		utilisateur.setEmail( email );
	}
	/* * Appel à la validation des mots de passe reçus, chiffrement du mot de * passe et initialisation de la propriété motDePasse du bean */
	private void traiterMotsDePasse( String motDePasse, String confirmation, Utilisateur utilisateur ) throws Exception {   
		try {     
			validationMotsDePasse( motDePasse, confirmation );
		} catch ( FormValidationException e ) {    
			setErreur( CHAMP_PASS, e.getMessage() );  
			setErreur( CHAMP_CONF, null ); 
		} 
	} 
	/* Validation de l'adresse email */
	private void validationEmail( String email ) throws FormValidationException {  
		if ( email != null ) {    
			if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {  
				throw new FormValidationException( "Merci de saisir une adresse mail valide." );    
			} else if ( utilisateurDao.trouver( email ) != null ) {    
				throw new FormValidationException( "Cette adresse email est déjà utilisée, merci d'en choisir une autre." );   
			}   
		} else {   
			throw new FormValidationException( "Merci de saisir une adresse mail." );  
		}
	}
	/* * Simple initialisation de la propriété dateInscription du bean avec la * date courante. */ 
	private void traiterDate( Timestamp date, Utilisateur utilisateur ) {  
		utilisateur.setDateInscription( date );
	}


}

