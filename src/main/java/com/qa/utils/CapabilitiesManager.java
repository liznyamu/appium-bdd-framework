package com.qa.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class CapabilitiesManager {
    TestUtils utils = new TestUtils();

    protected AppiumDriver setDriverCaps() throws Exception {
        GlobalParams params = new GlobalParams();
        Properties props = new PropertyManager().getProps();
        AppiumDriver driver = null;

        URL url = new URL(props.getProperty("appiumURL"));
        utils.log().info("initializing the appium server ");
        switch (params.getPlatformName()) {
            case "Android":
                String androidAppUrl = System.getProperty("user.dir") + File.separator +
                        "src" + File.separator + "test" + File.separator + "resources" +
                        File.separator + props.getProperty("androidAppLocation");

                // For emulator only set avd and avdLaunchTimeout
                UiAutomator2Options androidOptions = new UiAutomator2Options().
                        setUdid(params.getUdid()).
                        setDeviceName(params.getDeviceName()).
                        setApp(androidAppUrl).
                        setAppPackage(props.getProperty("androidAppPackage")).
                        setAppActivity(props.getProperty("androidAppActivity")).
                        setSystemPort(Integer.parseInt(params.getSystemPort())).
                        setChromedriverPort(Integer.parseInt(params.getChromeDriverPort())).
                        setNewCommandTimeout(Duration.ofMinutes(5)).
                        setAvd(params.getDeviceName()).
                        setAvdLaunchTimeout(Duration.ofMinutes(10));
                driver = new AndroidDriver(new ServerManager().getServer().getUrl(), androidOptions);
                utils.log().info( "appPackage <> " + driver.getCapabilities().getCapability("appPackage").toString());
                break;
            case "iOS":
                String iOSAppUrl = System.getProperty("user.dir") + File.separator +
                        "src" + File.separator + "test" + File.separator + "resources" +
                        File.separator + props.getProperty("iOSAppLocation");

                XCUITestOptions iOSOptions = new XCUITestOptions()
                        .setUdid(params.getUdid())
                        .setDeviceName(params.getDeviceName())
                        .setApp(iOSAppUrl)
                        .setBundleId(props.getProperty("iOSBundleId"))
                        .setNewCommandTimeout(Duration.ofMinutes(5))
                        .setSimulatorStartupTimeout(Duration.ofMinutes(5))
                        .setSimpleIsVisibleCheck(true)
                        .setWdaLocalPort(Integer.parseInt(params.getWdaLocalPort()));
                driver = new IOSDriver(new ServerManager().getServer().getUrl(), iOSOptions);
                utils.log().info( "bundleId <> " + driver.getCapabilities().getCapability("bundleId").toString());
                break;
        }

        if(driver == null){
            utils.log().fatal("Driver initialization failure. ABORT!!");
            throw new Exception("Appium driver is null. ABORT!!");
        }
        utils.log().info("Driver is initialized.");
        return driver;
    }
}
