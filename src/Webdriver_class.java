import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Webdriver_class {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//System.setProperty("webdriver.gecko.driver","D:/Masters/2nd Sem/287 Bari");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://google.com");
		System.out.println(driver.getTitle());
		
		WebElement values=driver.findElement(By.id("lst-ib"));
		values.sendKeys("Hello");
		values.submit();
		//driver.close();
	}

}
