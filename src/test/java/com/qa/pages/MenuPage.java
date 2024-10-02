package com.qa.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MenuPage extends BasePage {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Menu\"]/XCUIElementTypeOther")
    private WebElement settingsBtn;

    public MenuPage(){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public SettingsPage pressSettingsBtn(){
        // TODO fails on iOS - menu is not opened
        click(settingsBtn, "press Settings button");
        return new SettingsPage();
    }
}
