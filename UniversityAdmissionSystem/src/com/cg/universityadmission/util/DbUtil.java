package com.cg.universityadmission.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.cg.universityadmission.exception.UASException;

public class DbUtil {

	static Connection conn = null;
	
	public static Connection estabblishConnection() throws UASException {

		Logger logger=Logger.getRootLogger();
		try
		{
		conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@10.219.34.3:1521/orcl", "trg203",
				"training203");
		}
		catch(Exception e)
		{
			logger.error("Exception occurred : Establish connection");
			throw new UASException("Exception : See log : "+e.getMessage());
		}

		return conn;
	}
}
