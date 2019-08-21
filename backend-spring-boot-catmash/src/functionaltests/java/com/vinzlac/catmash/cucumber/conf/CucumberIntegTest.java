package com.vinzlac.catmash.cucumber.conf;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty"},
		features = "src/functionaltests/resources/features",
		glue = "com.vinzlac.catmash.cucumber"
)
public class CucumberIntegTest {

}
