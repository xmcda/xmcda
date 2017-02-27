package io.github.oliviercailloux.y2016.xmcda;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import io.github.xmcda_modular.y2016.jaxb.Alternative;
import io.github.xmcda_modular.y2016.jaxb.Criterion;
import io.github.xmcda_modular.y2016.jaxb.DirectedCriterion;
import io.github.xmcda_modular.y2016.jaxb.ObjectFactory;

/**
 * Servlet implementation class CreateObject
 */
public class CreateAlternativeObject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAlternativeObject() {
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
		String id = request.getParameter("alternative");		
		JAXBContext jc = null;
		try {
			jc = JAXBContext.newInstance(Alternative.class);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Marshaller marshaller = null;
		try {
			marshaller = jc.createMarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final ObjectFactory f = new ObjectFactory();

		final XmlSchema annotation = ObjectFactory.class.getPackage().getAnnotation(XmlSchema.class);
		final String namespace = annotation.namespace();

		final Alternative alt = f.createAlternative();
		alt.setId(id);

		final QName altQName = new QName(namespace, "myAlt", "xm");
		final JAXBElement<Alternative> altEl = new JAXBElement<>(altQName, Alternative.class, alt);

		final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final Document doc = docBuilder.newDocument();
		docFactory.setNamespaceAware(true);
		final Element rootElement = doc.createElementNS("myNS", "m:AltAndCrit");
		rootElement.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, "xmlns:xm", namespace);
		doc.appendChild(rootElement);

		try {
			marshaller.marshal(altEl, rootElement);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		/** Inelegant. (Impl. dependent.) */
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		final DOMSource source = new DOMSource(doc);
		// final StreamResult result = new StreamResult(new File("file.xml"));
		StringWriter writer = new StringWriter();
		final StreamResult resultStream = new StreamResult(writer);
		try {
			transformer.transform(source, resultStream);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = writer.toString(); 
		
		request.setAttribute("result", result);
		request.getServletContext().getRequestDispatcher("/alternativeCreated.jsp").forward(request, response);
		
		
	}

}
