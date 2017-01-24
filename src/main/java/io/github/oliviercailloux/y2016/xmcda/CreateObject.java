package io.github.oliviercailloux.y2016.xmcda;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.namespace.QName;

import io.github.xmcda_modular.y2016.jaxb.Alternative;
import io.github.xmcda_modular.y2016.jaxb.Criterion;
import io.github.xmcda_modular.y2016.jaxb.ObjectFactory;

/**
 * Servlet implementation class CreateObject
 */
public class CreateObject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateObject() {
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
		String alt = request.getParameter("alternative");
		String critere = request.getParameter("critere");
		String mesurement = request.getParameter("mesurement");
		String categorie = request.getParameter("categorie");
		String utilite = request.getParameter("utilite");		
		
		JAXBContext jaxb = null;
		try {
			jaxb = JAXBContext.newInstance(Alternative.class, Criterion.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		try {
			Marshaller m = jaxb.createMarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		XmlSchema schema = ObjectFactory.class.getPackage().getAnnotation(XmlSchema.class);
		String namespace = schema.namespace();
		
		//to create alternative
		ObjectFactory factory = new ObjectFactory();
		Alternative alternative = factory.createAlternative();
		alternative.setId("alt");
		
		//To define balise name
		QName alternativeQName = new QName(namespace, "alternative", "xs");
		JAXBElement<Alternative> altElement = new JAXBElement<>(alternativeQName, Alternative.class, alternative);
		
		
		
	}

}
