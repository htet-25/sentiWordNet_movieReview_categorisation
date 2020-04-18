package com.thesis.model;

import java.io.Serializable;

public class ChartData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String category;
	int pcount;
	int ncount;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPcount() {
		return pcount;
	}
	public void setPcount(int pcount) {
		this.pcount = pcount;
	}
	public int getNcount() {
		return ncount;
	}
	public void setNcount(int ncount) {
		this.ncount = ncount;
	}
	
	public ChartData ()
	{
		this.category = "";
		this.pcount = 0;
		this.ncount = 0;
	}

}
