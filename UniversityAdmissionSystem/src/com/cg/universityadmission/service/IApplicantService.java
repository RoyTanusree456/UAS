package com.cg.universityadmission.service;

import java.util.ArrayList;

import com.cg.universityadmission.bean.ApplicantBean;
import com.cg.universityadmission.bean.ProgramBean;
import com.cg.universityadmission.bean.ProgramScheduledBean;
import com.cg.universityadmission.exception.UASException;

public interface IApplicantService {

	public abstract ArrayList<ProgramScheduledBean> viewAllPrograms()
			throws UASException;

	public abstract int applyForProgram(ApplicantBean applicant, String progName)
			throws UASException;

	public abstract String viewStatus(int appId) throws UASException;

}
