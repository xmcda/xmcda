package io.github.oliviercailloux.y2016.xmcda.objectsBeans;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class Criteria {
	@PersistenceContext
	private EntityManager em; 
	
	public void insertCriteria(String libelle, String preference){
		io.github.oliviercailloux.y2016.xmcda.entities.Criteria crit = new io.github.oliviercailloux.y2016.xmcda.entities.Criteria();
		crit.setLibelle(libelle);
		crit.setPreference(preference);
		
		em.persist(crit);
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
