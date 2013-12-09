package de.fhb.systemintegration.wikipedics.business.builder;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialManager;
import de.fhb.systemintegration.wikipedics.business.inter.EC2Manager;
import de.fhb.systemintegration.wikipedics.business.inter.ServerInstanceManager;
import de.fhb.systemintegration.wikipedics.business.inter.impl.
        CredentialManagerImpl;
import de.fhb.systemintegration.wikipedics.business.inter.impl.EC2ManagerImpl;
import de.fhb.systemintegration.wikipedics.business.inter.impl.
        ServerInstanceManagerImpl;

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
     * This method builds an instance of the CredentialManager.
     * @return the created instance
     */
    public static CredentialManager getCredentialManager() {
        return new CredentialManagerImpl();
    }

    /**
     * This method creates an instance of the ec2-manager.
     * @param credentials the aws credentials
     * @param config the client configuration
     * @param region the used region
     * @return the created instance
     */
    public static EC2Manager getEC2Manager(
            final AWSCredentials credentials,
            final ClientConfiguration config, final String region) {
        return new EC2ManagerImpl(credentials, config, region);
    }

    /**
     * This method builds an instance of the ServerInstanceManager.
     * @return the created instance
     */
    public static ServerInstanceManager getServerInstanceManager() {
        return new ServerInstanceManagerImpl();
    }

}
