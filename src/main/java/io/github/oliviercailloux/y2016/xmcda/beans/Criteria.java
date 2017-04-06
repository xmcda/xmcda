package io.github.oliviercailloux.y2016.xmcda.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Criteria {
	@PersistenceContext(unitName = "bdd_PU")
	private EntityManager em; 
	
	public void insertCriteria(String libelle, String preference){
		try {
			
			io.github.oliviercailloux.y2016.xmcda.entities.Criteria crit = new io.github.oliviercailloux.y2016.xmcda.entities.Criteria();
			
			crit.setLebelle(libelle);
			crit.setPreference(preference);
			
			em.persist(crit);
			
		} catch (Exception e) {
			System.out.println("Error when persist object " + e.getMessage());
		}
		
	}
	public void editCriteria(String libelle, String preference){
		io.github.oliviercailloux.y2016.xmcda.entities.Criteria crit = new io.github.oliviercailloux.y2016.xmcda.entities.Criteria();
		crit.setLebelle(libelle);
		crit.setPreference(preference);
		
		try{
			em.merge(crit);
		}catch(Exception e){
			System.out.println("Error when merging object "+e.getMessage());
		}
	}
	


}
