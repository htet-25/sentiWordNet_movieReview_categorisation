package com.thesis.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.thesis.model.Word;
import com.thesis.service.CalculatePositiveNegativeService;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;



@ManagedBean(name = "calculatePositiveNegativeAction")
@ViewScoped
public class CalculatePositeNegativeAction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8960748410953236774L;
	
	String review;
	
	




	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@PostConstruct
	public void init()  
	{
	
	}
	
	public String calculate() throws ClassNotFoundException, IOException
	{
		
		String roothpath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/")+"models/english-left3words-distsim.tagger";
		int totalncount = 0;
		MaxentTagger tagger =  new MaxentTagger(roothpath);
		String tagged = tagger.tagString(review);
		
		
		if(!tagged.equals(""))
		{
			ArrayList<Word> worddatalist = new ArrayList<>();
			tagged = tagged.toLowerCase();
			String[]wordlist = tagged.split(" ");
			for(int i=0; i<wordlist.length; i++)
			{
				Word word = new Word();
				String [] w = wordlist[i].split("/");
				word.setGrammar(w[1]);
				word.setWord(w[0]);
				worddatalist.add(word);
			}
		if(worddatalist.size() > 0)
		{
			CalculatePositiveNegativeService calculateservice = new CalculatePositiveNegativeService();
			totalncount = calculateservice.getReviewWordCount(worddatalist);
		}
			
		}
		if(totalncount>0)
		{
			if(totalncount%2==0)
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO!", "This Review is positive!"));
			else
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO!", "This Review is negative!"));
		}else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO!", "This Review is positive!"));
		
		return null;
	}
	
	public static void main(String[]args) throws ClassNotFoundException, IOException
	{
		/*String roothpath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/")+"models/english-left3words-distsim.tagger";
		String a = "I like watching movies";
		MaxentTagger tagger =  new MaxentTagger(roothpath);
		String tagged = tagger.tagString(a);
		System.out.println(tagged);*/
		//String taggedStr = tagger.tagString("The �Java Decompiler project� aims to develop tools in order to decompile and analyze");
	

	}

	
}
