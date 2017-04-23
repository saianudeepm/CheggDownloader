import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * Created with IntelliJ IDEA.
 * User: sai
 * Date: 7/16/13
 * Time: 1:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class DriverProvider {

  public static synchronized WebDriver getDriver(){
      WebDriver driver = new FirefoxDriver();
      driver.manage().window().maximize();
      return driver;

  }


}
