package de.fhb.systemintegration.wikipedics.ui.command;

import de.fhb.systemintegration.wikipedics.business.builder.BusinessBuilder;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialViewer;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialManager;
import de.fhb.systemintegration.wikipedics.domain.UserSetting;
import de.fhb.systemintegration.wikipedics.util.Config;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import java.util.Map;
import java.util.HashMap;
import org.mockito.Mockito;

/**
 * This is a test case for the ChangeCredentialCommand.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class ChangeCredentialCommandTest {

    /**
     * This is the credential viewer mock.
     */
    private CredentialViewer viewer;
    /**
     * This is the credential manager mock.
     */
    private CredentialManager manager;
    /**
     * This is the mock of the interface builder.
     */
    private BusinessBuilder builder;
    /**
     * This is an example user.
     */
    private UserSetting settings;

    /**
     * This is the option map which needed for the tests.
     */
    private Map<String, String> options;

    /**
     * This method setups the change credential command test.
     */
    @Before
    public void setUp() {
        Config.setId(1L);
        this.options = new HashMap<>();
        this.settings = new UserSetting();
        settings.setAccesskey("1122334455667788990011223344556677889900");
        settings.setSecretkey("123456");
        settings.setRegion("EU-WEST-1");
        this.manager = Mockito.mock(CredentialManager.class);
        this.viewer = Mockito.mock(CredentialViewer.class);
        this.builder = Mockito.mock(BusinessBuilder.class);
        Mockito.when(this.builder.getCredentialManager()).
                thenReturn(this.manager);
        Mockito.when(this.builder.getCredentialViewer()).
                thenReturn(this.viewer);
        Mockito.when(this.viewer.findById(1L)).thenReturn(this.settings);
    }

    /**
     * This method checks that nobody can execute the credential command
     * without any changed credential.
     */
    @Test
    public void checkNoCredentials() {
        ChangeCredentialCommand changeCredentialCommand =
                new ChangeCredentialCommand(this.options);
        changeCredentialCommand.setBuilder(this.builder);
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
        this.options.put("accesskey", "");
        ChangeCredentialCommand changeCredentialCommand =
                new ChangeCredentialCommand(this.options);
        changeCredentialCommand.setBuilder(this.builder);
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
        options.put("secretkey", "");
        ChangeCredentialCommand changeCredentialCommand =
                new ChangeCredentialCommand(this.options);
        changeCredentialCommand.setBuilder(this.builder);
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
        this.options.put("accesskey", "ABCD");
        ChangeCredentialCommand changeCredentialCommand =
                new ChangeCredentialCommand(this.options);
        changeCredentialCommand.setBuilder(this.builder);
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
        this.options.put("secretkey", "ABCD");
        ChangeCredentialCommand changeCredentialCommand =
                new ChangeCredentialCommand(this.options);
        changeCredentialCommand.setBuilder(this.builder);
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
        this.options.put("accesskey", "ABCDEF123456ABCDEF78ABCDEFG");
        ChangeCredentialCommand changeCredentialCommand =
                new ChangeCredentialCommand(this.options);
        changeCredentialCommand.setBuilder(this.builder);
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
        this.options.put("secretkey",
                "ABCDEF123456ABCDEF78ABCDEFGABCDEF123456ABCDEF78ABCDEFGABC");
        ChangeCredentialCommand changeCredentialCommand =
                new ChangeCredentialCommand(this.options);
        changeCredentialCommand.setBuilder(this.builder);
        changeCredentialCommand.doAction();
        Assert.assertEquals("The secret key is to long.",
                changeCredentialCommand.getMessages().get(0));
    }

    /**
     * This method check that the access key is updates successfully.
     */
    @Test
    public void checkSuccessAccesskeyChange() {
        this.options.put("accesskey", "ABCDEF123456ABCDEF78");
        ChangeCredentialCommand changeCredentialCommand =
                new ChangeCredentialCommand(this.options);
        changeCredentialCommand.setBuilder(this.builder);
        changeCredentialCommand.doAction();
        Assert.assertEquals("The access key is successfully updated.",
                changeCredentialCommand.getMessages().get(0));
    }

    /**
     * This method check that the access key is updates successfully.
     */
    @Test
    public void checkSuccessSecretkeyChange() {
        this.options.put("secretkey",
                "ABCDEF123456ABCDEF78ABCDEF123456ABCDEF78");
        ChangeCredentialCommand changeCredentialCommand =
                new ChangeCredentialCommand(this.options);
        changeCredentialCommand.setBuilder(this.builder);
        changeCredentialCommand.doAction();
        Assert.assertEquals("The secret key is successfully updated.",
                changeCredentialCommand.getMessages().get(0));
    }

}
