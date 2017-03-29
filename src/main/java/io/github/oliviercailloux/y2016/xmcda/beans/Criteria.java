package io.github.oliviercailloux.y2016.xmcda.beans;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class Criteria {
	@PersistenceContext
	private EntityManager em; 
	
	public void insertCriteria(String libelle, String preference){
		try {
			
			io.github.oliviercailloux.y2016.xmcda.entities.Criteria crit = new io.github.oliviercailloux.y2016.xmcda.entities.Criteria();
			
			crit.setLibelle(libelle);
			crit.setPreference(preference);
			
			em.persist(crit);
			em.flush();
			System.out.println("je suis dans le bean creteria, je termine l insersion");
		} catch (Exception e) {
			System.out.println("Error when persist object " + e.getMessage());
		}
		
	}
	public void editCriteria(String libelle, String preference){
		io.github.oliviercailloux.y2016.xmcda.entities.Criteria crit = new io.github.oliviercailloux.y2016.xmcda.entities.Criteria();
		crit.setLibelle(libelle);
		crit.setPreference(preference);
		
		try{
			em.merge(crit);
		}catch(Exception e){
			System.out.println("Error when merging object "+e.getMessage());
		}
	}
	


}
