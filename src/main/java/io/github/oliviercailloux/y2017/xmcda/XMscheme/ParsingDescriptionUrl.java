package io.github.oliviercailloux.y2017.xmcda.XMscheme;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParsingDescriptionUrl {
		
    public List<InputStruct> Parse(URL url, HttpServletRequest request) throws DocumentException {
    	List<InputStruct> inputs = new ArrayList<InputStruct>();
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        Element program = document.getRootElement().element("program");
        XM_scheme xm = new XM_scheme();
        xm.setENDPOINT_ADDRESS(program.attributeValue("name"));
        xm.setENDPOINT_ADDRESS("-");
        xm.setENDPOINT_ADDRESS(program.attributeValue("provider"));
        xm.setENDPOINT_ADDRESS(".py");
        // a-t-on besoin de cette partie ?
		HttpSession session = request.getSession();
        session.setAttribute("serviceName", program.attributeValue("name"));
        session.setAttribute("providerName", program.attributeValue("provider"));
        Element parameters = document.getRootElement().element("parameters");
        // iterate through child elements of root with element name "input"
        for ( Iterator<?> i = parameters.elementIterator( "input" ); i.hasNext(); ) {
            Element input = (Element) i.next();
        	InputStruct inputDummy = new InputStruct();
            inputDummy.id = input.attributeValue("id");
            inputDummy.name = input.attributeValue("name");
            inputDummy.displayName = input.attributeValue("displayName");
            inputDummy.isoptional = input.attributeValue("isoptional");
            inputs.add(inputDummy);
        }
        return inputs;
    }
}
