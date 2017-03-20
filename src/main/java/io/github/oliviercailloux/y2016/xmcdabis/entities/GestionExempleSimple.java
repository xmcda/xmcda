package io.github.oliviercailloux.y2016.xmcdabis.entities;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;

import io.github.oliviercailloux.y2016.xmcdabis.dao.DAOException;

/**
 * Entity implementation class for Entity: GestionExempleSimple
 *
 */
@ManagedBean
@SessionScoped
//@Stateless
public class GestionExempleSimple implements Serializable {
	static final long serialVersionUID = 1L; 
	Integer id = null; 
	String libelle = null;
	String email = null;
	@PersistenceContext( unitName = "bdd_PU" ) 
	EntityManager emm ;

	public GestionExempleSimple() {
	}

	public String getResponse() { 
		String retour = null;
		Personne exempleSimple = new Personne(); 
		//exempleSimple.setIdPersonne(getId());
		exempleSimple.setNomPersonne(getLibelle());
		exempleSimple.setEmail(getEmail());
		//return exempleSimple.getEmail();
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JSFTest");
//		EntityManager emm = emf.createEntityManager();
//		EntityTransaction et = emm.getTransaction();
//		et.begin();
		try {   
			System.out.println("debut");
			emm.persist(exempleSimple);
			System.out.println("id"+exempleSimple.getIdPersonne());
			System.out.println("fin");
		} catch ( Exception e ) {   
			throw new DAOException(e);  
		}  
//		System.out.println("debut");
//		//em.persist(exempleSimple);
//		System.out.println("id"+exempleSimple.getIdPersonne());
//		System.out.println("fin");
////		et.commit();
//		//exempleSimple = emm.find(Personne.class, getId());
		retour = "insertion ok"+ exempleSimple.getNomPersonne(); 
		return retour;
	}
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public String getLibelle() { return libelle;}
	public String getEmail() { return email;}
	public void setLibelle(String libelle) { this.libelle = libelle; }  
	public void setEmail(String email) { this.email=email;}
}
