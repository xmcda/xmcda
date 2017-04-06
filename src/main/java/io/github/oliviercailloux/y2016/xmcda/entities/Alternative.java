package io.github.oliviercailloux.y2016.xmcda.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the alternative database table.
 * 
 */
@Entity
@NamedQuery(name="Alternative.findAll", query="SELECT a FROM Alternative a")
public class Alternative {

	@Id
	private int id;

	private String lebelle;

	public Alternative() {
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

}