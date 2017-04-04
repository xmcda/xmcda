package io.github.oliviercailloux.y2016.xmcda.beans;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.github.oliviercailloux.y2016.xmcda.entities.Performancetable;

//import io.github.oliviercailloux.y2016.xmcda.entities.Performancetable;

@RequestScoped
public class PerformanTableBean {
	@PersistenceContext
	private EntityManager em;

	public void insertPerformanceTable(String type, String utilite) {
		Performancetable perf = new Performancetable();
		perf.setType(type);
		perf.setUtilite(utilite);

		em.persist(perf);
	}

	public void editPerformanceTable(String type, String utilite) {
		Performancetable perf = new Performancetable();
		perf.setType(type);
		perf.setUtilite(utilite);

		try {
			em.merge(perf);
		} catch (Exception e) {
			System.out.println("Error when merging object " + e.getMessage());
		}
	}

	public void removePerformanceTable(String type, String utilite) {
		Performancetable perfId = new Performancetable();
		int primaryKey = perfId.getId();
		Performancetable perf = em.find(Performancetable.class, primaryKey);

		em.remove(perf);
	}

	// private String type;
	// private String utilite;
	// public PerformanTable() {
	// // TODO Auto-generated constructor stub
	// }
	// public String getType() {
	// return type;
	// }
	// public void setType(String type) {
	// this.type = type;
	// }
	// public String getUtilite() {
	// return utilite;
	// }
	// public void setUtilite(String utilite) {
	// this.utilite = utilite;
	// }

}
