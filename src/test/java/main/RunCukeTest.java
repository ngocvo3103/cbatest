package main;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty", "html:target/cucumberHtmlReport",
                "html:target/cucumberHtmlReport",     //  for html result
                "pretty:target/cucumber-json-report.json"   // for json result
        },
        features = "src/test/java/features",
        glue = {"stepDefinitions"},
        strict = true
)

public class RunCukeTest {
}