package com.cg.universityadmission.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.cg.universityadmission.bean.ApplicantBean;
import com.cg.universityadmission.exception.UASException;
import com.cg.universityadmission.util.DbUtil;

public class MacDaoImp implements IMacDao {

	Connection conn = null;
	Logger logger = Logger.getRootLogger();

	/*******************************************************************************************************
	 - Function Name	:	login(String loginId, String password)
	 - Input Parameters	:	String loginId, String password
	 - Return Type		:	boolean
	 - Throws			:  	UASException
	 - Author			:	Tanusree Roy
	 - Creation Date	:	30/08/2018
	 - Description		:	Logging into admin account
	 ********************************************************************************************************/
	
	@Override
	public boolean login(String loginId, String password) throws UASException {

		try {
			conn = DbUtil.estabblishConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement(IQueryMapper.SELECT_QUERY10);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				if ((rs.getString(1).equals(loginId))
						&& (rs.getString(2).equals(password))) {
					return true;
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Exception occurred : MAC login");
			throw new UASException("Exception : See log");
		}
		return false;
	}

	/*******************************************************************************************************
	 - Function Name	:	viewApplicationByProgram(String progName)
	 - Input Parameters	:	String progName
	 - Return Type		:	ArrayList<ApplicantBean>
	 - Throws			:  	UASException
	 - Author			:	Tanusree Roy
	 - Creation Date	:	30/08/2018
	 - Description		:	Viewing application(s) for a program
	 ********************************************************************************************************/
	
	@Override
	public ArrayList<ApplicantBean> viewApplicationByProgram(String progName)
			throws UASException {

		ArrayList<ApplicantBean> applicationList = new ArrayList<ApplicantBean>();
		int count = 0;
		try {
			conn = DbUtil.estabblishConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement(IQueryMapper.SELECT_QUERY11);
			preparedStatement.setString(1, progName);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int applicationId = rs.getInt(1);
				String name = rs.getString(2);
				Date dob = rs.getDate(3);
				String qualification = rs.getString(4);
				int marks = rs.getInt(5);
				String goals = rs.getString(6);
				String email = rs.getString(7);
				int progId = rs.getInt(8);
				String status = rs.getString(9);
				Date doi = rs.getDate(10);
				count++;
				ApplicantBean application = new ApplicantBean(applicationId,
						name, dob, qualification, marks, goals, email, progId,
						status, doi);
				applicationList.add(application);
			}
			if (count == 0)
				return null;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Exception occurred : View applications for a program");
			throw new UASException("Exception : See log");
		}
		return applicationList;
	}

	/*******************************************************************************************************
	 - Function Name	:	updateApplicationStatus(int applicationId, String newStatus)
	 - Input Parameters	:	int applicationId, String newStatus
	 - Return Type		:	int
	 - Throws			:  	UASException
	 - Author			:	Tanusree Roy
	 - Creation Date	:	01/09/2018
	 - Description		:	Updating application status of an application
	 ********************************************************************************************************/
	
	@Override
	public int updateApplicationStatus(int applicationId, String newStatus)
			throws UASException {

		int updateStatus = 0;
		int insertStatus = 0;
		int deleteStatus = 0;
		String oldStatus = "";
		try {
			conn = DbUtil.estabblishConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement(IQueryMapper.SELECT_QUERY12);
			preparedStatement.setInt(1, applicationId);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.isBeforeFirst() == false)// wrong app_id
			{
				return -2;
			}

			while (rs.next()) {
				double diffDay = rs.getDouble(1);
				// String interviewTime = rs.getString(2);
				/*
				 * int hi=Integer.parseInt(interviewTime.substring(0, 2)); int
				 * mi=Integer.parseInt(interviewTime.substring(3, 5)); int
				 * si=Integer.parseInt(interviewTime.substring(6));
				 * if(hi<IQueryMapper.hh)
				 */
				if (diffDay >= 0) {
					conn = DbUtil.estabblishConnection();
					preparedStatement = conn
							.prepareStatement(IQueryMapper.SELECT_QUERY4);
					preparedStatement.setInt(1, applicationId);
					rs = preparedStatement.executeQuery();
					while (rs.next()) {
						oldStatus = rs.getString(1);
					}
					if (oldStatus.equals(newStatus)) // same status
					{
						return -1;
					} else {
						conn = DbUtil.estabblishConnection();
						preparedStatement = conn
								.prepareStatement(IQueryMapper.UPDATE_QUERY4);
						preparedStatement.setString(1, newStatus);
						preparedStatement.setInt(2, applicationId);
						updateStatus = preparedStatement.executeUpdate();
						if ("Confirmed".equals(newStatus)) {
							String email = "";
							int progId = 0;
							conn = DbUtil.estabblishConnection();
							preparedStatement = conn
									.prepareStatement(IQueryMapper.SELECT_QUERY13);
							preparedStatement.setInt(1, applicationId);
							rs = preparedStatement.executeQuery();
							while (rs.next()) {
								email = rs.getString(1);
								progId = rs.getInt(2);
							}
							conn = DbUtil.estabblishConnection();
							preparedStatement = conn
									.prepareStatement(IQueryMapper.INSERT_QUERY4);
							preparedStatement.setString(1, email);
							preparedStatement.setInt(2, applicationId);
							preparedStatement.setInt(3, progId);
							insertStatus = preparedStatement.executeUpdate();
						} else if ("Rejected".equals(newStatus)) {
							conn = DbUtil.estabblishConnection();
							preparedStatement = conn
									.prepareStatement(IQueryMapper.DELETE_QUERY3);
							preparedStatement.setInt(1, applicationId);
							deleteStatus = preparedStatement.executeUpdate();
						}

					}
				}
			}

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Exception occurred : Update application status");
			throw new UASException("Exception : See log");
		}
		return updateStatus;
	}
}