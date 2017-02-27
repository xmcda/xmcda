package io.github.oliviercailloux.y2016.xmcda;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
public class InstanciationXM {

	public static void buildeXMInstance() throws ParserConfigurationException{
		 try {
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.newDocument();
	         // schema
	         Element xsschema = doc.createElement("xs:schema");
	         xsschema.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xs", "http://www.w3.org/2001/XMLSchema");
	         xsschema.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xm", "ressources/xsd");
	         

	         Attr att1 = doc.createAttribute("targetNamespace");
	         att1.setValue("src/main/webapp/fileRessources/data");
	         xsschema.setAttributeNode(att1);

	         Attr att2 = doc.createAttribute("xmlns:xac");
	         att2.setValue("src/main/webapp/fileRessources/Data");
	         xsschema.setAttributeNode(att2);

	         Attr att3 = doc.createAttribute("elementFormDefault");
	         att3.setValue("unqualified");
	         xsschema.setAttributeNode(att3);
	         
	         doc.appendChild(xsschema);

	         //import 
	         Element importus = doc.createElement("xs:import");
	         xsschema.appendChild(importus);
	         
	         // setting attribute to import
	         Attr impattr1 = doc.createAttribute("namespace");
	         impattr1.setValue("src/main/webapp/fileRessources/xsd");
	         importus.setAttributeNode(impattr1);
	         
	         Attr impattr2 = doc.createAttribute("namespace");
	         impattr2.setValue("src/main/webapp/fileRessources/xmcda-modular.xsd");
	         importus.setAttributeNode(impattr2);
	         
	         
	         //element
	         Element element = doc.createElement("xs:element");
	         xsschema.appendChild(element);

	         // setting attribute to element
	         Attr attr = doc.createAttribute("name");
	         attr.setValue("AtrrName");
	         element.setAttributeNode(attr);

	         // ComplexType
	         Element complextype = doc.createElement("xs:complexType");
	         element.appendChild(complextype);

	      // sequence
	         Element sequence = doc.createElement("xs:sequence");
	         complextype.appendChild(sequence);
	       
	         //element
	         Element element2 = doc.createElement("xs:element");
	         sequence.appendChild(element2);
	       
	         // setting attribute to element
	         Attr attr2 = doc.createAttribute("name");
	         attr2.setValue("AtrrName");
	         element2.setAttributeNode(attr2);
	         
	         Attr attr3 = doc.createAttribute("type");
	         attr3.setValue("typeName");
	         element2.setAttributeNode(attr3);
	
	         // write the content into xml file
	         TransformerFactory transformerFactory =
	         TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(doc);
	         StreamResult result = new StreamResult(new File("donnee.xsd"));
	         transformer.transform(source, result);
	         // Output to console for testing
	         StreamResult consoleResult = new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	
}