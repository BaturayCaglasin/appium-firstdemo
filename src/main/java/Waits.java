import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Waits {

    public static void main(String[] args) throws  Exception {

AppiumDriver driver = CreateDriverSession.initializeDriver("Android");

//Implicit Wait:
driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
MobileElement viewElement = (MobileElement) driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Views\"]"));
viewElement.click();

//Explicit Wait:
WebDriverWait wait = new WebDriverWait(driver,10);
wait.until(ExpectedConditions.elementToBeClickable(viewElement));
viewElement.click();

    }
}
