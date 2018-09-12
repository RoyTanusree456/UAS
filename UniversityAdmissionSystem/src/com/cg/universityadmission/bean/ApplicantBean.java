package com.cg.universityadmission.bean;

//import java.util.Date;

import java.sql.Date;

public class ApplicantBean {

	private int applicationId;
	private String name;
	private Date dob;
	private String qualification;
	private int marks;
	private String goals;
	private String email;
	private int progId;
	private String status;
	private Date doi;

	public ApplicantBean() {
		super();
	}

	// for insertion(when applicant applies)
	public ApplicantBean(String name, Date dob, String qualification,
			int marks, String goals, String email) {
		super();
		this.name = name;
		this.dob = dob;
		this.qualification = qualification;
		this.marks = marks;
		this.goals = goals;
		this.email = email;
	}

	// for retrieval(when mac views all applications)

	public ApplicantBean(int applicationId, String name, Date dob,
			String qualification, int marks, String goals, String email,
			int progId, String status, Date doi) {
		super();
		this.applicationId = applicationId;
		this.name = name;
		this.dob = dob;
		this.qualification = qualification;
		this.marks = marks;
		this.goals = goals;
		this.email = email;
		this.progId = progId;
		this.status = status;
		this.doi = doi;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public String getGoals() {
		return goals;
	}

	public void setGoals(String goals) {
		this.goals = goals;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getProgId() {
		return progId;
	}

	public void setProgId(int progId) {
		this.progId = progId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDoi() {
		return doi;
	}

	public void setDoi(Date doi) {
		this.doi = doi;
	}

}
