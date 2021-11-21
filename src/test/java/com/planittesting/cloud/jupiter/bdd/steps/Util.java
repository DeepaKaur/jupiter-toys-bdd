package com.planittesting.cloud.jupiter.bdd.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Util {
    public static WebDriver getWebDriver() {
        String os = System.getProperty("os.name");
        System.out.println("Using System Property: " + os);
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
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }
}
