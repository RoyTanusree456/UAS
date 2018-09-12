package com.cg.universityadmission.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cg.universityadmission.dao.AdminDaoImp;
import com.cg.universityadmission.exception.UASException;

public class AdminDaoImpTest {

	AdminDaoImp adminDao = null;

	@Test
	public void testLogin() {

		adminDao = new AdminDaoImp();

		try {
			assertEquals(true, adminDao.login("aa101", "aa101"));
		} catch (UASException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Exception in Junit");
		}

	}

}
