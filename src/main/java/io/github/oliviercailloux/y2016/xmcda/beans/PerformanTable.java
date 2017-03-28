package io.github.oliviercailloux.y2016.xmcda.beans;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.github.oliviercailloux.y2016.xmcda.entities.Performancetable;

@RequestScoped
public class PerformanTable {
	@PersistenceContext
	private EntityManager em; 
	
	public void insertPerformanceTable(String type, String utilite){
		Performancetable perf = new Performancetable(); 		
		perf.setType(type);
		perf.setUtilite(utilite);
		
		em.persist(perf);
		em.flush();
	}
	public void editPerformanceTable(String type, String utilite){
		Performancetable perf = new Performancetable(); 
		perf.setType(type);
		perf.setUtilite(utilite);
		
		try{
			em.merge(perf);
		}catch(Exception e){
			System.out.println("Error when merging object "+e.getMessage());
		}		
	}
	
//	private String type;
//	private String utilite;
//	public PerformanTable() {
//		// TODO Auto-generated constructor stub
//	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
//	public String getUtilite() {
//		return utilite;
//	}
//	public void setUtilite(String utilite) {
//		this.utilite = utilite;
//	}

}
