package io.github.oliviercailloux.y2016.xmcdabis.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.oliviercailloux.y2016.xmcdabis.dao.UtilisateurDao;
import io.github.oliviercailloux.y2016.xmcdabis.entities.Utilisateur;
import io.github.oliviercailloux.y2016.xmcdabis.forms.InscriptionForm;
@WebServlet( urlPatterns = { "/inscription" } ) 

public class Inscription extends HttpServlet {  
	public static final String ATT_USER = "utilisateur"; 
	public static final String ATT_FORM = "form"; 
	public static final String VUE  = "/testresultat.jsp";  
	// Injection de notre EJB (Session Bean Stateless)  
	@EJB   
	private UtilisateurDao   utilisateurDao; 

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {    
		/* Affichage de la page d'inscription */   
		getServletContext().getRequestDispatcher(VUE).forward(request, response); 
//		response.setContentType("text/html");
//		PrintWriter printWriter = response.getWriter();
//		printWriter.println("<BODY>");
//		printWriter.println("<CENTER><H4>Compte N� "+ "yes" +"</H4>");
//		
//		printWriter.println("</BODY>");
//		printWriter.println("</HTML>"); 
	}  

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {  
		/* Pr�paration de l'objet formulaire */     
		InscriptionForm form = new InscriptionForm( utilisateurDao);  
		
		/* Traitement de la requ�te et r�cup�ration du bean en r�sultant */      
		Utilisateur utilisateur = null;
		try {
			utilisateur = form.inscrireUtilisateur( request );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		/* Stockage du formulaire et du bean dans l'objet request
		 */
		request.setAttribute( ATT_FORM, form);  
		request.setAttribute( ATT_USER, utilisateur);  
		getServletContext().getRequestDispatcher(VUE).forward( request, response );
	}		 	
}
