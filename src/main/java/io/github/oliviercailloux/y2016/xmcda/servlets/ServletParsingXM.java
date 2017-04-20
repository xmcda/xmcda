package io.github.oliviercailloux.y2016.xmcda.servlets;

import io.github.oliviercailloux.y2017.xmcda.XMscheme.InputStruct;
import io.github.oliviercailloux.y2017.xmcda.XMscheme.ParsingDescriptionUrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ServletParsingXM
 */
@WebServlet("/Parsing")
public class ServletParsingXM extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject ParsingDescriptionUrl parseur;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletParsingXM() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGetVersion2(request, response);
	}

	protected void doGetVersion1(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter printWriter = response.getWriter();

		printWriter.println("<h2>");

		printWriter.println("Entrée dans la servlet à ");

		printWriter.println("</h2>");

	}

	protected void doGetVersion2(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {

		URL url=new URL("http://www.decision-deck.org/ws/_downloads/description-wsDD.xml");
		ParsingDescriptionUrl parseur=new ParsingDescriptionUrl();
		List<InputStruct> inputs;
		try {
			inputs=parseur.Parse(url, request);
			PrintWriter printWriter = response.getWriter();
			printWriter.println("<h2>");
			printWriter.println("La taille de la liste est "+ inputs.size());
			printWriter.println("</h2>");
			printWriter.println("<h2>");
			printWriter.println("La taille de la liste est "+ inputs.get(1).getName());
			printWriter.println("</h2>");
		} catch (Exception e2) {

			System.out.println(e2.getMessage().toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPostVersion1(request, response);
	}

	protected void doPostVersion1(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {
		try {
			String ul = request.getParameter("URL");
			URL url=new URL(ul);
			List<String> obligatoire = new ArrayList<String>();
			
			List<String> facultatif = new ArrayList<String>();
			// replace with CDI context
			//ParsingDescriptionUrl parseur = new ParsingDescriptionUrl();
			List<InputStruct> inputs = parseur.Parse(url, request);
			for (int i=0;i<inputs.size();i++){
				if(inputs.get(i).getIsoptional().equals("0")){
					obligatoire.add(inputs.get(i).getName());
				} else{
					facultatif.add(inputs.get(i).getName());
				}
			}
			request.setAttribute("obligatoires", obligatoire);
			request.setAttribute("facultatifs", facultatif);
			request.getRequestDispatcher("/parse.jsp").forward(request, response);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
