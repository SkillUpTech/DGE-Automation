package com.skillup.dge.testPages;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.dge.utility.ProcessExcel;
import com.dge.utility.Utils;

public class RegressionTesting {

	String CURRENT_SHEET = "";
	int CURRENT_ROW = 0;
	String startTime = "";
	String endTime = "";
	String duration = "";
	public static String nameOfBrowser = "";
	public String nameOfEnvironment = "";
	public String jiraStatusUpdation = "";
	public static LinkedHashMap<String, ArrayList<ArrayList<String>>> EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP;
	private ConcurrentHashMap<String, String> sheetsResult = new ConcurrentHashMap<>();
	public static String ENV_TO_USE = "";
	String getEnvironment = "";
	WebDriver driver;
	String sheetStatus = "";
	String sheetName = "";
	ExtentSparkReporter extentSparkReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;
	public static String driverPath = "D:\\chromedriver137\\chromedriver-win64\\chromedriver.exe";

	@BeforeTest
	@Parameters({"browser", "env"})
	public void setup(String browserName, String env) throws Exception {
		extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/extentReport.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentSparkReporter.config().setDocumentTitle("DGE Course card Test Report");
		extentSparkReporter.config().setReportName("Test Report");
		extentSparkReporter.config().setTheme(Theme.STANDARD);
		extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

		nameOfBrowser = browserName;
		nameOfEnvironment = env;

		if (browserName.equalsIgnoreCase("firefox")) {
			driver = this.openDriver(browserName, getEnvironment);
		} else if (browserName.equalsIgnoreCase("Chrome")) {
			if (env.equalsIgnoreCase("stagecourses")) {
				getEnvironment = "stagecourses.";
			}
			driver = this.openDriver(browserName, getEnvironment);
		} else {
			throw new Exception("Browser is not correct");
		}
	}

	public WebDriver openDriver(String browserName, String env) {
		return DriverManager.getDriver(browserName, env);
	}

