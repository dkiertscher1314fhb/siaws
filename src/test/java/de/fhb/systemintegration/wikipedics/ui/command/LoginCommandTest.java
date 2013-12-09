package de.fhb.systemintegration.wikipedics.ui.command;

import de.fhb.systemintegration.wikipedics.business.builder.BusinessBuilder;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialManager;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialViewer;
import de.fhb.systemintegration.wikipedics.domain.UserSetting;
import de.fhb.systemintegration.wikipedics.util.Config;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a test case for the LoginCommand.
 */
public final class LoginCommandTest {

    /**
     * This is the option map which needed for the tests.
     */
    private Map<String, String> options;

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
     * This method setups the change credential command test.
     */
    @Before
    public void setUp() {
        Config.setId(-1L);
        this.options = new HashMap<>();
        this.manager = Mockito.mock(CredentialManager.class);
        this.viewer = Mockito.mock(CredentialViewer.class);
        this.builder = Mockito.mock(BusinessBuilder.class);
        Mockito.when(this.builder.getCredentialManager()).
                thenReturn(this.manager);
        Mockito.when(this.builder.getCredentialViewer()).
                thenReturn(this.viewer);
    }

    /**
     * This method checks that nobody can login without
     * to give an aws access key.
     */
    @Test
    public void checkNoLoginWithoutAccesskey() {
        this.options.put("secretkey", "1234567890");
        this.options.put("region", "EU-WEST-1");
        this.options.put("keyname", "wikipedics");
        LoginCommand loginCommand = new LoginCommand(this.options);
        loginCommand.setBuilder(this.builder);
        loginCommand.doAction();
        Assert.assertEquals("You give no access key.",
                loginCommand.getMessages().get(0));
    }

    /**
     * This method checks that nobody can login without
     * to give an aws secret key.
     */
    @Test
    public void checkNoLoginWithoutSecretkey() {
        this.options.put("accesskey", "1234567890");
        this.options.put("region", "EU-WEST-1");
        this.options.put("keyname", "wikipedics");
        LoginCommand loginCommand = new LoginCommand(this.options);
        loginCommand.setBuilder(this.builder);
        loginCommand.doAction();
        Assert.assertEquals("You give no secret key.",
                loginCommand.getMessages().get(0));
    }

    /**
     * This method checks that nobody can login without
     * to give an aws key name.
     */
    @Test
    public void checkNoLoginWithoutKeyName() {
        this.options.put("accesskey", "1234567890");
        this.options.put("region", "EU-WEST-1");
        this.options.put("secretkey", "123456ABC");
        LoginCommand loginCommand = new LoginCommand(this.options);
        loginCommand.setBuilder(this.builder);
        loginCommand.doAction();
        Assert.assertEquals("You give no ssh key name.",
                loginCommand.getMessages().get(0));
    }

    /**
     * This method checks that nobody can login without
     * to give an aws region.
     */
    @Test
    public void checkNoLoginWithoutRegion() {
        this.options.put("accesskey", "1234567890");
        this.options.put("keyname", "wikipedics");
        this.options.put("secretkey", "123456ABC");
        LoginCommand loginCommand = new LoginCommand(this.options);
        loginCommand.setBuilder(this.builder);
        loginCommand.doAction();
        Assert.assertEquals("You give no region.",
                loginCommand.getMessages().get(0));
    }

    /**
     * This method checks that nobody can make a double login.
     */
    @Test
    public void checkNoDoubleLogin() {
        Config.setId(1L);
        this.options.put("region", "EU-WEST-1");
        this.options.put("accesskey", "1234567890");
        this.options.put("keyname", "wikipedics");
        this.options.put("secretkey", "123456ABC");
        LoginCommand loginCommand = new LoginCommand(this.options);
        loginCommand.setBuilder(this.builder);
        loginCommand.doAction();
        Assert.assertEquals("You are successfully login, please use "
                + "the changeCredentials-Command to change any Credentials.",
                loginCommand.getMessages().get(0));
    }

    /**
     * This method checks that are user can login successfully.
     */
    @Test
    public void checkSuccessfullyLogin() {
        final UserSetting setting = new UserSetting();
        setting.setAccesskey("1234567890ABCDEFGHIJ");
        setting.setKeyname("wikipedics");
        setting.setRegion("EU-WEST-1");
        setting.setSecretkey("AABBCCDDEEFFGGHHIIJJ"
                + "11223344556677889900");
        final UserSetting saveUser = new UserSetting();
        saveUser.setAccesskey("1234567890ABCDEFGHIJ");
        saveUser.setKeyname("wikipedics");
        saveUser.setRegion("EU-WEST-1");
        saveUser.setSecretkey("AABBCCDDEEFFGGHHIIJJ"
                + "11223344556677889900");
        saveUser.setId(1L);
        Mockito.when(this.manager.saveCredentials((UserSetting) Mockito.any()))
                .thenReturn(saveUser);
        this.options.put("region", "EU-WEST-1");
        this.options.put("accesskey", "1234567890ABCDEFGHIJ");
        this.options.put("keyname", "wikipedics");
        this.options.put("secretkey", "AABBCCDDEEFFGGHHIIJJ"
                + "11223344556677889900");
        LoginCommand loginCommand = new LoginCommand(this.options);
        loginCommand.setBuilder(this.builder);
        loginCommand.doAction();
        Assert.assertEquals("You are successfully login, your credentials "
                + "are stored successfully.",
                loginCommand.getMessages().get(0));
    }


}
