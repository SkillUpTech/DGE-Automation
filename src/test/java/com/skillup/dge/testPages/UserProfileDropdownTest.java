package com.skillup.dge.testPages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.openqa.selenium.WebDriver;

import com.skillup.dge.webPages.CourseCardProcess;
import com.skillup.dge.webPages.HomePage;

public class UserProfileDropdownTest implements Callable<String>{
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	String sheetStatus = "Pass";
	CourseCardProcess courseCardProcess;
	HomePage homePage;
	public UserProfileDropdownTest(WebDriver driver, ArrayList<ArrayList<String>> sheetData)
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
			 	case "checkBeginCourseCard":
			 		checkBeginCourseCard(row);
			        break;
				case "checkAbleToOpenUserDropdown":
			 		checkAbleToOpenUserDropdown();
			        break;
			 	case "checkCoursesOptionFromDropdown":
			 		checkCoursesOptionFromDropdown();
			        break;
			 	case "checkDiscoverOptionFromDropdown":
			 		checkDiscoverOptionFromDropdown();
			        break;
			 	case "checkDashboardOptionFromDropdown":
			 		checkDashboardOptionFromDropdown();
			        break;
			 	case "checkProfileOptionsFromUserDropdown":
			 		checkProfileOptionsFromUserDropdown();
			        break;
			 	case "checkViewRecordFromProfilePage":
			 		checkViewRecordFromProfilePage();
			        break;
			 	case "checkLinkFromRecordPage":
			 		checkLinkFromRecordPage();
			        break;
			 	case "checkBackToProfileIcon":
			 		checkBackToProfileIcon();
			        break;
			 	case "checkDOBLink":
			 		checkDOBLink();
			        break;
			 	case "checkEditFullnameFromAccountInformation":
			 		checkEditFullnameFromAccountInformation();
			        break;
			 	case "checkCancelFromEditFullnameInAccountInformation":
			 		checkCancelFromEditFullnameInAccountInformation();
			        break;
			 	case "checkSaveFromEditFullnameInAccountInformation":
			 		checkSaveFromEditFullnameInAccountInformation();
			        break;
			 	case "checkEducationFromProfileInformation":
			 		checkEducationFromProfileInformation();
			        break;
			 	case "checkEducationToCancelFromProfileInformation":
			 		checkEducationToCancelFromProfileInformation();
			        break;
			 	case "checkEducationToSaveFromProfileInformation":
			 		checkEducationToSaveFromProfileInformation();
			        break;
			 	case "checkGenderFromProfileInformation":
			 		checkGenderFromProfileInformation();
			        break;
			 	case "checkGenderToCancelFromProfileInformation":
			 		checkGenderToCancelFromProfileInformation();
			        break;
			 	case "checkGenderToSaveFromProfileInformation":
			 		checkGenderToSaveFromProfileInformation();
			        break;
			 	case "checkSpokenlanguageFromProfileInformation":
			 		checkSpokenlanguageFromProfileInformation();
			        break;
			 	case "checkCancelInSpokenlanguageFromProfileInformation":
			 		checkCancelInSpokenlanguageFromProfileInformation();
			        break;
			 	case "checkSaveInSpokenlanguageFromProfileInformation":
			 		checkSaveInSpokenlanguageFromProfileInformation();
			        break;
			 	case "checkSocialMediaLinks":
			 		checkSocialMediaLinks();
			        break;
			 	case "checkLinkedInFromSocialMediaLinks":
			 		checkLinkedInFromSocialMediaLinks();
			        break;
			 	case "checkLinkedInToCancelFromSocialMediaLinks":
			 		checkLinkedInToCancelFromSocialMediaLinks();
			        break;
			 	case "checkLinkedInToSaveFromSocialMediaLinks":
			 		checkLinkedInToSaveFromSocialMediaLinks();
			        break;
			 	case "checkFacebookFromSocialMediaLinks":
			 		checkFacebookFromSocialMediaLinks();
			        break;
			 	case "checkFacebookToCancelFromSocialMediaLinks":
			 		checkFacebookToCancelFromSocialMediaLinks();
			        break;
			 	case "checkFacebookToSaveFromSocialMediaLinks":
			 		checkFacebookToSaveFromSocialMediaLinks();
			        break;
			 	case "checkTwitterFromSocialMediaLinks":
			 		checkTwitterFromSocialMediaLinks();
			        break;
			 	case "checkTwitterToCancelFromSocialMediaLinks":
			 		checkTwitterToCancelFromSocialMediaLinks();
			        break;
			 	case "checkTwitterToSaveFromSocialMediaLinks":
			 		checkTwitterToSaveFromSocialMediaLinks();
			        break;
			 	case "checkLinkedAccounts":
			 		checkLinkedAccounts();
			        break;
			 	case "checkOrderHistory":
			 		checkOrderHistory();
			        break;
			 	case "checkSupportLinkFromOrderHistory":
			 		checkSupportLinkFromOrderHistory();
			        break;
			 	case "checkSignOutProcess":
			 		checkSignOutProcess();
			        break;
			 	case "checkEnrollCourseWithoutSignIn":
			 		checkEnrollCourseWithoutSignIn(row.get(1));
			        break;
				}
			}
		 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			sheetStatus = "Fail"; // Set status to Fail if there are errors
		} finally {
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

				List<ArrayList<String>> sheetData = excelData.get("UserProfileDropdown");

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
	public void checkSignInProcess(ArrayList<String> data) {
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
	public void checkAbleToOpenUserDropdown() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkAbleToOpenUserDropdown();
	        updateExcelData(status, 4);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkCoursesOptionFromDropdown() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkCoursesOptionFromDropdown();
	        updateExcelData(status, 5);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkDiscoverOptionFromDropdown() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkDiscoverOptionFromDropdown();
	        updateExcelData(status, 6);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkDashboardOptionFromDropdown() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkDashboardOptionFromDropdown();
	        updateExcelData(status, 7);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkProfileOptionsFromUserDropdown() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkProfileOptionsFromUserDropdown();
	        updateExcelData(status, 8);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkViewRecordFromProfilePage() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkViewRecordFromProfilePage();
	        updateExcelData(status, 9);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkLinkFromRecordPage() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkLinkFromRecordPage();
	        updateExcelData(status, 10);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkBackToProfileIcon() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkBackToProfileIcon();
	        updateExcelData(status, 11);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkDOBLink() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkDOBLink();
	        updateExcelData(status, 12);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkEditFullnameFromAccountInformation() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkEditFullnameFromAccountInformation();
	        updateExcelData(status, 13);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkCancelFromEditFullnameInAccountInformation() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkcancelFromEditFullnameInAccountInformation();
	        updateExcelData(status, 14);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkSaveFromEditFullnameInAccountInformation() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkSaveFromEditFullnameInAccountInformation();
	        updateExcelData(status, 15);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkEducationFromProfileInformation() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkEducationFromProfileInformation();
	        updateExcelData(status, 16);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkEducationToCancelFromProfileInformation() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkEducationToCancelFromProfileInformation();
	        updateExcelData(status, 17);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkEducationToSaveFromProfileInformation() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkEducationToSaveFromProfileInformation();
	        updateExcelData(status, 18);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkGenderFromProfileInformation() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkGenderFromProfileInformation();
	        updateExcelData(status, 19);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkGenderToCancelFromProfileInformation() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkGenderToCancelFromProfileInformation();
	        updateExcelData(status, 20);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkGenderToSaveFromProfileInformation() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkGenderToSaveFromProfileInformation();
	        updateExcelData(status, 21);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkSpokenlanguageFromProfileInformation() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkSpokenlanguageFromProfileInformation();
	        updateExcelData(status, 22);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkCancelInSpokenlanguageFromProfileInformation() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkCancelInSpokenlanguageFromProfileInformation();
	        updateExcelData(status, 23);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkSaveInSpokenlanguageFromProfileInformation() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkSaveInSpokenlanguageFromProfileInformation();
	        updateExcelData(status, 24);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkSocialMediaLinks() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkSocialMediaLinks();
	        updateExcelData(status, 25);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkLinkedInFromSocialMediaLinks() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkLinkedInFromSocialMediaLinks();
	        updateExcelData(status, 26);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkLinkedInToCancelFromSocialMediaLinks() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkLinkedInToCancelFromSocialMediaLinks();
	        updateExcelData(status, 27);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkLinkedInToSaveFromSocialMediaLinks() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkLinkedInToSaveFromSocialMediaLinks();
	        updateExcelData(status, 28);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkFacebookFromSocialMediaLinks() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkFacebookFromSocialMediaLinks();
	        updateExcelData(status, 29);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkFacebookToCancelFromSocialMediaLinks() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkFacebookToCancelFromSocialMediaLinks();
	        updateExcelData(status, 30);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkFacebookToSaveFromSocialMediaLinks() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkFacebookToSaveFromSocialMediaLinks();
	        updateExcelData(status, 31);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkTwitterFromSocialMediaLinks() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkTwitterFromSocialMediaLinks();
	        updateExcelData(status, 32);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkTwitterToCancelFromSocialMediaLinks() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkTwitterToCancelFromSocialMediaLinks();
	        updateExcelData(status, 33);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkTwitterToSaveFromSocialMediaLinks() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkTwitterToSaveFromSocialMediaLinks();
	        updateExcelData(status, 34);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkLinkedAccounts() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkLinkedAccounts();
	        updateExcelData(status, 35);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkOrderHistory() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkOrderHistory();
	        updateExcelData(status, 36);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkSupportLinkFromOrderHistory() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkSupportLinkFromOrderHistory();
	        updateExcelData(status, 37);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkSignOutProcess() {
	    try {
	        ArrayList<String> status = courseCardProcess.checkSignOutProcess();
	        updateExcelData(status, 38);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void checkEnrollCourseWithoutSignIn(String courseName) {
	    try {
	        ArrayList<String> status = courseCardProcess.checkEnrollCourseWithoutSignIn(courseName);
	        updateExcelData(status, 39);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
