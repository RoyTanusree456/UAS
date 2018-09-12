package com.cg.universityadmission.service;

import java.util.ArrayList;

import com.cg.universityadmission.bean.ApplicantBean;
import com.cg.universityadmission.exception.UASException;

public interface IMacService {
	public abstract boolean login(String loginId, String password)
			throws UASException;

	public abstract ArrayList<ApplicantBean> viewApplicationByProgram(
			String progName) throws UASException;

	public abstract int updateApplicationStatus(int applicationId,
			String newStatus) throws UASException;

}
