package io.github.oliviercailloux.y2016.xmcda.objectsBeans;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Criteria {
    private int id;
	private String libelle;
	private String preference;
	public Criteria() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getPreference() {
		return preference;
	}
	public void setPreference(String preference) {
		this.preference = preference;
	}

}
