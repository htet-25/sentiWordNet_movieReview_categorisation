package com.stc.centraldatabase.model.user;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

public class AspectWord {
	int id;
	int type;
	String aspectWord;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAspectWord() {
		return aspectWord;
	}
	public void setAspectWord(String aspectWord) {
		this.aspectWord = aspectWord;
	}
	
	public AspectWord()
	{
		this.id = 0;
		this.type = 0;
		this.aspectWord = "";
	}

}
