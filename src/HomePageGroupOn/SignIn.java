package HomePageGroupOn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

public class SignIn{
	
	

	WebDriver driver;
	String url;
	private Properties data;
	
	
	 @Before
	    public void setUp() throws FileNotFoundException, IOException{
			
			System.setProperty("webdriver.chrome.driver", "D:/Masters/2nd Sem/287 Bari/chromedriver.exe");
			driver= new ChromeDriver();
			data = new Properties();
			data.load(new FileInputStream("Data/search.properties"));
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		    driver.manage().window().maximize();
	        url = "http://www.github.com/";        
	        
	   }
	 
	
	 @Test
	 public void validtest(String url){
		
		int validtests = Integer.parseInt(data.getProperty("validTests"));
		while(validtests>0){
			try{
				
			
				driver.get(url);
				WebElement userID= driver.findElement(By.id("login_field"));
				userID.sendKeys(data.getProperty("validuser"+validtests));
				WebElement password= driver.findElement(By.id("password"));
				password.sendKeys(data.getProperty("validpassword"+validtests));
				WebElement signIn = driver.findElement(By.name("commit"));
				signIn.click();
				String title = driver.getTitle();
				if(title.equals("GitHub")){
					System.out.println("Passed Test Cases "+validtests);
				}
				else{
					System.out.println("For Valid credentials, Test Cases failed for case no "+validtests);
				}
			}
			catch(Exception e){
				System.out.println("For Valid credentials, Test Cases failed with Exception for case no "+validtests);
				System.out.println(e);
			}
			validtests--;
		}
		
		
	}
	
	
	@Test
	public void Invalidtest(String url){
		
		int invalidtests = Integer.parseInt(data.getProperty("InvalidTests"));
		while(invalidtests>0){
			
			try{
				
			
				driver.get(url);
				WebElement userID= driver.findElement(By.id("login_field"));
				userID.sendKeys(data.getProperty("Invaliduser"+invalidtests));
				WebElement password= driver.findElement(By.id("password"));
				password.sendKeys(data.getProperty("Invalidpassword"+invalidtests));
				WebElement signIn = driver.findElement(By.name("commit"));
				signIn.click();
			
				WebElement alert = driver.findElement(new ByChained(By.id("js-flash-container"),By.className("container")));
				if(alert.getText().contains("Incorrect username or password.")){
				
					System.out.println("Invalid Test Case Passed number "+invalidtests);
				}
				else{
				
					System.out.println("Invalid Test case failed numner "+invalidtests);
				}
			}
			catch(Exception e){
				
				System.out.println("Invalid Test case failed with Exception numner "+invalidtests);
				System.out.println(e);
				
			}
			
			invalidtests--;
		}
		
	}
	
	
	@After
	public void close(){
	    driver.close();
	}

	

}



