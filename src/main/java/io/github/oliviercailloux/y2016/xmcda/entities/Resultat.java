package io.github.oliviercailloux.y2016.xmcda.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the resultat database table.
 * 
 */
@Entity
@NamedQuery(name="Resultat.findAll", query="SELECT r FROM Resultat r")
public class Resultat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idresult;

	private String mailuser;

	private String resultwebservice;

	public Resultat() {
	}

	public int getIdresult() {
		return this.idresult;
	}

	public void setIdresult(int idresult) {
		this.idresult = idresult;
	}

	public String getMailuser() {
		return this.mailuser;
	}

	public void setMailuser(String mailuser) {
		this.mailuser = mailuser;
	}

	public String getResultwebservice() {
		return this.resultwebservice;
	}

	public void setResultwebservice(String resultwebservice) {
		this.resultwebservice = resultwebservice;
	}

}