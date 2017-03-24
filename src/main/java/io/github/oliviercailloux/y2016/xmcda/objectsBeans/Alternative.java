package io.github.oliviercailloux.y2016.xmcda.objectsBeans;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class Alternative {
	@PersistenceContext
	private EntityManager em; 
	
	
	public void insertAlternative(String libelle){
		io.github.oliviercailloux.y2016.xmcda.entitiesFromDB.Alternative alt = new io.github.oliviercailloux.y2016.xmcda.entitiesFromDB.Alternative(); 
		alt.setLibelle(libelle);
		
		try{
			em.persist(alt);
			em.flush();
		}catch(Exception e){
			System.out.println("Error when persist object "+e.getMessage());
		}
	}
	public void editAlternative(String libelle){
		io.github.oliviercailloux.y2016.xmcda.entitiesFromDB.Alternative alt = new io.github.oliviercailloux.y2016.xmcda.entitiesFromDB.Alternative(); 
		alt.setLibelle(libelle);
		
		try{
			em.merge(alt);
		}catch(Exception e){
			System.out.println("Error when merging object "+e.getMessage());
		}
	}
	
//    private int id;
//	private String libelle;
//	public Alternative() {
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
}
