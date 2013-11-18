package de.fhb.systemintegration.wikipedics.cucumber.features.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.Before;
import org.junit.Assert;
import de.fhb.systemintegration.wikipedics.util.ApplicationRunner;

/**
 * This class contains the step definitions of the accesskey-feature.
 *
 * @author mlelansky
 * @version 0.0.1
  */
public final class AccesskeyStepDefinitions {

    /**
     * The test runner object of the application.
     */
    private ApplicationRunner runner;

    /**
     * This method setups the acceptance test.
     * It initialize the application runner which access the real application.
     *
     */
    @Before
    public void setUp() {
        this.runner = new ApplicationRunner();
    }

    /**
     * This method makes some initialisations for the tests.
     */
    @Given("^an running test application$")
    public void startTestApplication() {
        runner.startApplication();
    }

    /**
     * This method simulate the different user inputs.
     * @param key the accesskey
     */
    @When("^the user enter \"([^\"]*)\"$")
    public void enterAccesskey(final String key) {
        runner.setUserInput(key);
    }

    /**
     * This method define the checks for the scenario.
     * @param message the message which should be checked
     */
    @Then("^the user should get the message \"([^\"]*)\"$")
    public void validateMessage(final String message) {
        Assert.assertEquals(message, runner.getDisplayed());
    }

    /**
     * This method cleans the test.
     */
    @After
    public void tearDown() {
        this.runner.close();
    }

}
