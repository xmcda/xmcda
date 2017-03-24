package io.github.oliviercailloux.y2016.xmcda.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Criteria {
	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
	@Column (name = "libelle")
	private String libelle;
	@Column (name = "preference")
	private String preference;
	
	public Criteria(){}
	
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
