package io.github.oliviercailloux.y2017.xmcda.XMscheme;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.google.common.io.Resources;

/** @author Anis **/

@RequestScoped
public class XM_scheme {
	private static String ENDPOINT_ADDRESS = "http://webservices.decision-deck.org/soap/";

	private static List<InputStruct> inputs = new ArrayList<InputStruct>();
	private static String webServiceName;
	private static String webServiceProvider;

	public static String getENDPOINT_ADDRESS() {
		return ENDPOINT_ADDRESS;
	}

	public static void setENDPOINT_ADDRESS(String eNDPOINT_ADDRESS) {
		ENDPOINT_ADDRESS += eNDPOINT_ADDRESS;
	}

	public static List<InputStruct> getInputs() {
		return inputs;
	}

	public static void setInputs(List<InputStruct> inputs) {
		XM_scheme.inputs = inputs;
	}

	public static String getWebServiceName() {
		return webServiceName;
	}

	public static void setWebServiceName(String webServiceName) {
		XM_scheme.webServiceName = webServiceName;
	}

	public static String getWebServiceProvider() {
		return webServiceProvider;
	}

	public static void setWebServiceProvider(String webServiceProvider) {
		XM_scheme.webServiceProvider = webServiceProvider;
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
		getTransformer().transform(ret, result);
		final Node resultNode = result.getNode();
		return resultNode;
	}

	public void setFileContentToNodeValue(String sourceFile, Node destNode) throws IOException {
		final URL resUrl = getClass().getResource(sourceFile);
		// final URL resUrl = new File(sourceFile).toURI().toURL();
		final String resStr = Resources.toString(resUrl, StandardCharsets.UTF_8);
		final Text textNode = destNode.getOwnerDocument().createTextNode(resStr);
		destNode.appendChild(textNode);
	}

	public void ServiceInvoke(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final Service svc = Service.create(new QName("ServiceNamespace", "ServiceLocalPart"));
		final QName portQName = new QName("PortNamespace", "PortLocalPart");
		setENDPOINT_ADDRESS(webServiceName);
		setENDPOINT_ADDRESS("-");
		setENDPOINT_ADDRESS(webServiceProvider);
		setENDPOINT_ADDRESS(".py");
		svc.addPort(portQName, SOAPBinding.SOAP11HTTP_BINDING, ENDPOINT_ADDRESS);
		ENDPOINT_ADDRESS = "http://webservices.decision-deck.org/soap/";
		final Dispatch<Source> dispatch = svc.createDispatch(portQName, Source.class, Mode.PAYLOAD);
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		final DocumentBuilder builder = factory.newDocumentBuilder();
		final String ticket = null;
		final Document doc = builder.newDocument();
		final Element submit = doc.createElement("submitProblem");
		submit.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
		List<Element> subs = new ArrayList<Element>();
		for (int i = 0; i < getInputs().size(); i++) {
			// for now, we handle only the obligatory inputs
			if (getInputs().get(i).getIsoptional().equals("0")) {
				subs.add(doc.createElement(getInputs().get(i).getName()));
			}
		}
		doc.appendChild(submit);
		// for (int i = 0; i < subs.size(); i++) {
		// submit.appendChild(subs.get(i));
		// }
		for (int i = 0; i < subs.size(); i++) {
			String content = subs.get(i).getTagName();
			content += ".xml";
			setFileContentToNodeValue(content, subs.get(i));
		}
		//
		// setFileContentToNodeValue("C:/Users/Anis/Desktop/alternatives.xml",
		// subs.get(0));
		// setFileContentToNodeValue("C:/Users/Anis/Desktop/overallValues.xml",
		// subs.get(1));

		final Attr attrType1 = doc.createAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:type");
		attrType1.setValue("xsd:string");
		subs.get(0).setAttributeNodeNS(attrType1);
		for (int i = 1; i < subs.size(); i++) {
			final Attr attrTypeTemp = (Attr) attrType1.cloneNode(true);
			subs.get(i).setAttributeNodeNS(attrTypeTemp);
		}
		Node ret = invoke(dispatch, new DOMSource(doc));
		final NodeList directChildren = ret.getChildNodes();
		final Node firstChild = directChildren.item(0);
		final NodeList subChildren = firstChild.getChildNodes();
		final Node secondSubChild = subChildren.item(1);
		// ticket = secondSubChild.getFirstChild().getTextContent();
		final Document requestSolutionDoc = builder.newDocument();
		final Element requestSolution = requestSolutionDoc.createElement("requestSolution");
		requestSolution.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsd",
				"http://www.w3.org/2001/XMLSchema");
		final Element ticketEl = requestSolutionDoc.createElement("ticket");
		requestSolutionDoc.appendChild(requestSolution);
		requestSolution.appendChild(ticketEl);
		final Text ticketTextNode = requestSolutionDoc.createTextNode(ticket);
		ticketEl.appendChild(ticketTextNode);
		final Attr attrType = requestSolutionDoc.createAttributeNS("http://www.w3.org/2001/XMLSchema-instance",
				"xsi:type");
		attrType.setValue("xsd:string");
		ticketEl.setAttributeNodeNS(attrType);
		final Node solution = invoke(dispatch, new DOMSource(requestSolutionDoc));
		request.setAttribute("result", asString(solution));
	}
}