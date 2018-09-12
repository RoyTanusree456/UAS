package com.cg.universityadmission.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.universityadmission.bean.ApplicantBean;
import com.cg.universityadmission.bean.ProgramScheduledBean;
import com.cg.universityadmission.dao.ApplicantDaoImp;
import com.cg.universityadmission.dao.IApplicantDao;
import com.cg.universityadmission.exception.UASException;

public class ApplicantServiceImp implements IApplicantService {

	IApplicantDao applicantDao = null;

	@Override
	public ArrayList<ProgramScheduledBean> viewAllPrograms()
			throws UASException {

		applicantDao = new ApplicantDaoImp();
		return applicantDao.viewAllPrograms();

	}

	@Override
	public int applyForProgram(ApplicantBean applicant, String progName)
			throws UASException {

		applicantDao = new ApplicantDaoImp();
		return applicantDao.applyForProgram(applicant, progName);

	}

	@Override
	public String viewStatus(int appId) throws UASException {

		applicantDao = new ApplicantDaoImp();
		return applicantDao.viewStatus(appId);
	}

	// **********validations**************

	// name of applicant
	public boolean isValidName(String name) {
		Pattern pattern = Pattern.compile("^[A-Za-z\\s]{2,20}$");
		Matcher matcherOb = pattern.matcher(name);
		return (matcherOb.matches());
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

	// marks
	public boolean isValidMarks(String marks) {

		if (isInteger(marks)) {
			if (Integer.parseInt(marks) >= 0 && Integer.parseInt(marks) <= 100)
				return true;
		}
		return false;
	}

	// Id
	public boolean isValidId(String id) {

		if (isInteger(id)) {
			return true;
		}
		return false;
	}

	// number
	public boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// email
	public boolean isValidEmail(String email) {
		Pattern pattern = Pattern
				.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
		Matcher matcherOb = pattern.matcher(email);
		return matcherOb.matches();
	}

	// text
	public boolean isValidText(String text) {

		Pattern pattern = Pattern.compile("^[A-Za-z\\s\\.]{1,}$");
		Matcher matcherOb = pattern.matcher(text);
		return (matcherOb.matches());

	}
}