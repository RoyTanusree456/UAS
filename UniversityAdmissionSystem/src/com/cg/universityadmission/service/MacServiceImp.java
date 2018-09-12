package com.cg.universityadmission.service;

import java.util.ArrayList;

import com.cg.universityadmission.bean.ApplicantBean;
import com.cg.universityadmission.dao.AdminDaoImp;
import com.cg.universityadmission.dao.IAdminDao;
import com.cg.universityadmission.dao.IMacDao;
import com.cg.universityadmission.dao.MacDaoImp;
import com.cg.universityadmission.exception.UASException;

public class MacServiceImp implements IMacService {
	IMacDao macDao = null;

	public boolean login(String loginId, String password) throws UASException {
		macDao = new MacDaoImp();
		return macDao.login(loginId, password);
	}

	@Override
	public ArrayList<ApplicantBean> viewApplicationByProgram(String progName)
			throws UASException {

		macDao = new MacDaoImp();
		return macDao.viewApplicationByProgram(progName);
	}

	@Override
	public int updateApplicationStatus(int applicationId, String newStatus)
			throws UASException {

		macDao = new MacDaoImp();
		return macDao.updateApplicationStatus(applicationId, newStatus);
	}

	// *******validations

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

}
