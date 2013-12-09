package de.fhb.systemintegration.wikipedics.ui.command;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import de.fhb.systemintegration.wikipedics.business.builder.BusinessBuilder;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialManager;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialViewer;
import de.fhb.systemintegration.wikipedics.domain.UserSetting;
import de.fhb.systemintegration.wikipedics.util.Config;
import org.junit.Before;
import org.mockito.Mockito;

import java.util.Map;
import java.util.HashMap;

/**
 * This is an abstract test case class for some command test.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public abstract class AbstractCommandTest {

    /**
     * This is the credential viewer mock.
     */
    private CredentialViewer credentialViewerMock;
    /**
     * This is the credential manager mock.
     */
    private CredentialManager credentialManagerMock;

    /**
     * This is the mock of the interface builder.
     */
    private BusinessBuilder builderMock;
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
    public final void setUp() {
        Config.setId(1L);
        this.options = new HashMap<>();
        this.settings = new UserSetting();
        settings.setAccesskey("1122334455667788990011223344556677889900");
        settings.setSecretkey("123456");
        settings.setRegion("EU-WEST-1");
        settings.setKeyname("wikipedics");
        this.credentialManagerMock = Mockito.mock(CredentialManager.class);
        this.credentialViewerMock = Mockito.mock(CredentialViewer.class);
        this.builderMock = Mockito.mock(BusinessBuilder.class);
        Mockito.when(this.builderMock.getCredentialManager()).
                thenReturn(this.credentialManagerMock);
        Mockito.when(this.builderMock.getCredentialViewer()).
                thenReturn(this.credentialViewerMock);
        AWSCredentials credentials = Mockito.mock(AWSCredentials.class);
        ClientConfiguration config = Mockito.mock(ClientConfiguration.class);
        Mockito.when(this.credentialViewerMock.findById(1L)).
                thenReturn(this.settings);
    }

    /**
     * This method returns the mock instance of the CredentialViewer.
     * @return the mock instance
     */
    protected final CredentialViewer getViewerMock() {
        return this.credentialViewerMock;
    }

    /**
     * This method returns the mock instance of the CredentialManager.
     * @return the mock instance
     */
    protected final CredentialManager getCredentialManagerMock() {
        return this.credentialManagerMock;
    }

    /**
     * This method returns the mock instance of the BusinessBuilder.
     * @return the mock instance
     */
    protected final BusinessBuilder getBuilderMock() {
        return this.builderMock;
    }

    /**
     * This method returns the actual user instance.
     * @return the user instance
     */
    protected final UserSetting getSettings() {
        return this.settings;
    }

    /**
     * This method returns the actual command option map.
     * @return the option map
     */
    protected final Map<String, String> getOptions() {
        return this.options;
    }

}
