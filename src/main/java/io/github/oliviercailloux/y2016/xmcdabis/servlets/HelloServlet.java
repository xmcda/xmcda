package io.github.oliviercailloux.y2016.xmcdabis.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.oliviercailloux.y2016.xmcdabis.entities.Hello;


/**
 * Servlet implementation class HelloServlet
 * @param <Hello>
 */
@WebServlet(description = "test Cdi Injection", urlPatterns = { "/HelloServlet" })
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 @Inject Hello hello;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		printWriter.println("<HTML><head><tilte> hello injection example</tilte></head>");
		printWriter.println("<BODY>");
			
		printWriter.println("<h1>"+hello.sayHello("Rafik IKHLEF, ca va today")+"</h1>");	
		printWriter.println("<h1>azul</h1>");
		printWriter.println("</BODY>");
		printWriter.println("</HTML>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
