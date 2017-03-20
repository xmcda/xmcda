package io.github.oliviercailloux.y2016.xmcdabis.dao;
import java.awt.Component;
import java.awt.List;
import java.util.Vector;

import javax.ejb.Stateless; 
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import io.github.oliviercailloux.y2016.xmcdabis.entities.Utilisateur;


@Stateless
public class UtilisateurDao {

	private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM Utilisateur u WHERE u.email=:email";  
	private static final String JPQL_SELECT_PAR_MDP = "SELECT u FROM Utilisateur u WHERE u.email=:email and u.motDePasse =:mdp";  
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_MDP = "mdp";

	// Injection du manager, qui s'occupe de la connexion avec la BDD   
 @PersistenceContext( unitName = "bdd_PU" )    
	private EntityManager em;    
	
// Enregistrement d'un nouvel utilisateur   
	public void creer(Utilisateur utilisateur) throws DAOException {  

		try {   
			System.out.println("je suis la fonction creer dao");
			em.persist(utilisateur); 
			System.out.println("je sors de  la fonction creer dao");
			
		} catch ( Exception e ) {   
			throw new DAOException(e);  
		}  
	} 
	
// Recherche d'un utilisateur � partir de son adresse email  
	public Utilisateur trouver(String email) throws DAOException {   
		Utilisateur utilisateur = null;   
		Query requete = em.createQuery(JPQL_SELECT_PAR_EMAIL);   
		requete.setParameter( PARAM_EMAIL, email );  
		
		try {         
			utilisateur = (Utilisateur) requete.getSingleResult();  
		} catch ( NoResultException e ) {    
			return null;     
		} catch ( Exception e ) {    
			throw new DAOException(e);   
		}  
		return utilisateur; 
	} 
	//Recherche d'un utilisateur � partir de son adresse email et son mot de passe 
	public int trouverLogin(String email, String mdp) throws DAOException {   
		Utilisateur utilisateur = null;   
		Query requete = em.createQuery(JPQL_SELECT_PAR_MDP);   
		requete.setParameter( PARAM_EMAIL, email );   
		requete.setParameter( PARAM_MDP, mdp );  
		try {         
			if (requete.getResultList().size()!= 0){ // si la liste n'est pas vide alors il exisite, renvoie 1
				return 1;
			}
		} catch ( NoResultException e ) {    
		    
		} catch ( Exception e ) {    
			throw new DAOException(e);   
		}  
		return 0;  // renvoie 0 sinon
	} 
}



