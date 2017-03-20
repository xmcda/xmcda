package io.github.oliviercailloux.y2016.xmcdabis.forms;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import io.github.oliviercailloux.y2016.xmcdabis.dao.DAOException;
import io.github.oliviercailloux.y2016.xmcdabis.dao.UtilisateurDao;
import io.github.oliviercailloux.y2016.xmcdabis.entities.Utilisateur;


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
			traiterMotsDePasse(motDePasse, confirmation, utilisateur);
			traiterDate( date, utilisateur );
			traiterNom( nom, utilisateur ); 
			if ( erreurs.isEmpty() ) { 
				
				utilisateurDao.creer(utilisateur);
				//utilisateur = utilisateurDao.trouver("raf.ikhlef@gmail.Com");
				
				resultat = "Succ�s de l'inscription.";
			} else {  
				resultat = "�chec de l'inscription.";
			}  
		} catch ( DAOException e ) { 
			resultat = "�chec de l'inscription : une erreur impr�vue est survenue, merci de r�essayer dans quelques instants.";
			e.printStackTrace(); 
		}  
		return utilisateur; 
	} 

	private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception {  
		if ( motDePasse != null && confirmation != null ) {    
			if ( !motDePasse.equals( confirmation ) ) {     
				throw new Exception( "Les mots de passe entr�s sont diff�rents, merci de les saisir � nouveau." );  
			} else if ( motDePasse.length() < 3 ) {    
				throw new Exception( "Les mots de passe doivent contenir au moins 3 caract�res." );
			}  
		} else { 
			throw new Exception( "Merci de saisir et confirmer votre mot de passe." );  
		} 
	} 
	private void validationNom( String nom ) throws Exception {   
		if ( nom != null && nom.length() < 3 ) {
			throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caract�res." );   
		}
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
	/* * Appel � la validation de l'adresse email re�ue et initialisation de la * propri�t� email du bean */ 
	private void traiterEmail( String email, Utilisateur utilisateur ) throws Exception { 
		try {       
			validationEmail( email ); 
		} catch ( FormValidationException e ) {      
			setErreur( CHAMP_EMAIL, e.getMessage() ); 
		}
		utilisateur.setEmail( email );
	}
	/* * Appel � la validation de l'adresse email re�ue et initialisation de la * propri�t� email du bean */ 
	private void traiterNom( String nom, Utilisateur utilisateur ) throws Exception { 
		try {       
			validationNom( nom ); 
		} catch ( FormValidationException e ) {      
			setErreur( CHAMP_EMAIL, e.getMessage() ); 
		}
		utilisateur.setNom(nom);
	}
	
	/* * Appel � la validation des mots de passe re�us, chiffrement du mot de * passe et initialisation de la propri�t� motDePasse du bean */
	private void traiterMotsDePasse( String motDePasse, String confirmation, Utilisateur utilisateur ) throws Exception {   
		try {     
			validationMotsDePasse( motDePasse, confirmation);
		} catch ( FormValidationException e ) {    
			setErreur( CHAMP_PASS, e.getMessage() );  
			setErreur( CHAMP_CONF, null ); 
		} 
		utilisateur.setMotDePasse(motDePasse);
	} 
	/* Validation de l'adresse email */
	private void validationEmail( String email ) throws FormValidationException {  
		if ( email != null ) {    
			if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {  
				throw new FormValidationException( "Merci de saisir une adresse mail valide." );    
			} else if ( utilisateurDao.trouver( email ) != null ) {    
				throw new FormValidationException( "Cette adresse email est d�j� utilis�e, merci d'en choisir une autre." );   
			}   
		} else {   
			throw new FormValidationException( "Merci de saisir une adresse mail." );  
		}
	}
	/* * Simple initialisation de la propri�t� dateInscription du bean avec la * date courante. */ 
	private void traiterDate( Timestamp date, Utilisateur utilisateur ) {  
		utilisateur.setDateInscription( date );
	}
	


}