	@Test
	public void startTesting() {
		System.out.println("ExtentSparkReporter initialized.");

		ExecutorService service = Executors.newFixedThreadPool(1);
		CompletionService<String> completionService = new ExecutorCompletionService<>(service);

		String excelPath = "D:\\Doc\\vennilVersion.xlsx";
		EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP = new LinkedHashMap<>();

		startTime = new SimpleDateFormat(Utils.DEFAULT_DATA_FORMAT).format(Calendar.getInstance().getTime());

		try {
			LinkedHashMap<String, ArrayList<ArrayList<String>>> data = ProcessExcel.readExcelFileAsRows(excelPath);
			EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.putAll(data);

			ArrayList<ArrayList<String>> master = data.get("Master");
			ArrayList<String> environment = master.get(1);
			if (master.get(1).toString().contains(nameOfEnvironment)) {
				ENV_TO_USE = getEnvironment;
			}

			ArrayList<String> jiraExecution = master.get(1);
			jiraStatusUpdation = jiraExecution.get(1);

			ArrayList<String> pages = master.get(0);
			HashMap<String, Callable<String>> taskMap = new HashMap<>();

			for (int j = 0; j < pages.size(); j++) {
				sheetName = pages.get(j);

				if (data.containsKey(sheetName)) {
					ArrayList<ArrayList<String>> sheetData = data.get(sheetName);
					try {
						sheetStatus = "";
						switch (sheetName) {
							case "Register":
								taskMap.put(sheetName, new RegisterPageTest(driver, sheetData));
								break;
							case "SignIn":
								taskMap.put(sheetName, new SignInTest(driver, sheetData));
								break;
							case "ForgetPwd":
								taskMap.put(sheetName, new ForgetPasswordTest(driver, sheetData));
								break;
							case "VennilaDasboard":
								taskMap.put(sheetName, new HomePageTest(driver, sheetData));
								break;
							case "CourseCard_AIF":
								taskMap.put(sheetName, new CourseCardProcessTest(driver, sheetData));
								break;
							default:
								System.out.println("No class found for sheet: " + sheetName);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			List<Map.Entry<String, Callable<String>>> taskList = new ArrayList<>(taskMap.entrySet());
			Map<Future<String>, String> futureToSheetMap = new HashMap<>();
			int submittedTasks = 0;

			for (int i = 0; i < Math.min(5, taskList.size()); i++) {
				Map.Entry<String, Callable<String>> entry = taskList.get(i);
				Future<String> future = completionService.submit(entry.getValue());
				futureToSheetMap.put(future, entry.getKey());
				System.out.println("Submitting task: " + entry.getKey());
				submittedTasks++;
			}

			for (int i = 0; i < taskList.size(); i++) {
				try {
					Future<String> completedFuture = completionService.take();
					String result = completedFuture.get();
					String sheetName = futureToSheetMap.remove(completedFuture);

					sheetsResult.put(sheetName, result);
					System.out.println("Completed task: " + sheetName + " with result: " + result);

					// Log to Extent report
					extentTest = extentReports.createTest(sheetName);
					extentTest.log(Status.INFO, "Execution Result: " + result);

					if (submittedTasks < taskList.size()) {
						Map.Entry<String, Callable<String>> nextEntry = taskList.get(submittedTasks);
						Future<String> future = completionService.submit(nextEntry.getValue());
						futureToSheetMap.put(future, nextEntry.getKey());
						System.out.println("Submitting task: " + nextEntry.getKey());
						submittedTasks++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DriverManager.quitDriver();
			} catch (Exception e) {
				System.out.println("Driver quit failed: " + e.getMessage());
			}

			endTime = new SimpleDateFormat(Utils.DEFAULT_DATA_FORMAT).format(Calendar.getInstance().getTime());
			duration = Utils.findDifference(startTime, endTime);
			prepareConsolidatedSheet();

			LocalDateTime currentDateTime = LocalDateTime.now();
			String formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

			if (ENV_TO_USE.contains("stagecourses")) {
				ProcessExcel.writeExcelFileAsRows(EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP,
						"D:\\", "stagecourses_result_" + formattedDateTime + ".xlsx");
			}

			service.shutdown();
			extentReports.flush();
			System.out.println("All tasks completed.");
		}
	}

	private void prepareConsolidatedSheet() {
		ArrayList<ArrayList<String>> consolidatedSheedData = new ArrayList<>();

		ArrayList<String> testEnvRow = new ArrayList<>();
		testEnvRow.add("Test environment" + Utils.DELIMITTER + "bold" + Utils.DELIMITTER + "backgroundlime" + Utils.DELIMITTER + "border");
		testEnvRow.add(ENV_TO_USE + Utils.DELIMITTER + "backgroundLT" + Utils.DELIMITTER + "border");

		ArrayList<String> executionStartsOn = new ArrayList<>();
		executionStartsOn.add("Test execution starts on" + Utils.DELIMITTER + "bold" + Utils.DELIMITTER + "backgroundlime" + Utils.DELIMITTER + "border");
		executionStartsOn.add(startTime + Utils.DELIMITTER + "backgroundLT" + Utils.DELIMITTER + "border");

		ArrayList<String> executionEndsOn = new ArrayList<>();
		executionEndsOn.add("Test execution ends on" + Utils.DELIMITTER + "bold" + Utils.DELIMITTER + "backgroundlime" + Utils.DELIMITTER + "border");
		executionEndsOn.add(endTime + Utils.DELIMITTER + "backgroundLT" + Utils.DELIMITTER + "border");

		ArrayList<String> executionDuration = new ArrayList<>();
		executionDuration.add("Execution time" + Utils.DELIMITTER + "bold" + Utils.DELIMITTER + "backgroundlime" + Utils.DELIMITTER + "border");
		executionDuration.add(duration + Utils.DELIMITTER + "backgroundLT" + Utils.DELIMITTER + "border");

		ArrayList<String> jiraStatus = new ArrayList<>();
		jiraStatus.add("JiraExecutionStatus" + Utils.DELIMITTER + "bold" + Utils.DELIMITTER + "backgroundlime" + Utils.DELIMITTER + "border");
		jiraStatus.add(jiraStatusUpdation + Utils.DELIMITTER + "backgroundLT" + Utils.DELIMITTER + "border");

		ArrayList<String> emptyRow = new ArrayList<>();
		emptyRow.add("");

		ArrayList<String> courseResultHeader = new ArrayList<>();
		courseResultHeader.add("Courses" + Utils.DELIMITTER + "bold" + Utils.DELIMITTER + "backgroundLT" + Utils.DELIMITTER + "border");
		courseResultHeader.add("Result" + Utils.DELIMITTER + "bold" + Utils.DELIMITTER + "backgroundLT" + Utils.DELIMITTER + "border");

		consolidatedSheedData.add(testEnvRow);
		consolidatedSheedData.add(executionStartsOn);
		consolidatedSheedData.add(executionEndsOn);
		consolidatedSheedData.add(executionDuration);
		consolidatedSheedData.add(jiraStatus);
		consolidatedSheedData.add(emptyRow);
		consolidatedSheedData.add(courseResultHeader);

		boolean hasFailedSheets = false;
		for (Map.Entry<String, String> entry : sheetsResult.entrySet()) {
			String sheetName = entry.getKey();
			String sheetStatus = entry.getValue();

			ArrayList<String> sheetResult = new ArrayList<>();
			sheetResult.add(sheetName + Utils.DELIMITTER + "backgroundlime" + Utils.DELIMITTER + "border");

			if (sheetStatus.equalsIgnoreCase("Fail")) {
				hasFailedSheets = true;
			}

			sheetResult.add(sheetStatus + Utils.DELIMITTER + "color" +
					(sheetStatus.equalsIgnoreCase("Pass") ? "Green" : "Red") + Utils.DELIMITTER +
					"backgroundlime" + Utils.DELIMITTER + "border");

			consolidatedSheedData.add(sheetResult);
		}

		EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.put(
				"Consolidated Result" + Utils.DELIMITTER + (hasFailedSheets ? "red" : "green"),
				consolidatedSheedData);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		try {
			if (result.getStatus() == ITestResult.SUCCESS) {
				System.out.println("Test passed.");
			} else if (result.getStatus() == ITestResult.FAILURE) {
				System.out.println("Test failed.");
			} else if (result.getStatus() == ITestResult.SKIP) {
				System.out.println("Test skipped.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateJiraWithTestResult(String testCaseId, String status) {
		// Placeholder: Integrate with Jira API if needed
	}
}
