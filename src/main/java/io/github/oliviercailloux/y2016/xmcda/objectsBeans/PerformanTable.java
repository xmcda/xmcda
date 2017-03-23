package io.github.oliviercailloux.y2016.xmcda.objectsBeans;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class PerformanTable {
	private String type;
	private String utilite;
	public PerformanTable() {
		// TODO Auto-generated constructor stub
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUtilite() {
		return utilite;
	}
	public void setUtilite(String utilite) {
		this.utilite = utilite;
	}

}
