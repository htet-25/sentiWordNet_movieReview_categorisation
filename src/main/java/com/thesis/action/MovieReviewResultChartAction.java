package com.thesis.action;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.thesis.model.ReviewResult;

@ManagedBean(name = "mReviewResChartAction")
@ViewScoped
public class MovieReviewResultChartAction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ReviewResult>reslist = new ArrayList<>();
	
	public ArrayList<ReviewResult> getReslist() {
		return reslist;
	}

	public void setReslist(ArrayList<ReviewResult> reslist) {
		this.reslist = reslist;
	}

	

	@PostConstruct
    public void init() {
		this.reslist = (ArrayList<ReviewResult>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("reviewResultList");
		if(reslist != null)
		{
			
		}
	}

}
