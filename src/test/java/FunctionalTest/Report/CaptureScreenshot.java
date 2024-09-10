package FunctionalTest.Report;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class CaptureScreenshot {
	
	WebDriver driver;
	  
	public static String Screenshot(WebDriver driver) throws IOException {
		
	TakesScreenshot srcShot= ((TakesScreenshot)driver);
File srcfile = srcShot.getScreenshotAs(OutputType.FILE);
String destination = "C:\\Users\\admin\\eclipse-workspace\\Report\\Extent Report\\myextent.png";
File destiFile=new File(destination);
FileUtils.copyFile(srcfile, destiFile);
return destination;

	}

}
