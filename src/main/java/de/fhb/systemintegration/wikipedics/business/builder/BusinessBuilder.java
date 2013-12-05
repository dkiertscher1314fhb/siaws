package de.fhb.systemintegration.wikipedics.business.builder;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import de.fhb.systemintegration.wikipedics.business.inter.BeanstalkManager;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialManager;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialViewer;

/**
 * This is a facade interface which returns
 * the possible manager and viewer interfaces.
 */
public interface BusinessBuilder {

    /**
     * This method return the an instance of the credential manager.
     * @return the credential manager interface
     */
    CredentialManager getCredentialManager();

    /**
     * This method returns an instance of the credential viewer.
     * @return the credential viewer interface
     */
    CredentialViewer getCredentialViewer();

    /**
     * This method returns an instance of the beanstalk manager.
     * @param credentials the amazon credentials
     * @param config the client configuration
     * @param region the region where the application should be deployed
     * @return the beanstalk manager interface
     */
    BeanstalkManager getBeanstalkManager(final AWSCredentials credentials,
                                         final ClientConfiguration config,
                                         final String region);
}
