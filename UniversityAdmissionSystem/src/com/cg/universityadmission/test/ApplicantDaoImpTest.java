package com.cg.universityadmission.test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import com.cg.universityadmission.bean.ApplicantBean;
import com.cg.universityadmission.dao.ApplicantDaoImp;
import com.cg.universityadmission.dao.IApplicantDao;
import com.cg.universityadmission.exception.UASException;

public class ApplicantDaoImpTest {

	IApplicantDao applicantDao = null;

	@Test
	public void testApplyForProgram() {

		applicantDao = new ApplicantDaoImp();
		ApplicantBean applicant = new ApplicantBean("TestName",
				Date.valueOf("1995-04-30"), "TestQual", 100, "TestGoal",
				"test.mail@test.com");
		try {
			assertEquals(1145, applicantDao.applyForProgram(applicant, "prg5"));
		} catch (UASException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Exception in Junit");
			
		}
	}
}
