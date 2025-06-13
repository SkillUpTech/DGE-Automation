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
			 	case "checkFindCourseLink":
			 		checkFindCourseLink();
			        break;
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
			 	case "checkBeginCourseCard":
			 		checkBeginCourseCard();
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
				/*
				 * case "startCourse": startCourse(row.get(1)); break;
				 */
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
			 		checkArticleCreation();
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

				List<ArrayList<String>> sheetData = excelData.get("CourseCard");

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
	
	public void checkFindCourseLink() {
		try {

			ArrayList<String> status = courseCardProcess.checkFindCourseLink();
			updateExcelData(status, 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
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
	  public void checkBeginCourseCard() 
	  { 
		  try 
		  {
			  ArrayList<String> status =  courseCardProcess.checkBeginCourseCard(); updateExcelData(status, 30);
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
			  ArrayList<String> status =  courseCardProcess.checkExpandAll(); updateExcelData(status, 31);
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
			  ArrayList<String> status =  courseCardProcess.checkCollapseAll(); updateExcelData(status, 32);
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
			  ArrayList<String> status =  courseCardProcess.checkStartCourseButton(); updateExcelData(status, 33);
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
			  ArrayList<String> status =  courseCardProcess.checkBookMarksPage(); updateExcelData(status, 34);
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
			  ArrayList<String> status =  courseCardProcess.checkPageISBookMarkedOrNot(); updateExcelData(status, 35);
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
			  ArrayList<String> status =  courseCardProcess.checkPageIsNavigatoToCourse(); updateExcelData(status, 36);
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
			  ArrayList<String> status =  courseCardProcess.checkBookmarksLinkFromHome(); updateExcelData(status, 37);
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace(); 
		  }
	  }

		public void checkViewButtonFromBookmarkPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkViewButtonFromBookmarkPage();
		        updateExcelData(status, 38);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkCourseOutlineTrayIcon() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkCourseOutlineTrayIcon();
		        updateExcelData(status, 39);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkDiscussionIcon() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkDiscussionIcon();
		        updateExcelData(status, 40);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkDiscussionFrame() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkDiscussionFrame();
		        updateExcelData(status, 41);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkDiscussionTitle() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkDiscussionTitle();
		        updateExcelData(status, 42);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkDiscussionSectionCloseIcon() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkDiscussionSectionCloseIcon();
		        updateExcelData(status, 43);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkBellIcon() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkBellIcon();
		        updateExcelData(status, 44);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkNotificationTray() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkNotificationTray();
		        updateExcelData(status, 45);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkNavigateToCourseHomePage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkNavigateToCourseHomePage();
		        updateExcelData(status, 46);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkLaunchTourLink() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkLaunchTourLink();
		        updateExcelData(status, 47);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkLaunchTourAlert() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkLaunchTourAlert();
		        updateExcelData(status, 48);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkDismissFromLaunchTourAlert() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkDismissFromLaunchTourAlert();
		        updateExcelData(status, 49);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkOkayFromLaunchTourAlert() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkOkayFromLaunchTourAlert();
		        updateExcelData(status, 50);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkNextFromLaunchTourAlert() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkNextFromLaunchTourAlert();
		        updateExcelData(status, 51);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkViewAllCourseLink() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkViewAllCourseLink();
		        updateExcelData(status, 52);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void startCourse(String filepath) {
		    try {
		        ArrayList<String> status = courseCardProcess.startCourse(filepath);
		        updateExcelData(status, 53);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkProgressTab() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkProgressTab();
		        updateExcelData(status, 54);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void checkDiscussionTabFromHomePage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkDiscussionTabFromHomePage();
		        updateExcelData(status, 55);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkAddPostButtonFromDiscussionHomePage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkAddPostButtonFromDiscussionHomePage();
		        updateExcelData(status, 56);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkAddPostFromDisplayedFromDiscussionPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkAddPostFromDisplayedFromDiscussionPage();
		        updateExcelData(status, 57);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkCancelFromDiscussionPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkCancelFromDiscussionPage();
		        updateExcelData(status, 58);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkSelectTopicAreaFromDiscussionPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkSelectTopicAreaFromDiscussionPage();
		        updateExcelData(status, 59);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkPostFieldFromDiscussionPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkPostFieldFromDiscussionPage();
		        updateExcelData(status, 60);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkParagraphFromDiscussionPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkParagraphFromDiscussionPage();
		        updateExcelData(status, 61);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkSubmitButtonDiscussionFromPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkSubmitButtonDiscussionFromPage();
		        updateExcelData(status, 62);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkRecentActivityFromDiscussionPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkRecentActivityFromDiscussionPage();
		        updateExcelData(status, 63);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkAllPostsFromDiscussionPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkAllPostsFromDiscussionPage();
		        updateExcelData(status, 64);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkTopicsFromDiscussionPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkTopicsFromDiscussionPage();
		        updateExcelData(status, 65);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkLearnersFromDiscussionPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkLearnersFromDiscussionPage();
		        updateExcelData(status, 66);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkNotesTab() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkNotesTab();
		        updateExcelData(status, 67);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkWikiTab() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkWikiTab();
		        updateExcelData(status, 68);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkArticle() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkArticle();
		        updateExcelData(status, 69);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkArticleFormBackButton() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkArticleFormBackButton();
		        updateExcelData(status, 70);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkArticleCreation() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkArticleCreation();
		        updateExcelData(status, 71);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkViewArticleButton() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkViewArticleButton();
		        updateExcelData(status, 72);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkEditArticleButton() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkEditArticleButton();
		        updateExcelData(status, 73);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkAlertAfterModifyChanges() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkAlertAfterModifyChanges();
		        updateExcelData(status, 74);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkChangesArticleButton() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkChangesArticleButton();
		        updateExcelData(status, 75);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkAbleToOpenUserDropdown() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkAbleToOpenUserDropdown();
		        updateExcelData(status, 76);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkCoursesOptionFromDropdown() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkCoursesOptionFromDropdown();
		        updateExcelData(status, 77);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkDiscoverOptionFromDropdown() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkDiscoverOptionFromDropdown();
		        updateExcelData(status, 78);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkDashboardOptionFromDropdown() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkDashboardOptionFromDropdown();
		        updateExcelData(status, 79);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkProfileOptionsFromUserDropdown() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkProfileOptionsFromUserDropdown();
		        updateExcelData(status, 80);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkViewRecordFromProfilePage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkViewRecordFromProfilePage();
		        updateExcelData(status, 81);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkLinkFromRecordPage() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkLinkFromRecordPage();
		        updateExcelData(status, 82);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkBackToProfileIcon() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkBackToProfileIcon();
		        updateExcelData(status, 83);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkDOBLink() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkDOBLink();
		        updateExcelData(status, 84);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkEditFullnameFromAccountInformation() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkEditFullnameFromAccountInformation();
		        updateExcelData(status, 85);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkCancelFromEditFullnameInAccountInformation() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkcancelFromEditFullnameInAccountInformation();
		        updateExcelData(status, 86);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkSaveFromEditFullnameInAccountInformation() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkSaveFromEditFullnameInAccountInformation();
		        updateExcelData(status, 87);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkEducationFromProfileInformation() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkEducationFromProfileInformation();
		        updateExcelData(status, 88);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkEducationToCancelFromProfileInformation() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkEducationToCancelFromProfileInformation();
		        updateExcelData(status, 89);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkEducationToSaveFromProfileInformation() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkEducationToSaveFromProfileInformation();
		        updateExcelData(status, 90);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkGenderFromProfileInformation() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkGenderFromProfileInformation();
		        updateExcelData(status, 91);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkGenderToCancelFromProfileInformation() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkGenderToCancelFromProfileInformation();
		        updateExcelData(status, 92);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkGenderToSaveFromProfileInformation() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkGenderToSaveFromProfileInformation();
		        updateExcelData(status, 93);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkSpokenlanguageFromProfileInformation() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkSpokenlanguageFromProfileInformation();
		        updateExcelData(status, 94);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkCancelInSpokenlanguageFromProfileInformation() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkCancelInSpokenlanguageFromProfileInformation();
		        updateExcelData(status, 95);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkSaveInSpokenlanguageFromProfileInformation() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkSaveInSpokenlanguageFromProfileInformation();
		        updateExcelData(status, 96);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkSocialMediaLinks() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkSocialMediaLinks();
		        updateExcelData(status, 97);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkLinkedInFromSocialMediaLinks() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkLinkedInFromSocialMediaLinks();
		        updateExcelData(status, 98);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkLinkedInToCancelFromSocialMediaLinks() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkLinkedInToCancelFromSocialMediaLinks();
		        updateExcelData(status, 99);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkLinkedInToSaveFromSocialMediaLinks() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkLinkedInToSaveFromSocialMediaLinks();
		        updateExcelData(status, 100);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkFacebookFromSocialMediaLinks() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkFacebookFromSocialMediaLinks();
		        updateExcelData(status, 101);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkFacebookToCancelFromSocialMediaLinks() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkFacebookToCancelFromSocialMediaLinks();
		        updateExcelData(status, 102);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkFacebookToSaveFromSocialMediaLinks() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkFacebookToSaveFromSocialMediaLinks();
		        updateExcelData(status, 103);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkTwitterFromSocialMediaLinks() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkTwitterFromSocialMediaLinks();
		        updateExcelData(status, 104);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkTwitterToCancelFromSocialMediaLinks() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkTwitterToCancelFromSocialMediaLinks();
		        updateExcelData(status, 105);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkTwitterToSaveFromSocialMediaLinks() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkTwitterToSaveFromSocialMediaLinks();
		        updateExcelData(status, 106);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkLinkedAccounts() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkLinkedAccounts();
		        updateExcelData(status, 107);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkOrderHistory() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkOrderHistory();
		        updateExcelData(status, 108);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkSupportLinkFromOrderHistory() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkSupportLinkFromOrderHistory();
		        updateExcelData(status, 109);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkSignOutProcess() {
		    try {
		        ArrayList<String> status = courseCardProcess.checkSignOutProcess();
		        updateExcelData(status, 110);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		public void checkEnrollCourseWithoutSignIn(String courseName) {
		    try {
		        ArrayList<String> status = courseCardProcess.checkEnrollCourseWithoutSignIn(courseName);
		        updateExcelData(status, 111);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		

}
