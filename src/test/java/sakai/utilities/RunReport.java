package sakai.utilities;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RunReport {

    @Test
    public void generateReport() {
        SakaiLogger.logInfo("Generating Cucumber HTML Report");
        List<String> jsonPaths = new ArrayList();
        jsonPaths.add("result/cucumber.json");
        Configuration config = new Configuration(new File("result"), "NYU Sakai Test Suite");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}
