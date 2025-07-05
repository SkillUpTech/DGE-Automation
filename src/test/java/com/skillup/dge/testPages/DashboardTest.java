package com.skillup.dge.testPages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.openqa.selenium.WebDriver;

import com.skillup.dge.webPages.CourseCardProcess;
import com.skillup.dge.webPages.HomePage;

public class DashboardTest implements Callable<String>
{
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	String sheetStatus = "Pass";
	CourseCardProcess courseCardProcess;
	HomePage homePage;
	public DashboardTest(WebDriver driver, ArrayList<ArrayList<String>> sheetData)
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

				switch (firstColumn) 
				{
				case "HomePageURL":
			 		HomePageURL(row.get(1));
			        break;
			 	case "checkSignInProcess":
			 		checkSignInProcess(row);
			        break;
				/*
				 * case "checkFindCourseLink": checkFindCourseLink(); break;
				 */
			 	case "checkCardSelection":
			 		checkCardSelection(row);
			        break;
			 	case "checkTitleFromCourseAboutPage":
			 		checkTitleFromCourseAboutPage();
			        break;
			 	case "checkOrgFromCourseAboutPage":
			 		checkOrgFromCourseAboutPage();
			        break;
				/*
				 * case "checkDateFromCourseSummary": checkDateFromCourseSummary(); break;
				 */
			 	case "checkTwitterSharingFromCourseSummary":
			 		checkTwitterSharingFromCourseSummary();
			        break;
			 	case "checkFacebookSharingFromCourseSummary":
			 		checkFacebookSharingFromCourseSummary();
			        break;
			 	case "checkMailSharingFromCourseSummary":
			 		checkMailSharingFromCourseSummary();
			        break;
			 	case "checkEnrollButton":
			 		checkEnrollButton();
			        break;
			 	case "CardSettingsOptions":
			 		CardSettingsOptions();
			        break;
			 	case "checkEmailSettingsOption":
			 		checkEmailSettingsOption();
			        break;
			 	case "checkEmailNevermindOption":
			 		checkEmailNevermindOption();
			        break;
			 	case "checkMailsOnOffSubmission":
			 		checkMailsOnOffSubmission();
			        break;
			 	case "checkSettingsLinkToUnEnrollCard":
			 		checkSettingsLinkToUnEnrollCard();
			        break;
			 	case "checkUnenrollOption":
			 		checkUnenrollOption();
			        break;
			 	case "checkUnEnrollNeverMindButton":
			 		checkUnEnrollNeverMindButton();
			        break;
			 	case "checkSkipSurveyFromUnenrollOption":
			 		checkSkipSurveyFromUnenrollOption();
			        break;
			 	case "checkReturnToDasboardButton":
			 		checkReturnToDasboardButton();
			        break;
			 	case "checkSelectCourseToUnEnroll":
			 		checkSelectCourseToUnEnroll(row);
			        break;
			 	case "checkReasonToUnenroll":
			 		checkReasonToUnenroll();
			        break;
			 	case "selectCourseCardToComplete":
			 		selectCourseCardToComplete(row);
			        break;
			 	case "checkCourseCardTitleWithSummaryPage":
			 		checkCourseCardTitleWithSummaryPage();
			        break;
			 	case "checkCourseCardOrgWithSummaryPage":
			 		checkCourseCardOrgWithSummaryPage();
			        break;
				/*
				 * case "checkCourseCardDateWithSummaryPage":
				 * checkCourseCardDateWithSummaryPage(); break;
				 */
			 	case "checkCourseCardTitleWithDashboard":
			 		checkCourseCardTitleWithDashboard();
			        break;
			 	case "checkCourseCardOrgWithDashboard":
			 		checkCourseCardOrgWithDashboard();
			 		break;
			 	case "checkCourseCardDateWithDashboard":
			 		checkCourseCardDateWithDashboard();
			        break;
				
				}
			}
		 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			sheetStatus = "Fail"; // Set status to Fail if there are errors
		} finally {
			System.out.println("DGE Dashboard validation Process completed");
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

				List<ArrayList<String>> sheetData = excelData.get("Dashboard");

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
						columnIndex = 3;
					if (issue.contains("Fail"))
						columnIndex = 4;
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
	public void checkSignInProcess(ArrayList<String> data) {
		try {

			ArrayList<String> status = homePage.checkSignInProcess(data);
			updateExcelData(status, 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * public void checkFindCourseLink() { try {
	 * 
	 * ArrayList<String> status = courseCardProcess.checkFindCourseLink();
	 * updateExcelData(status, 3); } catch (Exception e) { e.printStackTrace(); } }
	 */
	public void checkCardSelection(ArrayList<String> data) {
		try {

			ArrayList<String> status = courseCardProcess.checkEnrollCardToCompleteCourse(data);
			updateExcelData(status, 4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkTitleFromCourseAboutPage() {
		try {

			ArrayList<String> status = courseCardProcess.checkTitleFromCourseAboutPage();
			updateExcelData(status, 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkOrgFromCourseAboutPage() {
		try {

			ArrayList<String> status = courseCardProcess.checkOrgFromCourseAboutPage();
			updateExcelData(status, 6);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkDateFromCourseSummary() {
		try {

			ArrayList<String> status = courseCardProcess.checkDateFromCourseSummary();
			updateExcelData(status, 7);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkTwitterSharingFromCourseSummary() {
		try {

			ArrayList<String> status = courseCardProcess.checkTwitterSharingFromCourseSummary();
			updateExcelData(status, 8);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkFacebookSharingFromCourseSummary() {
		try {

			ArrayList<String> status = courseCardProcess.checkFacebookSharingFromCourseSummary();
			updateExcelData(status, 9);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkMailSharingFromCourseSummary() {
		try {

			ArrayList<String> status = courseCardProcess.checkMailSharingFromCourseSummary();
			updateExcelData(status, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkEnrollButton() {
		try {

			ArrayList<String> status = courseCardProcess.checkEnrollButton();
			updateExcelData(status, 11);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void CardSettingsOptions() {
		try {

			ArrayList<String> status = courseCardProcess.CardSettingsOptions();
			updateExcelData(status, 12);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkEmailSettingsOption() {
		try {

			ArrayList<String> status = courseCardProcess.checkEmailSettingsOption();
			updateExcelData(status, 13);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkEmailNevermindOption() {
		try {

			ArrayList<String> status = courseCardProcess.checkEmailNevermindOption();
			updateExcelData(status, 14);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkMailsOnOffSubmission() {
		try {

			ArrayList<String> status = courseCardProcess.checkMailsOnOffSubmission();
			updateExcelData(status, 15);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkSettingsLinkToUnEnrollCard() {
		try {

			ArrayList<String> status = courseCardProcess.checkSettingsLinkToUnEnrollCard();
			updateExcelData(status, 16);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkUnenrollOption() {
		try {

			ArrayList<String> status = courseCardProcess.checkUnenrollOption();
			updateExcelData(status, 17);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkUnEnrollNeverMindButton() {
		try {

			ArrayList<String> status = courseCardProcess.checkUnEnrollNeverMindButton();
			updateExcelData(status, 18);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkSkipSurveyFromUnenrollOption() {
		try {

			ArrayList<String> status = courseCardProcess.checkSkipSurveyFromUnenrollOption();
			updateExcelData(status, 19);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkReturnToDasboardButton() {
		try {

			ArrayList<String> status = courseCardProcess.checkReturnToDasboardButton();
			updateExcelData(status, 20);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkSelectCourseToUnEnroll(ArrayList<String> data) {
		try {

			ArrayList<String> status = courseCardProcess.checkSelectCourseToUnEnroll(data);
			updateExcelData(status, 21);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkReasonToUnenroll() {
		try {

			ArrayList<String> status = courseCardProcess.checkReasonToUnenroll();
			updateExcelData(status, 22);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void selectCourseCardToComplete(ArrayList<String> data) {
		try {

			ArrayList<String> status = courseCardProcess.selectCourseCardToComplete(data);
			updateExcelData(status, 23);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkCourseCardTitleWithSummaryPage() {
		try {

			ArrayList<String> status = courseCardProcess.checkCourseCardTitleWithSummaryPage();
			updateExcelData(status, 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkCourseCardOrgWithSummaryPage() {
		try {

			ArrayList<String> status = courseCardProcess.checkCourseCardOrgWithSummaryPage();
			updateExcelData(status, 25);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkCourseCardDateWithSummaryPage() {
		try {

			ArrayList<String> status = courseCardProcess.checkCourseCardDateWithSummaryPage();
			updateExcelData(status, 26);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkCourseCardTitleWithDashboard() {
		try {

			ArrayList<String> status = courseCardProcess.checkCourseCardTitleWithDashboard();
			updateExcelData(status, 27);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	  public void checkCourseCardOrgWithDashboard() 
	  { 
		  try 
		  {
			  ArrayList<String> status =  courseCardProcess.checkCourseCardOrgWithDashboard(); updateExcelData(status, 28);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	  public void checkCourseCardDateWithDashboard() 
	  { 
		  try 
		  {
			  ArrayList<String> status =  courseCardProcess.checkCourseCardDateWithDashboard(); updateExcelData(status, 29);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	 

}
