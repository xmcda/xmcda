package io.github.oliviercailloux.y2016.xmcda.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.oliviercailloux.y2017.xmcda.XMscheme.XM_scheme;

/**
 * Servlet implementation class ServletInvoke
 */
@WebServlet("/invoke")
public class ServletInvoke extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	XM_scheme xm;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletInvoke() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			xm.ServiceInvoke(request, response);
			request.getRequestDispatcher("/invoke.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
