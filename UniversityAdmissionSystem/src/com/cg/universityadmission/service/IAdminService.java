package com.cg.universityadmission.service;

import java.sql.Date;
import java.util.ArrayList;

import com.cg.universityadmission.bean.ApplicantBean;
import com.cg.universityadmission.bean.ProgramBean;
import com.cg.universityadmission.bean.ProgramScheduledBean;
import com.cg.universityadmission.exception.UASException;

public interface IAdminService {
	public abstract boolean login(String loginId, String password)
			throws UASException;

	public abstract int updateDescription(String description, String progName)
			throws UASException;

	public abstract int updateEligibility(String eligibility, String progName)
			throws UASException;

	public abstract int updateDescriptionEligibility(String description,
			String eligibility, String progName) throws UASException;

	public abstract int addProgramsOffered(ProgramBean program)
			throws UASException;

	public abstract int deleteProgramsOffered(String progName)
			throws UASException;

	public abstract int addProgramsScheduled(ProgramScheduledBean program)
			throws UASException;

	public abstract int deleteProgramsScheduled(int scheduledProgId)
			throws UASException;

	public abstract ArrayList<ApplicantBean> viewApplicationByStatus(
			String applicationStatus, String progName) throws UASException;

	public abstract ArrayList<ProgramScheduledBean> viewScheduledProgramsInPeriod(
			Date start, Date end) throws UASException;

}
