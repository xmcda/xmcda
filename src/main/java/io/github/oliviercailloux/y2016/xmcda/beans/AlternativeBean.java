package io.github.oliviercailloux.y2016.xmcda.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AlternativeBean {

	@PersistenceContext(unitName = "bdd_PU")
	private EntityManager em;

	public void insertAlternative(String libelle) {
		io.github.oliviercailloux.y2016.xmcda.entities.Alternative alt = new io.github.oliviercailloux.y2016.xmcda.entities.Alternative();
		alt.setLibelle(libelle);

		try {
			em.persist(alt);
			em.flush();
		} catch (Exception e) {
			System.out.println("Error when persist object " + e.getMessage());
		}
	}

	public void editAlternative(String libelle) {
		io.github.oliviercailloux.y2016.xmcda.entities.Alternative alt = new io.github.oliviercailloux.y2016.xmcda.entities.Alternative();
		alt.setLibelle(libelle);

		try {
			em.merge(alt);
		} catch (Exception e) {
			System.out.println("Error when merging object " + e.getMessage());
		}
	}

	// private int id;
	// private String libelle;
	// public Alternative() {
	// // TODO Auto-generated constructor stub
	// }
	// public int getId() {
	// return id;
	// }
	// public void setId(int id) {
	// this.id = id;
	// }
	// public String getLibelle() {
	// return libelle;
	// }
	// public void setLibelle(String libelle) {
	// this.libelle = libelle;
	// }
}
