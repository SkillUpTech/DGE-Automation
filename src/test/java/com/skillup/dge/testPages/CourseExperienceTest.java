package com.skillup.dge.testPages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.openqa.selenium.WebDriver;

import com.skillup.dge.webPages.CourseCardProcess;
import com.skillup.dge.webPages.HomePage;

public class CourseExperienceTest implements Callable<String>
{
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	String sheetStatus = "Pass";
	CourseCardProcess courseCardProcess;
	HomePage homePage;
	public CourseExperienceTest(WebDriver driver, ArrayList<ArrayList<String>> sheetData)
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
			 	case "checkExpandAllProcess":
			 		checkExpandAllProcess();
			        break;
			 	case "checkCollapseAll":
			 		checkCollapseAll();
			        break;
			 	case "checkStartCourseButton":
			 		checkStartCourseButton();
			        break;
			 	case "checkBookMarksPage":
			 		checkBookMarksPage();
			        break;
			 	case "checkPageISBookMarkedOrNot":
			 		checkPageISBookMarkedOrNot();
			        break;
			 	case "checkPageIsNavigatoToCourse":
			 		checkPageIsNavigatoToCourse();
			        break;
			 	case "checkPageIsBookmarkedOrNot":
			 		checkPageISBookMarkedOrNot();
			        break;
			 	case "checkViewButtonFromBookmarkPage":
			 		checkViewButtonFromBookmarkPage();
			        break;
			 	case "checkCourseOutlineTrayIcon":
			 		checkCourseOutlineTrayIcon();
			        break;
			 	case "checkDiscussionIcon":
			 		checkDiscussionIcon();
			        break;
			 	case "checkDiscussionFrame":
			 		checkDiscussionFrame();
			        break;
			 	case "checkDiscussionTitle":
			 		checkDiscussionTitle();
			        break;
			 	case "checkDiscussionSectionCloseIcon":
			 		checkDiscussionSectionCloseIcon();
			        break;
			 	case "checkBellIcon":
			 		checkBellIcon();
			        break;
			 	case "checkNotificationTray":
			 		checkNotificationTray();
			        break;
			 	case "checkNavigateToCourseHomePage":
			 		checkNavigateToCourseHomePage();
			        break;
			 	case "checkLaunchTourLink":
			 		checkLaunchTourLink();
			        break;
			 	case "checkLaunchTourAlert":
			 		checkLaunchTourAlert();
			        break;
			 	case "checkDismissFromLaunchTourAlert":
			 		checkDismissFromLaunchTourAlert();
			        break;
			 	case "checkOkayFromLaunchTourAlert":
			 		checkOkayFromLaunchTourAlert();
			        break;
			 	case "checkNextFromLaunchTourAlert":
			 		checkNextFromLaunchTourAlert();
			        break;
			 	case "checkViewAllCourseLink":
			 		checkViewAllCourseLink();
			        break;
				// case "startCourse": startCourse(row); break; 
				 
			 	case "checkProgressTab":
			 		checkProgressTab();
			        break;
			        
