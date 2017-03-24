package io.github.oliviercailloux.y2016.xmcda.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PerformanceTable {
	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
	@Column (name = "type")
	private String type; 
	@Column (name = "utilite")
	private String utilite;
	
	public PerformanceTable(){}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUtilite() {
		return utilite;
	}
	public void setUtilite(String utilite) {
		this.utilite = utilite;
	} 
	
	

}
