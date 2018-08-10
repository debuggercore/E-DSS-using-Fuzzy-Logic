package com.model;

import java.io.Serializable;

public class RankingModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String electiveName;
	
	public String getElectiveName() {
		return electiveName;
	}

	public void setElectiveName(String electiveName) {
		this.electiveName = electiveName;
	}

	public Double getCog() {
		return cog;
	}

	public void setCog(Double cog) {
		this.cog = cog;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private Double cog;
	
	private String userId;

}