			 	case "checkDiscussionTabFromHomePage":
			 		checkDiscussionTabFromHomePage();
			        break;
			 	case "checkAddPostButtonFromDiscussionHomePage":
			 		checkAddPostButtonFromDiscussionHomePage();
			        break;
			 	case "checkAddPostFromDisplayedFromDiscussionPage":
			 		checkAddPostFromDisplayedFromDiscussionPage();
			        break;
			 	case "checkCancelFromDiscussionPage":
			 		checkCancelFromDiscussionPage();
			        break;
			 	case "checkSelectTopicAreaFromDiscussionPage":
			 		checkSelectTopicAreaFromDiscussionPage();
			        break;
			 	case "checkPostFieldFromDiscussionPage":
			 		checkPostFieldFromDiscussionPage();
			        break;
			 	case "checkParagraphFromDiscussionPage":
			 		checkParagraphFromDiscussionPage();
			        break;
			 	case "checkSubmitButtonDiscussionFromPage":
			 		checkSubmitButtonDiscussionFromPage();
			        break;
			 	case "checkRecentActivityFromDiscussionPage":
			 		checkRecentActivityFromDiscussionPage();
			        break;
			 	case "checkAllPostsFromDiscussionPage":
			 		checkAllPostsFromDiscussionPage();
			        break;
			 	case "checkTopicsFromDiscussionPage":
			 		checkTopicsFromDiscussionPage();
			        break;
			 	case "checkLearnersFromDiscussionPage":
			 		checkLearnersFromDiscussionPage();
			        break;
			 	case "checkNotesTab":
			 		checkNotesTab();
			        break;
			 	case "checkWikiTab":
			 		checkWikiTab();
			        break;
			 	case "checkArticle":
			 		checkArticle();
			        break;
			 	case "checkArticleFormBackButton":
			 		checkArticleFormBackButton();
			        break;
			 	case "checkArticleCreation":
			 		checkArticleCreation(row);
			        break;
			 	case "checkEditArticleButton":
			 		checkEditArticleButton();
			        break;
			 	case "checkAlertAfterModifyChanges":
			 		checkAlertAfterModifyChanges();
			        break;
			 	case "checkChangesArticleButton":
			 		checkChangesArticleButton();
			        break;

			}
		 
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			sheetStatus = "Fail"; // Set status to Fail if there are errors
		} finally {
			System.out.println("course experience validation Process completed");
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

				List<ArrayList<String>> sheetData = excelData.get("CourseExperience");

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
	  public void checkExpandAllProcess() 
	  { 
		  try 
		  {
			  ArrayList<String> status =  courseCardProcess.checkExpandAll(); updateExcelData(status, 4);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	  public void checkCollapseAll() 
	  { 
		  try 
		  {
			  ArrayList<String> status =  courseCardProcess.checkCollapseAll(); updateExcelData(status, 5);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	  public void checkStartCourseButton() 
	  { 
		  try 
		  {
			  ArrayList<String> status =  courseCardProcess.checkStartCourseButton(); updateExcelData(status, 6);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	  public void checkBookMarksPage() 
	  { 
		  try 
		  {
			  ArrayList<String> status =  courseCardProcess.checkBookMarksPage(); updateExcelData(status, 7);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	  public void checkPageISBookMarkedOrNot() 
	  { 
		  try 
		  {
			  ArrayList<String> status =  courseCardProcess.checkPageISBookMarkedOrNot(); updateExcelData(status, 8);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	  public void checkPageIsNavigatoToCourse() 
	  { 
		  try 
		  {
			  ArrayList<String> status =  courseCardProcess.checkPageIsNavigatoToCourse(); updateExcelData(status, 9);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }
	  public void checkBookmarksLinkFromHome() 
	  { 
		  try 
		  {
			  ArrayList<String> status =  courseCardProcess.checkBookmarksLinkFromHome(); updateExcelData(status, 10);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }

		public void checkViewButtonFromBookmarkPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkViewButtonFromBookmarkPage();
		        updateExcelData(status, 11);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkCourseOutlineTrayIcon() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkCourseOutlineTrayIcon();
		        updateExcelData(status, 12);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkDiscussionIcon() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkDiscussionIcon();
		        updateExcelData(status, 13);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkDiscussionFrame() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkDiscussionFrame();
		        updateExcelData(status, 14);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkDiscussionTitle() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkDiscussionTitle();
		        updateExcelData(status, 15);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkDiscussionSectionCloseIcon() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkDiscussionSectionCloseIcon();
		        updateExcelData(status, 16);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkBellIcon() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkBellIcon();
		        updateExcelData(status, 17);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkNotificationTray() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkNotificationTray();
		        updateExcelData(status, 18);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkNavigateToCourseHomePage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkNavigateToCourseHomePage();
		        updateExcelData(status, 19);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkLaunchTourLink() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkLaunchTourLink();
		        updateExcelData(status, 20);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkLaunchTourAlert() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkLaunchTourAlert();
		        updateExcelData(status, 21);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkDismissFromLaunchTourAlert() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkDismissFromLaunchTourAlert();
		        updateExcelData(status, 22);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkOkayFromLaunchTourAlert() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkOkayFromLaunchTourAlert();
		        updateExcelData(status, 23);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkNextFromLaunchTourAlert() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkNextFromLaunchTourAlert();
		        updateExcelData(status, 24);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkViewAllCourseLink() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkViewAllCourseLink();
		        updateExcelData(status, 25);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void startCourse(ArrayList<String> data) 
		{
		    try
		    {
		        ArrayList<String> status = courseCardProcess.startCourse(data);
		        updateExcelData(status, 26);
		    } 
		    catch (Exception e) 
		    {
		        e.printStackTrace();
		    }
		}

		public void checkProgressTab() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkProgressTab();
		        updateExcelData(status, 27);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkDiscussionTabFromHomePage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkDiscussionTabFromHomePage();
		        updateExcelData(status, 28);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkAddPostButtonFromDiscussionHomePage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkAddPostButtonFromDiscussionHomePage();
		        updateExcelData(status, 29);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkAddPostFromDisplayedFromDiscussionPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkAddPostFromDisplayedFromDiscussionPage();
		        updateExcelData(status, 30);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkCancelFromDiscussionPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkCancelFromDiscussionPage();
		        updateExcelData(status, 31);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkSelectTopicAreaFromDiscussionPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkSelectTopicAreaFromDiscussionPage();
		        updateExcelData(status, 32);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkPostFieldFromDiscussionPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkPostFieldFromDiscussionPage();
		        updateExcelData(status, 33);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkParagraphFromDiscussionPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkParagraphFromDiscussionPage();
		        updateExcelData(status, 34);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkSubmitButtonDiscussionFromPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkSubmitButtonDiscussionFromPage();
		        updateExcelData(status, 35);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkRecentActivityFromDiscussionPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkRecentActivityFromDiscussionPage();
		        updateExcelData(status, 36);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkAllPostsFromDiscussionPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkAllPostsFromDiscussionPage();
		        updateExcelData(status, 37);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkTopicsFromDiscussionPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkTopicsFromDiscussionPage();
		        updateExcelData(status, 38);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkLearnersFromDiscussionPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkLearnersFromDiscussionPage();
		        updateExcelData(status, 39);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkNotesTab() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkNotesTab();
		        updateExcelData(status, 40);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkWikiTab() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkWikiTab();
		        updateExcelData(status, 41);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkArticle() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkArticle();
		        updateExcelData(status, 42);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkArticleFormBackButton() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkArticleFormBackButton();
		        updateExcelData(status, 43);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkArticleCreation(ArrayList<String> data) {
		    try {
		        ArrayList<String> status = courseCardProcess.checkArticleCreation(data);
		        updateExcelData(status, 44);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkViewArticleButton() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkViewArticleButton();
		        updateExcelData(status, 45);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkEditArticleButton() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkEditArticleButton();
		        updateExcelData(status, 46);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkAlertAfterModifyChanges() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkAlertAfterModifyChanges();
		        updateExcelData(status, 47);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkChangesArticleButton() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkChangesArticleButton();
		        updateExcelData(status, 48);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
}
