package io.github.oliviercailloux.y2016.xmcda.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import io.github.oliviercailloux.xmcda_2_2_1_jaxb.ObjectFactory;
import io.github.oliviercailloux.xmcda_2_2_1_jaxb.X2Alternative;
import io.github.oliviercailloux.xmcda_2_2_1_jaxb.X2Alternatives;
import io.github.oliviercailloux.xmcda_2_2_1_jaxb.XMCDA;

/**
 * Servlet implementation class X2CreateAlternativeObject
 */
@WebServlet("/X2CreateAlternativeObject")
public class X2CreateAlternativeObject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public X2CreateAlternativeObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		createObjectX2(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	protected void createObjectX2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JAXBContext jc = null;
		try {
			jc = JAXBContext.newInstance(XMCDA.class);
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
		Marshaller marshaller = null;
		try {
			marshaller = jc.createMarshaller();
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
		final ObjectFactory f = new ObjectFactory();

		final X2Alternatives alternatives = f.createX2Alternatives();
		final X2Alternative alt = f.createX2Alternative();
		alt.setId("a01");
		alternatives.getDescriptionOrAlternative().add(alt);

		final XMCDA xmcda = f.createXMCDA();
		final List<JAXBElement<?>> xmcdaSubElements = xmcda.getProjectReferenceOrMethodMessagesOrMethodParameters();
		xmcdaSubElements.add(f.createXMCDAAlternatives(alternatives));

		try {
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		} catch (PropertyException e) {
			e.printStackTrace();
		}
		try {
			marshaller.marshal(xmcda, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

}
