package com.model;

import java.io.Serializable;

public class ElectivePredictObj implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String subject1;
	private String subject1Name;
	private String subject2;
	private String subject2Name;	
	private String electiveName;
	private String userId;
	private String electiveNamePart1;
	private String electiveNamePart2;	
	private String pageName;	
	private String filePath1;	
	private String filePath2;

	public String getSubject1() {
		return subject1;
	}

	public void setSubject1(String subject1) {
		this.subject1 = subject1;
	}

	public String getSubject1Name() {
		return subject1Name;
	}

	public void setSubject1Name(String subject1Name) {
		this.subject1Name = subject1Name;
	}

	public String getSubject2() {
		return subject2;
	}

	public void setSubject2(String subject2) {
		this.subject2 = subject2;
	}

	public String getSubject2Name() {
		return subject2Name;
	}

	public void setSubject2Name(String subject2Name) {
		this.subject2Name = subject2Name;
	}	

	public String getElectiveName() {
		return electiveName;
	}

	public void setElectiveName(String electiveName) {
		this.electiveName = electiveName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getElectiveNamePart1() {
		return electiveNamePart1;
	}

	public void setElectiveNamePart1(String electiveNamePart1) {
		this.electiveNamePart1 = electiveNamePart1;
	}

	public String getElectiveNamePart2() {
		return electiveNamePart2;
	}

	public void setElectiveNamePart2(String electiveNamePart2) {
		this.electiveNamePart2 = electiveNamePart2;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getFilePath1() {
		return filePath1;
	}

	public void setFilePath1(String filePath1) {
		this.filePath1 = filePath1;
	}

	public String getFilePath2() {
		return filePath2;
	}

	public void setFilePath2(String filePath2) {
		this.filePath2 = filePath2;
	}
}
