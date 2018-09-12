package com.cg.universityadmission.bean;

public class ProgramBean {

	private String progName;
	private String description;
	private String eligibility;
	private int duration;
	private String certificate;

	public ProgramBean() {
		super();
	}

	public ProgramBean(String progName, String description, String eligibility,
			int duration, String certificate) {
		super();
		this.progName = progName;
		this.description = description;
		this.eligibility = eligibility;
		this.duration = duration;
		this.certificate = certificate;
	}

	public String getProgName() {
		return progName;
	}

	public void setProgName(String progName) {
		this.progName = progName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEligibility() {
		return eligibility;
	}

	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

}
