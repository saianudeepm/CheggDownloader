import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Created with IntelliJ IDEA.
 * User: sai
 * Date: 7/16/13
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScreenShotDownloader {

    WebDriver driver;
    HashMap<String,String> configMap;

    public ScreenShotDownloader(){
       try {
            driver= DriverProvider.getDriver();
            configMap= PropertiesLoader.load(new File("SiteDetails.properties"));
       }catch (Exception e){e.printStackTrace();}

    }

    public Boolean loginToServer() throws Exception{
        driver.get(configMap.get("loginPage"));
        Thread.sleep(100);
        //set the username
        driver.findElement(By.xpath((configMap.get("usernameXpath")))).sendKeys(configMap.get("username"));
        //set the password
        driver.findElement(By.xpath((configMap.get("passwordXpath")))).sendKeys(configMap.get("password"));
        //click submit
        driver.findElement(By.xpath((configMap.get("submitXpath")))).click();
        if(driver.getCurrentUrl().contains(configMap.get("loginPage"))){
            return false;
        }
        return true;
    }

    public void captureScreen(String folderName, String fileName) throws Exception {
        Integer wtimeout = Integer.parseInt(configMap.get("viewPastTimeout"));
        Integer ltimeout = Integer.parseInt(configMap.get("loadingTimeout"));
        Integer vtimeout = Integer.parseInt(configMap.get("waitingTimeout"));
        Thread.sleep(wtimeout);
        (new WebDriverWait(driver, ltimeout))
            .until(new ExpectedCondition<WebElement>() {
                public WebElement apply(WebDriver ndriver) {
                    return ndriver.findElement(By.xpath("//section[contains(@class, 'TBS_SOLUTION_STEP')]"));
                }
            });
        try {
            ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('bookmark-count-tooltip')[0].style['opacity']=0");
        } catch (Exception e) {
            ;
        }
        // 64 - Chegg Header
        // 50 - Problem navigator
        ShootingStrategy sstg = ShootingStrategies.viewportNonRetina(vtimeout, 64 + 50, 0);
        final Screenshot sshot = new AShot()
            .shootingStrategy(sstg)
            .takeScreenshot(driver);
        final BufferedImage image = sshot.getImage();
        boolean folderN = new File("output"+File.separator+configMap.get("bookName")+File.separator+folderName+File.separator+fileName+".png").mkdirs();
        if (folderN) {
            System.out.println("Create File " + "output"+File.separator+configMap.get("bookName")+File.separator+folderName+File.separator+fileName+".png");
        }
        try {
            ImageIO.write(image, "PNG", new File("output"+File.separator+configMap.get("bookName")+File.separator+folderName+File.separator+fileName+".png"));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void downloadBook() throws InterruptedException{
        boolean reachedEnd = false;
        while(!reachedEnd){
            try{
                String info = driver.findElement(By.xpath(configMap.get("infoXpath"))).getText();
                String chapterNumber = info.substring(0,info.indexOf(","));
                String problemNumber = info.substring(info.indexOf(",")+2,info.length());
                if(!driver.findElement(By.xpath(configMap.get("nextpageXpath"))).isDisplayed()){
                    reachedEnd = true;
                }
                //set show answer if not set
                /*if(!driver.findElement(By.xpath(configMap.get("showAnswerXpath"))).isSelected()&& !isClicked){
                    driver.findElement(By.xpath(configMap.get("showAnswerXpath"))).click();
                    isClicked=true;
                }*/
                //capture the screenshot only when there is an available answer
                if(!problemNumber.replaceAll(" ","").equalsIgnoreCase("N/A"))
                    captureScreen(chapterNumber,problemNumber);
                //back to top to avoid cannot-click error
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
                //go to next problem
                if(!reachedEnd) {
                    driver.findElement(By.xpath(configMap.get("nextpageXpath"))).click();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    Thread.sleep(2500);
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(driver.getCurrentUrl());
                //go to next problem
                driver.findElement(By.xpath(configMap.get("nextpageXpath"))).click();
            }
        }
        driver.close();
    }

    public void navigateToBook(){
        driver.get(configMap.get("downloadUrl"));
    }
}
