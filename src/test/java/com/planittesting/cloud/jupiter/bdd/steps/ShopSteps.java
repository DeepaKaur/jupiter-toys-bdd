package com.planittesting.cloud.jupiter.bdd.steps;

import com.planittesting.cloud.jupiter.bdd.common.Util;
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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShopSteps {
    private WebDriver driver;

    @Before
    public void initializeWebDriver() {
        if (driver == null) {
            driver = Util.getWebDriver();
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


    @When("User buys {string} {string}")
    public void buyItems(String quantity, String Name) {
        String xpath = "//h4[contains(text(),'" + Name + "')]/parent::*";
        WebElement shopitem = driver.findElement(By.xpath(xpath));
        for (int i = 1; i <= Integer.parseInt(quantity); i++) {
            shopitem.findElement(By.linkText("Buy")).click();
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

    @Then("verify that the subtotal of each product is correct")
    public void verifyTotal() {
        List<WebElement> items = driver.findElements(By.xpath("//tbody/tr"));
        for (WebElement item : items) {
            List<WebElement> details = item.findElements(By.xpath("./td"));
            String count = details.get(2).findElement(By.xpath("./input")).getAttribute("value");
            String price = details.get(1).getText().substring(1);
            String subtotal = details.get(3).getText().substring(1);
            double totalprice = Integer.parseInt(count) * Double.parseDouble(price);
            Assert.assertEquals(Double.parseDouble(subtotal), totalprice, 0.01);
        }
    }
}
