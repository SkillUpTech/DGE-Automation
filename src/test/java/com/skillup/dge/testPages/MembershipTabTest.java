package com.skillup.dge.testPages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.openqa.selenium.WebDriver;

import com.skillup.dge.webPages.CourseCardProcess;
import com.skillup.dge.webPages.HomePage;
import com.skillup.dge.webPages.MembershipPage;

public class MembershipTabTest implements Callable<String>{
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	String sheetStatus = "Pass";
	CourseCardProcess courseCardProcess;
	HomePage homePage;
	MembershipPage membershipPage;
	public MembershipTabTest(WebDriver driver, ArrayList<ArrayList<String>> sheetData)
	{
		this.sheetData = sheetData;
		this.driver = driver;
	}
	@Override
	public String call() throws Exception
	{
		// TODO Auto-generated method stub
		System.out.println("DGE HomePage validation Process started");
		this.courseCardProcess = new CourseCardProcess(driver);
		this.homePage = new HomePage(driver);
		try
		{
			for (int i = 0; i < this.sheetData.size(); i++)
			{
				ArrayList<String> row = this.sheetData.get(i);
				String firstColumn = row.get(0);

				switch (firstColumn) {
				case "HomePageURL":
					HomePageURL(row.get(1));
					break;
				case "checkSignInUsingStaffCredential":
					checkSignInUsingStaffCredential(row);
					break;
				case "checkBeginCourseCard":
					checkBeginCourseCard(row);
					break;
				case "checkInstructorTab":
					checkInstructorTab();
					break;
				case "checkMembershipTab":
					checkMembershipTab();
					break;
				case "ValidateEnrollWithValidEmail":
					ValidateEnrollWithValidEmail(row);
					break;
				case "ValidateEnrollWithValidUsername":
					ValidateEnrollWithValidUsername(row);
					break;
				case "ValidateEnrollWithInvalidEmail":
					ValidateEnrollWithInvalidEmail(row);
					break;
				case "EnrollWithBlankInput":
					EnrollWithBlankInput(row);
					break;
				case "EnrollWithAutoEnrollChecked":
					EnrollWithAutoEnrollChecked(row);
					break;
				case "EnrollWithNotifyUserChecked":
					EnrollWithNotifyUserChecked(row);
					break;
				case "checkNotificationMailReceivedOrNotFromUserSide":
					checkNotificationMailReceivedOrNotFromUserSide(row.get(1));
					break;
				case "EnrollWithValidMailAndBothCheckboxesSelected":
					EnrollWithValidMailAndBothCheckboxesSelected(row);
					break;
				case "checkCourseActivationLinkReceivedFromRegisteredUserMail":
					checkCourseActivationLinkReceivedFromRegisteredUserMail(row.get(1));
					break;
				case "checkEnrolledCourseActivationLinkForRegisteredUser":
					checkEnrolledCourseActivationLinkForRegisteredUser(row.get(1));
					break;
				case "checkSignInProcessForRegisteredUserToEnrollCourse":
					checkSignInProcessForRegisteredUserToEnrollCourse(row);
					break;
				case "checkEnrolledCourseIsAvailableInRegisteredUserDashboard":
					checkEnrolledCourseIsAvailableInRegisteredUserDashboard(row);
					break;
				case "SignoutFromUserDashboard1":
					SignoutFromUserDashboard1();
					break;
				case "signInUsingStaffAccount1":
					signInUsingStaffAccount1(row);
					break;
				case "selectCourseCard1":
					selectCourseCard1(row);
					break;
				case "NavigateToMembershipSection1":
					NavigateToMembershipSection1();
					break;
				case "EnrollNonRegisteredUserAndEnableAllCheckbox":
					EnrollNonRegisteredUserAndEnableAllCheckbox(row);
					break;
				case "checkEnrollCourseActivationLinkReceivedFromNonRegisteredUserMail":
					checkEnrollCourseActivationLinkReceivedFromNonRegisteredUserMail(row.get(1));
					break;
				case "checkEnrolledCourseActivationLinkForNonRegisteredUser":
					checkEnrolledCourseActivationLinkForNonRegisteredUser(row);
					break;
				case "checkSignupProcessForNonRegisteredUserToEnrollCourse":
					checkSignupProcessForNonRegisteredUserToEnrollCourse(row);
					break;
				case "checkSignInProcessForNonRegisteredUserToEnrollCourse":
					checkSignInProcessForNonRegisteredUserToEnrollCourse(row);
					break;
				case "checkEnrolledCourseIsAvailableInNewRegisteredDashboard":
					checkEnrolledCourseIsAvailableInNewRegisteredDashboard(row);
					break;
				case "signoutFromUserDashboard2":
					signoutFromUserDashboard2();
					break;
				case "signInUsingStaffAccount2":
					signInUsingStaffAccount2(row);
					break;
				case "selectCourseCard2":
					selectCourseCard2(row);
					break;
				case "NavigateMembershipTabFromInstructionSection2":
					NavigateMembershipTabFromInstructionSection2();
					break;
				case "EnrollWithNoCheckboxesSelected":
					EnrollWithNoCheckboxesSelected(row);
					break;
				case "UnenrollWithRegisteredUserMailAndBothCheckBoxSelected":
					UnenrollWithRegisteredUserMailAndBothCheckBoxSelected(row);
					break;
				case "checkUnEnrollCourseActivationLinkFromRegisteredUserMail":
					checkUnEnrollCourseActivationLinkFromRegisteredUserMail(row.get(1));
					break;
				case "checkUnEnrolledCourseActivationLinkForRegisteredUserMail":
					checkUnEnrolledCourseActivationLinkForRegisteredUserMail(row.get(1));
					break;
				case "checkSignInProcessForRegisteredUserToVerifyUnEnrolledCourse":
					checkSignInProcessForRegisteredUserToVerifyUnEnrolledCourse(row);
					break;
				case "checkUnEnrolledCourseIsAvailableInRegisteredUserDashboard":
					checkUnEnrolledCourseIsAvailableInRegisteredUserDashboard(row);
					break;
				case "SignoutFromUserDashboard3":
					SignoutFromUserDashboard3();
					break;
				case "signInUsingStaffAccount3":
					signInUsingStaffAccount3(row);
					break;
				case "selectCourseCard3":
					selectCourseCard3(row);
					break;
				case "NavigateMembershipTabFromInstructionSection3":
					NavigateMembershipTabFromInstructionSection3();
					break;
				case "UnEnrollNonRegisteredUserAndEnableAllCheckbox":
					UnEnrollNonRegisteredUserAndEnableAllCheckbox(row);
					break;
				case "checkUnEnrollCourseActivationLinkFromNonRegisteredUserMail":
					checkUnEnrollCourseActivationLinkFromNonRegisteredUserMail(row);
					break;
				case "checkUnEnrolledCourseActivationLinkForNonRegisteredUser":
					checkUnEnrolledCourseActivationLinkForNonRegisteredUser(row);
					break;
				case "checkSignupProcessForNonRegisteredUserToVerifyUnenrolledCourse":
					checkSignupProcessForNonRegisteredUserToVerifyUnenrolledCourse(row);
					break;
				case "checkSignInProcessForNonRegisteredUserToVerifyUnenrolledCourse":
					checkSignInProcessForNonRegisteredUserToVerifyUnenrolledCourse(row);
					break;
				case "checkUnEnrolledCourseIsAvailableForNewlyRegisterdUserDashboard":
					checkUnEnrolledCourseIsAvailableForNewlyRegisterdUserDashboard(row);
					break;
				case "SignoutFromUserDashboard4":
					SignoutFromUserDashboard4();
					break;
				case "signInUsingStaffAccount4":
					signInUsingStaffAccount4(row);
					break;
				case "selectCourseCard4":
					selectCourseCard4(row);
					break;
				case "NavigateMembershipTabFromInstructionSection4":
					NavigateMembershipTabFromInstructionSection4();
					break;
				case "UnenrollWithValidUsername":
					UnenrollWithValidUsername(row);
					break;
				case "UnenrollWithBlankInput":
					UnenrollWithBlankInput(row);
					break;
				default:
					System.err.println("Unrecognized case: " + firstColumn);
					break;
				}

			}
			 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			sheetStatus = "Fail"; // Set status to Fail if there are errors
		} finally
		{
			System.out.println("User Profile validation Process completed");
		}
		return sheetStatus;
	}
	public void updateExcelData(ArrayList<String> status, int rowIndex)
	{
		try {
			status.removeIf(item -> item == null || item.isBlank());
			if (status.size() > 0) {
				sheetStatus = "Fail"; // Set status to Fail if there are errors
				Map<String, ArrayList<ArrayList<String>>> excelData = com.skillup.dge.testPages.RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP;

				List<ArrayList<String>> sheetData = excelData.get("MembershipTab");

				while (sheetData.size() <= rowIndex) {
					sheetData.add(new ArrayList<>()); // Add new rows if missing
				}

				List<String> rowData = sheetData.get(rowIndex);

				while (sheetData.size() <= rowIndex) {
					sheetData.add(new ArrayList<>()); // Add new rows if missing
				}

				// **Ensure row has at least 16 columns**

				for (int i = 0; i < status.size(); i++) { // Loop through issues
					String issue = status.get(i);

					// Define column mappings for each issue
					int columnIndex = -1;
					if (issue.contains("Pass"))
						columnIndex = 2;
					if (issue.contains("Fail"))
						columnIndex = 3;
					while (rowData.size() <= columnIndex)
					{
						rowData.add(""); // Ensure column exists
					}

					Thread.sleep(1000);
					// Update the value at column 15 safely
					rowData.set(columnIndex, rowData.get(columnIndex) + "; " + issue + " - failed");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void HomePageURL(String data) {
		try {

			ArrayList<String> status = homePage.checkHomePageURL(data);
			updateExcelData(status, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkSignInUsingStaffCredential(ArrayList<String> data) {
		try {

			ArrayList<String> status = homePage.checkSignInProcess(data);
			updateExcelData(status, 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 public void checkBeginCourseCard(ArrayList<String> data) 
	  { 
		  try 
		  {
			  ArrayList<String> status =  courseCardProcess.checkBeginCourseCard(data); updateExcelData(status, 3);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkInstructorTab() 
	  { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkInstructorTab(); updateExcelData(status, 4);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkMembershipTab() 
	  { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkMembershipTab(); updateExcelData(status, 5);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void ValidateEnrollWithValidEmail(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.ValidateEnrollWithValidEmail(data); updateExcelData(status, 6);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void ValidateEnrollWithValidUsername(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.ValidateEnrollWithValidUsername(data); updateExcelData(status, 7);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void ValidateEnrollWithInvalidEmail(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.ValidateEnrollWithInvalidEmail(data); updateExcelData(status, 8);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void EnrollWithBlankInput(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.EnrollWithBlankInput(data); updateExcelData(status, 9);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void EnrollWithAutoEnrollChecked(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.EnrollWithAutoEnrollChecked(data); updateExcelData(status, 10);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void EnrollWithNotifyUserChecked(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.EnrollWithNotifyUserChecked(data); updateExcelData(status, 11);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkNotificationMailReceivedOrNotFromUserSide(String data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkNotificationMailReceivedOrNotFromUserSide(data);
			  updateExcelData(status, 12);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void EnrollWithValidMailAndBothCheckboxesSelected(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.EnrollWithValidMailAndBothCheckboxesSelected(data); updateExcelData(status, 13);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkCourseActivationLinkReceivedFromRegisteredUserMail(String data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkCourseActivationLinkReceivedFromRegisteredUserMail(data); updateExcelData(status, 14);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkEnrolledCourseActivationLinkForRegisteredUser(String data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkEnrolledCourseActivationLinkForRegisteredUser(data); updateExcelData(status, 15);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkSignInProcessForRegisteredUserToEnrollCourse(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkSignInProcessForRegisteredUserToEnrollCourse(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkEnrolledCourseIsAvailableInRegisteredUserDashboard(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkEnrolledCourseIsAvailableInRegisteredUserDashboard(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void SignoutFromUserDashboard1() 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.SignoutFromUserDashboard1(); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void signInUsingStaffAccount1(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.signInUsingStaffAccount1(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void selectCourseCard1(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.selectCourseCard1(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void NavigateToMembershipSection1() 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.NavigateToMembershipSection1(); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void EnrollNonRegisteredUserAndEnableAllCheckbox(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.EnrollNonRegisteredUserAndEnableAllCheckbox(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkEnrollCourseActivationLinkReceivedFromNonRegisteredUserMail(String data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkEnrollCourseActivationLinkReceivedFromNonRegisteredUserMail(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkEnrolledCourseActivationLinkForNonRegisteredUser(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkEnrolledCourseActivationLinkForNonRegisteredUser(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkSignupProcessForNonRegisteredUserToEnrollCourse(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkSignupProcessForNonRegisteredUserToEnrollCourse(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkSignInProcessForNonRegisteredUserToEnrollCourse(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkSignInProcessForNonRegisteredUserToEnrollCourse(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkEnrolledCourseIsAvailableInNewRegisteredDashboard(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkEnrolledCourseIsAvailableInNewRegisteredDashboard(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void signoutFromUserDashboard2() 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.signoutFromUserDashboard2(); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void signInUsingStaffAccount2(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.signInUsingStaffAccount2(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void selectCourseCard2(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.selectCourseCard2(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void NavigateMembershipTabFromInstructionSection2() 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.NavigateMembershipTabFromInstructionSection2(); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void EnrollWithNoCheckboxesSelected(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.EnrollWithNoCheckboxesSelected(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void UnenrollWithRegisteredUserMailAndBothCheckBoxSelected(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.UnenrollWithRegisteredUserMailAndBothCheckBoxSelected(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkUnEnrollCourseActivationLinkFromRegisteredUserMail(String data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkUnEnrollCourseActivationLinkFromRegisteredUserMail(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkUnEnrolledCourseActivationLinkForRegisteredUserMail(String data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkUnEnrolledCourseActivationLinkForRegisteredUserMail(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkSignInProcessForRegisteredUserToVerifyUnEnrolledCourse(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkSignInProcessForRegisteredUserToVerifyUnEnrolledCourse(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkUnEnrolledCourseIsAvailableInRegisteredUserDashboard(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkUnEnrolledCourseIsAvailableInRegisteredUserDashboard(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void SignoutFromUserDashboard3() 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.SignoutFromUserDashboard3(); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void signInUsingStaffAccount3(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.signInUsingStaffAccount3(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void selectCourseCard3(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.selectCourseCard3(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void NavigateMembershipTabFromInstructionSection3() 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.NavigateMembershipTabFromInstructionSection3(); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void UnEnrollNonRegisteredUserAndEnableAllCheckbox(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.UnEnrollNonRegisteredUserAndEnableAllCheckbox(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkUnEnrollCourseActivationLinkFromNonRegisteredUserMail(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkUnEnrollCourseActivationLinkFromNonRegisteredUserMail(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkUnEnrolledCourseActivationLinkForNonRegisteredUser(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkUnEnrolledCourseActivationLinkForNonRegisteredUser(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkSignupProcessForNonRegisteredUserToVerifyUnenrolledCourse(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkSignupProcessForNonRegisteredUserToVerifyUnenrolledCourse(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkSignInProcessForNonRegisteredUserToVerifyUnenrolledCourse(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkSignInProcessForNonRegisteredUserToVerifyUnenrolledCourse(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void checkUnEnrolledCourseIsAvailableForNewlyRegisterdUserDashboard(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.checkUnEnrolledCourseIsAvailableForNewlyRegisterdUserDashboard(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void SignoutFromUserDashboard4() 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.SignoutFromUserDashboard4(); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void signInUsingStaffAccount4(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.signInUsingStaffAccount4(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void selectCourseCard4(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.selectCourseCard4(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void NavigateMembershipTabFromInstructionSection4() 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.NavigateMembershipTabFromInstructionSection4(); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void UnenrollWithValidUsername(ArrayList<String> data) 
	 { 
		  try 
		  {
			  ArrayList<String> status =  membershipPage.UnenrollWithValidUsername(data); updateExcelData(status, 16);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 public void UnenrollWithBlankInput(ArrayList<String> data) 
		 { 
			  try 
			  {
				  ArrayList<String> status =  membershipPage.UnenrollWithBlankInput(data); updateExcelData(status, 16);
			  } 
			  catch (Exception e)
			  { 
				  e.printStackTrace(); 
			  }
		  }
}
