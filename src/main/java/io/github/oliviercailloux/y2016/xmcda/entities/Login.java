package io.github.oliviercailloux.y2016.xmcda.entities;

import java.io.Serializable;


/**
 * Entity implementation class for Entity: Login
 *
 */

public class Login implements Serializable {
	private static final long serialVersionUID = 1L;
	public Login() {
		super();
	}

	
	private String email; 
	
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
