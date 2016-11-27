package HomePageGroupOn;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class HomePage{
	
	
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
	public void testLinks(){
		
		driver.get(url);
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total Element "+links.size());
        ArrayList<String> Names = new ArrayList<String>();
        for(WebElement linkElement : links){
        	if(linkElement.getText().length() > 0)
        		Names.add(linkElement.getText());
        }               
        
        String cssClass,id;
        for(String name : Names){
        	try{
        		
        		if(name.equals("terms of service")|| name.equals("privacy policy"))
        			continue;
            	System.out.println(("Testing Link: "+name));   

            	JavascriptExecutor js = (JavascriptExecutor) driver;        	     
            	WebElement currentElement = driver.findElement(By.linkText(name));
            	cssClass = currentElement.getAttribute("class"); 
            	id = currentElement.getAttribute("id");
            	System.out.println("CSS "+cssClass);
            	if(cssClass.contains("element-invisible") ||
            			cssClass.contains("accessibility-aid js-skip-to-content")|| 
            			cssClass.contains("dropdown-toggle") || 
            			id.contains("scrolldown")){ 
            		continue;
            	}
            
            	js.executeScript("scroll(0,"+currentElement.getLocation().y+(-350)+")");
            	currentElement.click();
            	
            	System.out.println(("Next Page Title: "+driver.getTitle()));
            	
            	driver.navigate().back();
        	}
        	catch(Exception e){
        	
        		System.out.println(name + " Exception Generated");
        	}
        }
        
	}



	@Test
	public void testButtons(){
		
		driver.get(url);
        List<WebElement> links = driver.findElements(By.tagName("button"));
        System.out.println("Total Element "+links.size());
        for(WebElement linkElement : links){
        	try{
        	
        		System.out.println(linkElement.getText());
        		if(linkElement.getText().equals("Sign up for GitHub"))
        		{
        			System.out.println("Clicked On "+linkElement.getText());
        			linkElement.submit();
        			System.out.println(("Title of page: "+driver.getTitle()));           	
        			driver.navigate().back();
        		}
        	}
        	catch(Exception e){
        		
    			System.out.println( "Exception generated");
       		}
        		
       }
      
    
    
	}
	
	@Test
	public void searchBarTeting(){
	
		driver.get(url);
		int invalidtests = Integer.parseInt(data.getProperty("search"));
		
		while(invalidtests>0)
		{
			
		
			try{
			
		
			WebElement currentElement = driver.findElement(By.name("q"));
			currentElement.sendKeys(data.getProperty("search"+invalidtests));
			currentElement.submit();
			System.out.println("Title Page: "+driver.getTitle());
			driver.close();
	
			}
			catch(Exception e){
				System.out.println("Exception Ocuuure for key "+invalidtests);
			}
			invalidtests--;
		}
		
	}


	@After
	public void close(){
	    driver.close();
	}

	
	
}


