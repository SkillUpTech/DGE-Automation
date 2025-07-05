package com.skillup.dge.webPages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MembershipPage
{
	WebDriver driver;
	HomePage homePage;
	RegisterPage registerPage;
	CourseCardProcess courseCardProcess;
	public MembershipPage(WebDriver driver)
	{
		this.driver = driver;
		this.homePage = new HomePage(driver);
		this.registerPage = new RegisterPage(driver);
	}
	public ArrayList<String> checkInstructorTab()
	{
		ArrayList<String> status = new ArrayList<String>();
		String instructorTab = "//div[@class='nav-menu']//a[7]";
		try 
		{
			if (driver.findElements(By.xpath(instructorTab)).size() > 0) {
				status.add(homePage.clickWebElement(instructorTab));
				Thread.sleep(200);
				status.add(registerPage.FocusWindow("instructor"));
			} 
			else
			{
				System.out.println("Instructor tab is not displayed");
				status.add("Fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkMembershipTab()
	{
		ArrayList<String> status = new ArrayList<String>();
		String membershipTab = "//ul[@class='instructor-nav']/li[2]/button";
		try 
		{
			if (driver.findElements(By.xpath(membershipTab)).size() > 0)
			{
				status.add(homePage.clickWebElement(membershipTab));
				Thread.sleep(200);
				status.add(registerPage.FocusWindow("membership"));
			} 
			else
			{
				System.out.println("membership tab is not displayed");
				status.add("Fail");
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> batchEnrollment(ArrayList<String> data)
	{
		//String textFieldData, String checkBoxValue, String buttonName
		ArrayList<String> status = new ArrayList<String>();
		String textField = "//*[@name='student-ids']";
		String autoEnrollButton = "//*[@id='auto-enroll']";
		String notifyEmail = "//*[@id='email-students']";
		String enrollButton = "//input[@value='Enroll']";
		String unEnrollButton = "//input[@value='Unenroll']";
		
		try
		{
			status.addAll(courseCardProcess.enterTextOnField(textField, data.get(1)));//argument - locator and text to enter in field
			if (data.get(2).equals("disableAllCheckbox")) 
			{
				status.add(homePage.clickWebElement(autoEnrollButton));
				status.add(homePage.clickWebElement(notifyEmail));
			} 
			else if(data.get(2).equals("enableAllCheckbox"))
			{
				System.out.println("By default it will selects checkbox");
			}
			else if (data.get(2).equals("enableNotifyEmailCheckbox")) 
			{
				status.add(homePage.clickWebElement(notifyEmail));
			}
			else if (data.get(2).equals("enableAutoEnrollCheckbox")) 
			{
				status.add(homePage.clickWebElement(autoEnrollButton));
			}
			
			if (data.get(3).equals("enrollButton")) 
			{
				status.add(homePage.clickWebElement(enrollButton));
			}
			else if (data.get(3).equals("unEnrollButton")) 
			{
				status.add(homePage.clickWebElement(unEnrollButton));
			}
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
public String checkSignoutFromMembershipTab()
{
	String status = "";
	String dropDownFromMembershipTab = "//div[@class='toggle-user-dropdown']";
	String clickSignoutOption = "//div[@id='user-menu']/div[5]/a";
	
	try {
		if (driver.findElements(By.xpath(dropDownFromMembershipTab)).size() > 0) {
			status = homePage.clickWebElement(dropDownFromMembershipTab);
			Thread.sleep(200);
			if (driver.findElements(By.xpath(clickSignoutOption)).size() > 0) {
				status = homePage.clickWebElement(clickSignoutOption);
				Thread.sleep(200);
			} else {
				System.out.println("Sign out option is not displayed in membership tab");
				status = "Fail";
			}
			status = registerPage.FocusWindow("logout");
			Thread.sleep(200);
		} else {
			System.out.println("Sign out button is not displayed");
			status = "Fail";
		}
	} catch (Exception e) {
		e.printStackTrace();
		status = "Fail";
	}
	return status;
}

	public ArrayList<String> ValidateEnrollWithValidEmail(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		String confirmationMsg = "//h3[contains(text(),'Successfully')]";
		try
		{
			status.addAll(this.batchEnrollment(data));
			if (driver.findElements(By.xpath(confirmationMsg)).size() > 0) 
			{
				System.out.println("Confirmation message is displayed");
			} 
			else 
			{
				status.add("Fail");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> ValidateEnrollWithValidUsername(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		String confirmationMsg = "//h3[contains(text(),'Successfully')]";
		try
		{
			driver.navigate().refresh();
			status.addAll(this.batchEnrollment(data));
			if (driver.findElements(By.xpath(confirmationMsg)).size() > 0) 
			{
				System.out.println("Confirmation message is displayed");
			} 
			else 
			{
				status.add("Fail");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> ValidateEnrollWithInvalidEmail(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		String errorMessage = "//h3[contains(text(),'The following email addresses and/or usernames are invalid:')]";
		try
		{
			driver.navigate().refresh();
			status.addAll(this.batchEnrollment(data));
			if (driver.findElements(By.xpath(errorMessage)).size() > 0) 
			{
				System.out.println("Confirmation message is displayed");
			} 
			else 
			{
				status.add("Fail");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> EnrollWithBlankInput(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		String blankErrorMessage = "//h3[contains(text(),'This field must not be blank')]";
		try
		{
			driver.navigate().refresh();
			status.addAll(this.batchEnrollment(data));
			if (driver.findElements(By.xpath(blankErrorMessage)).size() > 0) 
			{
				System.out.println("error message is displayed");
			} 
			else 
			{
				status.add("Fail");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> EnrollWithAutoEnrollChecked(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		String confirmationMsg = "//h3[contains(text(),'Successfully')]";
		try
		{
			driver.navigate().refresh();
			status.addAll(this.batchEnrollment(data));
			//login and check for auto-enrollment confirmation
			if (driver.findElements(By.xpath(confirmationMsg)).size() > 0) 
			{
				System.out.println("Confirmation message is displayed");
			} 
			else 
			{
				status.add("Fail");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> EnrollWithNotifyUserChecked(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		String confirmationMsg = "//h3[contains(text(),'Successfully')]";
		try
		{
			driver.navigate().refresh();
			status.addAll(this.batchEnrollment(data));
			if (driver.findElements(By.xpath(confirmationMsg)).size() > 0) 
			{
				System.out.println("Confirmation message is displayed");
			} 
			else 
			{
				status.add("Fail");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkNotificationMailReceivedOrNotFromUserSide(String checkMailSubject)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			getLink = com.dge.utility.FetchLinkMail.checkLinkMail(checkMailSubject);//link send to register mail or unregisterd mail
			if (getLink != null && !getLink.isEmpty()) 
			{
				System.out.println("Mail sent successfully with link: " + getLink);
			} 
			else 
			{
				status.add("Fail");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> signInUsingStaffAccount1(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(homePage.checkSignInProcess(data));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> NavigateToMembershipSection1()
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(this.checkInstructorTab());//click on instructor tab
			status.addAll(this.checkMembershipTab());//click on membership tab
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> EnrollWithValidMailAndBothCheckboxesSelected(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		
		try
		{
			driver.navigate().refresh();
			status.addAll(this.batchEnrollment(data));
			String confirmationMsg = "//h3[contains(text(),'Successfully')]";
			if (driver.findElements(By.xpath(confirmationMsg)).size() > 0) {
				System.out.println("Confirmation message is displayed");
			} else {
				status.add("Fail");
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	String getLink = "";
	public ArrayList<String> checkCourseActivationLinkReceivedFromRegisteredUserMail(String checkMailSubject)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			getLink = com.dge.utility.FetchLinkMail.checkLinkMail(checkMailSubject);//link send to register mail or unregisterd mail
			if (getLink != null && !getLink.isEmpty()) 
			{
				System.out.println("Mail sent successfully with link: " + getLink);
			} 
			else 
			{
				status.add("Fail");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkEnrolledCourseActivationLinkForRegisteredUser(String data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			driver.get(getLink);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			status.add(registerPage.FocusWindow(data));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSignInProcessForRegisteredUserToEnrollCourse(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(homePage.checkSignInProcess(data));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkEnrolledCourseIsAvailableInRegisteredUserDashboard(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		
		try
		{
			status.addAll(this.checkCourseCardFromDashboard(data));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> SignoutFromUserDashboard1() 
	{
		ArrayList<String> status = new ArrayList<String>();
		try 
		{
			status.addAll(courseCardProcess.checkSignOutProcess());//signout from user dashboard
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> selectCourseCard1(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try {
			status.addAll(courseCardProcess.checkBeginCourseCard(data));
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> NavigateMembershipTabFromInstructionSection1() 
	{
		ArrayList<String> status = new ArrayList<String>();
		try 
		{
			status.addAll(this.checkInstructorTab());//click on instructor tab
			status.addAll(this.checkMembershipTab());//click on membership tab
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> signoutFromUserDashboard2() 
	{
		ArrayList<String> status = new ArrayList<String>();
		try 
		{
			status.addAll(courseCardProcess.checkSignOutProcess());//signout from dashboard
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> signInUsingStaffAccount2(ArrayList<String> data) 
	{
		ArrayList<String> status = new ArrayList<String>();
		try 
		{
			status.addAll(homePage.checkSignInProcess(data));//
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> selectCourseCard2(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try {
			status.addAll(courseCardProcess.checkBeginCourseCard(data));
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> EnrollNonRegisteredUserAndEnableAllCheckbox(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(this.batchEnrollment(data));
			String confirmationMsg = "//h3[contains(text(),'Successfully')]";
			if (driver.findElements(By.xpath(confirmationMsg)).size() > 0) {
				System.out.println("Confirmation message is displayed");
			} else {
				status.add("Fail");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkEnrollCourseActivationLinkReceivedFromNonRegisteredUserMail(String data)
	{
		ArrayList<String> status = new ArrayList<String>();
		String checkMailSubject = data;
		try
		{
			getLink = com.dge.utility.FetchLinkMail.checkLinkMail(checkMailSubject);//link send to register mail or unregisterd mail
			if (getLink != null && !getLink.isEmpty()) 
			{
				System.out.println("Mail sent successfully with link: " + getLink);
			} 
			else 
			{
				status.add("Fail");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkEnrolledCourseActivationLinkForNonRegisteredUser(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			driver.get(getLink);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			status.add(registerPage.FocusWindow(data.get(1)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSignupProcessForNonRegisteredUserToEnrollCourse(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(registerPage.registerProcess(data));// sign in process for non registered user
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSignInProcessForNonRegisteredUserToEnrollCourse(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(homePage.checkSignInProcess(data));// sign in process for non registered user
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkEnrolledCourseIsAvailableInNewRegisteredDashboard(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(this.checkCourseCardFromDashboard(data));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> NavigateMembershipTabFromInstructionSection2()
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(this.checkInstructorTab());//click on instructor tab
			status.addAll(this.checkMembershipTab());//click on membership tab
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> EnrollWithNoCheckboxesSelected(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(this.batchEnrollment(data));
			String confirmationMsg = "//h3[contains(text(),'Successfully')]";
			if (driver.findElements(By.xpath(confirmationMsg)).size() > 0) {
				System.out.println("Confirmation message is displayed");
			} else {
				status.add("Fail");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> UnenrollWithRegisteredUserMailAndBothCheckBoxSelected(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(this.batchEnrollment(data));
			String confirmationMsg = "//h3[contains(text(),'Successfully')]";
			if (driver.findElements(By.xpath(confirmationMsg)).size() > 0) {
				System.out.println("Confirmation message is displayed");
			} else {
				status.add("Fail");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkUnEnrollCourseActivationLinkFromRegisteredUserMail(String data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			getLink = com.dge.utility.FetchLinkMail.checkLinkMail(data);// link send to register mail or unregisterd
																		// mail
			if (getLink != null && !getLink.isEmpty()) {
				System.out.println("Mail sent successfully with link: " + getLink);
			} else {
				status.add("Fail");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkUnEnrolledCourseActivationLinkForRegisteredUserMail(String data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			driver.get(getLink);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			status.add(registerPage.FocusWindow(data));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSignInProcessForRegisteredUserToVerifyUnEnrolledCourse(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(homePage.checkSignInProcess(data));// sign in process for registered user to verify unenrolled course
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkUnEnrolledCourseIsAvailableInRegisteredUserDashboard(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(this.checkCourseCardFromDashboard(data));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> SignoutFromUserDashboard3()
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(courseCardProcess.checkSignOutProcess());//signout from dashboard
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> signInUsingStaffAccount3(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(homePage.checkSignInProcess(data));//sign in process for staff account
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> NavigateMembershipTabFromInstructionSection3()
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(this.checkInstructorTab());//click on instructor tab
			status.addAll(this.checkMembershipTab());//click on membership tab
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> UnEnrollNonRegisteredUserAndEnableAllCheckbox(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(this.batchEnrollment(data));
			String confirmationMsg = "//h3[contains(text(),'Successfully')]";
			if (driver.findElements(By.xpath(confirmationMsg)).size() > 0) {
				System.out.println("Confirmation message is displayed");
			} else {
				status.add("Fail");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkUnEnrollCourseActivationLinkFromNonRegisteredUserMail(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			getLink = com.dge.utility.FetchLinkMail.checkLinkMail(data.get(1));// link send to register mail or
																				// unregisterd
																				// mail
			if (getLink != null && !getLink.isEmpty()) {
				System.out.println("Mail sent successfully with link: " + getLink);
			} else {
				status.add("Fail");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkUnEnrolledCourseActivationLinkForNonRegisteredUser(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			driver.get(getLink);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			status.add(registerPage.FocusWindow(data.get(1)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSignupProcessForNonRegisteredUserToVerifyUnenrolledCourse(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(registerPage.registerProcess(data));// sign in process for non registered user to verify unenrolled course
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkSignInProcessForNonRegisteredUserToVerifyUnenrolledCourse(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(homePage.checkSignInProcess(data));// sign in process for non registered user to verify unenrolled course
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> checkUnEnrolledCourseIsAvailableForNewlyRegisterdUserDashboard(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(this.checkCourseCardFromDashboard(data));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}

	public ArrayList<String> SignoutFromUserDashboard4() 
    {
        ArrayList<String> status = new ArrayList<String>();
        try 
        {
            status.addAll(courseCardProcess.checkSignOutProcess());//signout from dashboard
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            status.add("Fail");
        }
        return status;
    }
    public ArrayList<String> signInUsingStaffAccount4(ArrayList<String> data) 
    {
        ArrayList<String> status = new ArrayList<String>();
        try 
        {
            status.addAll(homePage.checkSignInProcess(data));//signout from dashboard
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            status.add("Fail");
        }
        return status;
    }
    public ArrayList<String> selectCourseCard4(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try {
			status.addAll(courseCardProcess.checkBeginCourseCard(data));
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> selectCourseCard3(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try {
			status.addAll(courseCardProcess.checkBeginCourseCard(data));
		} catch (Exception e) {
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
    
    
    public ArrayList<String> NavigateMembershipTabFromInstructionSection4()
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(this.checkInstructorTab());//click on instructor tab
			status.addAll(this.checkMembershipTab());//click on membership tab
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	
	public ArrayList<String> UnenrollWithValidUsername(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			driver.navigate().refresh();
			status.addAll(this.batchEnrollment(data));
			String confirmationMsg = "//h3[contains(text(),'Successfully')]";
			if (driver.findElements(By.xpath(confirmationMsg)).size() > 0) {
				System.out.println("Confirmation message is displayed");
			} else {
				status.add("Fail");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
	public ArrayList<String> UnenrollWithBlankInput(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		String blankErrorMessage = "//h3[contains(text(),'This field must not be blank')]";
		try
		{
			driver.navigate().refresh();
			status.addAll(this.batchEnrollment(data));
			if (driver.findElements(By.xpath(blankErrorMessage)).size() > 0) 
			{
				System.out.println("error message is displayed");
			} 
			else 
			{
				status.add("Fail");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}

	public  ArrayList<String> checkCourseCardFromDashboard(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		String cardsList = "//div[@data-testid='CourseCard']//*[@class='pgn__card-body']";
		String courseCardTitle = "./div[1]//*[@class='course-card-title']";
		String courseCodeFromDashboard = "./div[2]//span[@data-testid='CourseCardDetails']";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try 
		{
			List<WebElement> cardsFromDashboard = driver.findElements(By.xpath(cardsList));
			for (WebElement card : cardsFromDashboard) 
			{
				WebElement courseCardTitleFromDashboardLocator = card.findElement(By.xpath(courseCardTitle));
				js.executeScript("arguments[0].scrollIntoView(true);", courseCardTitleFromDashboardLocator);
				wait.until(ExpectedConditions.visibilityOf(courseCardTitleFromDashboardLocator));
				String courseCardTitleTextFromDashboard = courseCardTitleFromDashboardLocator.getText();
				if(data.get(1).equals(courseCardTitleTextFromDashboard))
				{
					status.add(courseCardProcess.textComparision(courseCardTitleTextFromDashboard, data.get(1)));
					WebElement courseCodeFromDashboardLocator = card.findElement(By.xpath(courseCodeFromDashboard));
					js.executeScript("arguments[0].scrollIntoView(true);", courseCodeFromDashboardLocator);
					if (data.get(2).equals(courseCodeFromDashboardLocator.getText()))
					{
						status.add(courseCardProcess.textComparision(courseCodeFromDashboardLocator.getText(),
								courseCodeFromDashboard));
						break;
					} 
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}

}
