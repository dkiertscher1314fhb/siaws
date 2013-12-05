package de.fhb.systemintegration.wikipedics.ui.command;

import de.fhb.systemintegration.wikipedics.util.Config;
import org.junit.Test;
import org.junit.Assert;

/**
 * This is the test case for the DeployCommand.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class DeployCommandTest extends AbstractCommandTest {

    /**
     * This method checks that an AWS Beanstalk application is deployed.
     */
    @Test
    public void checkNoDeployWithoutCredentials() {
        Config.setId(Long.valueOf(-1));
        DeployCommand deployCommand = new DeployCommand(this.getOptions());
        deployCommand.setBuilder(this.getBuilderMock());
        deployCommand.doAction();
        Assert.assertEquals("No User Information are found.",
                deployCommand.getMessages().get(0));
    }

}
