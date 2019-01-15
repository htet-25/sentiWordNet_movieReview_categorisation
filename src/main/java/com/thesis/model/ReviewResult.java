package com.thesis.model;

public class ReviewResult {
	
	String sentence;
	String category;
	String type;
	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public ReviewResult()
	{
		this.sentence = "";
		this.category = "";
		this.type = "";
	}

}
