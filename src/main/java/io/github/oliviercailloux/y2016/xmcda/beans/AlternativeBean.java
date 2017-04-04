package io.github.oliviercailloux.y2016.xmcda.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.github.oliviercailloux.y2016.xmcda.entities.Alternative;

@Stateless
public class AlternativeBean {

	@PersistenceContext(unitName = "xmcda")
	private EntityManager em;

	public void insertAlternative(String libelle) {
		io.github.oliviercailloux.y2016.xmcda.entities.Alternative alt = new io.github.oliviercailloux.y2016.xmcda.entities.Alternative();
		alt.setLibelle(libelle);

		try {
			em.persist(alt);
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

	public void removeAlternative(String libelle) {
		Alternative altId = new Alternative();
		int primaryKey = altId.getId();
		Alternative alt = null;
		try {
			em.find(Alternative.class, primaryKey);
		} catch (Exception e) {
			System.out.println("Error when find Alternative " + e);
		}
		try {
			em.remove(alt);
		} catch (Exception e) {
			System.out.println("Error when remove Alternative " + e);
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
