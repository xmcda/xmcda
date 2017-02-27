package xmcda;

import io.github.xmcda_modular.y2016.jaxb.Alternative;
import io.github.xmcda_modular.y2016.jaxb.ObjectFactory;

public class TestClass {
	
	public void createObject(){
		ObjectFactory factory = new ObjectFactory();
		Alternative alt = factory.createAlternative();
	}

}
