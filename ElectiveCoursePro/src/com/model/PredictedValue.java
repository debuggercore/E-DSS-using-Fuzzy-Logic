package com.model;

import java.io.Serializable;

public class PredictedValue implements Serializable {

	private int predictionId;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String subject1Name;

	public String getSubject1Name() {
		return subject1Name;
	}

	public void setSubject1Name(String subject1Name) {
		this.subject1Name = subject1Name;
	}

	public String getSubject2Name() {
		return subject2Name;
	}

	public void setSubject2Name(String subject2Name) {
		this.subject2Name = subject2Name;
	}

	public String getSubject3Name() {
		return subject3Name;
	}

	public void setSubject3Name(String subject3Name) {
		this.subject3Name = subject3Name;
	}

	public String getSubject4Name() {
		return subject4Name;
	}

	public void setSubject4Name(String subject4Name) {
		this.subject4Name = subject4Name;
	}

	public String getSubject1() {
		return subject1;
	}

	public void setSubject1(String subject1) {
		this.subject1 = subject1;
	}

	public String getSubject2() {
		return subject2;
	}

	public void setSubject2(String subject2) {
		this.subject2 = subject2;
	}

	public String getSubject3() {
		return subject3;
	}

	public void setSubject3(String subject3) {
		this.subject3 = subject3;
	}

	public String getSubject4() {
		return subject4;
	}

	public void setSubject4(String subject4) {
		this.subject4 = subject4;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getPredictedCapable() {
		return predictedCapable;
	}

	public void setPredictedCapable(String predictedCapable) {
		this.predictedCapable = predictedCapable;
	}

	public String getElectiveCourse() {
		return electiveCourse;
	}

	public void setElectiveCourse(String electiveCourse) {
		this.electiveCourse = electiveCourse;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCog() {
		return cog;
	}

	public void setCog(String cog) {
		this.cog = cog;
	}

	public String getFirstFuzzyOutputCog() {
		return firstFuzzyOutputCog;
	}

	public void setFirstFuzzyOutputCog(String firstFuzzyOutputCog) {
		this.firstFuzzyOutputCog = firstFuzzyOutputCog;
	}

	public String getSecondFuzzyOutputCog() {
		return secondFuzzyOutputCog;
	}

	public void setSecondFuzzyOutputCog(String secondFuzzyOutputCog) {
		this.secondFuzzyOutputCog = secondFuzzyOutputCog;
	}

	public int getPredictionId() {
		return predictionId;
	}

	public void setPredictionId(int predictionId) {
		this.predictionId = predictionId;
	}

	private String subject2Name;

	private String subject3Name;

	private String subject4Name;

	private String subject1;

	private String subject2;

	private String subject3;

	private String subject4;

	private boolean status;

	private String predictedCapable;

	private String electiveCourse;

	private String userId;

	private String cog;

	private String firstFuzzyOutputCog;

	private String secondFuzzyOutputCog;

}
