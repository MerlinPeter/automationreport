package automationreport;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.WebElementHandler;
import org.openqa.selenium.support.ui.Select;


public class Driver {

	public static void main(String[] args)  throws Exception{
	
    AutomationScripts.SFDCLogin();
    
//    AutomationScripts.validateErrorMessage();

	}

}
