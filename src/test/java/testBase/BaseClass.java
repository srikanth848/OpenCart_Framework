package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties properties;
	
	@BeforeClass(groups={"Sanity", "Regression", "Master", "DataDriven"})
	@Parameters({"OS", "Browser"})
	public void setup(String os, String br) throws IOException, URISyntaxException {
		
		FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
		properties = new Properties();
		properties.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		if(properties.getProperty("execution_env").equalsIgnoreCase("remote")){
			String hubURL = "http://localhost:4444/wd/hub";
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			switch(os.toLowerCase()) {
			case "windows": capabilities.setPlatform(Platform.WIN11); break;
			case "mac": capabilities.setPlatform(Platform.MAC); break;
			case "linux": capabilities.setPlatform(Platform.LINUX); break;
			default: System.out.println("Invalid OS name.."+ os); return;
			}
			
			switch(br.toLowerCase()) {
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default: System.out.println("Invalid browser name.. "+ br); return;
			}
			
			driver = new RemoteWebDriver(new URI(hubURL).toURL(), capabilities);
			
		}
		
		if(properties.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(br.toLowerCase()) {
			case "edge": driver = new EdgeDriver(); break;
			case "chrome": driver = new ChromeDriver(); break;
			case "firefox": driver = new FirefoxDriver(); break;
			default: System.out.println("Invalid browser name.. "+ br); return;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(properties.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups={"Sanity", "Regression", "Master", "DataDriven"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
		//String genratedString = RandomStringUtils.randomAlphabetic(8);
		String genratedString = RandomStringUtils.secureStrong().nextAlphabetic(8);
		return genratedString;
	}
	
	public String randomNumber() {
		//String generatedNumber = RandomStringUtils.randomNumeric(11);
		String generatedNumber = RandomStringUtils.secureStrong().nextNumeric(11);
		return generatedNumber;
	}
	
	public String randomAlphaNumeric() {
		//String generatedAlphaNumeric = RandomStringUtils.randomAlphanumeric(10);
		String generatedAlphaNumeric = RandomStringUtils.secureStrong().nextAlphanumeric(14);
		return generatedAlphaNumeric;
	}
	
	public String captureScreen(String tname) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}


}
