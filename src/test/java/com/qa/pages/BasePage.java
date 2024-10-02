package com.qa.pages;

import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParams;
import com.qa.utils.PropertyManager;
import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

public class BasePage {
    // Global variables
    protected AppiumDriver driver ;
    GlobalParams params = new GlobalParams();
    Properties props = new PropertyManager().getProps();
    TestUtils utils = new TestUtils();

    public BasePage(){
        this.driver = new DriverManager().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public void waitForVisibility(WebElement e) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void click(WebElement e) {
        waitForVisibility(e);
        e.click();
    }

    public void click(WebElement e, String msg) {
        // TODO use optional parameters and log only when set -- instead overloading click()
        waitForVisibility(e);
        utils.log().info(msg);
        e.click();
    }

    public void clear(WebElement e) {
        waitForVisibility(e);
        e.clear();
    }

    public void sendKeys(WebElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public void sendKeys(WebElement e, String txt, String msg) {
        // TODO use optional parameters and log only when set -- instead overloading sendKeys()
        waitForVisibility(e);
        utils.log().info(msg);
        e.sendKeys(txt);
    }

    public String getAttribute(WebElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public String getText(WebElement e) {
        return switch (params.getPlatformName()) {
            case "Android" -> getAttribute(e, "text");
            case "iOS" -> getAttribute(e, "label");
            default -> null;
        };
    }

    public String getText(WebElement e, String msg) {
        // TODO use optional parameters and log only when set -- instead overloading click()
        String txt = switch (params.getPlatformName()) {
            case "Android" -> getAttribute(e, "text");
            case "iOS" -> getAttribute(e, "label");
            default -> null;
        };

        utils.log().info(msg + txt);
        return txt;
    }

    public void closeApp() {
        switch (params.getPlatformName()) {
            case "Android":
                ((InteractsWithApps) driver).terminateApp(props.getProperty("androidAppPackage"));
                break;
            case "iOS":
                ((InteractsWithApps) driver).terminateApp(props.getProperty("iOSBundleId"));
                break;
            default:
                throw new IllegalStateException("Invalid platform - " + params.getPlatformName());
        }
    }

    public void launchApp() {
        switch (params.getPlatformName()) {
            case "Android":
                ((InteractsWithApps) driver).activateApp(props.getProperty("androidAppPackage"));
                break;
            case "iOS":
                ((InteractsWithApps) driver).activateApp(props.getProperty("iOSBundleId"));
                break;
            default:
                throw new IllegalStateException("Invalid platform - " + params.getPlatformName());
        }
    }


    public WebElement androidScrollToElement() {
        /*https://developer.android.com/reference/androidx/test/uiautomator/UiScrollable
        Homework - check why and where we used UiSelector on Android*/

        // with multiple scrollable parent locators on the Android page
       /* return getDriver().findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()"
                + ".description(\"android.widget.ScrollView\")).scrollIntoView("
                + "new UiSelector().description(\"test-Price\"));"));*/

        // with 1 scrollable  element on the Android page
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()"
                        + ".scrollable(true)).scrollIntoView("
                        + "new UiSelector().description(\"test-Price\"));"));
    }

    public void iOSScrollToElement() {

        // scroll without the accessibility id ie use Scrollable Parent
       /* RemoteWebElement parent = (RemoteWebElement) getDriver().findElement(By.className("XCUIElementTypeScrollView"));
        String parentId = parent.getId();
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("element", parentId);
//        scrollObject.put("direction", "down"); // scroll down by length of screen
//        scrollObject.put("predicateString", "label == 'ADD TO CART'");
        scrollObject.put("name", "test-ADD TO CART");*/

        // scroll to element with accessibility id set
        RemoteWebElement element = (RemoteWebElement) driver.findElement(By.name("test-ADD TO CART"));
        String elementId = element.getId();
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("element", elementId);
        scrollObject.put("toVisible", "<any text here>");
        driver.executeScript("mobile:scroll", scrollObject);
    }

}
