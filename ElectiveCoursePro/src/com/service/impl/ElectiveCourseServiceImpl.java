package com.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import net.sourceforge.jFuzzyLogic.FIS;

import org.springframework.beans.factory.annotation.Autowired;

import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.Remove;
import wekaframework.classfiers.custom.J48Custom;

import com.constants.ElectiveCourseConstantsIF;
import com.dao.inter.ElectiveCourseDAOIF;
import com.fuzzy.util.FuzzyUtilMain;
import com.fuzzy.util.OutputValue;
import com.model.AttributeInformation;
import com.model.ElectivePredictObj;
import com.model.FileInfo;
import com.model.FileInfoTemp;
import com.model.GlobalRowInfo;
import com.model.PredictedValue;
import com.model.RankingModel;
import com.model.RegisterUser;
import com.model.RowInfo;
import com.model.StatusInfo;
import com.service.inter.ElectiveCourseServiceIF;
import com.util.C45;
import com.util.ElectiveCourseAttribute;
import com.util.ElectiveCourseDataInstancesHelper;
import com.utils.ArffFileUtility;

public class ElectiveCourseServiceImpl implements ElectiveCourseServiceIF {

	@Autowired
	private ElectiveCourseDAOIF electiveCourseDao;

	public ElectiveCourseDAOIF getElectiveCourseDao() {
		return electiveCourseDao;
	}

	public void setElectiveCourseDao(ElectiveCourseDAOIF electiveCourseDao) {
		this.electiveCourseDao = electiveCourseDao;
	}

	@Override
	public StatusInfo doRegistration(RegisterUser registerUser) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			List<String> userIdList = electiveCourseDao.retriveUserIds();
			if (null == userIdList || userIdList.isEmpty()
					|| userIdList.size() == 0) {
				statusInfo = electiveCourseDao.insertLogin(registerUser);

				if (!statusInfo.isStatus()) {
					statusInfo
							.setErrMsg(ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);
					statusInfo.setStatus(false);
					return statusInfo;
				} else {
					return statusInfo;
				}

			}

			if (userIdList.contains(registerUser.getUserId())) {
				statusInfo
						.setErrMsg(ElectiveCourseConstantsIF.Message.USERALREADY_EXIST);
				statusInfo.setStatus(false);
				return statusInfo;
			} else {
				statusInfo = electiveCourseDao.insertLogin(registerUser);

				if (!statusInfo.isStatus()) {
					statusInfo
							.setErrMsg(ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);
					statusInfo.setStatus(false);
					return statusInfo;
				} else {
					return statusInfo;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			statusInfo = new StatusInfo();
			statusInfo
					.setErrMsg(ElectiveCourseConstantsIF.Message.INTERNAL_ERROR);
			statusInfo.setStatus(false);
			return statusInfo;

		}
	}

