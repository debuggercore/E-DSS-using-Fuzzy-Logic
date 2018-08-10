package com.constants;

public interface ElectiveCourseConstantsIF {

	public static final String MODEL_NAME = "obj";

	interface SessionIF {

	}

	interface Message {

		public static final String INTERNAL_ERROR = "Please Contact System Adminitrator. An Internal Error has Ocuurred";
		public static final String FIRSTNAME_EMPTY = "First Name cannot be Empty";
		public static final String LASTNAME_EMPTY = "Last Name cannot be Empty";
		public static final String USERID_EMPTY = "User ID Cannot be Empty";
		public static final String EMAIL_EMPTY = "Email Cannot be Empty";
		public static final String PASSWORD_EMPTY = "Password Cannot be Empty";
		public static final String USERREGISTERED_SUCESS_MSG = "User Has Registered Sucessfully.Please Login";
		public static final String USERALREADY_EXIST = "User Already Exist";
		public static final String NO_USER_EXISTS = "No User Already Exist";
		public static final String PASSWORD_WRONG = "Password does not exist";
		public static final String USER_LOGIN_SUCESS = "User Login is Sucessful";
		public static final String LOGIN_EMPTY = "Login Type Cannot be Empty";
		public static final String LOGINID_EMPTY = "Login ID is Empty";
		public static final String INVALID_LOGIN = "Invalid Login";
		public static final String SEX_ERR_MSG = "Sex Must be Selected";
		public static final String DOB_ERR_MSG = "Date of Birth Must be Selected";
		public static final String EMPTY_LOCATORVALUE = "Locator Value cannot be Empty";
		public static final String LOCATORVALUE_ADD_FAILED = "Locator Value Addition has Failed";
		public static final String LOCATORVALUE_ADD_SUCESS = "Locator Value Added Sucessfully";
		public static final String COULD_NOT_ADD_FILEINFO = "Could not add the File Information";
		public static final String DUPLICATE_FILEINFO = "Duplicate File Name or Elective Already Exists";
		public static final String EMPTY_FILENAMES = "File Names are Empty";
		public static final String FILENAME_SUCESS = "File Names are retrived Sucessfully";
		public static final String FILENAME_REMOVED_SUCESS = "Filename Removed Sucessfully";
		public static final String FILENAME_REMOVAL_FAILED = "Filename Removal Failed";
		public static final String COULD_NOT_REMOVE_FILE = "Could not Remove File";
		public static final String READ_DATASETS_FAILED = "Reading of Data Sets has Failed";
		public static final String READ_DATASETS_SUCESS = "Reading of Data Sets is sucessful";
		public static final String LOCATORVALUE_NOT_SET = "Locator Value is not Set for Data Sets";
		public static final String FILENAME_ADD_SUCESS = "File Name added Sucessfully";
		public static final String FILENAME_ADDITION_FAILED = "File Name Addition has Failed";
		public static final String FILENAME_REMOVE_SUCESS = "File Name Removed Sucessful";
		public static final String EMPTY_SUBJECT1_MARKS_ERROR_EMPTY = "Subject1 Marks Cannot be Empty";
		public static final String EMPTY_SUBJECT2_MARKS_ERROR_EMPTY = "Subject2 Marks Cannot be Empty";
		public static final String EMPTY_SUBJECT3_MARKS_ERROR_EMPTY = "Subject3 Marks Cannot be Empty";
		public static final String EMPTY_SUBJECT4_MARKS_ERROR_EMPTY = "Subject4 Marks Cannot be Empty";
		public static final String EMPTY_SUBJECT1_NAME_ERROR_EMPTY = "Subject1 Name Cannot be Empty";
		public static final String EMPTY_SUBJECT2_NAME_ERROR_EMPTY = "Subject2 Name Cannot be Empty";
		public static final String EMPTY_SUBJECT3_NAME_ERROR_EMPTY = "Subject3 Name Cannot be Empty";
		public static final String EMPTY_SUBJECT4_NAME_ERROR_EMPTY = "Subjecct4 Name Cannot be Empty";
		public static final String EMPTY_ELECTIVENAME_ERROR_EMPTY = "Elective Name Cannot be Empty";
		public static final String COULD_NOT_PREDICT_ELECTIVE = "Could not Predict Elective";
		public static final String PREDICT_ELECTIVE_SUCCESS = "Predicting Elective is Successful!!";
		public static final String COULD_NOT_FIND_FILEINFO_FOR_ELECTIVE_ERROR = "Could not Find FIle Information From Admin";
		public static final String PATH_NOT_SET_ERROR = "Path Not Set Error";
		public static final String INSERT_PREDICTION_FAILED = "Insertion of Prediction has Failed";
		public static final String ELECTIVENAMEPART1_EMPTY = "Elective Name part1 Cannot be Empty";
		public static final String ELECTIVENAMEPART2_EMPTY = "Elective Name part2 Cannot be Empty";
		public static final String EMPTY_RECOMMENDATIONS = "Recommendations cannot be Performed";
		public static final String RECOMMENDATIONS_SUCESS = "Recommendations is Sucessful";
		public static final String SESSION_EXPIRE = "Session has Expired. Logout and Re Login";
		public static final String DISTINCT_ELECTIVES_EMPTY = "No Distinct Electives Found";
		public static final String DELETE_RANKING_FAILED = "Deletion of Ranking has Failed.Contact System Admin";
		public static final String INSERT_ERROR_RANKINGMODEL = "Insertion of Ranking has Failed. Contact System Admin";
		public static final String CANNOT_RANK_AT_THIS_POINT_OF_TIME = "Cannot Rank at this Point of Time";

	}

