package com.cg.universityadmission.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cg.universityadmission.dao.IMacDao;
import com.cg.universityadmission.dao.MacDaoImp;
import com.cg.universityadmission.exception.UASException;

public class MacDaoImpTest {

	IMacDao macDao = null;

	@Test
	public void testViewApplicationByProgram() {

		macDao = new MacDaoImp();
		try {
			assertNotNull(macDao.viewApplicationByProgram("prg1"));
		} catch (UASException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Exception in Junit");
		}
	}

}
