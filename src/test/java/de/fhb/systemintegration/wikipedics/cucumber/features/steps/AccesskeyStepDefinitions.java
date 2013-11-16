package de.fhb.systemintegration.wikipedics.cucumber.features.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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
    private ApplicationRunner runner = new ApplicationRunner();

    /**
     * This method makes some initialisations for the tests.
     */
    @Given("^an running test application$")
    public void startTestApplication() {
        runner.startApplication();
    }

    /**
     * This method define the program execution steps.
     */
    @When("^the user enter an empty accesskey$")
    public void enterEmptyAccesskey() {
        runner.setUserInput("-accesskey=");
    }

    /**
     * This method define the checks for the scenario.
     */
    @Then("^the user should get a message that the acceskey is to short$")
    public void validateErrorMessage() {
        Assert.assertEquals("The accesskey is empty!", runner.getDisplayed());
    }

}
