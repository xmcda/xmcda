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
	private static final String JPQL_SELECT_PAR_MDP = "SELECT u FROM User u WHERE u.email=:email and u.motDePasse =:motdepasse";  
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_MDP = "motdepasse";

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
		System.out.println("voila l email que je vais tester dans trouver de user"+ email);
		User utilisateur = null;   
		Query requete = em.createQuery(JPQL_SELECT_PAR_EMAIL);   
		requete.setParameter( PARAM_EMAIL, email );  
		System.out.println("voila l email que je vais tester"+  PARAM_EMAIL);
		
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
		System.out.println("je suis dans la methode trouverlogin");
		//Utilisateur utilisateur = null;   
		User u = trouver(email);
		if(u!=null && mdp.equals(u.getMotDePasse())){
			System.out.println("voila j ai trouvé oki dans trouverlogin bingo");
			return 1;
		}else {
			System.out.println("j ai ren trouvé c la merda");
			return 0;
		}
	} 
	public void saveReuslt(Resultat res) throws DAOException {  
		try {   
			em.persist(res); 
			//em.close();
		} catch ( Exception e ) {   
			throw new DAOException(e);  
		}  
	} 
}



