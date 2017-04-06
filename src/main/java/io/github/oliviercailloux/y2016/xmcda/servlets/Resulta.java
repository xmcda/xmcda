package io.github.oliviercailloux.y2016.xmcda.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.github.oliviercailloux.y2016.xmcda.dao.UserDao;
import io.github.oliviercailloux.y2016.xmcda.entities.Resultat;
import io.github.oliviercailloux.y2016.xmcda.entities.User;

/**
 * Servlet implementation class Resultat
 */
@WebServlet("/Resultat")
public class Resulta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB   
	private UserDao   utilisateurDao; 
	@Inject io.github.oliviercailloux.y2016.xmcda.entities.Resultat res;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Resulta() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		// recuperation de l email de l utilisatteur, 
		HttpSession session = request.getSession();
		User us= (User) session.getAttribute("user");
		String mail = us.getEmail();
		String ress = (String) session.getAttribute("result");
		// recuperation du resultat
		// construction de l objet resultat
		Resultat res = new Resultat();
		res.setResultwebservice(ress);
		res.setMailuser(mail);
		// persister dans la table
		utilisateurDao.saveReuslt(res);
		
	}

}
