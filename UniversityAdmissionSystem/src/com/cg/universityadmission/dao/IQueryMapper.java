package com.cg.universityadmission.dao;

public interface IQueryMapper {

	// applicant's
	public static final String SELECT_QUERY1 = "SELECT * FROM PROGRAMS_SCHEDULED ORDER BY Program_Name";
	public static final String SELECT_QUERY2 = "SELECT Scheduled_Program_Id FROM PROGRAMS_SCHEDULED WHERE PROGRAM_NAME=?";
	public static final String SELECT_QUERY3 = "SELECT app_id_seq.CURRVAL from DUAL";
	//public static final String SELECT_QUERY3 = "SELECT app_id_seq.CURRVAL, Date_Of_Interview from DUAL, APPLICATION WHERE Application_Id = app_id_seq.CURRVAL ";
	public static final String SELECT_QUERY4 = "SELECT Status from APPLICATION WHERE Application_Id=?";

	public static final String INSERT_QUERY1 = "INSERT INTO APPLICATION VALUES(app_id_seq.NEXTVAL,?,?,?,?,?,?,?,?,SYSDATE+3)";

	public static final String UPDATE_QUERY5 = "UPDATE APPLICATION SET Date_Of_Interview = NULL WHERE Application_Id = ?";

	// admin's
	public static final String SELECT_QUERY5 = "SELECT * FROM USERS WHERE Role='admin'";
	public static final String SELECT_QUERY6 = "SELECT Program_Name from PROGRAMS_OFFERED WHERE Program_Name=?";
	public static final String SELECT_QUERY7 = "SELECT COUNT(Application_Id) FROM APPLICATION WHERE SCHEDULED_PROGRAM_ID=?";
	public static final String SELECT_QUERY8 = "SELECT * FROM APPLICATION WHERE STATUS=? AND SCHEDULED_PROGRAM_ID IN(SELECT SCHEDULED_PROGRAM_ID FROM PROGRAMS_SCHEDULED WHERE PROGRAM_NAME=?)";
	public static final String SELECT_QUERY9 = "SELECT * FROM PROGRAMS_SCHEDULED WHERE START_DATE BETWEEN ? AND ?";

	public static final String UPDATE_QUERY1 = "UPDATE PROGRAMS_OFFERED SET Description=? WHERE Program_Name=?";
	public static final String UPDATE_QUERY2 = "UPDATE PROGRAMS_OFFERED SET Applicant_Eligibility=? WHERE Program_Name=?";
	public static final String UPDATE_QUERY3 = "UPDATE PROGRAMS_OFFERED SET Description=?, Applicant_Eligibility=? WHERE Program_Name=?";

	public static final String INSERT_QUERY2 = "INSERT INTO PROGRAMS_OFFERED VALUES(?,?,?,?,?)";
	public static final String INSERT_QUERY3 = "INSERT INTO PROGRAMS_SCHEDULED VALUES(prog_id_seq.NEXTVAL,?,?,?,?,?)";

	public static final String DELETE_QUERY1 = "DELETE FROM PROGRAMS_OFFERED WHERE PROGRAM_NAME=?";
	public static final String DELETE_QUERY2 = "DELETE FROM PROGRAMS_SCHEDULED WHERE SCHEDULED_PROGRAM_ID=?";

	// mac's
	public static final String SELECT_QUERY10 = "SELECT * FROM USERS WHERE Role='mac'";
	public static final String SELECT_QUERY11 = "SELECT * FROM APPLICATION a, PROGRAMS_SCHEDULED ps WHERE a.Scheduled_Program_Id = ps.Scheduled_Program_Id AND ps.Program_Name=?";
	public static final String SELECT_QUERY12 = "SELECT ROUND((SYSDATE-Date_Of_Interview),2) FROM APPLICATION WHERE Application_Id = ?";
	//public static final String SELECT_QUERY12 = "SELECT ROUND((SYSDATE-Date_Of_Interview),2), TO_CHAR (Date_Of_Interview, 'HH24:MI:SS') FROM APPLICATION WHERE Application_Id = ?";
	public static final String SELECT_QUERY13 = "SELECT Email_Id, Scheduled_Program_Id FROM APPLICATION WHERE Application_Id=?";

	public static final String UPDATE_QUERY4 = "UPDATE APPLICATION SET Status = ? WHERE Application_Id = ?";

	public static final String INSERT_QUERY4 = "INSERT INTO PARTICIPANT VALUES(roll_seq.NEXTVAL,?,?,?)";
	
	public static final String DELETE_QUERY3 = "DELETE FROM PARTICIPANT WHERE Application_Id = ?";

	/*
	 * Date date = new Date(); SimpleDateFormat ft = new
	 * SimpleDateFormat("hh:mm:ss"); String time = ft.format(date); public
	 * static final int hh = Integer.parseInt(time.substring(0, 2)); public
	 * static final int mm = Integer.parseInt(time.substring(3, 5)); public
	 * static final int ss = Integer.parseInt(time.substring(6, 8));
	 */

}
