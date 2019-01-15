package com.thesis.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.thesis.model.ReviewResult;
import com.thesis.model.Word;
import com.thesis.service.AspectWordService;
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
	int type = 0;
	Map<String,String> aspectCategory;
	String categorytype = "";
	ArrayList<ReviewResult> reslist = new ArrayList<>();



	public ArrayList<ReviewResult> getReslist() {
		return reslist;
	}

	public void setReslist(ArrayList<ReviewResult> reslist) {
		this.reslist = reslist;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@PostConstruct
	public void init()  
	{
		 aspectCategory = new HashMap<String,String>();
		 aspectCategory.put("Screenplay", "1");
		 aspectCategory.put("Music", "2");
		 aspectCategory.put("Acting", "3");
		 aspectCategory.put("Plot", "4");
		 aspectCategory.put("Movie", "5");
		 aspectCategory.put("Direction", "6");
	}
	
	@SuppressWarnings("rawtypes")
	public String calculate() throws ClassNotFoundException, IOException
	{
		
		String roothpath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/")+"models/english-left3words-distsim.tagger";
		int totalncount = 0;
		
		String[] eachSentence = review.split("\\.");
		
		for (String sentence : eachSentence) 
		{
			ReviewResult res = new ReviewResult();
			MaxentTagger tagger =  new MaxentTagger(roothpath);			
			String tagged = tagger.tagString(sentence);
						
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
					ArrayList<Word> aspectwordList = divideAspectWordlist(worddatalist);
					if(aspectwordList.size() > 0)
					{
						AspectWordService aspectService = new AspectWordService();
						type = aspectService.getCategorytypeByMaxWord(aspectwordList);
					}
					CalculatePositiveNegativeService calculateservice = new CalculatePositiveNegativeService();
					totalncount = calculateservice.getReviewWordCount(worddatalist);
				}
				
			}
			if(type != 0)
			{
				 Iterator<?> it = aspectCategory.entrySet().iterator();
				    while (it.hasNext()) 
				    {
				        Map.Entry pair = (Map.Entry)it.next();
				       if(pair.getValue().equals(String.valueOf(type)))
				       {
				    	   categorytype = (String) pair.getKey();
				    	   break;
				       }
				    }
			}
			
			res.setCategory(categorytype);
			res.setSentence(sentence);
			if(totalncount>0)
			{
				if(totalncount%2==0)
					res.setType("Positive");
				else
					res.setType("Negative");
			}else
				res.setType("Positive");
			reslist.add(res);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reviewResultList", reslist);

		}
					
		return "reviewRes";
	}
	
	public ArrayList<Word> divideAspectWordlist(ArrayList<Word> wordlist)
	{
		ArrayList<Word> reslist = new ArrayList<>();
		for (Word word : wordlist) 
		{
			if(word.getGrammar().equals("jj") || word.getGrammar().equals("nn") || word.getGrammar().equals("vb")) 
			{
				reslist.add(word);				
			}	
		}
		return reslist;
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
