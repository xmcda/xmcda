package io.github.oliviercailloux.y2016.xmcda.beans;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class CriteriaBean {
	@PersistenceContext
	private EntityManager em; 
	
	public void insertCriteria(String libelle, String preference){
		io.github.oliviercailloux.y2016.xmcda.entities.Criteria crit = new io.github.oliviercailloux.y2016.xmcda.entities.Criteria();
		crit.setLibelle(libelle);
		crit.setPreference(preference);
		
		em.persist(crit);
		em.flush();
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
	
//    private int id;
//	private String libelle;
//	private String preference;
//	public Criteria() {
//		// TODO Auto-generated constructor stub
//	}
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getLibelle() {
//		return libelle;
//	}
//	public void setLibelle(String libelle) {
//		this.libelle = libelle;
//	}
//	public String getPreference() {
//		return preference;
//	}
//	public void setPreference(String preference) {
//		this.preference = preference;
//	}

}