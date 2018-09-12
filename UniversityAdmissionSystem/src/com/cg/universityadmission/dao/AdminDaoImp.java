package com.cg.universityadmission.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.cg.universityadmission.bean.ApplicantBean;
import com.cg.universityadmission.bean.ProgramBean;
import com.cg.universityadmission.bean.ProgramScheduledBean;
import com.cg.universityadmission.exception.UASException;
import com.cg.universityadmission.util.DbUtil;

public class AdminDaoImp implements IAdminDao {

	Connection conn = null;
	Logger logger = Logger.getRootLogger();

	/*******************************************************************************************************
	 - Function Name	:	login(String loginId, String password)
	 - Input Parameters	:	String loginId, String password
	 - Return Type		:	boolean
	 - Throws			:  	UASException
	 - Author			:	Tanusree Roy
	 - Creation Date	:	30/08/2018
	 - Description		:	Logging into MAC account
	 ********************************************************************************************************/
	
	@Override
	public boolean login(String loginId, String password) throws UASException {

		try {
			conn = DbUtil.estabblishConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement(IQueryMapper.SELECT_QUERY5);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				if ((rs.getString(1).equals(loginId))
						&& (rs.getString(2).equals(password))) {
					return true;
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Exception occurred : Admin login");
			throw new UASException("Exception : See log");
		}
		return false;
	}

	/*******************************************************************************************************
	 - Function Name	:	updateDescription(String description, String progName)
	 - Input Parameters	:	String description, String progName
	 - Return Type		:	int
	 - Throws			:  	UASException
	 - Author			:	Tanusree Roy
	 - Creation Date	:	31/08/2018
	 - Description		:	Updating description of a program
	 ********************************************************************************************************/
	
	@Override
	public int updateDescription(String description, String progName)
			throws UASException {

		int status = 0;
		try {
			conn = DbUtil.estabblishConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement(IQueryMapper.UPDATE_QUERY1);
			preparedStatement.setString(1, description);
			preparedStatement.setString(2, progName);
			status = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Exception occurred : Update program description");
			throw new UASException("Exception : See log : " + e.getMessage());
		}
		return status;
	}

	/*******************************************************************************************************
	 - Function Name	:	updateEligibility(String eligibility, String progName)
	 - Input Parameters	:	String eligibility, String progName
	 - Return Type		:	int
	 - Throws			:  	UASException
	 - Author			:	Tanusree Roy
	 - Creation Date	:	31/08/2018
	 - Description		:	Updating eligibility of a program
	 ********************************************************************************************************/
	
	@Override
	public int updateEligibility(String eligibility, String progName)
			throws UASException {

		int status = 0;
		try {
			conn = DbUtil.estabblishConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement(IQueryMapper.UPDATE_QUERY2);
			preparedStatement.setString(1, eligibility);
			preparedStatement.setString(2, progName);
			status = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Exception occurred : Update eligibility for a program");
			throw new UASException("Exception : See log");
		}
		return status;
	}

	/*******************************************************************************************************
	 - Function Name	:	updateDescriptionEligibility(String description, String eligibility, String progName)
	 - Input Parameters	:	String description, String eligibility, String progName
	 - Return Type		:	int
	 - Throws			:  	UASException
	 - Author			:	Tanusree Roy
	 - Creation Date	:	31/08/2018
	 - Description		:	Updating description and eligibility of a program
	 ********************************************************************************************************/
	
	@Override
	public int updateDescriptionEligibility(String description,
			String eligibility, String progName) throws UASException {

		int status = 0;
		try {
			conn = DbUtil.estabblishConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement(IQueryMapper.UPDATE_QUERY3);
			preparedStatement.setString(1, description);
			preparedStatement.setString(2, eligibility);
			preparedStatement.setString(3, progName);
			status = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Exception occurred : Update program description and eligibility for a program");
			throw new UASException("Exception : See log");
		}
		return status;
	}

	/*******************************************************************************************************
	 - Function Name	:	addProgramsOffered(ProgramBean program)
	 - Input Parameters	:	ProgramBean program
	 - Return Type		:	int
	 - Throws			:  	UASException
	 - Author			:	Tanusree Roy
	 - Creation Date	:	31/08/2018
	 - Description		:	Adding a program
	 ********************************************************************************************************/
	
	@Override
	public int addProgramsOffered(ProgramBean program) throws UASException {

		int status = 0;
		try {
			conn = DbUtil.estabblishConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement(IQueryMapper.INSERT_QUERY2);
			preparedStatement.setString(1, program.getProgName());
			preparedStatement.setString(2, program.getDescription());
			preparedStatement.setString(3, program.getEligibility());
			preparedStatement.setInt(4, program.getDuration());
			preparedStatement.setString(5, program.getCertificate());
			status = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Exception occurred : Add new program");
			return 0;
			// throw new UASException("Exception : See log");
		}
		return status;
	}
	
	/*******************************************************************************************************
	 - Function Name	:	deleteProgramsOffered(String progName)
	 - Input Parameters	:	String progName
	 - Return Type		:	int
	 - Throws			:  	UASException
	 - Author			:	Tanusree Roy
	 - Creation Date	:	31/08/2018
	 - Description		:	Deleting a program
	 ********************************************************************************************************/

	@Override
	public int deleteProgramsOffered(String progName) throws UASException {

		int status = 0;
		try {
			conn = DbUtil.estabblishConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement(IQueryMapper.DELETE_QUERY1);
			preparedStatement.setString(1, progName);
			status = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Exception occurred : Delete a program");
			throw new UASException("Exception : See log");
		}
		return status;
	}

	/*******************************************************************************************************
	 - Function Name	:	addProgramsScheduled(ProgramScheduledBean program)
	 - Input Parameters	:	ProgramScheduledBean program
	 - Return Type		:	int
	 - Throws			:  	UASException
	 - Author			:	Tanusree Roy
	 - Creation Date	:	31/08/2018
	 - Description		:	Adding a schedule for a program
	 ********************************************************************************************************/
	
	@Override
	public int addProgramsScheduled(ProgramScheduledBean program)
			throws UASException {

		int status = 0;
		try {
			conn = DbUtil.estabblishConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement(IQueryMapper.SELECT_QUERY6);
			preparedStatement.setString(1, program.getProgName());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				preparedStatement = conn
						.prepareStatement(IQueryMapper.INSERT_QUERY3);
				preparedStatement.setString(1, program.getProgName());
				preparedStatement.setString(2, program.getLocation());
				preparedStatement.setDate(3, program.getStart());
				preparedStatement.setDate(4, program.getEnd());
				preparedStatement.setInt(5, program.getSessions());
				status = preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Exception occurred : Add schedule for a program");
			throw new UASException("Exception : See log");
		}
		return status;
	}

	/*******************************************************************************************************
	 - Function Name	:	deleteProgramsScheduled(int scheduledProgId)
	 - Input Parameters	:	int scheduledProgId
	 - Return Type		:	int
	 - Throws			:  	UASException
	 - Author			:	Tanusree Roy
	 - Creation Date	:	31/08/2018
	 - Description		:	Deleting a schedule for a program (only if no one has applied for it)
	 ********************************************************************************************************/
	
	@Override
	public int deleteProgramsScheduled(int scheduledProgId) throws UASException {

		int count = 0, status = 0;
		try {
			conn = DbUtil.estabblishConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement(IQueryMapper.SELECT_QUERY7);
			preparedStatement.setInt(1, scheduledProgId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count == 0) {
				preparedStatement = conn
						.prepareStatement(IQueryMapper.DELETE_QUERY2);
				preparedStatement.setInt(1, scheduledProgId);
				status = preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Exception occurred : Delete schedule of a program");
			throw new UASException("Exception : See log");
		}
		return status;
	}
	
	/*******************************************************************************************************
	 - Function Name	:	viewApplicationByStatus(String applicationStatus, String progName)
	 - Input Parameters	:	String applicationStatus, String progName
	 - Return Type		:	ArrayList<ApplicantBean>
	 - Throws			:  	UASException
	 - Author			:	Tanusree Roy
	 - Creation Date	:	31/08/2018
	 - Description		:	Viewing status-wise list of applications for a program (Accepted/Confirmed/Rejected)
	 ********************************************************************************************************/

	@Override
	public ArrayList<ApplicantBean> viewApplicationByStatus(
			String applicationStatus, String progName) throws UASException {

		ArrayList<ApplicantBean> applicationList = new ArrayList<ApplicantBean>();
		try {
			conn = DbUtil.estabblishConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement(IQueryMapper.SELECT_QUERY8);
			preparedStatement.setString(1, applicationStatus);
			preparedStatement.setString(2, progName);
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
				ApplicantBean application = new ApplicantBean(applicationId,
						name, dob, qualification, marks, goals, email, progId,
						status, doi);
				applicationList.add(application);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Exception occurred : View applications for a program by status");
			throw new UASException("Exception : See log");
		}
		return applicationList;
	}
	
	/*******************************************************************************************************
	 - Function Name	:	viewScheduledProgramsInPeriod(Date start, Date end)
	 - Input Parameters	:	Date start, Date end
	 - Return Type		:	ArrayList<ProgramScheduledBean>
	 - Throws			:  	UASException
	 - Author			:	Tanusree Roy
	 - Creation Date	:	31/08/2018
	 - Description		:	Viewing list of scheduled program(s) to commence within a period
	 ********************************************************************************************************/

	@Override
	public ArrayList<ProgramScheduledBean> viewScheduledProgramsInPeriod(
			Date start, Date end) throws UASException {

		ArrayList<ProgramScheduledBean> progList = new ArrayList<ProgramScheduledBean>();
		try {
			conn = DbUtil.estabblishConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement(IQueryMapper.SELECT_QUERY9);
			preparedStatement.setDate(1, start);
			preparedStatement.setDate(2, end);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int scheduledProgId = rs.getInt(1);
				String progName = rs.getString(2);
				String location = rs.getString(3);
				Date start1 = rs.getDate(4);
				Date end1 = rs.getDate(5);
				int sessions = rs.getInt(6);
				ProgramScheduledBean program = new ProgramScheduledBean(
						scheduledProgId, progName, location, start1, end1,
						sessions);
				progList.add(program);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Exception occurred : View scheduled programs in a time period");
			throw new UASException("Exception : See log");
		}
		return progList;
	}
}