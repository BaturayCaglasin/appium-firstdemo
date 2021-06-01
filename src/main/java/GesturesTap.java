import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class GesturesTap {

    public static void main(String[] args) throws  Exception {

        AppiumDriver driver = CreateDriverSession.initializeDriver("Android");
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        MobileElement viewElement = (MobileElement) driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Views\"]"));
        MobileElement animationElement = (MobileElement) driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Animation\"]"));
        /*
        GESTURES - Touch Action API ile yapılabilirler.
         1.) Tap
         2.) Press
         3.) Longpress
         4.)Wait-action
         5.)Release
         6.)Perform
         7.) MoveTo
         */

        // *** 1. TAP ***

        TouchAction touchAction =new TouchAction(driver);
        //kullanım 1:
        touchAction.tap(ElementOption.element(driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Views\"]")))); //elementin koordinantları gerekli.
        System.out.println("I tapped the button.");
        //kullanım 2:
        touchAction.tap(PointOption.point(driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Views\"]")).getLocation())).perform();
        System.out.println("I tapped the button.");
        //kullanım 3 (koordinant'lar ile)
        touchAction.tap(PointOption.point(538,168)).perform();
        System.out.println("I tapped the button.");

        // *** 2. PRESS AND LONG PRESS ***

        //PRESS:
        touchAction.press(ElementOption.element(driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Animation\"]")))).perform();
        System.out.println("I pressed the button.");

        //WAIT OPTION VE RELEASE:
        touchAction.press(ElementOption.element(driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Animation\"]")))).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).release().perform();
        System.out.println("I waited and released the button.");

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! SWIPE OPERASYONLARI İÇİN MOVETO KULLANILIR: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        // Bunun için iki koordinant gerekli. İlk koordinat;  başlangıç noktasının bulunduğu ilk elementin koordinant'larını gösterirken; ikinci koordinant ise bitiş noktasının bulunduğu
        //ikinci  elementin koordinant'larını gösterir.

        Dimension size = driver.manage().window().getSize();

        //for vertical swipe:
        int startX = size.width / 2;
       int endX = startX;
       int startY= (int) (size.height * 0.8);
       int endY= (int) (size.height*0.2);


//Hard coded version:
        touchAction.press(PointOption.point(488,1891))
                .waitAction( WaitOptions.waitOptions(Duration.ofSeconds(2))) //swipe çok hızlı olur ve test patlar. Bu bakımdan wait'liyoruz.
                .moveTo(PointOption.point(494,386)).release().perform();

        for (int i = 0; i<3; i++){  //three times swiping
            //Dynamic version for all smartphone windows:
            touchAction.press(PointOption.point(startX,startY))
                    .waitAction( WaitOptions.waitOptions(Duration.ofSeconds(2))) //swipe çok hızlı olur ve test patlar. Bu bakımdan wait'liyoruz.
                    .moveTo(PointOption.point(endX,endY)).release().perform();

            //FROM an Element TO an Element:

            touchAction.press(ElementOption.element(driver.findElement(By.xpath(""))))
                    .waitAction( WaitOptions.waitOptions(Duration.ofSeconds(2))) //swipe çok hızlı olur ve test patlar. Bu bakımdan wait'liyoruz.
                    .moveTo(ElementOption.element(driver.findElement(By.xpath("")))).release().perform();
        }
        }

}
