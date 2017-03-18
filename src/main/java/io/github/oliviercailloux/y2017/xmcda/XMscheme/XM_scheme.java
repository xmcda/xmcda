package io.github.oliviercailloux.y2017.xmcda.XMscheme;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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

import org.dom4j.DocumentException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.google.common.io.Resources;


/** @author Anis **/

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/xmcdaScheme" )
public class XM_scheme extends HttpServlet{
	private static String ENDPOINT_ADDRESS = "http://webservices.decision-deck.org/soap/";

	public String getENDPOINT_ADDRESS() {
		return ENDPOINT_ADDRESS;
	}

	public void setENDPOINT_ADDRESS(String eNDPOINT_ADDRESS) {
		ENDPOINT_ADDRESS += eNDPOINT_ADDRESS;
	}

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

	public  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<InputStruct> inputs = new ArrayList<InputStruct>();
		ParsingDescriptionUrl dsc = new ParsingDescriptionUrl();
		URL url = new URL("http://www.decision-deck.org/ws/_downloads/description-wsDD48.xml");
		try {
			inputs = dsc.Parse(url, request);
		} catch (DocumentException e1) {
		}
		HttpSession session = request.getSession();
		String attName = "input";
		int i = 1;
		for (InputStruct input : inputs)
		{
			attName = "input";
			attName += ""+i;
	        String inputItem = input.name;
	        session.setAttribute(attName, inputItem);
	        i++;
		}
        request.getRequestDispatcher("invokeService.jsp").forward(request, response);
        
		try {
			//ServiceInvoke(request, response);
		} catch (Exception e) {
		}
	}
	
	 public  void ServiceInvoke(HttpServletRequest request, HttpServletResponse response)
				throws Exception  
	{
	 
		final Service svc = Service.create(new QName("ServiceNamespace", "ServiceLocalPart"));
		final QName portQName = new QName("PortNamespace", "PortLocalPart");
		svc.addPort(portQName, SOAPBinding.SOAP11HTTP_BINDING, ENDPOINT_ADDRESS);
		final Dispatch<Source> dispatch = svc.createDispatch(portQName, Source.class, Mode.PAYLOAD);
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		final DocumentBuilder builder = factory.newDocumentBuilder();
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
		Node ret = invoke(dispatch, new DOMSource(doc));
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
		final Node solution = invoke(dispatch, new DOMSource(requestSolutionDoc));		
	}
	 
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//TODO			
		}
}
