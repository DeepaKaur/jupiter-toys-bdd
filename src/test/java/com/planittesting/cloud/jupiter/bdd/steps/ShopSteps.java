package com.planittesting.cloud.jupiter.bdd.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShopSteps {
    private WebDriver driver;

    @Before
    public void initializeWebDriver() {
        if (driver == null) {
            String driverPath = ContactSteps.class
                    .getClassLoader().getResource("chromedriver.exe").getPath();
            System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        }
    }

    @After
    public void cleanupWebDriver() {
        if (driver != null) {
            driver.close();
        }
    }

    @Given("A user is on home page and nevigates to shop page")
    public void navigateToHomePage() {
        driver.get("http://jupiter.cloud.planittesting.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Shop")).click();
    }


    @When("User buys {string} Funny cow")
    public void buyFunnyCows(String quantity) {
        System.out.println("buying cows");
        WebElement cow = driver.findElement(By.xpath("//h4[contains(text(),'Funny Cow')]/parent::*"));
        System.out.println(cow.getText());
        for (int i = 1; i <= Integer.parseInt(quantity); i++) {
            cow.findElement(By.linkText("Buy")).click();
        }
    }

    @And("User buys {string} Fluffy Bunny")
    public void buyBunny(String quantity) {
        System.out.println("buying bunny");
        WebElement bunny = driver.findElement(By.xpath("//h4[contains(text(),'Fluffy Bunny')]/parent::*"));
        for (int i = 1; i <= Integer.parseInt(quantity); i++) {
            bunny.findElement(By.linkText("Buy")).click();
        }
    }

    @And("User clicks on cart button")
    public void clickCart() {
        driver.findElement(By.partialLinkText("Cart")).click();
    }

    @Then("verify that there are {string} items in the cart")
    public void verifyItems(String number) {
        String message = driver.findElement(By.xpath("//p [@class = \"cart-msg\"]")).getText();
        Assert.assertTrue(message.contains(number));
    }

    @And("verify that {string} funny cows and  {string} fluffy bunny are in the cart")
    public void verifyCowBunny(String cows, String bunny) {
        List<WebElement> items = driver.findElements(By.xpath("//tbody/tr"));
        int expectedItems = Integer.parseInt(cows) + Integer.parseInt(bunny);
        int totalItems = 0;
        for (WebElement item : items) {
            List<WebElement> details = item.findElements(By.xpath("./td"));
            String count = details.get(2).findElement(By.xpath("./input")).getAttribute("value");
            totalItems += Integer.parseInt(count);
        }
        Assert.assertEquals(expectedItems, totalItems);
    }

}
