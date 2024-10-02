package com.qa.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.util.HashMap;

public class ServerManager {
    private static ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    public AppiumDriverLocalService getServer(){
        return server.get();
    }

    public void setServer(AppiumDriverLocalService server1){
        server.set(server1);
    }

    public void startServer(){
        AppiumDriverLocalService server = getAppiumServerDefault();
        server.start();
        if(!server.isRunning()){ //  if(server == null || !server.isRunning()){
            utils.log().fatal("Appium server not started. ABORT !!!");
            throw new AppiumServerHasNotBeenStartedLocallyException("Appium server not started. ABORT !!!");
        }
        server.clearOutPutStreams();
        setServer(server); // this.server.set(server) - static
        utils.log().info("Appium server started");
    }

    public AppiumDriverLocalService getAppiumServerDefault(){
        return AppiumDriverLocalService.buildDefaultService();
    }

    public AppiumDriverLocalService macGetAppiumServerDefault(){
        GlobalParams params = new GlobalParams();
        HashMap<String, String> environment = new HashMap<>();
        environment.put("PATH", "/Users/elizabethnyamu/Projects/tools/apache-maven-3.9.9/bin:/Users/elizabethnyamu/Library/Android/sdk/platform-tools:/Users/elizabethnyamu/Library/Android/sdk/cmdline-tools:/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home/bin:/opt/homebrew/bin:/opt/homebrew/sbin:/usr/local/bin:/System/Cryptexes/App/usr/bin:/usr/bin:/bin:/usr/sbin:/sbin:/var/run/com.apple.security.cryptexd/codex.system/bootstrap/usr/local/bin:/var/run/com.apple.security.cryptexd/codex.system/bootstrap/usr/bin:/var/run/com.apple.security.cryptexd/codex.system/bootstrap/usr/appleinternal/bin:/Library/Apple/usr/bin:/Library/Frameworks/Mono.framework/Versions/Current/Commands:/Applications/iTerm.app/Contents/Resources/utilities");
        environment.put("ANDROID_HOME", "/Users/elizabethnyamu/Library/Android/sdk");
        environment.put("JAVA_HOME", "/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home");
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
//                .usingDriverExecutable(new File("/opt/homebrew/bin/node"))
//                .withAppiumJS(new File("/opt/homebrew/lib/node_modules/appium/index.js"))
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//                .withEnvironment(environment)
                .withLogFile(new File("serverlogs" + File.separator
                        + params.getPlatformName() + "_" + params.getDeviceName() + File.separator
                        + "server.log"))
        );
    }
}
