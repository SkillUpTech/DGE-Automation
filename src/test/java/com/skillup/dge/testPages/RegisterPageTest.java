package com.skillup.dge.testPages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.openqa.selenium.WebDriver;

import com.skillup.dge.webPages.RegisterPage;


public class RegisterPageTest implements Callable<String>
{
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	RegisterPage registerPage;
	String sheetStatus = "Pass";

	public RegisterPageTest(WebDriver driver, ArrayList<ArrayList<String>> sheetData)
	{
		this.sheetData = sheetData;
		this.driver = driver;
	}

	@Override
	public String call() throws Exception 
	{
		// TODO Auto-generated method stub
		System.out.println("DGE HomePage validation Process started");
		this.registerPage = new RegisterPage(driver);
		try
		{
			for (int i = 0; i < this.sheetData.size(); i++)
			{
				ArrayList<String> row = this.sheetData.get(i);
				String firstColumn = row.get(0);

				switch (firstColumn) 
				{
				
				  case "TC_P_01": TC_P_01(row); break; case "TC_P_02": TC_P_02(row); break;
				  case "TC_P_03": TC_P_03(row); break; case "TC_P_04": TC_P_04(row); break;
				  case "TC_P_05": TC_P_05(row); break; case "TC_P_06": TC_P_06(row); break;
				 
			 	case "TC_N_01":
			 		TC_N_01(row);
			        break;
			 	case "TC_N_02":
			 		TC_N_02(row);
			        break;
			 	case "TC_N_03":
			 		TC_N_03(row);
			        break;
			 	case "TC_N_04":
			 		TC_N_04(row);
			        break;
			 	case "TC_N_05":
			 		TC_N_05(row);
			        break;
			 	case "TC_N_06":
			 		TC_N_06(row);
			        break;
			 	case "TC_N_07":
			 		TC_N_07(row);
			        break;
			 	case "TC_N_08":
			 		TC_N_08(row);
			        break;
			 	case "TC_N_09":
			 		TC_N_09(row);
			        break;
			 	case "TC_N_10":
			 		TC_N_10(row);
			        break;
			 	case "TC_N_11":
			 		TC_N_11(row);
			        break;
			 	case "TC_N_12":
			 		TC_N_12(row);
			        break;
			 	case "TC_N_13":
			 		TC_N_13(row);
			        break;
			 	case "TC_N_14":
			 		TC_N_14(row);
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
					if (issue.contains("fullname"))
						columnIndex = 4;
					if (issue.contains("email"))
						columnIndex = 5;
					if (issue.contains("username"))
						columnIndex = 6;
					if (issue.contains("password"))
						columnIndex = 7;
					if (issue.contains("Fail"))
						columnIndex = 8;
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
	
	public void TC_P_01(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_P_01(data);
			updateExcelData(status, 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_P_02(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_P_02(data);
			updateExcelData(status, 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_P_03(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_P_03(data);
			updateExcelData(status, 4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_P_04(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_P_04(data);
			updateExcelData(status, 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_P_05(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_P_05(data);
			updateExcelData(status, 6);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_P_06(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_P_06(data);
			updateExcelData(status, 7);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_N_01(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_N_01(data);
			updateExcelData(status, 9);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_N_02(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_N_02(data);
			updateExcelData(status, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_N_03(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_N_03(data);
			updateExcelData(status, 11);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_N_04(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_N_04(data);
			updateExcelData(status, 12);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_N_05(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_N_05(data);
			updateExcelData(status, 13);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_N_06(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_N_06(data);
			updateExcelData(status, 14);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_N_07(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_N_07(data);
			updateExcelData(status, 15);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_N_08(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_N_08(data);
			updateExcelData(status, 16);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_N_09(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_N_09(data);
			updateExcelData(status, 17);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_N_10(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_N_10(data);
			updateExcelData(status, 18);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_N_11(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_N_11(data);
			updateExcelData(status, 19);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_N_12(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_N_12(data);
			updateExcelData(status, 20);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_N_13(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_N_13(data);
			updateExcelData(status, 21);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TC_N_14(ArrayList<String> data)
	{
		try {

			ArrayList<String> status = registerPage.checkTC_N_14(data);
			updateExcelData(status, 22);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
