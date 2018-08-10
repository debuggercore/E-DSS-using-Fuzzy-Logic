package com.service.inter;

import java.util.List;

import com.model.ElectivePredictObj;
import com.model.FileInfo;
import com.model.FileInfoTemp;
import com.model.PredictedValue;
import com.model.RegisterUser;
import com.model.StatusInfo;

public interface ElectiveCourseServiceIF {

	StatusInfo doRegistration(RegisterUser registerUser);

	StatusInfo checkLogin(RegisterUser registerUser);

	StatusInfo updateConstant(String locatorValue);

	StatusInfo addFileName(FileInfo fileInfo);

	List<FileInfo> retriveFileInfo();

	StatusInfo removeFileName(FileInfoTemp fileInfoTemp);

	StatusInfo readDataSets(FileInfoTemp fileInfoTemp);

	StatusInfo predictElective(ElectivePredictObj electivePredictObj);

	StatusInfo predictElectiveFuzzy(ElectivePredictObj electivePredictObj);

	StatusInfo performRecommendations(String userId); 

}
