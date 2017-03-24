package io.github.oliviercailloux.y2016.xmcda.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the alternative database table.
 * 
 */
@Entity
@NamedQuery(name="Alternative.findAll", query="SELECT a FROM Alternative a")
public class Alternative implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String libelle;

	public Alternative() {
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

}