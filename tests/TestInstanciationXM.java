package tests;

import javax.xml.parsers.ParserConfigurationException;

import services.InstanciationXM;

public class TestInstanciationXM {
	   public static void main(String argv[]) throws ParserConfigurationException {
		   InstanciationXM ix=new InstanciationXM();
		   ix.buildeXMInstance();
	   }
}
