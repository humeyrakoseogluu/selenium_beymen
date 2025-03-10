package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(// Annotation to run the Cucumber tests with JUnit
        features = "src/test/java/features", // Path to the feature files containing Gherkin syntax
        glue = "stepDefinitions", // Path to the step definitions where the logic for steps is written
        plugin = {"pretty", "html:target/cucumber-html-report.html"} // Generate a pretty format and HTML report after test execution
)

public class TestRunner {
    // The test runner class coordinates the execution of Cucumber tests
    // It is used to integrate Cucumber with JUnit, specifying the feature files and step definitions.
}
