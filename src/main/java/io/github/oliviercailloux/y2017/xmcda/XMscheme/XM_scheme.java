package io.github.oliviercailloux.y2017.xmcda.XMscheme;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.soap.SOAPBinding;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.google.common.io.Resources;


/** @author Anis **/

@SuppressWarnings("serial")
//@WebServlet(urlPatterns = "/xmcdaScheme" )
public class XM_scheme extends HttpServlet{
	private static final String ENDPOINT_ADDRESS = "http://webservices.decision-deck.org/soap/rankAlternativesValues-RXMCDA.py";

	private Transformer transformer;

	public XM_scheme() {
		transformer = null;
	}

	public String asString(Node node)
			throws TransformerException, TransformerFactoryConfigurationError, TransformerConfigurationException {
		final StringWriter asString = new StringWriter();
		getTransformer().transform(new DOMSource(node), new StreamResult(asString));
		return asString.toString();
	}

	public Transformer getTransformer() throws TransformerFactoryConfigurationError, TransformerConfigurationException {
		if (transformer == null) {
			final TransformerFactory tFactory = TransformerFactory.newInstance();
			transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		}
		return transformer;
	}

	public Node invoke(Dispatch<Source> dispatch, Source src)
			throws TransformerException, TransformerFactoryConfigurationError, TransformerConfigurationException {
		final Source ret = dispatch.invoke(src);
		final DOMResult result = new DOMResult();
		// fix the problem of encoded text
		getTransformer().transform(ret, result);
		final Node resultNode = result.getNode();
		return resultNode;
	}

	public void setFileContentToNodeValue(String sourceFile, Node destNode) throws IOException {
		final URL resUrl = getClass().getResource(sourceFile);
		final String resStr = Resources.toString(resUrl, StandardCharsets.UTF_8);
		final Text textNode = destNode.getOwnerDocument().createTextNode(resStr);
		destNode.appendChild(textNode);
	}

	 public  void doGet(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException  {
	 
	//public String submitAndRequest() throws Exception {
		final Service svc = Service.create(new QName("ServiceNamespace", "ServiceLocalPart"));
		final QName portQName = new QName("PortNamespace", "PortLocalPart");
		svc.addPort(portQName, SOAPBinding.SOAP11HTTP_BINDING, ENDPOINT_ADDRESS);
		final Dispatch<Source> dispatch = svc.createDispatch(portQName, Source.class, Mode.PAYLOAD);
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		final String ticket;
		final Document doc = builder.newDocument();
		final Element submit = doc.createElement("submitProblem");
		submit.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
		final Element sub1 = doc.createElement("overallValues");
		final Element sub2 = doc.createElement("alternatives");
		doc.appendChild(submit);
		submit.appendChild(sub1);
		submit.appendChild(sub2);
		setFileContentToNodeValue("overallValues.xml", sub1);
		setFileContentToNodeValue("alternatives.xml", sub2);
		final Attr attrType1 = doc.createAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:type");
		attrType1.setValue("xsd:string");
		sub1.setAttributeNodeNS(attrType1);
		final Attr attrType2 = (Attr) attrType1.cloneNode(true);
		sub2.setAttributeNodeNS(attrType2);
		Node ret = null;
		try {
			ret = invoke(dispatch, new DOMSource(doc));
		} catch (TransformerConfigurationException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (TransformerException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (TransformerFactoryConfigurationError e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		final NodeList directChildren = ret.getChildNodes();
		final Node firstChild = directChildren.item(0);
		final NodeList subChildren = firstChild.getChildNodes();
		final Node secondSubChild = subChildren.item(1);
		ticket = secondSubChild.getFirstChild().getTextContent();
		System.out.println("");
		System.out.println( "The ticket :" );
		System.out.println( ticket );
		System.out.println("");
		final Document requestSolutionDoc = builder.newDocument();
		final Element requestSolution = requestSolutionDoc.createElement("requestSolution");
		requestSolution.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
		final Element ticketEl = requestSolutionDoc.createElement("ticket");
		requestSolutionDoc.appendChild(requestSolution);
		requestSolution.appendChild(ticketEl);
		final Text ticketTextNode = requestSolutionDoc.createTextNode(ticket);
		ticketEl.appendChild(ticketTextNode);
		final Attr attrType = requestSolutionDoc.createAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:type");
		attrType.setValue("xsd:string");
		ticketEl.setAttributeNodeNS(attrType);
		System.out.println("The Request :");
		try {
			System.out.println( asString(requestSolutionDoc) );
		} catch (TransformerException | TransformerFactoryConfigurationError e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Node solution = null;
		try {
			solution = invoke(dispatch, new DOMSource(requestSolutionDoc));
		} catch (TransformerException | TransformerFactoryConfigurationError e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println("The Response :");		
		String result = null;
		try {
			result = asString(solution).replaceAll("&lt;", "<");
		} catch (TransformerException | TransformerFactoryConfigurationError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		result = result.replaceAll("&gt;", ">");
		System.out.println( result );

		final Node res = solution.getChildNodes().item(0).getChildNodes().item(0);
		String output = null;
		try {
			output = asString(res).replaceAll("&lt;", "<");
		} catch (TransformerException | TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		output = output.replaceAll("&gt;", ">");
		//Files.write(output.getBytes(), new File("src/main/java/Resources/alternativesRanks.xml"));
		//return output;
	}
	 
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//TODO			
		}
}
