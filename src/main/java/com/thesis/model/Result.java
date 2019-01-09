package com.thesis.model;

public class Result {
	
	int type;
	int tncount;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getTncount() {
		return tncount;
	}
	public void setTncount(int tncount) {
		this.tncount = tncount;
	}
	
	public Result()
	{
		this.type = 0;
		this.tncount = 0;
	}

}
