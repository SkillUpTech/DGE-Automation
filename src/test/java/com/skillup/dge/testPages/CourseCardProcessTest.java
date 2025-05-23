package com.skillup.dge.testPages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.openqa.selenium.WebDriver;

import com.skillup.dge.webPages.CourseCardProcess;
import com.skillup.dge.webPages.HomePage;

public class CourseCardProcessTest implements Callable<String>
{
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	String sheetStatus = "Pass";
	CourseCardProcess courseCardProcess;
	HomePage homePage;
	public CourseCardProcessTest(WebDriver driver, ArrayList<ArrayList<String>> sheetData)
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
			 	case "CourseCardSelection_EnrollCard":
			 		CourseCardSelection_EnrollCard(row);
			        break;
			 	case "checkTitleFromCourseAboutPage":
			 		checkTitleFromCourseAboutPage();
			        break;
			 	case "checkOrgFromCourseAboutPage":
			 		checkOrgFromCourseAboutPage();
			        break;
			 	case "checkDateFromCourseSummary":
			 		checkDateFromCourseSummary();
			        break;
			 	case "checkTwitterSharingFromCourseSummary":
			 		checkTwitterSharingFromCourseSummary();
			        break;
			 	case "checkFacebookSharingFromCourseSummary":
			 		checkFacebookSharingFromCourseSummary();
			        break;
			 	case "checkMailSharingFromCourseSummary":
			 		checkMailSharingFromCourseSummary();
			        break;
				}
			}
		 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			sheetStatus = "Fail"; // Set status to Fail if there are errors
		} finally {
			System.out.println("DGE HomePage validation Process completed");
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

				List<ArrayList<String>> sheetData = excelData.get("CourseCard_AIF");

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
			updateExcelData(status, 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkSignInProcess(ArrayList<String> data) {
		try {

			ArrayList<String> status = homePage.checkSignInProcess(data);
			updateExcelData(status, 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void CourseCardSelection_EnrollCard(ArrayList<String> data) {
		try {

			ArrayList<String> status = courseCardProcess.CourseCardSelection_EnrollCard(data);
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
	public void checkUnEnrollOption() {
		try {

			ArrayList<String> status = courseCardProcess.checkEnrollButton();
			updateExcelData(status, 13);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkUnEnrollProcess() {
		try {

			ArrayList<String> status = courseCardProcess.checkEnrollButton();
			updateExcelData(status, 14);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkEmailSettingsOption() {
		try {

			ArrayList<String> status = courseCardProcess.checkEnrollButton();
			updateExcelData(status, 15);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkEmailSettingProcess() {
		try {

			ArrayList<String> status = courseCardProcess.checkEnrollButton();
			updateExcelData(status, 16);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkCourseCardTitleFromContentPage() {
		try {

			ArrayList<String> status = courseCardProcess.checkEnrollButton();
			updateExcelData(status, 18);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkCourseCardOrgFromContentPage() {
		try {

			ArrayList<String> status = courseCardProcess.checkEnrollButton();
			updateExcelData(status, 19);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkCourseCardEndDateFromContentPage() {
		try {

			ArrayList<String> status = courseCardProcess.checkEnrollButton();
			updateExcelData(status, 20);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkExpandAllProcess() {
		try {

			ArrayList<String> status = courseCardProcess.checkEnrollButton();
			updateExcelData(status, 21);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkCollapseAll() {
		try {

			ArrayList<String> status = courseCardProcess.checkEnrollButton();
			updateExcelData(status, 22);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkBookMarksLink() {
		try {

			ArrayList<String> status = courseCardProcess.checkEnrollButton();
			updateExcelData(status, 23);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkLaunchTourLink() {
		try {

			ArrayList<String> status = courseCardProcess.checkEnrollButton();
			updateExcelData(status, 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkViewAllCourseLink() {
		try {

			ArrayList<String> status = courseCardProcess.checkEnrollButton();
			updateExcelData(status, 25);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkAllLinksFromCourseContent() {
		try {

			ArrayList<String> status = courseCardProcess.checkEnrollButton();
			updateExcelData(status, 26);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
