package io.github.oliviercailloux.y2016.xmcda.beans;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.github.oliviercailloux.y2016.xmcda.entities.Performancetable;

@RequestScoped
public class PerformanTableBean {
	@PersistenceContext
	private EntityManager em;

	public void insertPerformanceTable(String type, String utilite) {
		try {
			Performancetable perf = new Performancetable();
			perf.setType(type);
			perf.setUtilite(utilite);

			em.persist(perf);
		} catch (Exception e) {
			System.out.println("Error when persist object " + e.getMessage());
		}
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
}
