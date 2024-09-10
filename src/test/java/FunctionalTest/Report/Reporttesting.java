package FunctionalTest.Report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class Reporttesting {
	
	ExtentHtmlReporter reporter;
	ExtentReports reports;
	ExtentTest test;
	WebDriver driver;
	
	
	@BeforeTest
	public void GenerateReport() {
		
		reporter=new ExtentHtmlReporter("C:\\Users\\admin\\eclipse-workspace\\Report\\Extent Report\\FunctionalTestReport.html");
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setReportName("Functional Test report");
		reporter.config().setTimeStampFormat("dd/mm/yyyy, hh:mm:ss");
		
		reports=new ExtentReports();
		reports.attachReporter(reporter);
		
		
	}
	
	
	@AfterTest
	public void CloseReport() {
		reports.flush();
		
	}
	
	@BeforeMethod
	public void LaunchBrowser() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\eclipse-workspace\\TestBlog\\ChromeDriver\\chromedriver.exe");
//		ChromeOptions options=new ChromeOptions();
//		options.addArguments("Headless");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://amityonline.com/");
		Thread.sleep(1000);

	}

	@Test (priority = 1)
	public void AmityLogo() {
		test=reports.createTest("Amity Logo");
		WebElement amitylogoElement=driver.findElement(By.xpath("//img[@alt='amity-logo']"));
		Assert.assertTrue(amitylogoElement.isDisplayed(), "Logo is not displayed");
		if(amitylogoElement.isDisplayed()) {
			System.out.println("Amity Logo displayed");
			test.log(Status.PASS, "Amity logo Verified");
		}else {
			System.out.println("Amity Logo is not Displayed");
			test.log(Status.FAIL, "Amity logo not Verified");
		}
	
	}
	

	@Test(priority = 2)
	public void ProgramButton(){
		test=reports.createTest("Program Button");
		WebElement programButtonElement=driver.findElement(By.xpath("//span[@class='mr-1 uppercase laptop:font-medium headFootSprite serviceMenu pr-5 header_menuText__KlhzO flex gap-1 items-center']"));
		Assert.assertTrue(programButtonElement.isEnabled(), "Program buttton is not enabled");
		if(programButtonElement.isEnabled()) {
			System.out.println("Program button is working");
			test.log(Status.PASS, "Program button is working");
		}else {
			System.out.println("Program button is not working");
			test.log(Status.FAIL, "Program button is not working");
	}
	}
	
	@Test(priority = 3)
	public void CareerServices() throws InterruptedException{
		test=reports.createTest("Career Services");
		WebElement careerServicesButton=driver.findElement(By.xpath("//span[@class='mr-1 uppercase laptop:font-medium header_menuText__KlhzO'][normalize-space()='CAREER SERVICES']"));
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click();", careerServicesButton);
		Set<String> windowhandle1 = driver.getWindowHandles();
		List<String> handle6 = new ArrayList<String>();
		handle6.addAll(windowhandle1);
		Thread.sleep(2000);
		driver.switchTo().window(handle6.get(1));
		String CareerServicesURL = "https://amityonline.com/career-services";
		String ActualURL = driver.getCurrentUrl();
		if(ActualURL.equals(CareerServicesURL)) {
			System.out.println("Career Service Button is working");
			test.log(Status.PASS, "Career Service Button is working");
		}else {
			System.out.println("Career Service Button is not working");
			test.log(Status.FAIL, "Career Service Button is not working");
		}
		driver.close();	
		
	}
	
	@Test(priority = 4)
	public void AdvantagesButton(){
		test=reports.createTest("AdvantagesButton");
		WebElement advanragesButtonElement=driver.findElement(By.xpath("//span[@class='mr-1 uppercase laptop:font-medium header_menuText__KlhzO'][normalize-space()='ADVANTAGES']"));
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click();", advanragesButtonElement);
		String ExpadvanrageelementURL = "https://amityonline.com/amity-online-advantage";
		if(driver.getCurrentUrl().equals(ExpadvanrageelementURL)) {
			System.out.println("Advantages button is working");
			test.log(Status.PASS, "Advantages button is working");
		}else {
			System.out.println("Advantages button is not working");
			test.log(Status.FAIL, "Advantages button is not working");
		}
		
	}
	
	@Test(priority = 5)
	public void SearchButton(){
		test=reports.createTest("SearchButton");
		WebElement SearchButtonElement=driver.findElement(By.xpath("//div[@class='header_searchIcon__bLdgL header_searchIconNew__5aGyK']"));
		if(SearchButtonElement.isEnabled()) {
			Assert.assertTrue(true);
			System.out.println("Search button is working");
			test.log(Status.PASS, "Search button is working");
		}else {
			Assert.assertTrue(false);
			System.out.println("Search button is not working");
			test.log(Status.FAIL, "Search button is not working");
		}
		
		
	}
	
	@AfterMethod

	public void CloseBrowser(ITestResult result) throws IOException {
if(result.getStatus()==ITestResult.FAILURE) {
			String myscreenshot = CaptureScreenshot.Screenshot(driver);
			test.log(Status.FAIL, result.getThrowable());
			test.log(Status.FAIL, MarkupHelper.createLabel("Login Failed", ExtentColor.RED));
			test.addScreenCaptureFromPath(myscreenshot); 
		
	}
driver.quit();
}
}
