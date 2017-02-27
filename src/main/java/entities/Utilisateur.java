package entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// l anotaion @Table (name="Utilisateur"), veut dire que l utilisateur est li� � la ble Utilisateur

@Entity    // le bean utilisateur est un EJB Entity
public class Utilisateur {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	private String email; 
	@Column( name = "mot_de_passe" )
	private String motDePasse;
	private String nom;
	@Column( name = "date_inscription" )
	private Timestamp dateInscription; 
	public Long getId(){
		return id; } 
	public void setId( Long id ) { 
		this.id = id; 
	}
	public void setEmail( String email ) {  
		this.email = email;
	}
	public String getEmail() { 
		return email; 
	} 
	public void setMotDePasse( String motDePasse ) {   
		this.motDePasse = motDePasse; 
	}  
	public String getMotDePasse() {   
		return motDePasse; 
	}
	public void setNom( String nom ) { 
		this.nom = nom;   
	}  
	public String getNom() {    
		return nom;    
	}
	public Timestamp getDateInscription() { 
		return dateInscription; 
	} 
	public void setDateInscription( Timestamp dateInscription ) { 
		this.dateInscription = dateInscription; 
	} 

}
