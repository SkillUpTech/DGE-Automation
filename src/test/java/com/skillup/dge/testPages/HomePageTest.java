package com.skillup.dge.testPages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.openqa.selenium.WebDriver;

import com.skillup.dge.webPages.HomePage;

public class HomePageTest implements Callable<String>
{
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	String sheetStatus = "Pass";
	HomePage homePage;

	public HomePageTest(WebDriver driver, ArrayList<ArrayList<String>> sheetData)
	{
		this.sheetData = sheetData;
		this.driver = driver;
	}

	@Override
	public String call() throws Exception 
	{
		// TODO Auto-generated method stub
		System.out.println("DGE HomePage validation Process started");
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
			 	case "CheckSkillupLogoFromHomePageTop":
			 		CheckSkillupLogoFromHomePageTop();
			        break;
			 	case "CheckDiscoverTab":
			 		CheckDiscoverTab();
			        break;
			 	case "CheckRegisterButton":
			 		CheckRegisterButton();
			        break;
			 	case "CheckSignInButton":
			 		CheckSignInButton();
			        break;
			 	case "CheckSeacrhFieldTextBox":
			 		CheckSeacrhFieldTextBox();
			        break;
			 	case "CheckLogoFromHomepageFooter":
			 		CheckLogoFromHomepageFooter();
			        break;
			 	case "CheckAboutUsLinkFromHome":
			 		CheckAboutUsLinkFromHome();
			        break;
			 	case "CheckBlogLinkFromHome":
			 		CheckBlogLinkFromHome();
			        break;
			 	case "CheckDonateLinkFromHome":
			 		CheckDonateLinkFromHome();
			        break;
			 	case "CheckTOSLinkFromHome":
			 		CheckTOSLinkFromHome();
			        break;
			 	case "CheckPrivacyPolicyLinkFromHome":
			 		CheckPrivacyPolicyLinkFromHome();
			        break;
			 	case "CheckHelpLinkFromHome":
			 		CheckHelpLinkFromHome();
			        break;
			 	case "CheckContactUsLinkFromHome":
			 		CheckContactUsLinkFromHome();
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

				List<ArrayList<String>> sheetData = excelData.get("card");

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
	
	public void CheckSkillupLogoFromHomePageTop() {
		try {

			ArrayList<String> status = homePage.CheckSkillupLogoFromHomePageTop();
			updateExcelData(status, 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CheckDiscoverTab() {
		try {

			ArrayList<String> status = homePage.CheckDiscoverTab();
			updateExcelData(status, 4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CheckRegisterButton() {
		try {

			ArrayList<String> status = homePage.CheckRegisterButton();
			updateExcelData(status, 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CheckSignInButton() {
		try {

			ArrayList<String> status = homePage.CheckSignInButton();
			updateExcelData(status, 6);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void CheckSeacrhFieldTextBox() {
		try {

			ArrayList<String> status = homePage.CheckSeacrhFieldTextBox();
			updateExcelData(status, 7);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CheckLogoFromHomepageFooter() {
		try {

			ArrayList<String> status = homePage.CheckLogoFromHomepageFooter();
			updateExcelData(status, 8);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CheckAboutUsLinkFromHome() {
		try {

			ArrayList<String> status = homePage.CheckAboutUsLinkFromHome();
			updateExcelData(status, 9);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CheckBlogLinkFromHome() {
		try {

			ArrayList<String> status = homePage.CheckBlogLinkFromHome();
			updateExcelData(status, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CheckDonateLinkFromHome() {
		try {

			ArrayList<String> status = homePage.CheckDonateLinkFromHome();
			updateExcelData(status, 11);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CheckTOSLinkFromHome() {
		try {

			ArrayList<String> status = homePage.CheckTOSLinkFromHome();
			updateExcelData(status, 12);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void CheckPrivacyPolicyLinkFromHome() {
		try {

			ArrayList<String> status = homePage.CheckPrivacyPolicyLinkFromHome();
			updateExcelData(status, 13);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void CheckHelpLinkFromHome() {
		try {

			ArrayList<String> status = homePage.CheckHelpLinkFromHome();
			updateExcelData(status, 14);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void CheckContactUsLinkFromHome() {
		try {

			ArrayList<String> status = homePage.CheckContactUsLinkFromHome();
			updateExcelData(status, 15);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
