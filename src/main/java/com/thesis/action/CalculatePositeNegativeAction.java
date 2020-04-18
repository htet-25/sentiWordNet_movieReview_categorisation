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
import scala.actors.threadpool.Arrays;



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
		int ncount = 0;
		int tsentence = 0;
	
		review.replace("?", "\\.");
		String[] eachSentence = review.split("(\\?)|(\\.)");
		String[] negativeWordlist = {"am not","is not","isn't","are not","aren't","ain't","was not","wasn't","were","weren't","has not","hasn't",
				"have not","haven't","had not","hadn't","do not","don't","does not","doesn't","did not",
				"didn't","cannot","can't","couldn't","shall not","could not","should not","shouldn't",
				"will not","won't","may not","nobody","neither","none","nothing","non","no","not",
				"mayn't","might not","mightn't","must not","mustn't","seldom","never","hardly","rarely"};
		tsentence = eachSentence.length;
		for (String sentence : eachSentence) 
		{
			if(sentence.length()>3)
			{
				String lowersentence = sentence.toLowerCase();
				String[] originalwordlist = lowersentence.split(" ");
				ncount = countNegativeWordlist(originalwordlist, negativeWordlist);
						
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
							if(w[0].length()>3)
							{
								word.setGrammar(w[1]);
								word.setWord(w[0]);
								worddatalist.add(word);
							}
							
						}
					if(worddatalist.size() > 0)
					{
						ArrayList<Word> aspectwordList = divideAspectWordlist(worddatalist,"category");
						if(aspectwordList.size() > 0)
						{
							AspectWordService aspectService = new AspectWordService();
							type = aspectService.getCategorytypeByMaxWord(aspectwordList);
						}
						CalculatePositiveNegativeService calculateservice = new CalculatePositiveNegativeService();
						aspectwordList = divideAspectWordlist(worddatalist, "type");
						totalncount = calculateservice.getReviewWordCount(aspectwordList);
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
					if(ncount > 0)
					{
						if(ncount%2 == 0)
						{
							if(totalncount%2==0)
								res.setType("Positive");
							else
								res.setType("Negative");
						}else res.setType("Negative");
					}else
					{
						if(totalncount%2==0)
							res.setType("Positive");
						else
							res.setType("Negative");
					}
					
					
				}else
				{
					if(ncount > 0)
					{
						if(ncount%2 == 0)
						{
							res.setType("Positive");
						}else res.setType("Negative");

					}else res.setType("Positive");
					
				}
					
				reslist.add(res);
			}
			
			
			
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reviewResultList", reslist);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("tsentence", tsentence);

		}
					
		return "reviewRes";
	}
	
	public int countNegativeWordlist(String[]negativewords, String[] dlist)
	{
		int res = 0;
		ArrayList<String> defaultlist = new ArrayList<>();
		defaultlist.addAll(Arrays.asList(dlist));
		ArrayList<String> negativeWordlist = new ArrayList<>();
		negativeWordlist.addAll(Arrays.asList(negativewords));
		defaultlist.retainAll(negativeWordlist);
		res = defaultlist.size();
		
		return res;
	}
	
	public ArrayList<Word> divideAspectWordlist(ArrayList<Word> wordlist,String divideType)
	{
		ArrayList<Word> reslist = new ArrayList<>();
		if(divideType.equals("category"))
		{
			for (Word word : wordlist) 
			{
				if(word.getGrammar().equals("nn") || word.getGrammar().equals("vb")) 
				{
					reslist.add(word);				
				}	
			}
		}else if(divideType.equals("type"))
		{
			for (Word word : wordlist) 
			{
				if(word.getGrammar().equals("jj") || word.getGrammar().equals("rb")) 
				{
					reslist.add(word);				
				}
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
//	String rs = "How about this movie ? I like it. There are four.";
//	rs.replaceAll("\\?", ".");
//	String[] res = rs.split("(\\?)|(\\.)");
//	System.out.print(res);

	}

	
}
