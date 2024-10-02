package com.qa.utils;

import io.appium.java_client.AppiumDriver;

public class DriverManager {
    private static ThreadLocal <AppiumDriver>  driver = new ThreadLocal<>();

    public AppiumDriver getDriver(){
        return driver.get();
    }

    public void setDriver(AppiumDriver driver1){
        driver.set(driver1);
    }

    public  void initializeDriver() throws Exception {
        driver.set(new CapabilitiesManager().setDriverCaps());
    }
}
