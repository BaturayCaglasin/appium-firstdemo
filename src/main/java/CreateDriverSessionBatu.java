import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class CreateDriverSessionBatu extends Elements {


    public static void main(String[] args) throws MalformedURLException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"SNE LX1");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID,"HVYDU19329010363");
        //desiredCapabilities.setCapability("new command timeout",500); // session'ı kapatmadan önce 5 dk'a bekleyecek.

        String appURL=System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator+ "resources" + File.separator + "ApiDemos-debug.apk";
        //burada File Seperator kullanmamızın nedeni; IOS ve Windows'taki file path gösterim slash'ının farklı olmasının önüne geçilmesi istenmesindendir.
        desiredCapabilities.setCapability(MobileCapabilityType.APP,appURL);

        //appPackage, belirli bir app'i test etmeye yarar. Appium; ilk başta sadece set'lenen app'i açacaktır.
        //apk info uygulamasından (telefondaki) uygulamanın apk info'u çekilir ve buraya yazılır.
        //desiredCapabilities.setCapability("appPackage","io.appium.android.apis");
       // desiredCapabilities.setCapability("appActivity","io.appium.android.apis.accessibility.CustomViewAccessibilityActivity");

        //Android: Launch Emulator automatically - for virtual device:
        //desiredCapabilities.setCapability("avd","Pixel_3");
      //  desiredCapabilities.setCapability("avdLaunchTimeout","180000");

        //appium server'ın URL'i:
        URL url = new URL("http://0.0.0.0:4723/wd/hub");

        AppiumDriver driver = new AndroidDriver(url,desiredCapabilities);
        System.out.println("session id:"+driver.getSessionId());

        MobileElement firstElement = (MobileElement) driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Accessibility\"]"));
        System.out.println(firstElement.getText());

        //!!AccesibiltyId locator'ı sadece android telefonlarda çalışır. IOS'ta patlar.!!!

        MobileElement secondElement = (MobileElement) driver.findElementsById("android:id/text1").get(2); //get ise app'teki index sıralamasından yararlanarak aynı class name'i taşayam elementleri unique'lemeye yarar.
        System.out.println(secondElement.getText());

        //*****Finding elements using UiAutomator*****
        /*
        MobileElement uielement = (MobileElement) ((FindsByAndroidUIAutomator) driver).findElementByAndroidUIAutomator("new UiSelector().className(\"
        android.widget.TextView\")").get(2);
        By uielement2 = MobileBy.AndroidUIAutomator(""); //bu da ikinci kullanımı
        */

        //!!!!UiAutomator'ı validation için kullanabilirsin. (clickable, checkable gibi)

         // public void DifferentWaysOfDefiningElements(driver){
       // PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    //}

        MobileElement viewElement = (MobileElement) driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Views\"]"));
        viewElement.click();

        (new TouchAction(driver))
                .press(new PointOption().withCoordinates(507, 1900))
                .moveTo(new PointOption().withCoordinates(520, 650))
                .release()
                .perform();

        (new TouchAction(driver))
                .press(new PointOption().withCoordinates(494, 1800))
                .moveTo(new PointOption().withCoordinates(499, 650))
                .release()
                .perform();

        (new TouchAction(driver))
                .press(new PointOption().withCoordinates(503, 1800))
                .moveTo(new PointOption().withCoordinates(494, 650))
                .release()
                .perform();

        MobileElement textFieldsElement = (MobileElement) driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"TextFields\"]"));
        textFieldsElement.click();

        MobileElement textFieldsInsideElement = (MobileElement) driver.findElement(By.id("io.appium.android.apis:id/edit"));
        textFieldsInsideElement.sendKeys("Hello Appium!");





    }

}

//!!!!!!!!!!!!Kurs der ki ayrı bir class'ın altında android ve IOS için şu locator'ları ayrı ayrı kullan:

/*
@AndroidBy(xpath=...);
@IOSXCUITFindBy(accessibility=...);
private static MobileElement elementname
 */