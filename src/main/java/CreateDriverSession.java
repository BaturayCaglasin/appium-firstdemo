import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import org.aspectj.weaver.tools.cache.AsynchronousFileCacheBacking;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class CreateDriverSession {
    //ram'de tek bir alan tutsun diye static metot olarak tanımlandı.
    public static AppiumDriver initializeDriver(String platformName) throws Exception {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);

        URL url = new URL("http://0.0.0.0:4723/wd/hub");
        switch (platformName) {
            case "Android":
                desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "SNE LX1");
                desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                desiredCapabilities.setCapability(MobileCapabilityType.UDID, "HVYDU19329010363");
                String andAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                        + File.separator + "resources" + File.separator + "ApiDemos-debug.apk";

                //desiredCapabilities.setCapability("appPackage","io.appium.android.apis");
                // desiredCapabilities.setCapability("appActivitiy","io.appium.android.apis.ApiDemos");

                desiredCapabilities.setCapability(MobileCapabilityType.APP, andAppUrl);
                desiredCapabilities.setCapability("simulatorStartupTimeout", 180000);
                desiredCapabilities.setCapability("unlockType", "pin");
                desiredCapabilities.setCapability("unlockKey", "1111");

                return new AndroidDriver(url, desiredCapabilities);

            case "IOS":
                desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone XR");
                desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                desiredCapabilities.setCapability(MobileCapabilityType.UDID, "9527463259a2c083dfc8062535b699a52b9b3cf9");
                String iosAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                        + File.separator + "resources" + File.separator + "UIKitCatalog-iphonesimulator.app";

                desiredCapabilities.setCapability("simulatorStartupTimeout", 1800000); //simulator timeout
                return new IOSDriver(url,  desiredCapabilities);

            default:
                throw new Exception("invalid platform");

        }

    }

}
