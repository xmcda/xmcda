package entity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersistUser {
	@PersistenceContext
	private EntityManager em; 
	
	public void persist(String name, String login, String pwd){
		User u = new User(name, login, pwd); 
		 
		em.persist(u);			
	}
	
}
