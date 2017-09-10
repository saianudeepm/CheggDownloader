import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created with IntelliJ IDEA.
 * User: sai
 * Date: 7/16/13
 * Time: 1:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class DriverProvider {

  public static synchronized WebDriver getDriver(){
      System.setProperty ( "webdriver.chrome.driver" , "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe" );
      WebDriver driver = new ChromeDriver();
      driver.manage().window().maximize();
      return driver;

  }


}
