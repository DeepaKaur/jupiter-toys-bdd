package com.planittesting.cloud.jupiter.bdd.common;

import com.planittesting.cloud.jupiter.bdd.steps.ContactSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Util {
    public static WebDriver getWebDriver() {
        String os = System.getProperty("os.name");
        String driverPath = "";
        if (os.contains("Windows")) {
            driverPath = ContactSteps.class
                    .getClassLoader().getResource("chromedriver.exe").getPath();
        } else if (os.contains("Linux")) {
            driverPath = ContactSteps.class
                    .getClassLoader().getResource("chromedriver_linux").getPath();
        } else if (os.contains("Mac")) {
            driverPath = ContactSteps.class
                    .getClassLoader().getResource("chromedriver_mac").getPath();
        }
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--disable-gpu","--window-size=1920,1200","--ignore-certificate-errors");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }
}
