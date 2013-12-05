package de.fhb.systemintegration.wikipedics.business.builder;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import de.fhb.systemintegration.wikipedics.business.inter.BeanstalkManager;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialManager;
import de.fhb.systemintegration.wikipedics.business.inter.impl.
        BeanstalkManagerImpl;
import de.fhb.systemintegration.wikipedics.business.inter.impl.
        CredentialManagerImpl;

/**
 * This is an utility class which connect the manager interfaces with the
 * implementation classes.
 *
 * @author mlelansky
 * @version 0.0.1
 */
final class BusinessManagerBuilder {

    /**
     * This is the default constructor.
     */
    private BusinessManagerBuilder() {
        super();
    }

    /**
     * This method builds an instance of the credential manager.
     * @return the created instance
     */
    public static CredentialManager getCredentialManager() {
        return new CredentialManagerImpl();
    }

    /**
     * This method builds an instance of the beanstalk manager.
     * @param credentials the amazon credentials
     * @param config the client configuration
     * @param region the region where the application should be deployed
     * @return the created instance
     */
    public static BeanstalkManager getBeanstalkManager(
            final AWSCredentials credentials, final ClientConfiguration config,
            final String region) {
        return new BeanstalkManagerImpl(credentials, config, region);
    }
}
