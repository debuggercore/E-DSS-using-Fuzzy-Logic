package com.controller.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.constants.ElectiveCourseConstantsIF;
import com.controller.inter.ElectiveCourseManageControllerIF;
import com.delegate.inter.ElectiveCourseDelegateIF;
import com.model.AJAXResponse;
import com.model.ElectivePredictObj;
import com.model.FileInfo;
import com.model.FileInfoTemp;
import com.model.GlobalRowInfo;
import com.model.Message;
import com.model.PredictedValue;
import com.model.RankingModel;
import com.model.RegisterUser;
import com.model.RegisterVerifyMsgs;
import com.model.StatusInfo;

@Controller
public class ElectiveCourseControllerImpl implements
		ElectiveCourseManageControllerIF {

	@Autowired
	private ElectiveCourseDelegateIF electiveCourseDelegate;

	public ElectiveCourseDelegateIF getElectiveCourseDelegate() {
		return electiveCourseDelegate;
	}

	public void setElectiveCourseDelegate(
			ElectiveCourseDelegateIF electiveCourseDelegate) {
		this.electiveCourseDelegate = electiveCourseDelegate;
	}

	@Override
	@RequestMapping(value = "/login.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView doLogin(HttpServletRequest request,
			@ModelAttribute RegisterUser registerUser) {

		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			String userId = registerUser.getUserId();

			if (null == userId || userId.isEmpty()
					|| userId.trim().length() == 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message(
						ElectiveCourseConstantsIF.Message.USERID_EMPTY);
				msg.setErrMessage(ElectiveCourseConstantsIF.Message.USERID_EMPTY);

				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(
						ElectiveCourseConstantsIF.Views.VIEW_LOGIN_INPUT,
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxResponse);
			}

			String password = registerUser.getUserPassword();

			if (null == password || password.isEmpty()
					|| password.trim().length() == 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message(
						ElectiveCourseConstantsIF.Message.PASSWORD_EMPTY);
				msg.setErrMessage(ElectiveCourseConstantsIF.Message.PASSWORD_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(
						ElectiveCourseConstantsIF.Views.VIEW_LOGIN_INPUT,
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxResponse);
			}

			StatusInfo statusInfo = electiveCourseDelegate
					.checkLogin(registerUser);

			if (!statusInfo.isStatus()) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message(statusInfo.getErrMsg());
				msg.setErrMessage(statusInfo.getErrMsg());
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return new ModelAndView(
						ElectiveCourseConstantsIF.Views.VIEW_LOGIN_INPUT,
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxResponse);
			} else if (statusInfo.isStatus()) {

				HttpSession session = request.getSession(true);

				session.setAttribute(
						ElectiveCourseConstantsIF.Keys.LOGINID_SESSION,
						registerUser.getUserId());
				session.setAttribute(
						ElectiveCourseConstantsIF.Keys.LOGINTYPE_SESSION,
						statusInfo.getType());

				if (statusInfo.getType() == ElectiveCourseConstantsIF.Keys.ADMIN_TYPE) {
					ajaxResponse = new AJAXResponse();
					ajaxResponse.setStatus(true);
					Message msg = new Message(
							ElectiveCourseConstantsIF.Message.USERREGISTERED_SUCESS_MSG);
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajaxResponse.setEbErrors(ebErrors);
					return new ModelAndView(
							ElectiveCourseConstantsIF.Views.VIEW_ADMIN_WELCOMEPAGE,
							ElectiveCourseConstantsIF.Keys.OBJ, ajaxResponse);
				} else if (statusInfo.getType() == ElectiveCourseConstantsIF.Keys.CUSTOMER_TYPE) {
					return new ModelAndView(
							ElectiveCourseConstantsIF.Views.VIEW_P_WELCOMEPAGE,
							ElectiveCourseConstantsIF.Keys.OBJ, ajaxResponse);
				} else {
					ajaxResponse = new AJAXResponse();
					ajaxResponse.setStatus(true);
					Message msg = new Message(
							ElectiveCourseConstantsIF.Message.USERREGISTERED_SUCESS_MSG);
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajaxResponse.setEbErrors(ebErrors);
					return new ModelAndView(
							ElectiveCourseConstantsIF.Views.VIEW_CUSTOMER_WELCOMEPAGE,
							ElectiveCourseConstantsIF.Keys.OBJ, ajaxResponse);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message(
					ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return new ModelAndView(
					ElectiveCourseConstantsIF.Views.VIEW_LOGIN_INPUT,
					ElectiveCourseConstantsIF.Keys.OBJ, ajaxResponse);
		}
		return null;

	}

	@Override
	@RequestMapping(value = "/logout.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView doLogout(HttpServletRequest request) {
		try {

			HttpSession session = request.getSession(false);

			session.invalidate();
			return new ModelAndView(
					ElectiveCourseConstantsIF.Views.APPLICATION_WELCOME_PAGE,
					ElectiveCourseConstantsIF.Keys.OBJ, null);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return new ModelAndView(
					ElectiveCourseConstantsIF.Views.APPLICATION_WELCOME_PAGE,
					ElectiveCourseConstantsIF.Keys.OBJ, null);
		}
	}

	@Override
	@RequestMapping(value = "/registerUser.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView registerUserInfo(
			@ModelAttribute RegisterUser registerUser,
			HttpServletRequest request) {
		RegisterVerifyMsgs registerVerifyMsgs = new RegisterVerifyMsgs();

		try {

			// Adding default login type as customer
			registerUser
					.setLoginType(ElectiveCourseConstantsIF.Keys.CUSTOMER_TYPE);

			String firstName = registerUser.getFirstName();
			if (null == firstName || firstName.isEmpty()
					|| firstName.trim().length() == 0) {

				registerVerifyMsgs
						.setFirstNameErrMsg(ElectiveCourseConstantsIF.Message.FIRSTNAME_EMPTY);

				return new ModelAndView(
						ElectiveCourseConstantsIF.Views.VIEW_REGISTER_INPUT,
						ElectiveCourseConstantsIF.Keys.OBJ, registerVerifyMsgs);

			}
			String lastName = registerUser.getLastName();
			if (null == lastName || lastName.isEmpty()
					|| lastName.trim().length() == 0) {

				registerVerifyMsgs
						.setLastNameErrMsg(ElectiveCourseConstantsIF.Message.LASTNAME_EMPTY);

				return new ModelAndView(
						ElectiveCourseConstantsIF.Views.VIEW_REGISTER_INPUT,
						ElectiveCourseConstantsIF.Keys.OBJ, registerVerifyMsgs);
			}

			String userId = registerUser.getUserId();

			if (null == userId || userId.isEmpty()
					|| userId.trim().length() == 0) {

				registerVerifyMsgs
						.setUserNameErrMsg(ElectiveCourseConstantsIF.Message.USERID_EMPTY);

				return new ModelAndView(
						ElectiveCourseConstantsIF.Views.VIEW_REGISTER_INPUT,
						ElectiveCourseConstantsIF.Keys.OBJ, registerVerifyMsgs);
			}

			String email = registerUser.getEmailId();

			if (null == email || email.isEmpty() || email.trim().length() == 0) {

				registerVerifyMsgs
						.setEmailErrMsg(ElectiveCourseConstantsIF.Message.EMAIL_EMPTY);

				return new ModelAndView(
						ElectiveCourseConstantsIF.Views.VIEW_REGISTER_INPUT,
						ElectiveCourseConstantsIF.Keys.OBJ, registerVerifyMsgs);
			}

			String password = registerUser.getUserPassword();

			if (null == password || password.isEmpty()
					|| password.trim().length() == 0) {

				registerVerifyMsgs
						.setPasswordErrMsg(ElectiveCourseConstantsIF.Message.PASSWORD_EMPTY);

				return new ModelAndView(
						ElectiveCourseConstantsIF.Views.VIEW_REGISTER_INPUT,
						ElectiveCourseConstantsIF.Keys.OBJ, registerVerifyMsgs);
			}

			int sex = registerUser.getSex();

			if (sex <= 0) {

				registerVerifyMsgs
						.setAgeErrMsg(ElectiveCourseConstantsIF.Message.SEX_ERR_MSG);

				return new ModelAndView(
						ElectiveCourseConstantsIF.Views.VIEW_REGISTER_INPUT,
						ElectiveCourseConstantsIF.Keys.OBJ, registerVerifyMsgs);

			}

			String dob = registerUser.getDob();
			if (null == dob || dob.isEmpty() || dob.trim().length() == 0) {

				registerVerifyMsgs
						.setAgeErrMsg(ElectiveCourseConstantsIF.Message.DOB_ERR_MSG);

				return new ModelAndView(
						ElectiveCourseConstantsIF.Views.VIEW_REGISTER_INPUT,
						ElectiveCourseConstantsIF.Keys.OBJ, registerVerifyMsgs);

			}

			StatusInfo statusInfo = electiveCourseDelegate
					.doRegistration(registerUser);

			if (!statusInfo.isStatus()) {

				registerVerifyMsgs.setSeverMessage(statusInfo.getErrMsg());

				return new ModelAndView(
						ElectiveCourseConstantsIF.Views.VIEW_REGISTER_INPUT,
						ElectiveCourseConstantsIF.Keys.OBJ, registerVerifyMsgs);
			}

			registerVerifyMsgs
					.setSucessMsg(ElectiveCourseConstantsIF.Message.USERREGISTERED_SUCESS_MSG);

			return new ModelAndView(
					ElectiveCourseConstantsIF.Views.VIEW_REGISTER_INPUT,
					ElectiveCourseConstantsIF.Keys.OBJ, registerVerifyMsgs);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());

			registerVerifyMsgs
					.setSeverMessage(ElectiveCourseConstantsIF.Message.USERREGISTERED_SUCESS_MSG);

			return new ModelAndView(
					ElectiveCourseConstantsIF.Views.VIEW_REGISTER_INPUT,
					ElectiveCourseConstantsIF.Keys.OBJ, registerVerifyMsgs);
		}

	}

	@Override
	@RequestMapping(value = "/addConstants.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView updateConstant(@RequestParam String locatorValue) {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();
			if (null == locatorValue || locatorValue.isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message(
						ElectiveCourseConstantsIF.Message.EMPTY_LOCATORVALUE);
				webSiteUrlMsg
						.setErrMessage(ElectiveCourseConstantsIF.Message.EMPTY_LOCATORVALUE);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						ElectiveCourseConstantsIF.Views.ADDCONSTANTS_INPUT,
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);

			}
			StatusInfo statusInfo = electiveCourseDelegate
					.updateConstant(locatorValue);
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message(
						ElectiveCourseConstantsIF.Message.LOCATORVALUE_ADD_FAILED);
				webSiteUrlMsg
						.setErrMessage(ElectiveCourseConstantsIF.Message.LOCATORVALUE_ADD_FAILED);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						ElectiveCourseConstantsIF.Views.ADDCONSTANTS_INPUT,
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);

			} else {
				ajaxRes = new AJAXResponse();
				ajaxRes.setStatus(true);
				ajaxRes.setMessage(ElectiveCourseConstantsIF.Message.LOCATORVALUE_ADD_SUCESS);
				return new ModelAndView(
						ElectiveCourseConstantsIF.Views.VIEW_ADMIN_SUCESS_PAGE,
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message(
					ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);
			webSiteUrlMsg
					.setErrMessage(ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(
					ElectiveCourseConstantsIF.Views.ADDCONSTANTS_INPUT,
					ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);
		}
	}

	@Override
	@RequestMapping(value = "/addFileName.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView addFileName(@ModelAttribute FileInfo fileInfo) {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();

			StatusInfo statusInfo = electiveCourseDelegate
					.addFileName(fileInfo);
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message(
						ElectiveCourseConstantsIF.Message.FILENAME_ADDITION_FAILED);
				webSiteUrlMsg
						.setErrMessage(ElectiveCourseConstantsIF.Message.FILENAME_ADDITION_FAILED);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						ElectiveCourseConstantsIF.Views.ADDFILENAME_INPUT_VIEW,
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);

			} else {
				ajaxRes = new AJAXResponse();
				ajaxRes.setStatus(true);
				ajaxRes.setMessage(ElectiveCourseConstantsIF.Message.FILENAME_ADD_SUCESS);
				return new ModelAndView(
						ElectiveCourseConstantsIF.Views.VIEW_ADMIN_SUCESS_PAGE,
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message(
					ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);
			webSiteUrlMsg
					.setErrMessage(ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(
					ElectiveCourseConstantsIF.Views.ADDFILENAME_INPUT_VIEW,
					ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);
		}
	}

	@Override
	@RequestMapping(value = "/viewFilenames.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody AJAXResponse viewFileNames(HttpServletRequest request) {
		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			List<FileInfo> keyWordList = electiveCourseDelegate
					.retriveFileInfo();
			if (null == keyWordList) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message(
						ElectiveCourseConstantsIF.Message.EMPTY_FILENAMES);
				msg.setErrMessage(ElectiveCourseConstantsIF.Message.EMPTY_FILENAMES);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(keyWordList);
			ajaxResponse
					.setMessage(ElectiveCourseConstantsIF.Message.FILENAME_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message(
					ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);
			msg.setErrMessage(ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

	@Override
	@RequestMapping(value = "/removeFileName.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse removeFileName(
			@RequestBody FileInfoTemp fileInfoTemp) {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();

			StatusInfo statusInfo = electiveCourseDelegate
					.removeFileName(fileInfoTemp);
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message(
						ElectiveCourseConstantsIF.Message.FILENAME_REMOVAL_FAILED);
				webSiteUrlMsg
						.setErrMessage(ElectiveCourseConstantsIF.Message.FILENAME_REMOVAL_FAILED);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;

			} else {
				ajaxRes = new AJAXResponse();
				ajaxRes.setStatus(true);
				ajaxRes.setMessage(ElectiveCourseConstantsIF.Message.FILENAME_REMOVE_SUCESS);
				return ajaxRes;
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message(
					ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);
			webSiteUrlMsg
					.setErrMessage(ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return ajaxRes;
		}
	}

	@Override
	@RequestMapping(value = "/retriveDataSets.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse readDataSets(
			@RequestBody FileInfoTemp fileInfoTemp) {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();

			StatusInfo statusInfo = electiveCourseDelegate
					.readDataSets(fileInfoTemp);
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message(
						ElectiveCourseConstantsIF.Message.READ_DATASETS_FAILED);
				webSiteUrlMsg
						.setErrMessage(ElectiveCourseConstantsIF.Message.READ_DATASETS_FAILED);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;

			} else {
				ajaxRes = new AJAXResponse();
				ajaxRes.setStatus(true);
				ajaxRes.setMessage(ElectiveCourseConstantsIF.Message.READ_DATASETS_SUCESS);
				ajaxRes.setModel((GlobalRowInfo) statusInfo.getModel());

				return ajaxRes;
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message(
					ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);
			webSiteUrlMsg
					.setErrMessage(ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return ajaxRes;
		}
	}

	@Override
	@RequestMapping(value = "/predictElective.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView predictElective(HttpServletRequest request,
			@ModelAttribute ElectivePredictObj electivePredictObj) {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();

			HttpSession session = request.getSession(false);

			if (session != null) {

				if (session
						.getAttribute(ElectiveCourseConstantsIF.Keys.LOGINID_SESSION) != null) {

					electivePredictObj
							.setUserId((String) session
									.getAttribute(ElectiveCourseConstantsIF.Keys.LOGINID_SESSION));
				}
			}

			if (null == electivePredictObj.getSubject1()
					|| electivePredictObj.getSubject1().isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message(
						ElectiveCourseConstantsIF.Message.EMPTY_SUBJECT1_MARKS_ERROR_EMPTY);
				webSiteUrlMsg
						.setErrMessage(ElectiveCourseConstantsIF.Message.EMPTY_SUBJECT1_MARKS_ERROR_EMPTY);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						electivePredictObj.getPageName(),
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);

			}

			if (null == electivePredictObj.getSubject2()
					|| electivePredictObj.getSubject2().isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message(
						ElectiveCourseConstantsIF.Message.EMPTY_SUBJECT2_MARKS_ERROR_EMPTY);
				webSiteUrlMsg
						.setErrMessage(ElectiveCourseConstantsIF.Message.EMPTY_SUBJECT2_MARKS_ERROR_EMPTY);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						electivePredictObj.getPageName(),
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);
			}

			
			if (null == electivePredictObj.getSubject1Name()
					|| electivePredictObj.getSubject1Name().isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message(
						ElectiveCourseConstantsIF.Message.EMPTY_SUBJECT1_NAME_ERROR_EMPTY);
				webSiteUrlMsg
						.setErrMessage(ElectiveCourseConstantsIF.Message.EMPTY_SUBJECT1_NAME_ERROR_EMPTY);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						electivePredictObj.getPageName(),
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);
			}

			if (null == electivePredictObj.getSubject2Name()
					|| electivePredictObj.getSubject2Name().isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message(
						ElectiveCourseConstantsIF.Message.EMPTY_SUBJECT2_NAME_ERROR_EMPTY);
				webSiteUrlMsg
						.setErrMessage(ElectiveCourseConstantsIF.Message.EMPTY_SUBJECT2_NAME_ERROR_EMPTY);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						electivePredictObj.getPageName(),
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);
			}
			
			if (null == electivePredictObj.getElectiveName()
					|| electivePredictObj.getElectiveName().isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message(
						ElectiveCourseConstantsIF.Message.EMPTY_ELECTIVENAME_ERROR_EMPTY);
				webSiteUrlMsg
						.setErrMessage(ElectiveCourseConstantsIF.Message.EMPTY_ELECTIVENAME_ERROR_EMPTY);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						electivePredictObj.getPageName(),
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);
			}

			StatusInfo statusInfo = electiveCourseDelegate
					.predictElective(electivePredictObj);
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();

				if (statusInfo.getErrMsg() != null) {
					ajaxRes.setStatus(false);
					Message webSiteUrlMsg = new Message(statusInfo.getErrMsg());
					webSiteUrlMsg.setErrMessage(statusInfo.getErrMsg());
					ebErrors.add(webSiteUrlMsg);
					ajaxRes.setEbErrors(ebErrors);
					return new ModelAndView(
							electivePredictObj.getPageName(),
							ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);
				} else {

					ajaxRes.setStatus(false);
					Message webSiteUrlMsg = new Message(
							ElectiveCourseConstantsIF.Message.COULD_NOT_PREDICT_ELECTIVE);
					webSiteUrlMsg
							.setErrMessage(ElectiveCourseConstantsIF.Message.COULD_NOT_PREDICT_ELECTIVE);
					ebErrors.add(webSiteUrlMsg);
					ajaxRes.setEbErrors(ebErrors);
					return new ModelAndView(
							electivePredictObj.getPageName(),
							ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);

				}

			} else {
				ajaxRes = new AJAXResponse();
				ajaxRes.setStatus(true);
				ajaxRes.setModel((PredictedValue) statusInfo.getModel());
				ajaxRes.setMessage(ElectiveCourseConstantsIF.Message.PREDICT_ELECTIVE_SUCCESS);
				return new ModelAndView(
						ElectiveCourseConstantsIF.Views.PREDICTION_RESULT_VIEW,
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message(
					ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);
			webSiteUrlMsg
					.setErrMessage(ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(
					electivePredictObj.getPageName(),
					ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);
		}
	}

	@Override
	@RequestMapping(value = "/predictElectiveFuzzy.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView predictElectiveFuzzy(HttpServletRequest request,
			ElectivePredictObj electivePredictObj) {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();

			HttpSession session = request.getSession(false);

			if (session != null) {

				if (session
						.getAttribute(ElectiveCourseConstantsIF.Keys.LOGINID_SESSION) != null) {

					electivePredictObj
							.setUserId((String) session
									.getAttribute(ElectiveCourseConstantsIF.Keys.LOGINID_SESSION));
				}
			}

			if (null == electivePredictObj.getElectiveNamePart1()
					|| electivePredictObj.getElectiveNamePart1().isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message(
						ElectiveCourseConstantsIF.Message.ELECTIVENAMEPART1_EMPTY);
				webSiteUrlMsg
						.setErrMessage(ElectiveCourseConstantsIF.Message.ELECTIVENAMEPART1_EMPTY);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(electivePredictObj.getPageName(),
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);

			}
			

			if (null == electivePredictObj.getSubject1()
					|| electivePredictObj.getSubject1().isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message(
						ElectiveCourseConstantsIF.Message.EMPTY_SUBJECT1_MARKS_ERROR_EMPTY);
				webSiteUrlMsg
						.setErrMessage(ElectiveCourseConstantsIF.Message.EMPTY_SUBJECT1_MARKS_ERROR_EMPTY);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(electivePredictObj.getPageName(),
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);

			}

			if (null == electivePredictObj.getSubject2()
					|| electivePredictObj.getSubject2().isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message(
						ElectiveCourseConstantsIF.Message.EMPTY_SUBJECT2_MARKS_ERROR_EMPTY);
				webSiteUrlMsg
						.setErrMessage(ElectiveCourseConstantsIF.Message.EMPTY_SUBJECT2_MARKS_ERROR_EMPTY);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(electivePredictObj.getPageName(),
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);
			}			
			
			if (null == electivePredictObj.getSubject1Name()
					|| electivePredictObj.getSubject1Name().isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message(
						ElectiveCourseConstantsIF.Message.EMPTY_SUBJECT1_NAME_ERROR_EMPTY);
				webSiteUrlMsg
						.setErrMessage(ElectiveCourseConstantsIF.Message.EMPTY_SUBJECT1_NAME_ERROR_EMPTY);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(electivePredictObj.getPageName(),
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);
			}

			if (null == electivePredictObj.getSubject2Name()
					|| electivePredictObj.getSubject2Name().isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message(
						ElectiveCourseConstantsIF.Message.EMPTY_SUBJECT2_NAME_ERROR_EMPTY);
				webSiteUrlMsg
						.setErrMessage(ElectiveCourseConstantsIF.Message.EMPTY_SUBJECT2_NAME_ERROR_EMPTY);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(electivePredictObj.getPageName(),
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);
			}
			
			if (null == electivePredictObj.getElectiveName()
					|| electivePredictObj.getElectiveName().isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message(
						ElectiveCourseConstantsIF.Message.EMPTY_ELECTIVENAME_ERROR_EMPTY);
				webSiteUrlMsg
						.setErrMessage(ElectiveCourseConstantsIF.Message.EMPTY_ELECTIVENAME_ERROR_EMPTY);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(electivePredictObj.getPageName(),
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);
			}

			StatusInfo statusInfo = electiveCourseDelegate
					.predictElectiveFuzzy(electivePredictObj);
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();

				if (statusInfo.getErrMsg() != null) {
					ajaxRes.setStatus(false);
					Message webSiteUrlMsg = new Message(statusInfo.getErrMsg());
					webSiteUrlMsg.setErrMessage(statusInfo.getErrMsg());
					ebErrors.add(webSiteUrlMsg);
					ajaxRes.setEbErrors(ebErrors);
					return new ModelAndView(electivePredictObj.getPageName(),
							ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);
				} else {

					ajaxRes.setStatus(false);
					Message webSiteUrlMsg = new Message(
							ElectiveCourseConstantsIF.Message.COULD_NOT_PREDICT_ELECTIVE);
					webSiteUrlMsg
							.setErrMessage(ElectiveCourseConstantsIF.Message.COULD_NOT_PREDICT_ELECTIVE);
					ebErrors.add(webSiteUrlMsg);
					ajaxRes.setEbErrors(ebErrors);
					return new ModelAndView(electivePredictObj.getPageName(),
							ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);

				}

			} else {
				ajaxRes = new AJAXResponse();
				ajaxRes.setStatus(true);
				ajaxRes.setModel((PredictedValue) statusInfo.getModel());
				ajaxRes.setMessage(ElectiveCourseConstantsIF.Message.PREDICT_ELECTIVE_SUCCESS);
				return new ModelAndView(
						ElectiveCourseConstantsIF.Views.PREDICTION_RESULTFUZZY_VIEW,
						ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message(
					ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);
			webSiteUrlMsg
					.setErrMessage(ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(
					ElectiveCourseConstantsIF.Views.ENTER_MARKS_VIEW,
					ElectiveCourseConstantsIF.Keys.OBJ, ajaxRes);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@RequestMapping(value = "/recommendationsList.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse doRecommendations(
			HttpServletRequest request) {
		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			String userId = null;

			HttpSession session = request.getSession(false);

			if (session != null) {

				if (session
						.getAttribute(ElectiveCourseConstantsIF.Keys.LOGINID_SESSION) != null) {

					userId = (String) session
							.getAttribute(ElectiveCourseConstantsIF.Keys.LOGINID_SESSION);
				}
			}

			if (null == userId) {

				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message(
						ElectiveCourseConstantsIF.Message.SESSION_EXPIRE);
				msg.setErrMessage(ElectiveCourseConstantsIF.Message.SESSION_EXPIRE);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}

			StatusInfo statusInfo = electiveCourseDelegate
					.performRecommendations(userId);
			if (!statusInfo.isStatus()) {

				if (statusInfo.getErrMsg() != null
						&& !statusInfo.getErrMsg().isEmpty()) {

					ajaxResponse = new AJAXResponse();
					ajaxResponse.setStatus(false);
					Message msg = new Message(statusInfo.getErrMsg());
					msg.setErrMessage(statusInfo.getErrMsg());
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajaxResponse.setEbErrors(ebErrors);
					return ajaxResponse;
				} else {

					ajaxResponse = new AJAXResponse();
					ajaxResponse.setStatus(false);
					Message msg = new Message(
							ElectiveCourseConstantsIF.Message.EMPTY_RECOMMENDATIONS);
					msg.setErrMessage(ElectiveCourseConstantsIF.Message.EMPTY_RECOMMENDATIONS);
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajaxResponse.setEbErrors(ebErrors);
					return ajaxResponse;
				}
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel((List<RankingModel>)statusInfo.getModel());
			ajaxResponse
					.setMessage(ElectiveCourseConstantsIF.Message.RECOMMENDATIONS_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			Message msg = new Message(
					ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);
			msg.setErrMessage(ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

}
