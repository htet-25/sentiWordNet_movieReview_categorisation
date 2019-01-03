package com.thesis.model;

public class Word {
	
	String word;
	String grammar;
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getGrammar() {
		return grammar;
	}
	public void setGrammar(String grammar) {
		this.grammar = grammar;
	}
	
	public Word()
	{
		this.word = "";
		this.grammar = "";
	}

}
