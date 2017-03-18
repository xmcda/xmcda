package io.github.oliviercailloux.y2016.xmcda.servlets;

import io.github.oliviercailloux.y2017.xmcda.XMscheme.InputStruct;
import io.github.oliviercailloux.y2017.xmcda.XMscheme.ParsingDescriptionUrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class ServletParsingXM
 */
@WebServlet("/Parsing")
public class ServletParsingXM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletParsingXM() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGetVersion1(request, response);
		doGetVersion2(request, response);
	}
	
	@SuppressWarnings("deprecation")

	protected void doGetVersion1(HttpServletRequest request,

	HttpServletResponse response) throws ServletException, IOException {

	PrintWriter printWriter = response.getWriter();

	printWriter.println("<h2>");

	printWriter.println("Entrée dans la servlet à ");

	printWriter.println("</h2>");

	}
	
	@SuppressWarnings("deprecation")

	protected void doGetVersion2(HttpServletRequest request,

	HttpServletResponse response) throws ServletException, IOException {

	URL url=new URL("http://www.decision-deck.org/ws/_downloads/description-wsDD.xml");
	ParsingDescriptionUrl parseur=new ParsingDescriptionUrl();
	List<InputStruct> inputs;
	
	
	try {
    inputs=parseur.Parse(url, request);
	PrintWriter printWriter = response.getWriter();
	int a=inputs.size();
	

	

	printWriter.println("<h2>");

	printWriter.println("La taille de la liste est "+ inputs.size());

	printWriter.println("</h2>");
	printWriter.println("<h2>");

	printWriter.println("La taille de la liste est "+ inputs.get(1).name);

	printWriter.println("</h2>");

	} catch (Exception e2) {

	System.out.println(e2.getMessage().toString());

	}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPostVersion1(request, response);
	}
	
	protected void doPostVersion1(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {

			try {
				String ul = request.getParameter("URL");
				URL url=new URL(ul);
				ParsingDescriptionUrl parseur=new ParsingDescriptionUrl();
			List<InputStruct> inputs=parseur.Parse(url, request);
			response.setContentType("text/html");

			
			PrintWriter printWriter = response.getWriter();
			
			
			printWriter

			.println("<h2>Les fichiers obligatoires"

			+ "</h2>");
			for (int i=0;i<inputs.size();i++){
				if(inputs.get(i).isoptional.equals("1")){
					printWriter.println("<h3>"+ inputs.get(i).name+"</h3>");
				}
			}
			printWriter

			.println("<h2>Les fichiers optionnels"+ "</h2>");
			
			for (int i=0;i<inputs.size();i++){
				if(inputs.get(i).isoptional.equals("0")){
					printWriter.println("<h3>"+ inputs.get(i).name+"</h3>");
				}
			}

			} catch (Exception e) {

			e.printStackTrace();

			}

			}
}
