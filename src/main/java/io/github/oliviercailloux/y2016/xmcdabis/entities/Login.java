package io.github.oliviercailloux.y2016.xmcdabis.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Entity implementation class for Entity: Login
 *
 */

public class Login implements Serializable {
	private static final long serialVersionUID = 1L;
	public Login() {
		super();
	}

	@NotNull( message = "Veuillez saisir une adresse email" ) 
	@Pattern( regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)", message = "Merci de saisir une adresse mail valide" ) 
	private String email; 
	@Column( name = "mot_de_passe" )
	@NotNull( message = "Veuillez saisir un mot de passe" )
	@Size( min = 4, message = "Le mot de passe doit contenir au moins  caractères" ) 
	private String motDePasse;
	


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
	

}
