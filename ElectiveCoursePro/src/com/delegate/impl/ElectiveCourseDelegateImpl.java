package com.delegate.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.delegate.inter.ElectiveCourseDelegateIF;
import com.model.ElectivePredictObj;
import com.model.FileInfo;
import com.model.FileInfoTemp;
import com.model.RegisterUser;
import com.model.StatusInfo;
import com.service.inter.ElectiveCourseServiceIF;

public class ElectiveCourseDelegateImpl implements ElectiveCourseDelegateIF {

	@Autowired
	private ElectiveCourseServiceIF electiveCourseService;

	public ElectiveCourseServiceIF getElectiveCourseService() {
		return electiveCourseService;
	}

	public void setElectiveCourseService(
			ElectiveCourseServiceIF electiveCourseService) {
		this.electiveCourseService = electiveCourseService;
	}

	@Override
	public StatusInfo doRegistration(RegisterUser registerUser) {
		return electiveCourseService.doRegistration(registerUser);
	}

	@Override
	public StatusInfo checkLogin(RegisterUser registerUser) {
		return electiveCourseService.checkLogin(registerUser);
	}

	@Override
	public StatusInfo updateConstant(String locatorValue) {
		return electiveCourseService.updateConstant(locatorValue);
	}

	@Override
	public StatusInfo addFileName(FileInfo fileInfo) {
		return electiveCourseService.addFileName(fileInfo);
	}

	@Override
	public List<FileInfo> retriveFileInfo() {
		return electiveCourseService.retriveFileInfo();
	}

	@Override
	public StatusInfo removeFileName(FileInfoTemp fileInfoTemp) {
		return electiveCourseService.removeFileName(fileInfoTemp);
	}

	@Override
	public StatusInfo readDataSets(FileInfoTemp fileInfoTemp) {
		return electiveCourseService.readDataSets(fileInfoTemp);
	}

	@Override
	public StatusInfo predictElective(ElectivePredictObj electivePredictObj) {
		return electiveCourseService.predictElective(electivePredictObj);
	}

	@Override
	public StatusInfo predictElectiveFuzzy(ElectivePredictObj electivePredictObj) {
		return electiveCourseService.predictElectiveFuzzy(electivePredictObj);
	}

	@Override
	public StatusInfo performRecommendations(String userId) {
		return electiveCourseService.performRecommendations(userId);
	}

	


}
