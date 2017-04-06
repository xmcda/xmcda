package io.github.oliviercailloux.y2016.xmcda.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.github.oliviercailloux.y2016.xmcda.dao.UserDao;
import io.github.oliviercailloux.y2016.xmcda.entities.Login;
import io.github.oliviercailloux.y2016.xmcda.entities.LoginUser;
import io.github.oliviercailloux.y2016.xmcda.entities.User;
import io.github.oliviercailloux.y2016.xmcda.forms.LoginForm;

/**
 * Servlet implementation class Login
 */
@WebServlet(description = "connexion à cmcda", urlPatterns = { "/login" })
public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID1 = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER = "utilisateur"; 
	public static final String ATT_FORM = "form"; 
	public static final String VUE  = "/index.jsp";  
	// Injection de notre EJB (Session Bean Stateless)  
	@EJB   
	private UserDao   utilisateurDao1; 
    public LoginUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {  
		/* Preparation de l'objet formulaire */     
		
		LoginForm form = new LoginForm( utilisateurDao1);  
		PrintWriter out = response.getWriter();
		/* Traitement de la requ�te et recup�ration du bean en resultant */      
		User login = null;
		try {
			login = form.login( request );
			if(login!=null){
				//request.setAttribute( ATT_FORM, form);  
				//request.setAttribute( ATT_USER, login);  
				
			}else{
				System.out.println("voila la valeur de login : " + login);
				out.print("n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		/* Stockage du formulaire et du bean dans l'objet request
		 */
		
		//System.out.println("resultat de la session: " + session.getAttribute(ATT_USER).toString());
		//getServletContext().getRequestDispatcher(VUE).forward( request, response );
		HttpSession session = request.getSession();
		session.setAttribute("user",login);
		out.print("y");
		
	}		 	
}
