package com.planittesting.cloud.jupiter.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@Shop",
        features = "classpath:features/",
        glue = {"com.planittesting.cloud.jupiter.bdd.steps"},
        publish = false)
public class TestJupiterToys {
}
