package io.github.oliviercailloux.y2016.xmcda.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.github.oliviercailloux.y2016.xmcda.dao.UserDao;
import io.github.oliviercailloux.y2016.xmcda.entities.User;
import io.github.oliviercailloux.y2016.xmcda.forms.InscriptionForm;
@WebServlet( urlPatterns = { "/inscription" } ) 

public class Inscription extends HttpServlet {  
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER = "utilisateur"; 
	public static final String ATT_FORM = "form"; 
	public static final String VUE  = "/index.jsp";  
	// Injection de notre EJB (Session Bean Stateless)  
	@EJB   
	private UserDao   utilisateurDao; 

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {    
		/* Affichage de la page d'inscription */   
		getServletContext().getRequestDispatcher(VUE).forward(request, response); 

	}  

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {  
		/* Preparation de l'objet formulaire */     
		
		InscriptionForm form = new InscriptionForm( utilisateurDao);  
		PrintWriter out = response.getWriter();
		/* Traitement de la requ�te et recup�ration du bean en resultant */      
		User utilisateur = null;
		try {
			utilisateur = form.inscrireUtilisateur( request );
			request.setAttribute("user", utilisateur);  
			out.print("y");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		HttpSession session = request.getSession();
		session.setAttribute("user", utilisateur);
		//out.print("y");
	  }
		
			 	
}
