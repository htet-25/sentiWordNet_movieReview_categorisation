package com.stc.centraldatatbase.util;

import java.io.Serializable;

public class SetupData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4681136410839323254L;
	String label;
	int value;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
	

}
