package io.github.oliviercailloux.y2017.xmcda.XMscheme;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
@RequestScoped
public class ParsingDescriptionUrl {
		
    public List<InputStruct> Parse(URL url, HttpServletRequest request) throws DocumentException {
    	List<InputStruct> inputs = new ArrayList<InputStruct>();
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        Element program = document.getRootElement().element("program");
        
        XM_scheme.setWebServiceName(program.attributeValue("name"));
        XM_scheme.setWebServiceProvider(program.attributeValue("provider"));

        Element parameters = document.getRootElement().element("parameters");
        // iterate through child elements of root with element name "input"
        for ( Iterator<?> i = parameters.elementIterator( "input" ); i.hasNext(); ) {
            Element input = (Element) i.next();
        	InputStruct inputDummy = new InputStruct();
            inputDummy.setId(input.attributeValue("id"));
            inputDummy.setName(input.attributeValue("name"));
            inputDummy.setDisplayName(input.attributeValue("displayName"));
            inputDummy.setIsoptional(input.attributeValue("isoptional"));
            inputs.add(inputDummy);
        }
        XM_scheme.getInputs().clear();
        XM_scheme.setInputs(inputs);
        return inputs;
    }
}
