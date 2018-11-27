package com.stc.centraldatabase.model.user;

public class SentiWordNet {
	
	int id;
	String poscore;
	String nescore;
	String terms;
	String gloss;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPoscore() {
		return poscore;
	}
	public void setPoscore(String poscore) {
		this.poscore = poscore;
	}
	public String getNescore() {
		return nescore;
	}
	public void setNescore(String nescore) {
		this.nescore = nescore;
	}
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}
	public String getGloss() {
		return gloss;
	}
	public void setGloss(String gloss) {
		this.gloss = gloss;
	}
	
	public SentiWordNet()
	{
		this.id = 0;
		this.poscore = "";
		this.nescore = "";
		this.terms = "";
		this.gloss = "";
	}

}
