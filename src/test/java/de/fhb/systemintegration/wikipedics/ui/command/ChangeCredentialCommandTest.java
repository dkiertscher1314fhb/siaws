package de.fhb.systemintegration.wikipedics.ui.command;

import org.junit.Test;
import org.junit.Assert;

/**
 * This is a test case for the ChangeCredentialCommand.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class ChangeCredentialCommandTest extends AbstractCommandTest {

    /**
     * This method checks that nobody can execute the credential command
     * without any changed credential.
     */
    @Test
    public void checkNoCredentials() {
        ChangeCredentialCommand changeCredentialCommand =
                new ChangeCredentialCommand(this.getOptions());
        changeCredentialCommand.setBuilder(this.getBuilderMock());
        changeCredentialCommand.doAction();
        Assert.assertEquals("You give no changed credentials.",
                changeCredentialCommand.getMessages().get(0));
    }

    /**
     * This method checks that nobody can execute the command
     * with an emtpy access key.
     */
    @Test
    public void checkEmptyAccesskey() {
        this.getOptions().put("accesskey", "");
        ChangeCredentialCommand changeCredentialCommand =
                new ChangeCredentialCommand(this.getOptions());
        changeCredentialCommand.setBuilder(this.getBuilderMock());
        changeCredentialCommand.doAction();
        Assert.assertEquals("The access key is empty.",
                changeCredentialCommand.getMessages().get(0));
    }

    /**
     * This method checks that nobody can execute the command
     * with an emtpy secret key.
     */
    @Test
    public void checkEmptySecretkey() {
        this.getOptions().put("secretkey", "");
        ChangeCredentialCommand changeCredentialCommand =
                new ChangeCredentialCommand(this.getOptions());
        changeCredentialCommand.setBuilder(this.getBuilderMock());
        changeCredentialCommand.doAction();
        Assert.assertEquals("The secret key is empty.",
                changeCredentialCommand.getMessages().get(0));
    }

    /**
     * This method checks that nobody can execute the command
     * with a to short access key.
     */
    @Test
    public void checkToShortAccesskey() {
        this.getOptions().put("accesskey", "ABCD");
        ChangeCredentialCommand changeCredentialCommand =
                new ChangeCredentialCommand(this.getOptions());
        changeCredentialCommand.setBuilder(this.getBuilderMock());
        changeCredentialCommand.doAction();
        Assert.assertEquals("The access key is to short.",
                changeCredentialCommand.getMessages().get(0));
    }

    /**
     * This method checks that nobody can execute the command
     * with a to short secret key.
     */
    @Test
    public void checkToShortSecretkey() {
        this.getOptions().put("secretkey", "ABCD");
        ChangeCredentialCommand changeCredentialCommand =
                new ChangeCredentialCommand(this.getOptions());
        changeCredentialCommand.setBuilder(this.getBuilderMock());
        changeCredentialCommand.doAction();
        Assert.assertEquals("The secret key is to short.",
                changeCredentialCommand.getMessages().get(0));
    }


    /**
     * This method checks that nobody can execute the command
     * with a to long access key.
     */
    @Test
    public void checkToLongAccesskey() {
        this.getOptions().put("accesskey", "ABCDEF123456ABCDEF78ABCDEFG");
        ChangeCredentialCommand changeCredentialCommand =
                new ChangeCredentialCommand(this.getOptions());
        changeCredentialCommand.setBuilder(this.getBuilderMock());
        changeCredentialCommand.doAction();
        Assert.assertEquals("The access key is to long.",
                changeCredentialCommand.getMessages().get(0));
    }

    /**
     * This method checks that nobody can execute the command
     * with a to long secret key.
     */
    @Test
    public void checkToLongSecretkey() {
        this.getOptions().put("secretkey",
                "ABCDEF123456ABCDEF78ABCDEFGABCDEF123456ABCDEF78ABCDEFGABC");
        ChangeCredentialCommand changeCredentialCommand =
                new ChangeCredentialCommand(this.getOptions());
        changeCredentialCommand.setBuilder(this.getBuilderMock());
        changeCredentialCommand.doAction();
        Assert.assertEquals("The secret key is to long.",
                changeCredentialCommand.getMessages().get(0));
    }

    /**
     * This method check that the access key is updates successfully.
     */
    @Test
    public void checkSuccessAccesskeyChange() {
        this.getOptions().put("accesskey", "ABCDEF123456ABCDEF78");
        ChangeCredentialCommand changeCredentialCommand =
                new ChangeCredentialCommand(this.getOptions());
        changeCredentialCommand.setBuilder(this.getBuilderMock());
        changeCredentialCommand.doAction();
        Assert.assertEquals("The access key is successfully updated.",
                changeCredentialCommand.getMessages().get(0));
    }

    /**
     * This method check that the access key is updates successfully.
     */
    @Test
    public void checkSuccessSecretkeyChange() {
        this.getOptions().put("secretkey",
                "ABCDEF123456ABCDEF78ABCDEF123456ABCDEF78");
        ChangeCredentialCommand changeCredentialCommand =
                new ChangeCredentialCommand(this.getOptions());
        changeCredentialCommand.setBuilder(this.getBuilderMock());
        changeCredentialCommand.doAction();
        Assert.assertEquals("The secret key is successfully updated.",
                changeCredentialCommand.getMessages().get(0));
    }

}
