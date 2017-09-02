package automationreport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ReusableMethods  extends Driver{
 	static BufferedWriter bw = null;
	static BufferedWriter bw1 = null;
	static String htmlname;
	static String objType;
	static String objName;
	static String TestData;
	static String rootPath;
	static int report;


	static Date cur_dt = null;
	static String exeStatus = "True";
	static int j = 1;

	
	public static void enterText(WebElement obj, String textVal,String objName) throws IOException{
	
		if(obj.isDisplayed()){
			obj.sendKeys(textVal);
		
			Update_Report(	"Pass: ", "enterText", textVal +"is entered in " +   objName+ "field", driver);
			
		}else{
			Update_Report("Fail:  "  ,  objName  + "  "+ "field is not displayed,please check your application"+"." , textVal,driver);

		}
	}
	

	public static void checkError(WebElement obj, String errorVal) throws IOException{
		//String storeVal = obj.getText();
		WebElement errorMsg = driver.findElement(By.id("error"));
		String expectedErrorMsg = " Please error out ";
		String actualErrorMsg = errorMsg.getText().trim();
		
		if(expectedErrorMsg.equals(actualErrorMsg)){
			Update_Report("Pass", "Sample ", "Error message mathced", driver);
		}else{
			Update_Report("Fail", "Validate Error message", " Expected error message is not matching with actual error meesage", driver);
		}
		
	/*	if(storeVal.equals("Please enter your password."))
		{
			Update_Report("Pass:  "  ,  objName  + "  "+ "field is not displayed,please check your application." ,storeVal, driver);
		 
		}	*/
		
	}
	
	
	
	public static void clickButton(WebElement obj, String textVal,String time) throws Exception{
		if(obj.isDisplayed()){
			try {
				obj.click();
                   
				if(time != null && !time.isEmpty()){
				long threadTime = Long.parseLong(time);
				Thread.sleep(threadTime);
				}    
			}catch (Exception e){
 
			  throw e ;
		}
		}
		
}
	
		
	public static String startReport(String scriptName, String ReportsPath) throws IOException{

		String strResultPath = null;


		String testScriptName =scriptName;


		cur_dt = new Date(); 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String strTimeStamp = dateFormat.format(cur_dt);

	 
		if (ReportsPath.endsWith("\\")) { 
			ReportsPath = ReportsPath + "\\";
		}

		strResultPath = ReportsPath  + "/" +testScriptName +"/"; 
		File f = new File(strResultPath);
		f.mkdirs();
		htmlname = strResultPath  + testScriptName + "_" + strTimeStamp 
				+ ".html";



		bw = new BufferedWriter(new FileWriter(htmlname));

		bw.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR><TD BGCOLOR=#66699 WIDTH=27%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
				+ "FireFox " + "</B></FONT></TD></TR>");
		bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR COLS=7><TD BGCOLOR=#BDBDBD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>SL No</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Name</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD> "
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=47%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Detail Report</B></FONT></TD></TR>");

  return htmlname ;
	}

	public static void Update_Report(String Res_type,String Action, String result, WebDriver dr) throws IOException {
		String str_time;
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		str_time = dateFormat.format(exec_time);
		if (Res_type.startsWith("Pass")) {
			
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ "Passed"
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ " " + "</FONT></TD></TR>");

		} else if (Res_type.startsWith("Fail")) {
			
			String ss1Path = screenshot(dr);
			exeStatus = "Failed";
			report = 1;
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ "<a href= "+
					ss1Path
					+ "  style=\"color: #FF0000\"> Failed </a>"

				+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
				+ dr + "</FONT></TD></TR>");

		} 
	}
	
	
public static String screenshot(WebDriver dr) throws IOException{
		
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String str_time = dateFormat.format(exec_time);
		
		String  ss1Path = "/Users/peter/ReportLogs/SFDCLogin/"+ str_time+".png";
		File scrFile = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(ss1Path));
		return ss1Path;
	}
	
	

	 public static String[][] readXLSheet (String dt_path,String sheetName) throws IOException{
			
			// Step 1: Get the XL Path
			File xlFile = new File(dt_path);
			
			//Step 2 : Access the XL File
			 
			FileInputStream xlDoc = new FileInputStream(xlFile);
			
			//Step3: Access the work book
		    XSSFWorkbook wb = new XSSFWorkbook(xlDoc);
			
			//Step 4: Access the sheet
			XSSFSheet sheet = wb.getSheet(sheetName);
			
			int iRowCount = sheet.getLastRowNum()+1;
			int iColCount = sheet.getRow(0).getLastCellNum();
			
			String[] [] xlData = new String[iRowCount][iColCount];
			
			for(int i=0;i< iRowCount; i++){
				for(int j=0; j< iColCount; j++){
					 
					 xlData[i][j]  = sheet.getRow(i).getCell(j).toString();
					
 				
				}
			
				
			}
			return xlData;
			
		}
	  
	  
}
