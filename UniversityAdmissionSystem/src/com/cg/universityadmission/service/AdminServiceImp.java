package com.cg.universityadmission.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.universityadmission.bean.ApplicantBean;
import com.cg.universityadmission.bean.ProgramBean;
import com.cg.universityadmission.bean.ProgramScheduledBean;
import com.cg.universityadmission.dao.AdminDaoImp;
import com.cg.universityadmission.dao.IAdminDao;
import com.cg.universityadmission.exception.UASException;

public class AdminServiceImp implements IAdminService {
	IAdminDao adminDao = null;

	public boolean login(String loginId, String password) throws UASException {
		adminDao = new AdminDaoImp();
		return adminDao.login(loginId, password);
	}

	@Override
	public int updateDescription(String description, String progName)
			throws UASException {
		adminDao = new AdminDaoImp();
		return adminDao.updateDescription(description, progName);
	}

	@Override
	public int updateEligibility(String eligibility, String progName)
			throws UASException {
		adminDao = new AdminDaoImp();
		return adminDao.updateEligibility(eligibility, progName);
	}

	@Override
	public int updateDescriptionEligibility(String description,
			String eligibility, String progName) throws UASException {
		adminDao = new AdminDaoImp();
		return adminDao.updateDescriptionEligibility(description, eligibility,
				progName);
	}

	@Override
	public int addProgramsOffered(ProgramBean program) throws UASException {
		adminDao = new AdminDaoImp();
		return adminDao.addProgramsOffered(program);
	}

	@Override
	public int deleteProgramsOffered(String progName) throws UASException {
		adminDao = new AdminDaoImp();
		return adminDao.deleteProgramsOffered(progName);
	}

	@Override
	public int addProgramsScheduled(ProgramScheduledBean program)
			throws UASException {
		adminDao = new AdminDaoImp();
		return adminDao.addProgramsScheduled(program);
	}

	@Override
	public int deleteProgramsScheduled(int scheduledProgId) throws UASException {
		adminDao = new AdminDaoImp();
		return adminDao.deleteProgramsScheduled(scheduledProgId);
	}

	@Override
	public ArrayList<ApplicantBean> viewApplicationByStatus(
			String applicationStatus, String progName) throws UASException {
		adminDao = new AdminDaoImp();
		return adminDao.viewApplicationByStatus(applicationStatus, progName);
	}

	@Override
	public ArrayList<ProgramScheduledBean> viewScheduledProgramsInPeriod(
			Date start, Date end) throws UASException {
		adminDao = new AdminDaoImp();
		return adminDao.viewScheduledProgramsInPeriod(start, end);
	}

	// ******validations**********

	// progName
	public boolean isValidProgram(String progName) {

		if(progName.length()>5)
			return false;
		try {
			Integer.parseInt(progName);
			return false;
		} catch (Exception e) {
			return true;
		}

	}
	
	// text : progDesc, progEligibility
		public boolean isValidText(String text) {

			Pattern pattern = Pattern.compile("^[A-Za-z\\s\\.]{1,}$");
			Matcher matcherOb = pattern.matcher(text);
			return (matcherOb.matches());

		}
	
	// location
		public boolean isValidLocation(String location) {
			Pattern pattern = Pattern.compile("^[A-Za-z]{1,10}$");
			Matcher matcherOb = pattern.matcher(location);
			return (matcherOb.matches());
		}

	// Id
	public boolean isValidId(String id) {

		if (isInteger(id)) {
			return true;
		}
		return false;
	}

	// duration
	public boolean isInteger(String input) {
		
		try {
			Integer.parseInt(input);
			
		} catch (Exception e) {
			return false;
		}
		if(Integer.parseInt(input)<=0)
		{
			return false;
		}
		return true;
	}

	// date
		public boolean isValidDate(String date) {
			Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
			Matcher matcherOb = pattern.matcher(date);
			if (matcherOb.matches()) {
				int yyyy = Integer.parseInt(date.substring(0, 4));
				int mm = Integer.parseInt(date.substring(5, 7));
				int dd = Integer.parseInt(date.substring(8));
				if ((yyyy > 2018 && mm <= 12 && dd <= 31)
						|| (yyyy == 2018 && mm >= 10 && dd <= 31))
					return true;
			}
			return false;
		}

		// dob
		public boolean isValidDob(String date) {

			Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
			Matcher matcherOb = pattern.matcher(date);
			if (matcherOb.matches()) {
				int yyyy = Integer.parseInt(date.substring(0, 4));
				int mm = Integer.parseInt(date.substring(5, 7));
				int dd = Integer.parseInt(date.substring(8));
				int age = calculateAge(LocalDate.of(yyyy, mm, dd),
						LocalDate.of(2018, 9, 10));
				if (age >= 18 && age <= 28) {
					return true;
				}
			}
			return false;
		}

		public int calculateAge(LocalDate dob, LocalDate currentDate) {
			if ((dob != null) && (currentDate != null)) {
				return Period.between(dob, currentDate).getYears();
			} else {
				return 0;
			}
		}

	// sessions
	public boolean isValidSession(String s) {
		if (isInteger(s)) {
			int session = Integer.parseInt(s);
			if (session >= 0 && session <= 7)
				return true;
		}
		return false;
	}
}
