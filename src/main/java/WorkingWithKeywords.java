import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class WorkingWithKeywords {
    public static void main(String[] args) throws Exception {
        AppiumDriver driver = CreateDriverSession.initializeDriver("Android");
       ((AndroidDriver) driver).pressKey(new KeyEvent().withKey(AndroidKey.A));//A tuşuna basar
        driver.hideKeyboard(); //çıkan keyboard'ı hide eder.
    }
}
