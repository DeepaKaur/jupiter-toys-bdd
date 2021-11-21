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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ContactSteps {
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

    @Given("A user is on home page")
    public void navigateToHome() {
        driver.get("http://jupiter.cloud.planittesting.com");
    }

    @And("navigates to contact page")
    public void navigateToContacts() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Contact")).click();
    }


    @When("User does not populate {string} and submits")
    public void setContactInputFields(String inputName, DataTable datatable) {
        String forename = datatable.cell(1, 0);
        driver.findElement(By.id("forename")).sendKeys(forename == null ? "" : forename);

        String surname = datatable.cell(1, 1);
        driver.findElement(By.id("surname")).sendKeys(surname == null ? "" : surname);

        String email = datatable.cell(1, 2);
        driver.findElement(By.id("email")).sendKeys(email == null ? "" : email);

        String telephone = datatable.cell(1, 3);
        driver.findElement(By.id("telephone")).sendKeys(telephone == null ? "" : telephone);

        String message = datatable.cell(1, 4);
        driver.findElement(By.id("message")).sendKeys(message == null ? "" : message);
    }

    @When("User populates mandatory fields")
    public void setContactInput(DataTable datatable) {
        setContactInputFields(" ", datatable);
    }

    @And("User submits the page")
    public void submitPage() {
        System.out.println("Submitting the page");
        driver.findElement(By.linkText("Submit")).click();
    }

    @Then("Error alert message is displayed")
    public void messageError() {
        String actualMessage = driver.findElement(By.xpath("//div[@class = 'alert alert-error ng-scope']")).getText();
        Assert.assertEquals("We welcome your feedback - but we won't get it unless you complete the form correctly.", actualMessage);
    }

    @And("Input error message {string} is displayed")
    public void inputErrorMessage(String expectedMessage) {
        String actualErrorMessage = "";
        if (expectedMessage.contains("Email")) {
            actualErrorMessage = driver.findElement(By.id("email-err")).getText();
        }
        if (expectedMessage.contains("Forename")) {
            actualErrorMessage = driver.findElement(By.id("forename-err")).getText();
        }
        if (expectedMessage.contains("Message")) {
            actualErrorMessage = driver.findElement(By.id("message-err")).getText();
        }
        Assert.assertEquals(expectedMessage, actualErrorMessage);
    }

    @Then("Successful submission message {string} is displayed")
    public void successMessage(String message) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class = 'alert alert-success'] ")));
        String actualMessage = driver.findElement(By.xpath("//div[@class = 'alert alert-success'] ")).getText();
        Assert.assertEquals(message, actualMessage);
    }
}
