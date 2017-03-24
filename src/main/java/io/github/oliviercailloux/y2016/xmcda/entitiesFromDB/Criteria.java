package io.github.oliviercailloux.y2016.xmcda.entitiesFromDB;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the criteria database table.
 * 
 */
@Entity
@NamedQuery(name="Criteria.findAll", query="SELECT c FROM Criteria c")
public class Criteria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String libelle;

	private String preference;

	public Criteria() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getPreference() {
		return this.preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

}