	interface Views {
		public static final String VIEW_REGISTER_INPUT = "registerview";
		public static final String VIEW_CUSTOMER_WELCOMEPAGE = "user";
		public static final String VIEW_LOGIN_INPUT = "login";
		public static final String VIEW_ADMIN_WELCOMEPAGE = "admin";
		public static final String VIEW_ERROR_PAGE = "error";
		public static final String APPLICATION_WELCOME_PAGE = "welcome";
		public static final String VIEW_SUCESS_PAGE = "sucess";
		public static final String VIEW_ADMIN_SUCESS_PAGE = "adminsucess";
		public static final String VIEW_ADMIN_ERROR_PAGE = "adminerror";
		public static final String ERROR_PAGE = "error";
		public static final String VIEW_USER_ERROR_PAGE = "erroruser";
		public static final String VIEW_SUCESS_ADMIN_PAGE = "adminsucess";
		public static final String VIEW_P_WELCOMEPAGE = "user";
		public static final String ADDCONSTANTS_INPUT = "addconstants";
		public static final String ADDFILENAME_INPUT_VIEW = "addfilename";
		public static final String ENTER_MARKS_VIEW = "entermarkselective1";
		public static final String PREDICTION_RESULT_VIEW = "prediction";
		public static final String ENTER_MARKSFUZZY_VIEW = "entermarksfuzzy";
		public static final String PREDICTION_RESULTFUZZY_VIEW = "predictionfuzzy";

	}

	interface Keys {
		public static final String OBJ = "obj";
		public static final int ADMIN_TYPE = 1;
		public static final String LOGINID_SESSION = "LOGINID_SESSION";
		public static final String LOGINTYPE_SESSION = "LOGINTYPE_SESSION";
		public static final int CUSTOMER_TYPE = 4;
		public static final String LOCATION_NAME = "FILEPATH";

	}

	interface DATABASESQL {
		// Query Keys for Login and Registration
		public static final String RETRIVE_REGISTER_USERIDS_SQL = "RETRIVE_REGISTER_USERIDS_SQL";
		public static final String INSERT_LOGIN_SQL = "INSERT_LOGIN_SQL";
		public static final String RETRIVE_PASSWORD_WHERE_USERID_SQL = "RETRIVE_PASSWORD_WHERE_USERID_SQL";
		public static final String RETRIVE_LOGINTYPE_WHERE_USERID_SQL = "RETRIVE_LOGINTYPE_WHERE_USERID_SQL";

		// Query for File Location
		public static final String RETRIVE_LOCATORS_WHERE_LOCATORNAME_SQL = "RETRIVE_LOCATORS_WHERE_LOCATORNAME_SQL";
		public static final String UPDATE_LOCATORVALUE_WHERE_LOCATORNAME_SQL = "UPDATE_LOCATORVALUE_WHERE_LOCATORNAME_SQL";

		// Query for File Info List
		public static final String RETRIVE_ALL_FILEINFO_SQL = "RETRIVE_ALL_FILEINFO_SQL";
		public static final String INSERT_FILENAME_SQL = "INSERT_FILENAME_SQL";
		public static final String DELETE_FROM_FILENAME_WHERE_FILENAME_SQL = "DELETE_FROM_FILENAME_WHERE_FILENAME_SQL";
		public static final String RETRIVE_PRPOERTYVALUE_SQL = "RETRIVE_PRPOERTYVALUE_SQL";
		public static final String INSERT_PREDICTIONVALUE_SQL = "INSERT_PREDICTIONVALUE_SQL";
		public static final String RETRIVE_FILEINFO_WHERE_ELECTIVENAME_SQL = "RETRIVE_FILEINFO_WHERE_ELECTIVENAME_SQL";
		public static final String DELETE_RANKING_WHERE_USERID_SQL = "DELETE_RANKING_WHERE_USERID_SQL";
		public static final String RETRIVE_ALL_PREDICTEDINFO_FOR_USERID_SQL = "RETRIVE_ALL_PREDICTEDINFO_FOR_USERID_SQL";
		public static final String INSERT_RANKINGMODEL_SQL = "INSERT_RANKINGMODEL_SQL";
		public static final String RANKING_ELECTIVE_WHERE_USERID_SQL = "RANKING_ELECTIVE_WHERE_USERID_SQL";

	}

	interface DATABASECOLUMNS {
		public static final String STATUS_COL = "STATUS";
		public static final String ANSWER1_COL = "ANSWER1";
		public static final String ANSWER2_COL = "ANSWER2";
		public static final String ANSWER3_COL = "ANSWER3";
		public static final String ANSWER4_COL = "ANSWER4";
		public static final String USERID_COL = "USERID";
		public static final String DATE_COL = "DATE";
		public static final String LOCATOR_NAME = "FILEPATH";
	}

	interface FileRead {

		public static final String LOC = "D:\\2017_PROJECTS_CODE\\IGEEKS\\ELECTIVECOURSE_SUGGESTION\\ElectiveCourse\\src\\dataset\\";
	}

}
