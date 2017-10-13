package sakai.utilities;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/",
        glue = "sakai/steps/",
        format = "json:target/cucumber.json",
        tags = {"@assignment"},
        monochrome = true
)
public class RunTest {
}
