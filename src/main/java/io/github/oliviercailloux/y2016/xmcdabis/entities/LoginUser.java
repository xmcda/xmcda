package io.github.oliviercailloux.y2016.xmcdabis.entities;


/**
 * Entity implementation class for Entity: Login
 *
 */

public class LoginUser{

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
