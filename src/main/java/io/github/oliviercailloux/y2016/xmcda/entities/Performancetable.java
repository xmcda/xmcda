package io.github.oliviercailloux.y2016.xmcda.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the performancetable database table.
 * 
 */
@Entity
@NamedQuery(name="Performancetable.findAll", query="SELECT p FROM Performancetable p")
public class Performancetable  {

	@Id
	private int id;

	private String type;

	private String utilite;

	public Performancetable() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUtilite() {
		return this.utilite;
	}

	public void setUtilite(String utilite) {
		this.utilite = utilite;
	}

}