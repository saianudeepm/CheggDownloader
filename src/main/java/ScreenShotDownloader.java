import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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
            configMap= PropertiesLoader.load(new File("src/main/resources/SiteDetails.properties"));
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

    public void captureScreen(String folderName, String fileName){
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("output"+File.separator+configMap.get("bookName")+File.separator+folderName+File.separator+fileName+".png"));

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
