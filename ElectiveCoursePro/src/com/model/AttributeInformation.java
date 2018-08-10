package com.model;

import java.io.Serializable;

public class AttributeInformation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String attributeName;
	
	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public boolean isElective() {
		return isElective;
	}

	public void setElective(boolean isElective) {
		this.isElective = isElective;
	}

	

	public String getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}



	private String attributeValue;
	
	private boolean isElective;
	
	private String isSelected;

}
