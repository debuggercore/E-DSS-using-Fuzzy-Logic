package com.controller.inter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.model.AJAXResponse;
import com.model.ElectivePredictObj;
import com.model.FileInfo;
import com.model.FileInfoTemp;
import com.model.RegisterUser;

public interface ElectiveCourseManageControllerIF {

	ModelAndView doLogin(HttpServletRequest request, RegisterUser registerUser);

	ModelAndView doLogout(HttpServletRequest request);

	ModelAndView registerUserInfo(RegisterUser registerUser,
			HttpServletRequest request);

	ModelAndView updateConstant(String locatorValue);

	ModelAndView addFileName(FileInfo fileInfo);

	AJAXResponse viewFileNames(HttpServletRequest request);

	AJAXResponse removeFileName(FileInfoTemp fileInfoTemp);

	AJAXResponse readDataSets(FileInfoTemp fileInfoTemp);

	ModelAndView predictElective(HttpServletRequest request,
			ElectivePredictObj electivePredictObj);

	ModelAndView predictElectiveFuzzy(HttpServletRequest request,
			ElectivePredictObj electivePredictObj);

	AJAXResponse doRecommendations(HttpServletRequest request);

}
