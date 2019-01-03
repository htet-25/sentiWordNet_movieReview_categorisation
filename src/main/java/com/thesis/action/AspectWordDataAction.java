package com.thesis.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "aspectWordDataAction")
@ViewScoped
public class AspectWordDataAction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Map<String,String> aspectCategory;
	String selectedcategory = "0";
	String aspectWord;
	
	 public String getSelectedcategory() {
		return selectedcategory;
	}

	public void setSelectedcategory(String selectedcategory) {
		this.selectedcategory = selectedcategory;
	}

	public void onCategoryChange()
	{
		if(!aspectCategory.equals("0"))
		{
			
		}
	}
	
	public String saveAspectWords()
	{
		
		return null;
	}
	
	@PostConstruct
	    public void init() {
		 aspectCategory = new HashMap<String,String>();
		 aspectCategory.put("Screenplay", "1");
		 aspectCategory.put("Music", "2");
		 aspectCategory.put("Acting", "3");
		 aspectCategory.put("Plot", "4");
		 aspectCategory.put("Movie", "5");
		 aspectCategory.put("Direction", "6");
	 }

	public Map<String, String> getAspectCategory() {
		return aspectCategory;
	}

	public void setAspectCategory(Map<String, String> aspectCategory) {
		this.aspectCategory = aspectCategory;
	}

	public String getAspectWord() {
		return aspectWord;
	}

	public void setAspectWord(String aspectWord) {
		this.aspectWord = aspectWord;
	}

}