	@Override
	public StatusInfo checkLogin(RegisterUser registerUser) {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			System.out.println("Inside Verify Login Service");
			boolean status = checkUserInformation(registerUser.getUserId());
			if (!status) {
				statusInfo
						.setErrMsg(ElectiveCourseConstantsIF.Message.NO_USER_EXISTS);
				statusInfo.setStatus(false);
				return statusInfo;
			} else {
				String password = electiveCourseDao
						.retrivePassword(registerUser.getUserId());

				if (null == password || password.isEmpty()) {
					statusInfo
							.setErrMsg(ElectiveCourseConstantsIF.Message.PASSWORD_WRONG);
					statusInfo.setStatus(false);
					return statusInfo;
				}
				if (!password.equals(registerUser.getUserPassword())) {
					statusInfo
							.setErrMsg(ElectiveCourseConstantsIF.Message.PASSWORD_WRONG);
					statusInfo.setStatus(false);
					return statusInfo;
				}
				if (password.equals(registerUser.getUserPassword())) {
					statusInfo
							.setErrMsg(ElectiveCourseConstantsIF.Message.USER_LOGIN_SUCESS);
					statusInfo.setStatus(true);
					// Now retrieve the login type
					int loginTypeDB = electiveCourseDao
							.retriveLoginType(registerUser.getUserId());
					statusInfo.setType(loginTypeDB);
					return statusInfo;
				}
			}
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			return statusInfo;

		}
		return statusInfo;
	}

	private boolean checkUserInformation(String userId) {
		try {
			List<String> userNameList = electiveCourseDao.retriveUserIds();

			System.out.println("LIST" + userNameList);
			if (null == userNameList || userNameList.isEmpty()
					|| userNameList.size() == 0) {
				return false;
			}
			if (userNameList.contains(userId)) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return true;
		}
	}

	@Override
	public StatusInfo updateConstant(String locatorValue) {

		StatusInfo statusInfo = new StatusInfo();
		try {

			boolean value = electiveCourseDao
					.updateLocationValueFromLocatioName(
							ElectiveCourseConstantsIF.Keys.LOCATION_NAME,
							locatorValue);

			if (value) {
				statusInfo.setStatus(true);

			} else {
				statusInfo.setStatus(false);
			}

		} catch (Exception e) {
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			return statusInfo;

		}
		return statusInfo;

	}

	@Override
	public StatusInfo addFileName(FileInfo fileInfo) {

		StatusInfo statusInfo = new StatusInfo();

		try {

			List<FileInfo> fileInfoList = electiveCourseDao
					.retriveAllFileInfo();

			if (fileInfoList == null || fileInfoList.isEmpty()) {

				statusInfo = electiveCourseDao.insertFileName(fileInfo);
				if (!statusInfo.isStatus()) {

					statusInfo.setStatus(false);
					statusInfo
							.setErrMsg(ElectiveCourseConstantsIF.Message.COULD_NOT_ADD_FILEINFO);
					return statusInfo;
				}
				statusInfo.setStatus(true);
				return statusInfo;
			}

			boolean value = isFileInfoExist(fileInfoList, fileInfo);

			if (value) {

				statusInfo.setStatus(false);
				statusInfo
						.setErrMsg(ElectiveCourseConstantsIF.Message.DUPLICATE_FILEINFO);
				return statusInfo;
			} else {

				statusInfo = electiveCourseDao.insertFileName(fileInfo);
				if (!statusInfo.isStatus()) {

					statusInfo.setStatus(false);
					statusInfo
							.setErrMsg(ElectiveCourseConstantsIF.Message.COULD_NOT_ADD_FILEINFO);
					return statusInfo;
				}
				statusInfo.setStatus(true);
				return statusInfo;

			}

		} catch (Exception e) {
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			return statusInfo;
		}

	}

	private boolean isFileInfoExist(List<FileInfo> fileInfoList,
			FileInfo givenFileInfo) {

		boolean value = false;

		if (fileInfoList != null && !fileInfoList.isEmpty()) {

			for (FileInfo fileInfoTemp : fileInfoList) {

				String fileNameTemp = fileInfoTemp.getFileName();

				String electiveName = fileInfoTemp.getElectiveName();

				if ((fileNameTemp.equals(givenFileInfo.getFileName()))
						|| (electiveName
								.equals(givenFileInfo.getElectiveName()))) {
					value = true;
					break;

				}

			}

		}

		return value;
	}

	@Override
	public List<FileInfo> retriveFileInfo() {

		List<FileInfo> fileInfoList = null;
		try {

			fileInfoList = electiveCourseDao.retriveAllFileInfo();

		} catch (Exception e) {
			e.printStackTrace();

			return fileInfoList;

		}
		return fileInfoList;

	}

	@Override
	public StatusInfo removeFileName(FileInfoTemp fileInfoTemp) {
		StatusInfo statusInfo = new StatusInfo();

		try {

			boolean value = electiveCourseDao.deleteFileName(fileInfoTemp);
			if (value) {

				statusInfo.setStatus(true);
				return statusInfo;

			} else {
				statusInfo.setStatus(false);
				statusInfo
						.setErrMsg(ElectiveCourseConstantsIF.Message.COULD_NOT_REMOVE_FILE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			return statusInfo;
		}
		return statusInfo;
	}

	@Override
	public StatusInfo readDataSets(FileInfoTemp fileInfoTemp) {
		StatusInfo statusInfo = new StatusInfo();

		try {

			String locator = electiveCourseDao
					.retrivePropertyFromConfig(ElectiveCourseConstantsIF.DATABASECOLUMNS.LOCATOR_NAME);

			if (locator == null || locator.isEmpty()) {

				statusInfo.setStatus(false);
				statusInfo
						.setErrMsg(ElectiveCourseConstantsIF.Message.LOCATORVALUE_NOT_SET);
				return statusInfo;

			}

			// Now Read the File

			String completePath = locator + fileInfoTemp.getFileName();

			BufferedReader reader = new BufferedReader(new FileReader(
					completePath));
			ElectiveCourseDataInstancesHelper data = new ElectiveCourseDataInstancesHelper(
					reader);

			int numberOfAttributes = data.getNum_attributes();
			Vector<ElectiveCourseAttribute> vectorAtt = data.getAttributes();

			List<String> nameOfAttributes = new ArrayList<String>();
			if (vectorAtt != null && !vectorAtt.isEmpty()) {
				for (int i = 0; i < vectorAtt.size(); i++) {
					ElectiveCourseAttribute att = vectorAtt.get(i);
					String v = att.name;
					nameOfAttributes.add(v);
				}

			}

			data.convert();

			String[][] vecti1 = data.getData_array();

			List<RowInfo> rowInfoList = new ArrayList<RowInfo>();

			for (int i = 0; i < vecti1.length; i++) {
				int len = vecti1[i].length;
				RowInfo rowInfo = new RowInfo();
				List<AttributeInformation> attributeInformations = new ArrayList<AttributeInformation>();
				for (int j = 0; j < len; j++) {
					String string = vecti1[i][j];
					AttributeInformation attributeInformation = new AttributeInformation();
					attributeInformation.setAttributeName(nameOfAttributes
							.get(j));
					attributeInformation.setAttributeValue(string);
					if (j == numberOfAttributes) {
						attributeInformation.setElective(true);
						attributeInformation.setIsSelected(string);
					}

					attributeInformations.add(attributeInformation);
				}

				rowInfo.setAttributeInfo(attributeInformations);

				rowInfoList.add(rowInfo);

			}
			reader.close();
			data.setClassIndex(data.num_attributes - 1);

			GlobalRowInfo globalRowInfo = new GlobalRowInfo();
			globalRowInfo.setRowInfoList(rowInfoList);
			statusInfo.setModel(globalRowInfo);

			statusInfo.setStatus(true);
			return statusInfo;

		} catch (Exception e) {
			e.printStackTrace();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			return statusInfo;
		}
	}

	@Override
	public StatusInfo predictElective(ElectivePredictObj electivePredictObj) {

		StatusInfo statusInfo = new StatusInfo();
		try {
			FileInfo fileInfo = electiveCourseDao
					.retriveFileInfoForElectiveName(electivePredictObj
							.getElectiveName());

			if (null == fileInfo) {

				statusInfo.setStatus(false);
				statusInfo
						.setErrMsg(ElectiveCourseConstantsIF.Message.COULD_NOT_FIND_FILEINFO_FOR_ELECTIVE_ERROR);

				return statusInfo;
			}

			// Obtain the Path of the File

			String path = electiveCourseDao
					.retrivePropertyFromConfig(ElectiveCourseConstantsIF.Keys.LOCATION_NAME);

			if (null == path || (path != null && path.isEmpty())) {
				statusInfo.setStatus(false);
				statusInfo
						.setErrMsg(ElectiveCourseConstantsIF.Message.PATH_NOT_SET_ERROR);
				return statusInfo;
			}

			String completePath = path + fileInfo.getFileName();

			// Reading the File of Training Data

			BufferedReader reader = new BufferedReader(new FileReader(
					completePath));
			Instances train = new Instances(reader);
			reader.close();
			// setting class attribute
			train.setClassIndex(train.numAttributes() - 1);

			String testPath = writeToFile(electivePredictObj, path);

			// Reading the Test Data
			BufferedReader reader1 = new BufferedReader(
					new FileReader(testPath));
			Instances test = new Instances(reader1);
			reader1.close();
			// setting class attribute
			test.setClassIndex(test.numAttributes() - 1);

			J48Custom j48Custom = new J48Custom();

			j48Custom.setTestInstances(test);
			j48Custom.setTrainInstance(train);
			// j48Custom.setC45(new C45());

			String predictedValue1 = j48Custom.predict();

			PredictedValue predictedValue = populateValues(electivePredictObj,
					predictedValue1);

			statusInfo = electiveCourseDao.insertPredict(predictedValue);

			if (!statusInfo.isStatus()) {

				statusInfo.setStatus(false);
				statusInfo
						.setErrMsg(ElectiveCourseConstantsIF.Message.INSERT_PREDICTION_FAILED);
				return statusInfo;
			}

			statusInfo.setModel(predictedValue);
			statusInfo.setStatus(true);

		} catch (Exception e) {
			e.printStackTrace();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			return statusInfo;

		}
		return statusInfo;

	}

	private PredictedValue populateValues(
			ElectivePredictObj electivePredictObj, String predictedValue1) {
		PredictedValue predictedValue = new PredictedValue();

		predictedValue.setSubject1(electivePredictObj.getSubject1());
		predictedValue.setSubject1Name(electivePredictObj.getSubject1Name());

		predictedValue.setSubject2(electivePredictObj.getSubject2());
		predictedValue.setSubject2Name(electivePredictObj.getSubject2Name());

		predictedValue.setElectiveCourse(electivePredictObj.getElectiveName());

		predictedValue.setUserId(electivePredictObj.getUserId());

		predictedValue.setPredictedCapable(predictedValue1);

		return predictedValue;
	}

	private String writeToFile(ElectivePredictObj electivePredictObj,
			String path) {
		// Reading the File of Test Data
		String testPath = path + "test.arff";

		ArffFileUtility fileUtilityAngular = new ArffFileUtility();

		StringBuffer buffer = convertTestDataToStr(electivePredictObj);

		fileUtilityAngular.send(testPath, buffer.toString());
		return testPath;
	}

	private StringBuffer convertTestDataToStr(
			ElectivePredictObj electivePredictObj) {
		StringBuffer buffer = new StringBuffer(electivePredictObj.getSubject1());
		buffer.append(",");
		buffer.append(electivePredictObj.getSubject2());
		buffer.append(",");
		buffer.append("S");
		return buffer;
	}

	@Override
	public StatusInfo predictElectiveFuzzy(ElectivePredictObj electivePredictObj) {
		StatusInfo statusInfo = new StatusInfo();
		try {
			FileInfo fileInfo = electiveCourseDao
					.retriveFileInfoForElectiveName(electivePredictObj
							.getElectiveNamePart1());

			if (null == fileInfo) {

				statusInfo.setStatus(false);
				statusInfo
						.setErrMsg(ElectiveCourseConstantsIF.Message.COULD_NOT_FIND_FILEINFO_FOR_ELECTIVE_ERROR);

				return statusInfo;
			}

			// Obtain the Path of the File

			String path = electiveCourseDao
					.retrivePropertyFromConfig(ElectiveCourseConstantsIF.Keys.LOCATION_NAME);

			if (null == path || (path != null && path.isEmpty())) {
				statusInfo.setStatus(false);
				statusInfo
						.setErrMsg(ElectiveCourseConstantsIF.Message.PATH_NOT_SET_ERROR);
				return statusInfo;
			}

			String completePath1 = path + fileInfo.getFileName();

			electivePredictObj.setFilePath1(completePath1);
			
			switch(electivePredictObj.getSubject1()){
			case "S":
				electivePredictObj.setSubject1("10");
				break;
			case "A":
				electivePredictObj.setSubject1("9");
				break;
			case "B":
				electivePredictObj.setSubject1("8");
				break;
			case "C":
				electivePredictObj.setSubject1("7");
				break;
			case "D":
				electivePredictObj.setSubject1("6");
				break;
			case "E":
				electivePredictObj.setSubject1("5");
				break;
			case "F":
				electivePredictObj.setSubject1("4");
				break;
			default:
				electivePredictObj.setSubject1("0");
				break;
			}
			
			switch(electivePredictObj.getSubject2()){
			case "S":
				electivePredictObj.setSubject2("10");
				break;
			case "A":
				electivePredictObj.setSubject2("9");
				break;
			case "B":
				electivePredictObj.setSubject2("8");
				break;
			case "C":
				electivePredictObj.setSubject2("7");
				break;
			case "D":
				electivePredictObj.setSubject2("6");
				break;
			case "E":
				electivePredictObj.setSubject2("5");
				break;
			case "F":
				electivePredictObj.setSubject2("4");
				break;
			default:
				electivePredictObj.setSubject2("0");
				break;
			}

			System.out.println("Subject 1: "+electivePredictObj.getSubject1()+" Subject 2:"+electivePredictObj.getSubject2());
			FuzzyUtilMain.setElectivePredictObj(electivePredictObj);

			String[] args = null;
			FuzzyUtilMain.main(args);

			OutputValue outputValue = FuzzyUtilMain.getOutputvalue();

			String predictedValue1 = findFuzzyOutput(outputValue.getValue());

			PredictedValue predictedValue = populateValues(electivePredictObj,
					predictedValue1);

			predictedValue.setCog(Double.toString(outputValue.getValue()));
			predictedValue.setFirstFuzzyOutputCog(Double.toString(outputValue
					.getValue1()));

			statusInfo = electiveCourseDao.insertPredict(predictedValue);

			if (!statusInfo.isStatus()) {

				statusInfo.setStatus(false);
				statusInfo
						.setErrMsg(ElectiveCourseConstantsIF.Message.INSERT_PREDICTION_FAILED);
				return statusInfo;
			}

			statusInfo.setModel(predictedValue);
			statusInfo.setStatus(true);

		} catch (Exception e) {
			e.printStackTrace();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			return statusInfo;

		}
		return statusInfo;
	}

	private String findFuzzyOutput(double midvalue) {
		String predictedValue1 = "CANNOTDETERMINE";
		if (midvalue >= 0 && midvalue <= 6) {
			predictedValue1 = "VERY LOW";
		} else if (midvalue >= 5 && midvalue <= 7) {
			predictedValue1 = "LOW";
		} else if (midvalue >= 6 && midvalue <= 8) {
			predictedValue1 = "MEDIUM";
		} else if (midvalue >= 7 && midvalue <= 9) {
			predictedValue1 = "HIGH";
		} else if (midvalue >= 8 && midvalue <= 10) {
			predictedValue1 = "VERY HIGH";
		}
		return predictedValue1;
	}

	private double findFuzzyOutputPart1(ElectivePredictObj electivePredictObj,
			FIS fis) {
		// Show
		fis.chart();

		// Set inputs
		fis.setVariable(electivePredictObj.getSubject1Name().toLowerCase(),
				new Double(electivePredictObj.getSubject1()));
		fis.setVariable(electivePredictObj.getSubject2Name().toLowerCase(),
				new Double(electivePredictObj.getSubject2()));

		// Evaluate
		fis.evaluate();

		double tip = fis.getVariable(
				electivePredictObj.getElectiveName().toLowerCase()).getValue();

		return tip;
	}

	private double findFuzzyOutputPart2(ElectivePredictObj electivePredictObj,
			FIS fis1) {
		// Show
		fis1.chart();

		// Set inputs
		/*
		 * fis1.setVariable(electivePredictObj.getSubject3Name().toLowerCase(),
		 * new Double(electivePredictObj.getSubject3()));
		 * fis1.setVariable(electivePredictObj.getSubject3Name().toLowerCase(),
		 * new Double(electivePredictObj.getSubject3()));
		 */

		// Evaluate
		fis1.evaluate();

		double tip = fis1.getVariable(
				electivePredictObj.getElectiveName().toLowerCase()).getValue();

		return tip;
	}

	@Override
	public StatusInfo performRecommendations(String userId) {

		StatusInfo statusInfo = new StatusInfo();

		try {

			statusInfo = electiveCourseDao.deleteRanking(userId);
			if (!statusInfo.isStatus()) {

				statusInfo.setStatus(false);
				statusInfo
						.setErrMsg(ElectiveCourseConstantsIF.Message.DELETE_RANKING_FAILED);
				return statusInfo;
			}

			List<PredictedValue> predictedValueList = electiveCourseDao
					.retriveAllPredictiveValues(userId);

			if (null == predictedValueList) {
				statusInfo.setStatus(false);
				statusInfo
						.setErrMsg(ElectiveCourseConstantsIF.Message.DISTINCT_ELECTIVES_EMPTY);
				return statusInfo;
			}

			Map<String, Double> mapElectiveNameCAG = new HashMap<String, Double>();

			for (PredictedValue predictedValue : predictedValueList) {
				if (predictedValue.getCog() != null
						&& !predictedValue.getCog().isEmpty()) {
					mapElectiveNameCAG.put(predictedValue.getElectiveCourse(),
							new Double(predictedValue.getCog()));
				}
			}

			// Now Find the Keys

			Set<String> electiveSets = mapElectiveNameCAG.keySet();

			List<RankingModel> rankingModelList = new ArrayList<RankingModel>();
			for (String electiveName : electiveSets) {
				RankingModel rankingModel = new RankingModel();
				rankingModel.setElectiveName(electiveName);
				rankingModel.setCog(mapElectiveNameCAG.get(electiveName));
				rankingModel.setUserId(userId);
				rankingModelList.add(rankingModel);
			}

			// Do Insertion and Rank it

			statusInfo = electiveCourseDao.insertRankingModel(rankingModelList);

			if (!statusInfo.isStatus()) {
				statusInfo.setStatus(false);
				statusInfo
						.setErrMsg(ElectiveCourseConstantsIF.Message.INSERT_ERROR_RANKINGMODEL);
				return statusInfo;
			}

			// Now Rank It

			List<RankingModel> rankModel = electiveCourseDao
					.rankElectives(userId);

			if (null == rankModel || rankModel.isEmpty()) {
				statusInfo.setStatus(false);
				statusInfo
						.setErrMsg(ElectiveCourseConstantsIF.Message.CANNOT_RANK_AT_THIS_POINT_OF_TIME);
				return statusInfo;
			}

			statusInfo.setStatus(true);
			statusInfo.setModel(rankModel);

		} catch (Exception e) {
			e.printStackTrace();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			return statusInfo;

		}
		return statusInfo;
	}
}