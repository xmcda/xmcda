package io.github.oliviercailloux.y2016.xmcdabis.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import io.github.oliviercailloux.y2016.xmcdabis.dao.UtilisateurDao;
@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {  
	private static final long serialVersionUID = 1L;
	private Login login;  
	// Injection de notre EJB (Session Bean Stateless) 
	@EJB 
	private UtilisateurDao    utilisateurDao; 
	// Initialisation de l'entité utilisateur  
	public LoginBean() {    
		login = new Login(); 
	}  
	// Méthode d'action appelée lors du clic sur le bouton du formulaire    //de login 
	public void login() { 
		  
		if(utilisateurDao.trouverLogin(login.getEmail(),login.getMotDePasse())!=0){  
			System.out.println("il existe");
		FacesMessage message = new FacesMessage( "Succès d'authautification !" );   
		FacesContext.getCurrentInstance().addMessage( null, message );  
		}else{
			System.out.println("il n'existe pas");
			FacesMessage message = new FacesMessage( "erreur d'authautification !" );   
			FacesContext.getCurrentInstance().addMessage( null, message );  
		}
	}   
	public Login getLogin() { 
		return login;   
	}  
 
}


