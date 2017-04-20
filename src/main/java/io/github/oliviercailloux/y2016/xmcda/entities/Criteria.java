package io.github.oliviercailloux.y2016.xmcda.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the criteria database table.
 * 
 */
@Entity
@NamedQuery(name="Criteria.findAll", query="SELECT c FROM Criteria c")
public class Criteria {
	

	@Id
	private int id;

	private String lebelle;

	private String preference;

	public Criteria() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLebelle() {
		return this.lebelle;
	}

	public void setLebelle(String lebelle) {
		this.lebelle = lebelle;
	}

	public String getPreference() {
		return this.preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

}