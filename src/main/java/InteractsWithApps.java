import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.appmanagement.AndroidInstallApplicationOptions;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class InteractsWithApps {

    //app kapatılıp açılabilir, arka planda bir süre bekletilip öne alınabilir, önden arkaya atılabilir vs vs
    /*
    * 1.) terminateApp
    * 1.5) activateApp
    * 2.) installApp
    * 3.) isAppInstalled
    * 4.) runAppInBackground
    * 5.) queryAppState
    *
    *
    * */

    public static void main(String[] args) throws Exception {
        AppiumDriver driver = CreateDriverSession.initializeDriver("Android");

        MobileElement viewElement = (MobileElement) driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Views\"]"));


        WebDriverWait wait = new WebDriverWait(driver ,10);
        wait.until(ExpectedConditions.elementToBeClickable(viewElement));


        //*** 1. App'i force şeklinde kapatmak: (3 sn sonra app kapansın) ***

        Thread.sleep(3000);
        driver.terminateApp("io.appium.android.apis"); //kapatılması istenen app'in ismi. ((app package ismi))

        //***1.5) App'i açmak için:

        driver.terminateApp("io.appium.android.apis");


        //** *2.Verilen app'i kurmak: ***
        driver.installApp(""); // install'u  istenen app'in path'i.

        //örnek kullanım:
        String appURL=System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator+ "resources" + File.separator + "ApiDemos-debug.apk";
        driver.installApp(appURL, new AndroidInstallApplicationOptions().withReplaceEnabled());

     //yukarıdaki örnekte eğer app'in install'ı gerçekleşmişse; ekranda " appismi already installed" döner.

    //***3. App'in install'ı gerçekleşmiş mi gerçekleşmemiş mi kontrolünü gerçekleştirmek:
        System.out.println(driver.isAppInstalled("io.appium.android.apis"));

//***4. App'i arka planda çalıştırmak: ***

        driver.runAppInBackground(Duration.ofSeconds(5)); //5 sn verilen app'i arka planda çalıştırır. 5 sn sonra app'i ön tarafa tekrardan atar.

//***5. App'in durumunu check etme: (örnek: APP_RUNNING_FOREGROUND)

        driver.queryAppState("io.appium.android.apis");


//***** LOCK VE UNLOCK THE DEVICE **** SADECE ANDROID'TE ÇALIŞIR!

//driver.lock metodu çıkmaz çünkü driver appiumDriver'dan geliyor. androidDriver'dan değil. Bunun için kullandığımız driver, Android driver'a kast edilir:

        ((AndroidDriver) driver).lockDevice(); //Screen'i anında lock'lar.
        ((AndroidDriver)driver).lockDevice(Duration.ofSeconds(5)); //Screen'i 5 sn sonra lock'lar.

        ((AndroidDriver) driver).unlockDevice(); //Screen'deki lock'ı anında kaldırır.
        ((AndroidDriver) driver).lockDevice(Duration.ofSeconds(5)); //Screen'deki lock'ı 5 sn sonra kaldırır.

        System.out.println(((AndroidDriver) driver).isDeviceLocked()); //Device'ın lock olup olmadığını konrol ederiz.

        //NOT: EĞER DEVICE'TA LOCK SCREEN PIN (PASSWORD) VAR İSE APPIUM DESIRED CAPABILITIES'TEN YARARLAN.

        //Create Driver Session class'ında:

        /*
        *         desiredCapabilities.setCapability("unlockType", "pin");
                   desiredCapabilities.setCapability("unlockKey","1111");
        * */




    }
}
