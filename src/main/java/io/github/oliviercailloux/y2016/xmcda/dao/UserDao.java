package io.github.oliviercailloux.y2016.xmcda.dao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import io.github.oliviercailloux.y2016.xmcda.entities.Resultat;
import io.github.oliviercailloux.y2016.xmcda.entities.User;


@Stateless
public class UserDao {

	private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM User u WHERE u.email=:email";  
	//private static final String JPQL_SELECT_PAR_MDP = "SELECT u FROM User u WHERE u.email=:email and u.motDePasse =:motdepasse";  
	private static final String PARAM_EMAIL = "email";
	//private static final String PARAM_MDP = "motdepasse";

	// Injection du manager, qui s'occupe de la connexion avec la BDD   
 @PersistenceContext( unitName = "bdd_PU" )    
	private EntityManager em;    
	
// Enregistrement d'un nouvel utilisateur   

	public void creer(User utilisateur) throws DAOException {  
		try {   
			em.persist(utilisateur); 
			//em.close();
		} catch ( Exception e ) {   
			throw new DAOException(e);  
		}  
	} 
	
// Recherche d'un utilisateur a partir de son adresse email  
	public User trouver(String email) throws DAOException {   
		User utilisateur = null;   
		Query requete = em.createQuery(JPQL_SELECT_PAR_EMAIL);   
		requete.setParameter( PARAM_EMAIL, email );  
		
		try {         
			utilisateur = (User) requete.getSingleResult();  
		} catch ( NoResultException e ) {    
			return null;     
		} catch ( Exception e ) {    
			throw new DAOException(e);   
		}  
		return utilisateur; 
	} 
	//Recherche d'un utilisateur � partir de son adresse email et son mot de passe 
	public int trouverLogin(String email, String mdp) throws DAOException {   
		
		//Utilisateur utilisateur = null;   
		User u = trouver(email);
		if(u!=null && mdp.equals(u.getMotDePasse())){
			
			return 1;
		}else {
			
			return 0;
		}
	}
	// enregistrement du resultat dans la base
	public void saveReuslt(Resultat res) throws DAOException {  
		try {   
			em.persist(res); 
			
		} catch ( Exception e ) {   
			throw new DAOException(e);  
		}  
	} 
}



