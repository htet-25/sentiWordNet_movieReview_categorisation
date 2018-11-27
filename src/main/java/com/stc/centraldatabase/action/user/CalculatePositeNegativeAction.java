package com.stc.centraldatabase.action.user;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.stc.centraldatabase.dao.user.SentiWordNetDao;
import com.stc.centraldatabase.model.user.SentiWordNet;
import com.stc.centraldatabase.model.user.Word;

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
		String ans = null;
		String roothpath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/")+"models/english-left3words-distsim.tagger";
		int totalpcount = 0;
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
			for (Word word : worddatalist) {
				SentiWordNetDao wordnetDao = new SentiWordNetDao();
				ArrayList<SentiWordNet> wordnetList = wordnetDao.getWordList(word.getWord());
				for (SentiWordNet sentiWordNet : wordnetList) {
					String[] originalwords = sentiWordNet.getTerms().split(" ");
				
						for(int j=0; j<originalwords.length; j++)
						{
							String[] originalword = originalwords[j].split("#");
							if(word.getWord().equals(originalword[0]))
							{
								if(!sentiWordNet.getNescore().equals("0") || !sentiWordNet.getPoscore().equals("0"))
								{
									if(Double.parseDouble(sentiWordNet.getNescore()) > Double.parseDouble(sentiWordNet.getPoscore()))
									{
										totalncount += 1;	
									}else if(Double.parseDouble(sentiWordNet.getPoscore()) > Double.parseDouble(sentiWordNet.getNescore()))
									{
										totalpcount += 1;
									}
								}
								break;
							}
						}				
				}
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
		//String taggedStr = tagger.tagString("The “Java Decompiler project” aims to develop tools in order to decompile and analyze");
	

	}

	
}
