package com.qa.stepdef;

import com.qa.pages.BasePage;
import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParams;
import com.qa.utils.ServerManager;
import com.qa.utils.VideoManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;

import java.io.IOException;


public class Hooks {

    /**
     * io.cucumber.java.Before runs before each scenario
     *  - initialize the global device params and the Log4j2 thread context
     *  - start the appium server ??
     */
    @Before
    public void initialize() throws Exception {
       /* GlobalParams params = new GlobalParams();
        params.initializeGlobalParams();

        ThreadContext.put("ROUTINGKEY", params.getPlatformName() + "_" + params.getDeviceName());

        new ServerManager().startServer();
        new DriverManager().initializeDriver();*/

        // ensure test independency
        new BasePage().launchApp();

        new VideoManager().startRecording();

    }

    /**
     * io.cucumber.java.Before runs after each scenario
     *  - stop the appium server if it's not null
     */
    @After
    public void quit(Scenario scenario) throws IOException {

        // ensure test independency
        new BasePage().closeApp();

        new VideoManager().stopRecording(scenario.getName());

        // take screenshot
        if(scenario.isFailed()){
            byte[] screenshot = new DriverManager().getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

      /*  DriverManager driverManager = new DriverManager();
        if(driverManager.getDriver() != null){
            driverManager.getDriver().quit();
            driverManager.setDriver(null);
        }

        ServerManager serverManager = new ServerManager();
        if(serverManager.getServer() != null){
            serverManager.getServer().stop();
        }*/
    }
}
