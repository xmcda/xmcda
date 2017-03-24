package io.github.oliviercailloux.y2016.xmcda.servlets;

import java.io.IOException;
import java.io.StringWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

import io.github.oliviercailloux.y2016.xmcda.objectsBeans.PerformanTable;
import io.github.xmcda_modular.y2016.jaxb.Criterion;
import io.github.xmcda_modular.y2016.jaxb.DirectedCriterion;
import io.github.xmcda_modular.y2016.jaxb.ObjectFactory;

/**
 * Servlet implementation class CreatePerformanceTableObject
 */
@WebServlet(urlPatterns = "/CreatePerformanceTableObject")
public class CreatePerformanceTableObject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject PerformanTable perfTable;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePerformanceTableObject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			createPerformaceTable(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		createPerformaceTable(request, response);
	}
	protected void createPerformaceTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mesurementOrCat = request.getParameter("mesurementOrCat");	
		
		if(mesurementOrCat.equals("Mesurement")){
			String utilite = request.getParameter("utilite");
			perfTable.insertPerformanceTable(mesurementOrCat, utilite);
			JAXBContext jc = null;
			try {
				jc = JAXBContext.newInstance(Criterion.class);
			} catch (JAXBException e) {		
				e.printStackTrace();
			}
			Marshaller marshaller = null;
			try {
				marshaller = jc.createMarshaller();
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			final ObjectFactory f = new ObjectFactory();

			final XmlSchema annotation = ObjectFactory.class.getPackage().getAnnotation(XmlSchema.class);
			final String namespace = annotation.namespace();

			final DirectedCriterion directedCriterion = f.createDirectedCriterion();
			directedCriterion.setId(mesurementOrCat);
			directedCriterion.setPreferenceDirection(utilite);
		
			final QName critQName = new QName(namespace, "critere", "xs");
			final JAXBElement<Criterion> critEl = new JAXBElement<>(critQName, Criterion.class, directedCriterion);

			final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = null;
			try {
				docBuilder = docFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}

			final Document doc = docBuilder.newDocument();
			docFactory.setNamespaceAware(true);
			final Element rootElement = doc.createElementNS("namespace", "m:Critere");
			rootElement.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, "xmlns:xs", namespace);
			doc.appendChild(rootElement);

			try {
				marshaller.marshal(critEl, rootElement);
			} catch (JAXBException e) {
				e.printStackTrace();
			}

			final TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = null;
			try {
				transformer = transformerFactory.newTransformer();
			} catch (TransformerConfigurationException e) {
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
				e.printStackTrace();
			}
			String result = writer.toString(); 
			
			request.setAttribute("result", result.replace("\"","&quot;").replace("<","&lt;").replace(">","&gt;"));
			request.getServletContext().getRequestDispatcher("/mesurementCreated.jsp").forward(request, response);

		}else{
			String utilite = request.getParameter("utilite");
			perfTable.insertPerformanceTable(mesurementOrCat, utilite);
			JAXBContext jc = null;
			try {
				jc = JAXBContext.newInstance(Criterion.class);
			} catch (JAXBException e) {		
				e.printStackTrace();
			}
			Marshaller marshaller = null;
			try {
				marshaller = jc.createMarshaller();
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			final ObjectFactory f = new ObjectFactory();

			final XmlSchema annotation = ObjectFactory.class.getPackage().getAnnotation(XmlSchema.class);
			final String namespace = annotation.namespace();

			final DirectedCriterion directedCriterion = f.createDirectedCriterion();
			directedCriterion.setId(mesurementOrCat);
			directedCriterion.setPreferenceDirection(utilite);
		
			final QName critQName = new QName(namespace, "critere", "xs");
			final JAXBElement<Criterion> critEl = new JAXBElement<>(critQName, Criterion.class, directedCriterion);

			final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = null;
			try {
				docBuilder = docFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}

			final Document doc = docBuilder.newDocument();
			docFactory.setNamespaceAware(true);
			final Element rootElement = doc.createElementNS("namespace", "m:Critere");
			rootElement.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, "xmlns:xs", namespace);
			doc.appendChild(rootElement);

			try {
				marshaller.marshal(critEl, rootElement);
			} catch (JAXBException e) {
				e.printStackTrace();
			}

			final TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = null;
			try {
				transformer = transformerFactory.newTransformer();
			} catch (TransformerConfigurationException e) {
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
			
			request.setAttribute("result", result.replace("\"","&quot;").replace("<","&lt;").replace(">","&gt;"));
			request.getServletContext().getRequestDispatcher("/categorieCreated.jsp").forward(request, response);
		
		}

	}


}
