package com.qa.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber.html", "summary"}
        , features = {"src/test/resources"}
        , glue = {"com/qa/stepdef"}
        , snippets = CucumberOptions.SnippetType.CAMELCASE
//        , dryRun=true
        , monochrome=true
//            , tags = "@foo and not @bar"
)
public class MyRunnerTest {
}
