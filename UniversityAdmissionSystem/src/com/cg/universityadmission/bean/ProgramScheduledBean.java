package com.cg.universityadmission.bean;

import java.sql.Date;


public class ProgramScheduledBean {
	
	private int scheduledProgId;
	private String progName;
	private String location;
	private Date start;
	private Date end;
	private int sessions;
	public ProgramScheduledBean() {
		super();
	}
	public ProgramScheduledBean(int scheduledProgId, String progName,
			String location, Date start2, Date end2, int sessions) {
		super();
		this.scheduledProgId = scheduledProgId;
		this.progName = progName;
		this.location = location;
		this.start = start2;
		this.end = end2;
		this.sessions = sessions;
	}
	
	public ProgramScheduledBean(String progName, String location, Date start,
			Date end, int sessions) {
		super();
		this.progName = progName;
		this.location = location;
		this.start = start;
		this.end = end;
		this.sessions = sessions;
	}
	public int getScheduledProgId() {
		return scheduledProgId;
	}
	public void setScheduledProgId(int scheduledProgId) {
		this.scheduledProgId = scheduledProgId;
	}
	public String getProgName() {
		return progName;
	}
	public void setProgName(String progName) {
		this.progName = progName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public int getSessions() {
		return sessions;
	}
	public void setSessions(int sessions) {
		this.sessions = sessions;
	}
	
	

}
