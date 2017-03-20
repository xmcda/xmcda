package io.github.oliviercailloux.y2016.xmcdabis.entities;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author rafik
 *
 */
@SuppressWarnings("unused")
@RequestScoped

public class Hello implements Serializable{

	private static final long serialVersionUID = 1L;
	public Hello() {
		// TODO Auto-generated constructor stub
	}
	public String sayHello(String name) {
		// TODO Auto-generated method stub
		return "Hello Mr. "+ name;
	}

}
