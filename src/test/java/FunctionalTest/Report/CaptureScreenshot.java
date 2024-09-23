package FunctionalTest.Report;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshot {
	WebDriver driver;

	public static String Screenshot(WebDriver driver) throws IOException{
		TakesScreenshot srcShot= ((TakesScreenshot)driver);
		File srcFile=srcShot.getScreenshotAs(OutputType.FILE);
		String destination= "C:\\Users\\admin\\eclipse-workspace\\Report\\Screenshot\\myscreenshot.png";
		File destiFile=new File(destination);
		FileUtils.copyFile(srcFile, destiFile);
		return destination;
		
	}


}
