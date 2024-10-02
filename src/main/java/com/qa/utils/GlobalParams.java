package com.qa.utils;

/**
 * GlobalParams - collects the properties to sent through the `mvn test` command —
 *      ie the device capabilities {
 *          common: platformName, udid, deviceName,
 *          android only: systemPort, chromeDriverPort,
 *          iOS only: wdaLocalPort, webkitDebutProxyPort
 *      }
 *
 * It uses ThreadLocal variables - so that framework supports parallel execution
 */
public class GlobalParams {
    private static ThreadLocal<String> platformName = new ThreadLocal<>();
    private static ThreadLocal<String> udid = new ThreadLocal<>();
    private static ThreadLocal<String> deviceName = new ThreadLocal<>();
    private static ThreadLocal<String> systemPort = new ThreadLocal<>();
    private static ThreadLocal<String> chromeDriverPort = new ThreadLocal<>();
    private static ThreadLocal<String> wdaLocalPort = new ThreadLocal<>();
    private static ThreadLocal<String> webkitDebutProxyPort = new ThreadLocal<>();

    public void setPlatformName(String platformName1){
        platformName.set(platformName1);
    }

    public String getPlatformName(){
        return platformName.get();
    }

    public void setUdid(String udid1){
        udid.set(udid1);
    }

    public String getUdid(){
        return udid.get();
    }

    public void setDeviceName(String deviceName1){
        deviceName.set(deviceName1);
    }

    public String getDeviceName(){
        return deviceName.get();
    }

    public void setSystemPort(String systemPort1){
        systemPort.set(systemPort1);
    }

    public String getSystemPort(){
        return systemPort.get();
    }

    public void setChromeDriverPort(String chromeDriverPort1){
        chromeDriverPort.set(chromeDriverPort1);
    }

    public String getChromeDriverPort(){
        return chromeDriverPort.get();
    }

    public void setWdaLocalPort(String wdaLocalPort1){
        wdaLocalPort.set(wdaLocalPort1);
    }

    public String getWdaLocalPort(){
        return wdaLocalPort.get();
    }

    public void setWebkitDebutProxyPort(String webkitDebutProxyPort1){
        webkitDebutProxyPort.set(webkitDebutProxyPort1);
    }

    public String getWebkitDebutProxyPort(){
        return webkitDebutProxyPort.get();
    }

    public void initializeGlobalParams(){
        GlobalParams params = new GlobalParams();
        params.setPlatformName(System.getProperty("platformName", "Android"));
        params.setUdid(System.getProperty("udid", "emulator-5554"));
        params.setDeviceName(System.getProperty("deviceName", "Medium_Phone_API_35"));

        switch (params.getPlatformName()){
            case "Android":
                params.setSystemPort(System.getProperty("systemPort", "10000"));
                params.setChromeDriverPort(System.getProperty("chromeDriverPort", "11000"));
                break;
            case "iOS":
                params.setWdaLocalPort(System.getProperty("wdaLocalPort", "10001"));
                params.setWebkitDebutProxyPort(System.getProperty("webkitDebugProxyPort", "11001"));
                break;
            default:
                throw new IllegalStateException("Invalid Platform Name: " + params.getPlatformName());
        }
    }
}
