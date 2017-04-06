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

import io.github.oliviercailloux.y2016.xmcda.dao.UtilisateurDao;
import io.github.oliviercailloux.y2016.xmcda.entities.Utilisateur;
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
	public static final String VUE = "/index.jsp";
	@EJB
	private UtilisateurDao utilisateurDao1;

	public LoginUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginForm form = new LoginForm(utilisateurDao1);
		PrintWriter out = response.getWriter();
		Utilisateur login = null;
		try {
			login = form.login(request);
			if (login != null) {

			} else {
				System.out.println("voila la valeur de login : " + login);
				out.print("n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		session.setAttribute("user", login);
		out.print("y");

	}
}
