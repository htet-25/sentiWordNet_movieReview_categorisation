package com.thesis.service;

import java.util.ArrayList;

import com.thesis.dao.SentiWordNetDao;
import com.thesis.model.SentiWordNet;
import com.thesis.model.Word;

public class CalculatePositiveNegativeService {
	
	public int getReviewWordCount(ArrayList<Word> worddatalist)
	{
		int tncount = 0;
		for (Word word : worddatalist) 
		{
			
			if(word.getGrammar().equals("jj")) 
			{
				
			}else if(word.getGrammar().equals("nn"))
			{
				
			}else if(word.getGrammar().equals("vb"))
			{
				
			}
			
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
									tncount += 1;	
								}
							}
							break;
						}
					}				
			}
		}
		return tncount;
	}
	

}
