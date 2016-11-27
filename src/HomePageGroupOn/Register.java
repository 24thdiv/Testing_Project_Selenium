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

public class Register{
	


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
	public void invalidRegisterTest(String url){
		
		int invalidtests = Integer.parseInt(data.getProperty("invalidTests"));
		while(invalidtests>0){
			try{
				
				driver.get(url);
				WebElement userID= driver.findElement(By.id("user_login"));
				userID.sendKeys(data.getProperty("invaliduser"+invalidtests));
				WebElement email= driver.findElement(By.id("user_email"));
				email.sendKeys(data.getProperty("invalidemail"+invalidtests));
				WebElement password= driver.findElement(By.id("user_password"));
				password.sendKeys(data.getProperty("invalidpassword"+invalidtests));
				WebElement register= driver.findElement(By.id("signup_button"));
				register.click();
			
				//WebElement alert = driver.findElement(new ByChained(By.id("js-pjax-container"),By.className("flash flash-error my-3")));
				WebElement alert=driver.findElement(By.cssSelector(".flash.flash-error.my-3"));
				
				if(alert.getText().equals("There were problems creating your account.")){
				
					System.out.println("Invalied Test cases passed number "+invalidtests);
				}
				else{
				
					System.out.println("Invalied Test cases failed number "+invalidtests);
				}
			}
			catch(Exception e){
				/*System.out.println("Invalied Test cases failed with exception number "+invalidtests);
				System.out.println(e);
		*/	
				e.printStackTrace();
			}
		
		invalidtests--;
		}
	
	}	
		
	@Test
	public void validRegisterTest(String url){
			
			int validtests = Integer.parseInt(data.getProperty("validTests"));
			while(validtests>0){
				try{
					
					driver.get(url);
					WebElement userID= driver.findElement(By.id("user_login"));
					userID.sendKeys(data.getProperty("validuser"+validtests));
					WebElement email= driver.findElement(By.id("user_email"));
					email.sendKeys(data.getProperty("validemail"+validtests));
					WebElement password= driver.findElement(By.id("user_password"));
					password.sendKeys(data.getProperty("validpassword"+validtests));
					WebElement register= driver.findElement(By.id("signup_button"));
					register.click();
					
				
					//WebElement alert = driver.findElement(new ByChained(By.id("js-pjax-container"),By.className("flash flash-error my-3")));
					WebElement confirm=driver.findElement(By.cssSelector(".btn btn-primary.js-choose-plan-submit"));
					confirm.click();
					WebElement skip = driver.findElement(By.linkText("skip this step"));
					skip.click();
					
					if(driver.getTitle().equals("Your Actions")){
					
						System.out.println("valied Test cases passed number "+validtests);
					}
					else{
					
						System.out.println("valied Test cases failed number "+validtests);
					}
				}
				catch(Exception e){
					/*System.out.println("Invalied Test cases failed with exception number "+invalidtests);
					System.out.println(e);
			*/	
					e.printStackTrace();
				}
			
			validtests--;
			}
		
		
		
		
		
		
		
	}



	@After
	public void close(){
	    driver.close();
	}

}

