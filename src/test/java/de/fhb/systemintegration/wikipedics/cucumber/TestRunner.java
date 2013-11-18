package de.fhb.systemintegration.wikipedics.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * This class is a special test runner class which starts all
 * the cucumber acceptance tests.
 *
 * @author mlelansky
 * @version 0.0.1
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/de/fhb/systemintegration/"
+ "wikipedics/cucumber/features", format = { "pretty", "html:build/cucumber" })
public class TestRunner {

}
