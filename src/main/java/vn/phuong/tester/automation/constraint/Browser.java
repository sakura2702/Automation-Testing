/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.phuong.tester.automation.constraint;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import vn.phuong.tester.automation.config.UserConfiguration;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author ADMIN
 */
public enum Browser {


    FIREFOX, IE, CHROME, ALL;

    private DesiredCapabilities capabilities;
    public static String preProcess(String input){
        return input.toUpperCase().trim();
    }
    public WebDriver getDriver(UserConfiguration userConfiguration){
        RemoteWebDriver driver;
        switch (this){
            case IE:
                capabilities =DesiredCapabilities.internetExplorer();
                capabilities.setJavascriptEnabled(true);
                capabilities.setCapability("acceptSslCerts", true);
                capabilities.setCapability("applicationCacheEnabled", true);
                capabilities.setCapability("handlesAlerts", true);
                capabilities.setCapability("browserConnectionEnabled", true);
                capabilities.setCapability("databaseEnabled", true);
                capabilities.setCapability("locationContextEnabled", true);
                capabilities.setCapability("nativeEvents", false);
                capabilities.setCapability("webStorageEnabled", true);
                driver = new InternetExplorerDriver();
                break;
            case CHROME:
                capabilities =DesiredCapabilities.chrome();
                capabilities.setJavascriptEnabled(true);
                capabilities.setCapability("acceptSslCerts", true);
                capabilities.setCapability("applicationCacheEnabled", true);
                capabilities.setCapability("handlesAlerts", true);
                capabilities.setCapability("browserConnectionEnabled", true);
                capabilities.setCapability("databaseEnabled", true);
                capabilities.setCapability("locationContextEnabled", true);
                capabilities.setCapability("nativeEvents", false);
                capabilities.setCapability("webStorageEnabled", true);
                driver = new ChromeDriver();
                break;
            default: {
                capabilities =DesiredCapabilities.firefox();
                capabilities.setJavascriptEnabled(true);
                capabilities.setCapability("acceptSslCerts", true);
                capabilities.setCapability("applicationCacheEnabled", true);
                capabilities.setCapability("handlesAlerts", true);
                capabilities.setCapability("browserConnectionEnabled", true);
                capabilities.setCapability("databaseEnabled", true);
                capabilities.setCapability("locationContextEnabled", true);
                capabilities.setCapability("nativeEvents", false);
                capabilities.setCapability("webStorageEnabled", true);
                driver = new FirefoxDriver();

            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(userConfiguration.getPageLoad(), TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(userConfiguration.getImplicityWait(), TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(userConfiguration.getImplicityWait(), TimeUnit.SECONDS);
        return driver;
    }
}
