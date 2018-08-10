package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import com.constants.ElectiveCourseConstantsIF;
import com.dao.inter.ElectiveCourseDAOIF;
import com.model.FileInfo;
import com.model.FileInfoTemp;
import com.model.PredictedValue;
import com.model.RankingModel;
import com.model.RegisterUser;
import com.model.StatusInfo;

public class ElectiveCourseDAOImpl implements ElectiveCourseDAOIF {

	protected SimpleJdbcTemplate template;

	public SimpleJdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(SimpleJdbcTemplate template) {
		this.template = template;
	}

	protected NamedParameterJdbcTemplate namedJdbcTemplate;

	public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
		return namedJdbcTemplate;
	}

	public void setNamedJdbcTemplate(
			NamedParameterJdbcTemplate namedJdbcTemplate) {
		this.namedJdbcTemplate = namedJdbcTemplate;
	}

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	protected MessageSource sqlProperties;

	public MessageSource getSqlProperties() {
		return sqlProperties;
	}

	public void setSqlProperties(MessageSource sqlProperties) {
		this.sqlProperties = sqlProperties;
	}

	protected JdbcTemplate jdbcTemplate;

	/**
	 * 
	 */
	public void init() {
		template = new SimpleJdbcTemplate(dataSource);
		namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<String> retriveUserIds() {
		try {
			String sql = sqlProperties
					.getMessage(
							ElectiveCourseConstantsIF.DATABASESQL.RETRIVE_REGISTER_USERIDS_SQL,
							null, null);
			return jdbcTemplate.queryForList(sql, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public StatusInfo insertLogin(RegisterUser registerUser) {
		StatusInfo insertLoginStatus = null;
		try {
			insertLoginStatus = new StatusInfo();
			String sql = sqlProperties.getMessage(
					ElectiveCourseConstantsIF.DATABASESQL.INSERT_LOGIN_SQL,
					null, null);
			jdbcTemplate.update(
					sql,
					new Object[] { registerUser.getFirstName(),
							registerUser.getLastName(),
							registerUser.getUserPassword(),
							registerUser.getEmailId(),
							registerUser.getUserId(),
							registerUser.getLoginType(), registerUser.getAge(),
							registerUser.getDob(), registerUser.getSex(),
							registerUser.getAnswer1(),
							registerUser.getAnswer2(),
							registerUser.getAnswer3(),
							registerUser.getAnswer4() }, new int[] {
							Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
							Types.VARCHAR, Types.VARCHAR, Types.INTEGER,
							Types.INTEGER, Types.VARCHAR, Types.INTEGER,
							Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
							Types.VARCHAR });
			insertLoginStatus.setStatus(true);
			return insertLoginStatus;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertLoginStatus = new StatusInfo();
			insertLoginStatus.setErrMsg(e.getMessage());
			insertLoginStatus.setStatus(false);
			return insertLoginStatus;

		}
	}

	@Override
	public String retrivePassword(String userId) {
		try {
			String sql = sqlProperties
					.getMessage(
							ElectiveCourseConstantsIF.DATABASESQL.RETRIVE_PASSWORD_WHERE_USERID_SQL,
							null, null);

			return jdbcTemplate.queryForList(sql, String.class, userId).get(0);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public int retriveLoginType(String userId) {
		try {
			String sql = sqlProperties
					.getMessage(
							ElectiveCourseConstantsIF.DATABASESQL.RETRIVE_LOGINTYPE_WHERE_USERID_SQL,
							null, null);
			return jdbcTemplate.queryForList(sql, Integer.class, userId).get(0);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return -1;
		}
	}

	@Override
	public String retriveLocationValueFromLocatioName(String locationName) {
		String locationValue = null;
		try {
			String sql = sqlProperties
					.getMessage(
							ElectiveCourseConstantsIF.DATABASESQL.RETRIVE_LOCATORS_WHERE_LOCATORNAME_SQL,
							null, null);
			locationValue = jdbcTemplate.queryForObject(sql, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
		return locationValue;
	}

	@Override
	public boolean updateLocationValueFromLocatioName(String locationName,
			String locationValue) {
		try {
			String sql = sqlProperties
					.getMessage(
							ElectiveCourseConstantsIF.DATABASESQL.UPDATE_LOCATORVALUE_WHERE_LOCATORNAME_SQL,
							null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("locatioName", locationName);
			paramMap.put("locatorValue", locationValue);
			namedJdbcTemplate.update(sql, paramMap);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return false;
		}
	}

	@Override
	public List<FileInfo> retriveAllFileInfo() {

		List<FileInfo> fileInfoList = null;
		try {

			String sql = sqlProperties
					.getMessage(
							ElectiveCourseConstantsIF.DATABASESQL.RETRIVE_ALL_FILEINFO_SQL,
							null, null);

			Map<String, Object> paramMap = null;

			fileInfoList = namedJdbcTemplate.query(sql, paramMap,
					new FileInfoMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			fileInfoList = null;
		}

		return fileInfoList;
	}

	private final class FileInfoMapper implements RowMapper<FileInfo> {

		public FileInfo mapRow(ResultSet rs, int arg1) throws SQLException {

			FileInfo fileInfo = new FileInfo();

			fileInfo.setFileId(rs.getInt("FILEID"));
			fileInfo.setFileName(rs.getString("FILENAME"));
			fileInfo.setElectiveName(rs.getString("ELECTIVENAME"));
			return fileInfo;

		}

	}

	@Override
	public FileInfo findFileInfoByFileName(String fileName) {
		FileInfo fileInfoList = null;
		try {

			String sql = sqlProperties
					.getMessage(
							ElectiveCourseConstantsIF.DATABASESQL.RETRIVE_ALL_FILEINFO_SQL,
							null, null);

			Map<String, Object> paramMap = null;

			fileInfoList = namedJdbcTemplate.query(sql, paramMap,
					new FileInfoMapper()).get(0);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			fileInfoList = null;
		}

		return fileInfoList;
	}

	public StatusInfo insertFileName(FileInfo fileInfo) {

		StatusInfo insertLoginStatus = null;
		try {
			insertLoginStatus = new StatusInfo();
			String sql = sqlProperties.getMessage(
					ElectiveCourseConstantsIF.DATABASESQL.INSERT_FILENAME_SQL,
					null, null);
			jdbcTemplate.update(sql, new Object[] { fileInfo.getFileName(),
					fileInfo.getElectiveName() }, new int[] { Types.VARCHAR,
					Types.VARCHAR });
			insertLoginStatus.setStatus(true);
			return insertLoginStatus;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertLoginStatus = new StatusInfo();
			insertLoginStatus.setErrMsg(e.getMessage());
			insertLoginStatus.setStatus(false);
			return insertLoginStatus;

		}

	}

	@Override
	public boolean deleteFileName(FileInfoTemp fileInfoTemp) {
		try {

			String sql = sqlProperties
					.getMessage(
							ElectiveCourseConstantsIF.DATABASESQL.DELETE_FROM_FILENAME_WHERE_FILENAME_SQL,
							null, null);
			jdbcTemplate.update(sql, fileInfoTemp.getFileName());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String retrivePropertyFromConfig(String propertyName) {

		String value = null;

		try {

			String sql = sqlProperties
					.getMessage(
							ElectiveCourseConstantsIF.DATABASESQL.RETRIVE_PRPOERTYVALUE_SQL,
							null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("locatorName", propertyName);

			value = namedJdbcTemplate.queryForObject(sql, paramMap,
					new PropertyValueMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			value = null;
		}

		return value;
	}

	private final class PropertyValueMapper implements RowMapper<String> {

		public String mapRow(ResultSet rs, int arg1) throws SQLException {
			return rs.getString("LOCATORVALUE");

		}

	}

	@Override
	public FileInfo retriveFileInfoForElectiveName(String electiveName) {
		try {
			String sql = sqlProperties
					.getMessage(
							ElectiveCourseConstantsIF.DATABASESQL.RETRIVE_FILEINFO_WHERE_ELECTIVENAME_SQL,
							null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ELECTIVENAME", electiveName);

			return namedJdbcTemplate.queryForObject(sql, paramMap,
					new FileInfoMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public StatusInfo insertPredict(PredictedValue predictedValue) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties
					.getMessage(
							ElectiveCourseConstantsIF.DATABASESQL.INSERT_PREDICTIONVALUE_SQL,
							null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("SUBJECT1NAME", predictedValue.getSubject1Name());
			paramMap.put("SUBJECT2NAME", predictedValue.getSubject2Name());
			paramMap.put("SUBJECT3NAME", predictedValue.getSubject3Name());
			paramMap.put("SUBJECT4NAME", predictedValue.getSubject4Name());
			paramMap.put("SUBJECT1", predictedValue.getSubject1());
			paramMap.put("SUBJECT2", predictedValue.getSubject2());
			paramMap.put("SUBJECT3", predictedValue.getSubject3());
			paramMap.put("SUBJECT4", predictedValue.getSubject4());
			paramMap.put("ELECTIVECOURSE", predictedValue.getElectiveCourse());
			paramMap.put("PREDICTEDVALUE", predictedValue.getPredictedCapable());
			paramMap.put("USERID", predictedValue.getUserId());
			paramMap.put("COG", predictedValue.getCog());

			namedJdbcTemplate.update(sql, paramMap);

			statusInfo.setStatus(true);
			return statusInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;

		}
	}

	@Override
	public StatusInfo deleteRanking(String userId) {
		StatusInfo statusInfo = new StatusInfo();
		try {

			String sql = sqlProperties
					.getMessage(
							ElectiveCourseConstantsIF.DATABASESQL.DELETE_RANKING_WHERE_USERID_SQL,
							null, null);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("USERID", userId);
			namedJdbcTemplate.update(sql, paramMap);
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
	public List<PredictedValue> retriveAllPredictiveValues(String userId) {
		List<PredictedValue> predictedValueList = null;
		try {

			String sql = sqlProperties
					.getMessage(
							ElectiveCourseConstantsIF.DATABASESQL.RETRIVE_ALL_PREDICTEDINFO_FOR_USERID_SQL,
							null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("USERID", userId);

			predictedValueList = namedJdbcTemplate.query(sql, paramMap,
					new PredictedValueMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			predictedValueList = null;
		}

		return predictedValueList;
	}

	private final class PredictedValueMapper implements
			RowMapper<PredictedValue> {

		public PredictedValue mapRow(ResultSet rs, int arg1)
				throws SQLException {

			PredictedValue predictedValue = new PredictedValue();

			predictedValue.setCog(rs.getString("COG"));
			predictedValue.setElectiveCourse(rs.getString("ELECTIVECOURSE"));
			predictedValue.setUserId(rs.getString("USERID"));

			return predictedValue;

		}

	}

	@Override
	public StatusInfo insertRankingModel(List<RankingModel> rankingModelList) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties
					.getMessage(
							ElectiveCourseConstantsIF.DATABASESQL.INSERT_RANKINGMODEL_SQL,
							null, null);

			for (RankingModel rankingModel : rankingModelList) {

				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("ELECTIVENAME", rankingModel.getElectiveName());
				paramMap.put("COG", rankingModel.getCog());
				paramMap.put("USERID", rankingModel.getUserId());

				namedJdbcTemplate.update(sql, paramMap);

			}

			statusInfo.setStatus(true);
			return statusInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;

		}
	}

	@Override
	public List<RankingModel> rankElectives(String userId) {
		List<RankingModel> rankingModelList = null;
		try {

			String sql = sqlProperties
					.getMessage(
							ElectiveCourseConstantsIF.DATABASESQL.RANKING_ELECTIVE_WHERE_USERID_SQL,
							null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("USERID", userId);

			rankingModelList = namedJdbcTemplate.query(sql, paramMap,
					new RankingModelMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			rankingModelList = null;
		}

		return rankingModelList;
	}

	private final class RankingModelMapper implements RowMapper<RankingModel> {

		public RankingModel mapRow(ResultSet rs, int arg1) throws SQLException {

			RankingModel rankingModel = new RankingModel();

			rankingModel.setCog(rs.getDouble("COG"));
			rankingModel.setElectiveName(rs.getString("ELECTIVENAME"));
			rankingModel.setUserId(rs.getString("USERID"));

			return rankingModel;

		}

	}

}
