package com.stc.centraldatabase.action.user;

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
	String selectedcategory;
	String aspectWord;
	
	 public String getSelectedcategory() {
		return selectedcategory;
	}

	public void setSelectedcategory(String selectedcategory) {
		this.selectedcategory = selectedcategory;
	}

	@PostConstruct
	    public void init() {
		 aspectCategory = new HashMap<String,String>();
		 aspectCategory.put("1", "Screenplay");
		 aspectCategory.put("2", "Music");
		 aspectCategory.put("3", "Acting");
		 aspectCategory.put("4", "Plot");
		 aspectCategory.put("5", "Movie");
		 aspectCategory.put("6", "Direction");
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
