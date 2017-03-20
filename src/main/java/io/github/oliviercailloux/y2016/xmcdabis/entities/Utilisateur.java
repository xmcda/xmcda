package io.github.oliviercailloux.y2016.xmcdabis.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

// l'anotaion 
@Table (name="Utilisateur")
//, veut dire que l utilisateur est li� � la table Utilisateur

@Entity    // le bean utilisateur est un EJB Entity
public class Utilisateur implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull( message = "Veuillez saisir une adresse email" ) 
	@Pattern( regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)", message = "Merci de saisir une adresse mail valide" ) 
	private String email; 
	@Column( name = "mot_de_passe" )
	@NotNull( message = "Veuillez saisir un mot de passe" )
	@Size( min = 4, message = "Le mot de passe doit contenir au moins  caractères" ) 
	private String motDePasse;
	@NotNull( message = "Veuillez saisir un nom d'utilisateur" ) 
	@Size( min = 1, message = "Le nom d'utilisateur doit contenir au moins 1 caractères" ) 
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
