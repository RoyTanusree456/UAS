package com.cg.universityadmission.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.hamcrest.core.SubstringMatcher;

import com.cg.universityadmission.bean.ApplicantBean;
import com.cg.universityadmission.bean.ProgramBean;
import com.cg.universityadmission.bean.ProgramScheduledBean;
import com.cg.universityadmission.exception.UASException;
import com.cg.universityadmission.service.AdminServiceImp;
import com.cg.universityadmission.service.ApplicantServiceImp;
import com.cg.universityadmission.service.MacServiceImp;

public class Client {
	public static void main(String[] args) throws UASException, IOException {
		// Scanner scanner = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Logger logger = Logger.getRootLogger();

		int user = 0;
		int adminOption = 0;
		int macOption = 0;
		int applicantOption = 0;
		boolean loginStatus;

		AdminServiceImp adminService = null;
		MacServiceImp macService = null;
		ApplicantServiceImp applicantService = null;

		AdminServiceImp service = new AdminServiceImp();

		System.out.println("****UNIVERSITY ADMISSION SYSTEM****");
		System.out.println();
		do {
			System.out.println(" 1. Admin");
			System.out.println(" 2. Member of Admission Committee");
			System.out.println(" 3. Applicant");
			System.out.println(" 4. EXIT");
			System.out.println("Select User : ");
			// String u = scanner.next();
			String u = br.readLine();
			while (true) {
				if (service.isInteger(u)) {
					logger.info("Entered valid option");
					break;
				} else {
					logger.error("Entered invalid option");
					System.err.println("Invalid format( only numbers )");
					// u = scanner.next();
					u = br.readLine();
				}
			}
			user = Integer.parseInt(u);
			switch (user) {
			case 1:// admin

				adminService = new AdminServiceImp();

				do {
					System.out.println("Enter login id : ");
					// String loginId = scanner.next();
					String loginId = br.readLine().trim();
					System.out.println("Enter password : ");
					// String password = scanner.next();
					String password = br.readLine().trim();
					loginStatus = adminService.login(loginId, password);
					if (loginStatus == false) {
						System.out.println("Wrong loginId or password");
					}
				} while (loginStatus == false);

				logger.info("Success : Admin login ");
				System.out.println("Login Successfull!!");

				do {
					
					System.out.println();
					/****************Admin operations****************/
					System.out.println(" 1. Update program information");
					System.out.println(" 2. Add program information");
					System.out.println(" 3. Delete program information");
					System.out.println(" 4. Add schedule of program");
					System.out.println(" 5. Delete schedule of program");
					System.out
							.println("6. View applicants for a scheduled program");
					System.out
							.println("7. View list of scheduled programs to start within a period");
					System.out.println("8. Logout");

					System.out.println("Enter option : ");

					// String ao = scanner.next();
					String ao = br.readLine();
					while (true) {
						if (service.isInteger(ao)) {
							logger.info("Entered valid option");
							break;
						} else {
							logger.error("Entered invalid option");
							System.err
									.println("Invalid format( only numbers )");
							// ao = scanner.next();
							ao = br.readLine();
						}
					}
					adminOption = Integer.parseInt(ao);

					switch (adminOption) {
					case 1:// update programsOffered

						String newDescription = "";
						String newEligibility = "";
						int status = 0;
						String progName = "";

						System.out
								.println("****Update program information****");
						System.out.println();
						System.out.println("1. Update Description");
						System.out.println("2. Update Eligibility");
						System.out
								.println("3. Update Description and Eligibility");

						System.out.println("Enter option : ");

						// String uo = scanner.next();
						String uo = br.readLine();
						while (true) {
							if (service.isInteger(uo)) {
								logger.info("Entered valid option");
								break;
							} else {
								logger.error("Entered invalid option");
								System.err
										.println("Invalid format( only numbers )");
								// uo = scanner.next();
								uo = br.readLine();
							}
						}
						int updateOption = Integer.parseInt(uo);

						switch (updateOption) {

						case 1:// update description

							System.out.println("Enter Program Name (case sensitive): ");
							// progName = scanner.next();
							progName = br.readLine();
							System.out.println("Enter new description for "
									+ progName + " : ");
							// newDescription = scanner.next();
							newDescription = br.readLine();

							while (true) {

								if (adminService.isValidText(newDescription)
										&& newDescription.length() <= 20) {
									logger.info("Entered valid new description text");
									break;
								} else {
									logger.error("Entered invalid description text");
									System.err
											.println("Invalid format( only alphabets ) / Maximum length :20 exceeded ");
									// newDescription = scanner.next();
									newDescription = br.readLine();
								}
							}

							status = adminService.updateDescription(
									newDescription, progName);

							if (status == 1) {

								logger.info("Success : Program description updated");

								System.out.println("Description for "
										+ progName + " updated successfully!!");
							} else {
								System.out
										.println("Wrong program name / Description for "
												+ progName
												+ "  could be updated!!");
							}

							break;

						case 2:// update eligibility

							System.out.println("Enter Program Name (case sensitive): ");
							// progName = scanner.next();
							progName = br.readLine();
							System.out.println("Enter new eligibility for "
									+ progName + " : ");
							// newEligibility = scanner.next();
							newEligibility = br.readLine();

							while (true) {

								if (adminService.isValidText(newEligibility)
										&& newEligibility.length() <= 40) {
									logger.info("Entered valid new eligibility text");
									break;
								} else {
									logger.error("Entered invalid eligibility text");
									System.err
											.println("Invalid format( only alphabets ) / Maximum length :40 exceeded ");
									// goals = scanner.next();
									newEligibility = br.readLine();
								}
							}
							status = adminService.updateEligibility(
									newEligibility, progName);

							if (status == 1) {

								logger.info("Success : Program eligibility updated");
								System.out.println("Eligibility for "
										+ progName + " updated successfully!!");
							} else {
								System.out
										.println("Wrong program name / Eligibility for "
												+ progName
												+ "  could be updated!!");
							}

							break;

						case 3:// update both description and eligibility

							System.out.println("Enter Program Name (case sensitive): ");
							// progName = scanner.next();
							progName = br.readLine();
							System.out.println("Enter new description for "
									+ progName + " : ");
							// newDescription = scanner.next();
							newDescription = br.readLine();
							while (true) {
								if (adminService.isValidText(newDescription)
										&& newDescription.length() <= 20) {
									logger.info("Entered valid new description text");
									break;
								} else {
									logger.error("Entered invalid description text");
									System.err
											.println("Invalid format( only alphabets ) / Maximum length :20 exceeded ");
									// newDescription = scanner.next();
									newDescription = br.readLine();
								}
							}
							System.out.println("Enter new eligibility for "
									+ progName + " : ");
							// newEligibility = scanner.next();
							newEligibility = br.readLine();
							while (true) {
								if (adminService.isValidText(newEligibility)
										&& newEligibility.length() <= 40) {
									logger.info("Entered valid new eligibility text");
									break;
								} else {
									logger.error("Entered invalid eligibility text");
									System.err
											.println("Invalid format( only alphabets ) / Maximum length :40 exceeded ");
									// newEligibility = scanner.next();
									newEligibility = br.readLine();
								}
							}
							status = adminService.updateDescriptionEligibility(
									newDescription, newEligibility, progName);
							if (status == 1) {
								logger.info("Success : Program description and eligibility updated");
								System.out
										.println("Description and Eligibility  for "
												+ progName
												+ " updated successfully!!");
							} else {
								System.out
										.println("Wrong program name / Description and Eligibility for "
												+ progName
												+ "  could be updated!!");
							}

							break;
						}

						break;

					case 2:// add programsOffered

						System.out.println("****Add program information****");
						System.out.println();
						System.out.println("Enter Program Name : ");
						// String progName1 = scanner.next();
						String progName1 = br.readLine();
						while (true) {
							if (adminService.isValidProgram(progName1)) {
								logger.info("Entered valid program name");
								break;
							} else {
								logger.error("Entered invalid program name");
								System.err
										.println("Invalid format( only alphanumeric ) / Maximum length :5 exceeded ");
								// progName1 = scanner.next();
								progName1 = br.readLine();
							}
						}
						System.out.println("Enter Program Description : ");
						// String description = scanner.next();
						String description = br.readLine();
						while (true) {
							if (adminService.isValidText(description)
									&& description.length() <= 20) {
								logger.info("Entered valid description text");
								break;
							} else {
								logger.error("Entered invalid description");
								System.err
										.println("Invalid format( only alphabets ) / Maximum length :20 exceeded ");
								// description = scanner.next();
								description = br.readLine();
							}
						}
						System.out.println("Enter Applicant Eligibility : ");
						// String eligibility = scanner.next();
						String eligibility = br.readLine();
						while (true) {
							if (adminService.isValidText(eligibility)
									&& eligibility.length() <= 40) {
								logger.info("Entered valid eligibility text");
								break;
							} else {
								logger.error("Entered invalid eligibility");
								System.err
										.println("Invalid format( only alphabets ) / Maximum length :40 exceeded ");
								// eligibility = scanner.next();
								eligibility = br.readLine();
							}
						}
						System.out.println("Enter Program Duration (days) : ");
						// String d = scanner.next();
						String d = br.readLine();
						while (true) {
							if (adminService.isInteger(d)) {
								logger.info("Entered valid duration");
								break;
							} else {
								logger.error("Entered invalid duration");
								System.err
										.println("Invalid format( only positive numbers )");
								// d = scanner.next();
								d = br.readLine();
							}
						}
						int duration = Integer.parseInt(d);
						System.out.println("Enter Degree Certificate Offered ");
						// String certificate = scanner.next();
						String certificate = br.readLine();
						while (true) {
							if (adminService.isValidText(certificate)
									&& certificate.length() <= 10) {
								logger.info("Entered valid certificate details");
								break;
							} else {
								logger.error("Entered invalid certificate details");
								System.err
										.println("Invalid format( only alphabets ) / Maximum length :10 exceeded ");
								// certificate = scanner.next();
								certificate = br.readLine();
							}
						}
						ProgramBean program = new ProgramBean(progName1,
								description, eligibility, duration, certificate);
						int insertStatus = adminService
								.addProgramsOffered(program);
						if (insertStatus == 1) {
							logger.info("Success : New program added");
							System.out
									.println("New Program Added successfully!!");
						} else {
							System.out
									.println("Program already exists!\nCould not add new program");
						}

						break;

					case 3:// delete programsOffered

						System.out
								.println("****Delete program information****");
						System.out.println();
						System.out.println("Enter Program Name (case sensitive): ");
						// String progName2 = scanner.next();
						String progName2 = br.readLine();
						int deleteStatus = adminService
								.deleteProgramsOffered(progName2);
						if (deleteStatus == 1) {
							logger.info("Success : Program deleted");
							System.out.println("Program : " + progName2
									+ " deleted successfully!!");
						} else {
							System.out
									.println("Wrong program name / Could not delete program : "
											+ progName2);
						}

						break;

					case 4:// add schedule for a program( only if exists )

						Date start,
						end;
						System.out.println("****Add program schedule****");
						System.out.println();
						System.out
								.println("Enter program name to be scheduled (case sensitive): ");
						// String progName3 = scanner.next();
						String progName3 = br.readLine();
						System.out.println("Enter location : ");
						// String location = scanner.next();
						String location = br.readLine();
						while (true) {
							if (adminService.isValidLocation(location)) {
								logger.info("Entered valid location");
								break;
							} else {
								logger.error("Entered invalid location");
								System.err
										.println("No numbers allowed / Maximum length :10 exceeded");
								// location = scanner.next();
								location = br.readLine();
							}
						}
						System.out.println("Enter start date(yyyy-mm-dd) : ");
						// String date = scanner.next();
						String date = br.readLine();
						while (true) {
							if (adminService.isValidDate(date)) {
								logger.info("Entered valid start date");
								break;
							} else {
								logger.error("Entered invalid start date");
								System.err
										.println("Invalid date / Invalid format ");
								// date = scanner.next();
								date = br.readLine();
							}
						}
						start = Date.valueOf(date);
						System.out.println("Enter end date(yyyy-mm-dd) : ");
						// date = scanner.next();
						date = br.readLine();
						end = Date.valueOf(date);
						while (true) {
							if (adminService.isValidDate(date)
									&& start.before(end)) {
								logger.info("Entered valid end date");
								break;
							} else {
								logger.error("Entered invalid end date");
								System.err
										.println("Invalid format / End date is before start date ");
								// date = scanner.next();
								date = br.readLine();
								end = Date.valueOf(date);
							}
						}
						System.out
								.println("Enter number of sessions per week : ");
						// String s = scanner.next();
						String s = br.readLine();
						while (true) {
							if (adminService.isValidSession(s)) {
								logger.info("Entered valid number of sessions");
								break;
							} else {
								logger.error("Entered invalid number of sessions");
								System.err
										.println("Invalid format (only numbers) / Min : 0, Max : 7");
								// s = scanner.next();
								s = br.readLine();
							}
						}
						int sessions = Integer.parseInt(s);
						ProgramScheduledBean programScheduled = new ProgramScheduledBean(
								progName3, location, start, end, sessions);
						int insertStatus1 = adminService
								.addProgramsScheduled(programScheduled);
						if (insertStatus1 == 1) {
							logger.info("Success : New program schedule added");
							System.out.println("Schedule added for program : "
									+ progName3 + " successfully!!");
						} else {
							System.out
									.println("This program is not available / Could not add schedule for program : "
											+ progName3);
						}

						break;

					case 5:// delete schedule for a program( only if no
							// application )

						System.out.println("****Delete program schedule****");
						System.out.println();
						System.out
								.println("Enter program schedule id to be deleted : ");
						// String id = scanner.next();
						String id = br.readLine();
						while (true) {
							if (adminService.isValidId(id)) {
								logger.info("Entered valid scheduled program id");
								break;
							} else {
								logger.error("Entered invalid scheduled program id");
								System.err
										.println("Invalid format (only numbers)");
								// id = scanner.next();
								id = br.readLine();
							}
						}
						int scheduledProgId = Integer.parseInt(id);
						int deleteStatus1 = adminService
								.deleteProgramsScheduled(scheduledProgId);
						if (deleteStatus1 == 1) {
							logger.info("Success : Program schedule deleted");
							System.out.println("Program schedule:"
									+ scheduledProgId
									+ " deleted successfully!!");
						} else {
							System.out
									.println("Could not delete program schedule !!\nApplicants found for this schedule/Wrong schedule id");
						}

						break;

					case 6:// viewApplicantByStatus for a scheduled program

						String applicationStatus = "";
						int statusOption = 0;
						System.out
								.println("****View applications for a scheduled program by status****");
						System.out.println();
						do {
							System.out.println("1. Accepted");
							System.out.println("2. Confirmed");
							System.out.println("3. Rejected");
							System.out.println("Enter status option");
							// String so = scanner.next();
							String so = br.readLine();
							while (true) {
								if (service.isInteger(so)) {
									logger.info("Entered valid option");
									break;
								} else {
									logger.error("Entered invalid option");
									System.err
											.println("Invalid format( only numbers )");
									// so = scanner.next();
									so = br.readLine();
								}
							}
							statusOption = Integer.parseInt(so);
							switch (statusOption) {
							case 1:
								applicationStatus = "Accepted";
								break;
							case 2:
								applicationStatus = "Confirmed";
								break;
							case 3:
								applicationStatus = "Rejected";
								break;
							default:
								System.out.println("Wrong option!!");
							}
						} while (statusOption < 1 || statusOption > 3);
						System.out.println("Enter program name : ");
						// String pName = scanner.next();
						String pName = br.readLine();
						ArrayList<ApplicantBean> applicationList = adminService
								.viewApplicationByStatus(applicationStatus,
										pName);
						int i = 0;
						for (ApplicantBean application : applicationList) {
							System.out.println((++i) + "[ Application id: "
									+ application.getApplicationId()
									+ " | Name: " + application.getName()
									+ " | DOB " + application.getDob()
									+ " | Highest Qualification: "
									+ application.getQualification()
									+ " | Marks: " + application.getMarks()
									+ " | Goals: " + application.getGoals()
									+ " | Email: " + application.getEmail()
									+ " | Scheduled_ProgId: "
									+ application.getProgId()
									+ " | Application Status: "
									+ application.getStatus()
									+ " | Interview Date: "
									+ application.getDoi() + "]");
						}
						if (i == 0) {
							System.out
									.println("No data found!! / Wrong program name");
						} else {
							logger.info("Success : Read applications of a program by status");
						}

						break;
					case 7:// viewScheduledProgramsInPeriod

						System.out
								.println("****View scheduled programs to start within a time period****");
						System.out.println();
						System.out.println("Enter start date (yyyy-mm-dd) : ");
						// date = scanner.next();
						date = br.readLine();
						while (true) {
							if (adminService.isValidDate(date)) {
								logger.info("Entered valid start date");
								break;
							} else {
								logger.error("Entered invalid start date");
								System.err
										.println("Invalid date / Invalid format");
								// date = scanner.next();
								date = br.readLine();
							}
						}
						Date startDate = Date.valueOf(date);
						System.out.println("Enter end date (yyyy-mm-dd) : ");
						// date = scanner.next();
						date = br.readLine();
						Date endDate = Date.valueOf(date);
						while (true) {
							if (adminService.isValidDate(date)
									&& startDate.before(endDate)) {
								logger.info("Entered valid end date");
								break;
							} else {
								logger.error("Entered invalid end date");
								System.err
										.println("Invalid format / End date is before start date ");
								// date = scanner.next();
								date = br.readLine();
								endDate = Date.valueOf(date);
							}
						}
						ArrayList<ProgramScheduledBean> progList = adminService
								.viewScheduledProgramsInPeriod(startDate,
										endDate);
						int j = 0;
						for (ProgramScheduledBean prog : progList) {
							System.out.println((++j)
									+ "[ Scheduled ProgramId: "
									+ prog.getScheduledProgId()
									+ " | Program Name: " + prog.getProgName()
									+ " | Location: " + prog.getLocation()
									+ " | Start Date: " + prog.getStart()
									+ " | End Date: " + prog.getEnd()
									+ " | Sessions per week: "
									+ prog.getSessions() + "]");
						}
						if (j == 0) {
							System.out.println("No data found!!");
						} else {
							logger.info("Success : Read scheduled programs to start in a time period");
						}

						break;

					case 8:

						System.out.println("Admin Logged out");
						System.exit(0);

					default:
						System.out.println("Wrong option!!");

					}
				} while (adminOption != 8);

				break;
			case 2:// mac

				macService = new MacServiceImp();
				do {
					System.out.println("Enter login id : ");
					// String loginId = scanner.next();
					String loginId = br.readLine();
					System.out.println("Enter password : ");
					// String password = scanner.next();
					String password = br.readLine();
					loginStatus = macService.login(loginId, password);
					if (loginStatus == false) {
						System.out.println("Wrong loginId or password");
					}
				} while (loginStatus == false);
				logger.info("Success : MAC login");
				System.out.println("Login Successfull!!");
				do {
					System.out.println();
					System.out
							.println("1. View applications for a specific program");
					System.out.println("2. Update application status");
					System.out.println("3. Logout");
					System.out.println("Enter option : ");
					// String mo = scanner.next();
					String mo = br.readLine();
					while (true) {
						if (service.isInteger(mo)) {
							logger.info("Entered valid option");
							break;
						} else {
							logger.error("Entered invalid option");
							System.err
									.println("Invalid format( only numbers )");
							// mo = scanner.next();
							mo = br.readLine();
						}
					}
					macOption = Integer.parseInt(mo);
					switch (macOption) {
					case 1:// viewApplicationByProgram
						macService = new MacServiceImp();
						System.out
								.println("****VIEW APPLICATIONS FOR A PROGRAM****");
						System.out.println();
						System.out.println("Enter the Program Name (case sensitive): ");
						// String progName = scanner.next();
						String progName = br.readLine();
						ArrayList<ApplicantBean> applicationList = macService
								.viewApplicationByProgram(progName);
						int i = 0;
						if (applicationList == null) {
							System.out
									.println("No Application found for the program/ Wrong Program name "
											+ progName);
						} else {
							logger.info("Success : Read applications for a program");
							for (ApplicantBean application : applicationList) {
								System.out.println((++i) + "[ Application id: "
										+ application.getApplicationId()
										+ " | Name: " + application.getName()
										+ " | DOB: " + application.getDob()
										+ " | Highest Qualification: "
										+ application.getQualification()
										+ " | Marks: " + application.getMarks()
										+ " | Goals: " + application.getGoals()
										+ " | Email: " + application.getEmail()
										+ " | Scheduled ProgId: "
										+ application.getProgId()
										+ " | Application Status: "
										+ application.getStatus()
										+ " | Interview Date: "
										+ application.getDoi() + "]");
							}
						}

						break;

					case 2:// updateApplicationStatus

						String newStatus = "";
						System.out
								.println("****UPDATE APPLICATION STATUS FOR AN APPLICANT****");
						System.out.println();
						System.out.println("Enter the Application Id : ");
						// String id = scanner.next();
						String id = br.readLine();
						while (true) {
							if (macService.isValidId(id)) {
								logger.info("Entered valid application id");
								break;
							} else {
								logger.error("Entered invalid application id");
								System.err
										.println("Invalid format( only numbers )");
								// id = scanner.next();
								id = br.readLine();
							}
						}
						int applicationId = Integer.parseInt(id);
						System.out
								.println("Enter new status (Confirmed/Rejected): ");
						// newStatus = scanner.next();
						newStatus = br.readLine();
						newStatus=newStatus.toUpperCase();
						System.out.println("gf: "+newStatus);
						while (true) {
							if ("CONFIRMED".equals(newStatus)
									|| "REJECTED".equals(newStatus)) {
								logger.info("Entered valid application status");
								break;
							} else {
								logger.error("Entered invalid application status");
								System.err
										.println("Wrong status(Confirmed/Rejected): ");
								// newStatus = scanner.next();
								newStatus = br.readLine();
								newStatus=newStatus.toUpperCase();
							}
						}
						if ("CONFIRMED".equals(newStatus))
						{
							newStatus="Confirmed";
						}
						else if("REJECTED".equals(newStatus))
						{
							newStatus="Rejected";
						}
						int status = macService.updateApplicationStatus(
								applicationId, newStatus);
						if (status == -2) {
							System.out.println("Wrong Application Id!!");
						} else if (status == -1) {
							System.out
									.println("Application Status of application id : "
											+ applicationId
											+ " was already "
											+ newStatus);
						} else if (status == 1) {
							logger.info("Success : Application status updated");
							System.out
									.println("Application Status of application id : "
											+ applicationId
											+ " updated successfully!!");
						} else {
							System.out
									.println("Interview not conducted\nCould not update application status");
						}

						break;

					case 3:

						System.out.println("Mac Logged out");
						System.exit(0);

					default:
						System.out.println("Wrong option!!");
					}
				} while (macOption != 3);

				break;

			case 3:// applicant

				int appId = 0;
				applicantService = new ApplicantServiceImp();
				do {
					System.out.println();
					System.out
							.println("1. View all programs scheduled by the university");
					System.out
							.println("2. Apply for a scheduled program of the university");
					System.out.println("3. View the application status");
					System.out.println("4. EXIT");
					System.out.println("Enter option : ");
					// String apo = scanner.next();
					String apo = br.readLine();
					while (true) {
						if (service.isInteger(apo)) {
							logger.info("Entered valid option");
							break;
						} else {
							logger.error("Entered invalid option");
							System.err
									.println("Invalid format( only numbers )");
							// apo = scanner.next();
							apo = br.readLine();
						}
					}
					applicantOption = Integer.parseInt(apo);
					switch (applicantOption) {
					case 1:// viewAllPrograms

						ArrayList<ProgramScheduledBean> progList = applicantService
								.viewAllPrograms();
						int i = 0;
						System.out.println("Scheduled Prog Id");
						/*for(ProgramScheduledBean prg : progList)
						{
							System.out.println(prg.getScheduledProgId());
							System.out.println("___________");
						}*/
						for (ProgramScheduledBean program : progList) {
							System.out.println((++i)
									+ "[ Scheduled ProgramId: "
									+ program.getScheduledProgId()
									+ " | Name: " + program.getProgName()
									+ " | Location: " + program.getLocation()
									+ " | Start Date: " + program.getStart()
									+ " | End Date: " + program.getEnd()
									+ " | Sessions per week: "
									+ program.getSessions() + "]");
						}
						if (i != 0) {
							logger.info("Success : Read all programs offered");
						}

						break;

					case 2:// applyForProgram

						System.out.println("****APPLICATION FORM****");
						System.out.println();
						System.out.println("Enter Full Name : ");
						// String name = scanner.next();
						String name = br.readLine();
						while (true) {
							if (applicantService.isValidName(name)) {
								logger.info("Entered valid applicant name");
								break;
							} else {
								logger.error("Entered invalid applicant name");
								System.err
										.println("Invalid name format / Min :2 Max :20 ");
								// name = scanner.next();
								name = br.readLine();
							}
						}
						System.out
								.println("Enter Date of Birth (yyyy-mm-dd) : ");
						// String date = scanner.next();
						String date = br.readLine();
						while (true) {
							if (applicantService.isValidDob(date)) {
								logger.info("Entered valid dob");
								break;
							} else {
								logger.error("Entered invalid dob");
								System.err
										.println("Invalid date( dob ) / Min : 18 Max : 28");
								// date = scanner.next();
								date = br.readLine();
							}
						}
						Date dob = Date.valueOf(date);
						System.out.println("Enter Highest Qualification : ");
						// String qualification = scanner.next();
						String qualification = br.readLine();
						while (true) {
							if (applicantService.isValidText(qualification)
									&& qualification.length() <= 10) {
								logger.info("Entered valid qualification text");
								break;
							} else {
								logger.error("Entered invalid qualification text");
								System.err
										.println("Invalid format( only alphabets ) / Maximum length :10 exceeded ");
								// qualification = scanner.next();
								qualification = br.readLine();
							}
						}
						System.out
								.println("Enter Marks( rounded : out of 100 ) : ");
						// String m = scanner.next();
						String m = br.readLine();
						while (true) {
							if (applicantService.isValidMarks(m)) {
								logger.info("Entered valid marks");
								break;
							} else {
								logger.error("Entered invalid marks");
								System.err
										.println("Invalid format( only numbers ) / Min : 0 Max : 100");
								// m = scanner.next();
								m = br.readLine();
							}
						}
						int marks = Integer.parseInt(m);
						System.out.println("Enter Goals : ");
						// String goals = scanner.next();
						String goals = br.readLine();
						while (true) {
							if (applicantService.isValidText(goals)
									&& goals.length() <= 20) {
								logger.info("Entered valid goal text");
								break;
							} else {
								logger.error("Entered invalid goal text");
								System.err
										.println("Invalid format( only aplhabets ) / Maximum length :20 exceeded ");
								// goals = scanner.next();
								goals = br.readLine();
							}
						}
						System.out.println("Enter Email : ");
						// String email = scanner.next();
						String email = br.readLine();
						while (true) {
							if (applicantService.isValidEmail(email)) {
								logger.info("Entered valid email");
								break;
							} else {
								logger.error("Entered invalid email");
								System.err.println("Invalid email format");
								// email = scanner.next();
								email = br.readLine();
							}
						}
						ApplicantBean applicant = new ApplicantBean(name, dob,
								qualification, marks, goals, email);
						System.out.println("Enter Program Name : ");
						// String progName = scanner.next();
						String progName = br.readLine();
						appId = applicantService.applyForProgram(applicant,
								progName);
						if (appId != -1) {
							logger.info("Success : Application received");
							System.out
									.println("Application received!!\nApplication id : "
											+ appId);
						} else {
							System.out
									.println("Application not received!!\nWrong Program Name");
						}

						break;

					case 3:// viewStatus

						System.out.println("****VIEW STATUS****");
						System.out.println();
						System.out.println("Enter your Application Id : ");
						// String id = scanner.next();
						String id = br.readLine();
						while (true) {
							if (applicantService.isValidId(id)) {
								logger.info("Entered valid application id");
								break;
							} else {
								logger.error("Entered invalid application id");
								System.err
										.println("Invalid format( only numbers )");
								// id = scanner.next();
								id = br.readLine();
							}
						}
						appId = Integer.parseInt(id);
						String status = applicantService.viewStatus(appId);
						if (status != null) {
							logger.info("Success : Read application status");
							System.out.println("Your application status : "
									+ status);
						} else {
							System.out.println("Wrong Application id");
						}

						break;

					case 4:

						System.out.println("Thank You for visiting");
						System.exit(0);

					default:
						System.out.println("Wrong option!!");
					}
				} while (applicantOption != 4);

				break;

			case 4:

				System.out.println("Exited University Admission System");
				System.exit(0);

			default:
				System.out.println("Sorry!!Wrong choice!!");
			}
		} while (user != 4);
	}
}