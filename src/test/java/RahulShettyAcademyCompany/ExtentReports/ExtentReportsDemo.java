package RahulShettyAcademyCompany.ExtentReports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsDemo {
	
	ExtentReports extent;
	
	@BeforeTest
	public void config() {
		//ExtentSparkReporter is used for configuration and ExtenReports is the main reporter which uses this configured extent Spark reporter
		
		String filePath=System.getProperty("user.dir")+"\\reports\\index.html"; //report generation path refresh the project explorer to see report after each run
		ExtentSparkReporter reporter=new ExtentSparkReporter(filePath);
		
		reporter.config().setReportName("Web Automation Results");//sets the report name displayed in header
		reporter.config().setDocumentTitle("Test Results"); //sets the page title
		
		extent=new ExtentReports(); 
		extent.attachReporter(reporter);//pass ExtentSparkReporter object here
		extent.setSystemInfo("Tester", "Rahul Shetty");
		
	}
	
	
	
	@Test
	public void initializeDemo() {
		
		ExtentTest test=extent.createTest("Initial Demo"); //this line is madatory in every test method ExtentTest is a test class used to configure/tweak the results
		
		System.setProperty("webdriver.chromedriver", "D:\\Selenium-Rahul Shetty\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/");
		System.out.println(driver.getTitle());
		
		
		driver.close(); //keep all code before extent.flush();
		test.fail("Failing this testcases using ExtentTest classes' test.fail()");//we normally integrate this in TestNG's itest listner to fail & capture screenshot & attach it to report
		
		extent.flush();//at the end of all @Test executinos we can use this method so that extent stops listening & generates the report
		

		
	}

}

