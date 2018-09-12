package com.cg.universityadmission.dao;

import java.util.ArrayList;

import com.cg.universityadmission.bean.ApplicantBean;
import com.cg.universityadmission.bean.ProgramBean;
import com.cg.universityadmission.bean.ProgramScheduledBean;
import com.cg.universityadmission.exception.UASException;

public interface IApplicantDao {

	public abstract ArrayList<ProgramScheduledBean> viewAllPrograms()
			throws UASException;

	public abstract int applyForProgram(ApplicantBean applicant, String progName)
			throws UASException;

	public abstract String viewStatus(int appId) throws UASException;
}
