package io.github.oliviercailloux.y2016.xmcdabis.dao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import io.github.oliviercailloux.y2016.xmcdabis.entities.Utilisateur;


@Stateless
public class UtilisateurDao {

	private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM Utilisateur u WHERE u.email=:email";  
	private static final String JPQL_SELECT_PAR_MDP = "SELECT u FROM Utilisateur u WHERE u.email=:email and u.motDePasse =:motdepasse";  
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_MDP = "motdepasse";

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
	
// Recherche d'un utilisateur a partir de son adresse email  
	public Utilisateur trouver(String email) throws DAOException {   
		Utilisateur utilisateur = null;   
		Query requete = em.createQuery(JPQL_SELECT_PAR_EMAIL);   
		requete.setParameter( PARAM_EMAIL, email );  
		System.out.println("voila l email que je vais tester"+  PARAM_EMAIL);
		
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
		System.out.println("je suis dans la methode trouverlogin");
		//Utilisateur utilisateur = null;   
		Utilisateur u = trouver(email);
		if(u!=null && mdp.equals(u.getMotDePasse())){
			System.out.println("voila j ai trouvé oki dans trouverlogin bingo");
			return 1;
		}else {
			System.out.println("j ai ren trouvé c la merda");
			return 0;
		}
	} 
}



