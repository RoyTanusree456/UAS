package com.cg.universityadmission.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.cg.universityadmission.bean.ApplicantBean;
import com.cg.universityadmission.bean.ProgramScheduledBean;
import com.cg.universityadmission.exception.UASException;
import com.cg.universityadmission.util.DbUtil;

public class ApplicantDaoImp implements IApplicantDao {

	Connection conn = null;
	Logger logger = Logger.getRootLogger();

	/*******************************************************************************************************
	 - Function Name	:	viewAllPrograms()
	 - Input Parameters	:	
	 - Return Type		:	ArrayList<ProgramScheduledBean>
	 - Throws			:  	UASException
	 - Author			:	Tanusree Roy
	 - Creation Date	:	29/08/2018
	 - Description		:	Viewing scheduled programs
	 ********************************************************************************************************/
	
	@Override
	public ArrayList<ProgramScheduledBean> viewAllPrograms()
			throws UASException {

		ArrayList<ProgramScheduledBean> progList = new ArrayList<ProgramScheduledBean>();
		try {
			conn = DbUtil.estabblishConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement(IQueryMapper.SELECT_QUERY1);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int scheduledProgId = rs.getInt(1);
				String progName = rs.getString(2);
				String location = rs.getString(3);
				Date start = rs.getDate(4);
				Date end = rs.getDate(5);
				int sessions = rs.getInt(6);
				ProgramScheduledBean program = new ProgramScheduledBean(
						scheduledProgId, progName, location, start, end,
						sessions);
				progList.add(program);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Exception occurred : View all scheduled programs");
			throw new UASException("Exception : See log");
		}
		return progList;
	}

	/*******************************************************************************************************
	 - Function Name	:	applyForProgram(ApplicantBean applicant, String progName)
	 - Input Parameters	:	ApplicantBean applicant, String progName
	 - Return Type		:	int
	 - Throws			:  	UASException
	 - Author			:	Tanusree Roy
	 - Creation Date	:	29/08/2018
	 - Description		:	Applying for a program
	 ********************************************************************************************************/
	
	@Override
	public int applyForProgram(ApplicantBean applicant, String progName)
			throws UASException {

		String scheduledProgId = "";
		int insertStatus = 0;
		String status = "";
		int appId = 0;
		if (applicant.getMarks() >= 75) {
			status = "Accepted";
		} else {
			status = "Rejected";
		}
		try {
			conn = DbUtil.estabblishConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement(IQueryMapper.SELECT_QUERY2);
			preparedStatement.setString(1, progName);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				scheduledProgId = (String) rs.getString(1);
				preparedStatement = conn
						.prepareStatement(IQueryMapper.INSERT_QUERY1);
				preparedStatement.setString(1, applicant.getName());
				preparedStatement.setDate(2, applicant.getDob());
				preparedStatement.setString(3, applicant.getQualification());
				preparedStatement.setInt(4, applicant.getMarks());
				preparedStatement.setString(5, applicant.getGoals());
				preparedStatement.setString(6, applicant.getEmail());
				preparedStatement.setString(7, scheduledProgId);
				preparedStatement.setString(8, status);
				insertStatus = preparedStatement.executeUpdate();
				if (insertStatus == 1) {
					preparedStatement = conn
							.prepareStatement(IQueryMapper.SELECT_QUERY3);
					rs = preparedStatement.executeQuery();
					while (rs.next()) {
						appId = rs.getInt(1);
						// doi = rs.getDate(2);
					}
					if ("Rejected".equals(status)) {
						preparedStatement = conn
								.prepareStatement(IQueryMapper.UPDATE_QUERY5);
						preparedStatement.setInt(1, appId);
						int rejectStatus = preparedStatement.executeUpdate();
					}
					return appId;
				}
			} else {
				return -1;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Exception occurred : Apply for a program");
			throw new UASException("Exception : See log");
		}
		return insertStatus;
	}
	
	/*******************************************************************************************************
	 - Function Name	:	viewStatus(int appId)
	 - Input Parameters	:	int appId
	 - Return Type		:	String
	 - Throws			:  	UASException
	 - Author			:	Tanusree Roy
	 - Creation Date	:	29/08/2018
	 - Description		:	Viewing application status
	 ********************************************************************************************************/

	@Override
	public String viewStatus(int appId) throws UASException {

		try {
			conn = DbUtil.estabblishConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement(IQueryMapper.SELECT_QUERY4);
			preparedStatement.setInt(1, appId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Exception occurred : View application status");
			throw new UASException("Exception : See log");
		}
		return null;
	}
}