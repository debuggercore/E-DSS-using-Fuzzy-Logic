package com.dao.inter;

import java.util.List;

import com.model.FileInfo;
import com.model.FileInfoTemp;
import com.model.PredictedValue;
import com.model.RankingModel;
import com.model.RegisterUser;
import com.model.StatusInfo;

public interface ElectiveCourseDAOIF {

	List<String> retriveUserIds();

	StatusInfo insertLogin(RegisterUser registerUser);

	String retrivePassword(String userId);

	int retriveLoginType(String userId);

	String retriveLocationValueFromLocatioName(String locationName);

	boolean updateLocationValueFromLocatioName(String locationName,
			String locationValue);

	List<FileInfo> retriveAllFileInfo();

	FileInfo findFileInfoByFileName(String fileName);

	StatusInfo insertFileName(FileInfo fileInfo);

	boolean deleteFileName(FileInfoTemp fileInfoTemp);

	String retrivePropertyFromConfig(String propertyName);

	FileInfo retriveFileInfoForElectiveName(String electiveName);

	StatusInfo insertPredict(PredictedValue predictedValue);

	List<PredictedValue> retriveAllPredictiveValues(String userId);

	StatusInfo insertRankingModel(List<RankingModel> rankingModelList);

	StatusInfo deleteRanking(String userId);

	List<RankingModel> rankElectives(String userId);
}
