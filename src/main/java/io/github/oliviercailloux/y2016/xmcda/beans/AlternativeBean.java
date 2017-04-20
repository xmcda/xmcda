package io.github.oliviercailloux.y2016.xmcda.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.NoResultException;

@Stateless
public class AlternativeBean {

	@PersistenceContext(unitName = "bdd_PU")
	private EntityManager em;

	public void insertAlternative(String libelle) {

		io.github.oliviercailloux.y2016.xmcda.entities.Alternative alt = new io.github.oliviercailloux.y2016.xmcda.entities.Alternative();
		alt.setLebelle(libelle);
		
		try {
			em.persist(alt);
			
		} catch (Exception e) {
			System.out.println("Error when persist object " + e.getMessage());
		}
	}

	public void editAlternative(String libelle) {
		io.github.oliviercailloux.y2016.xmcda.entities.Alternative alt = new io.github.oliviercailloux.y2016.xmcda.entities.Alternative();
		alt.setLebelle(libelle);

		try {
			em.merge(alt);
		} catch (Exception e) {
			System.out.println("Error when merging object " + e.getMessage());
		}
	}

}
