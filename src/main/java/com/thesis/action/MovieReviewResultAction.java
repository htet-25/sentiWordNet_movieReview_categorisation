package com.thesis.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.google.gson.Gson;
import com.thesis.model.ChartData;
import com.thesis.model.ReviewResult;

@ManagedBean(name = "mReviewResAction")
@ViewScoped
public class MovieReviewResultAction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	


	ArrayList<ReviewResult>reslist = new ArrayList<>();
	List <ChartData> chartDataList = new ArrayList<ChartData>();
	int count=0;
	public int positiveCount = 0;
	public int negativeCount = 0;
	String chartjson = "";
	int tsentence = 0;
	
	public String getChartjson() {
		return chartjson;
	}

	public void setChartjson(String chartjson) {
		this.chartjson = chartjson;
	}

	int catScreenplay=0;
	int catMusic = 0;
	int catActing = 0;
	int catPlot = 0;
	int catMovie = 0;
	int catDirection = 0;
	int catUntitle = 0;
	
	int ncatScreenplay=0;
	int ncatMusic = 0;
	int ncatActing = 0;
	int ncatPlot = 0;
	int ncatMovie = 0;
	int ncatDirection = 0;
	int nuntitle = 0;
	String analysis = "";

	
	public int getTsentence() {
		return tsentence;
	}

	public void setTsentence(int tsentence) {
		this.tsentence = tsentence;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArrayList<ReviewResult> getReslist() {
		return reslist;
	}

	public void setReslist(ArrayList<ReviewResult> reslist) {
		this.reslist = reslist;
	}

	
	
	public List<ChartData> getChartDataList() {
		return chartDataList;
	}

	public void setChartDataList(List<ChartData> chartDataList) {
		this.chartDataList = chartDataList;
	}

	
	public void calculatepCatCount(String cat)
	{
		if(cat.equals("Screenplay"))
			catScreenplay += 1;
		else if(cat.equals("Music"))
			catMusic += 1;
		else if(cat.equals("Movie"))
			catMovie += 1;
		else if(cat.equals("Acting"))
			catActing += 1;
		else if(cat.equals("Plot"))
			catPlot += 1;
		else if(cat.equals("Direction"))
			catDirection += 1;
		else catUntitle += 1;
	}
	
	public void calculatenCatCount(String cat)
	{
		if(cat.equals("Screenplay"))
			ncatScreenplay += 1;
		else if(cat.equals("Music"))
			ncatMusic += 1;
		else if(cat.equals("Movie"))
			ncatMovie += 1;
		else if(cat.equals("Acting"))
			ncatActing += 1;
		else if(cat.equals("Plot"))
			ncatPlot += 1;
		else if(cat.equals("Direction"))
			ncatDirection += 1;
		else nuntitle += 1;
	}


	public int getPositiveCount() {
		return positiveCount;
	}

	public void setPositiveCount(int positiveCount) {
		this.positiveCount = positiveCount;
	}

	public int getNegativeCount() {
		return negativeCount;
	}

	public void setNegativeCount(int negativeCount) {
		this.negativeCount = negativeCount;
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
    public void init() 
	{
		
		this.reslist = (ArrayList<ReviewResult>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("reviewResultList");
		this.tsentence = reslist.size();
		if(reslist != null)
		{
			for (ReviewResult reviewres : reslist) 
			{	
				if(reviewres.getType().equals("Positive"))
				{
					this.positiveCount += 1;
					calculatepCatCount(reviewres.getCategory());
				}
				else 
				{
					this.negativeCount += 1;
					calculatenCatCount(reviewres.getCategory());
				}
				
			}
			if(positiveCount < negativeCount)
				this.analysis = "Negative";
			else if(positiveCount > negativeCount)
				this.analysis = "Positive";
			else this.analysis = "Neutral";
		}
		
		ChartData catScreenplayData = new ChartData();
		catScreenplayData.setCategory("Screenplay");
		catScreenplayData.setPcount(catScreenplay);
		catScreenplayData.setNcount(ncatScreenplay);
		this.chartDataList.add(catScreenplayData);
		
		ChartData catMusicData = new ChartData();
		catMusicData.setCategory("Music");
		catMusicData.setNcount(ncatMusic);
		catMusicData.setPcount(catMusic);
		this.chartDataList.add(catMusicData);
		
		ChartData catMovieData = new ChartData();
		catMovieData.setCategory("Movie");
		catMovieData.setNcount(ncatMovie);
		catMovieData.setPcount(catMovie);
		this.chartDataList.add(catMovieData);
		
		ChartData catActingData = new ChartData();
		catActingData.setCategory("Acting");
		catActingData.setNcount(ncatActing);
		catActingData.setPcount(catActing);
		this.chartDataList.add(catActingData);
		ChartData catPlotData = new ChartData();
		catPlotData.setCategory("Plot");
		catPlotData.setNcount(ncatPlot);
		catPlotData.setPcount(catPlot);
		this.chartDataList.add(catPlotData);
		ChartData catDirectionData = new ChartData();
		catDirectionData.setCategory("Direction");
		catDirectionData.setNcount(ncatDirection);
		catDirectionData.setPcount(catDirection);
		this.chartDataList.add(catDirectionData);
		ChartData catUntitleData = new ChartData();
		catUntitleData.setCategory("Untitle");
		catUntitleData.setNcount(nuntitle);
		catUntitleData.setPcount(catUntitle);
		this.chartDataList.add(catUntitleData);

		chartjson = new Gson().toJson(chartDataList);		
	}



}
