package de.fhb.systemintegration.wikipedics.business.builder;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialManager;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialViewer;
import de.fhb.systemintegration.wikipedics.business.inter.EC2Manager;
import de.fhb.systemintegration.wikipedics.business.inter.ServerInstanceManager;
import de.fhb.systemintegration.wikipedics.business.inter.ServerInstanceViewer;

/**
 * This is a facade interface which returns
 * the possible manager and viewer interfaces.
 */
public interface BusinessBuilder {

    /**
     * This method returns an instance of the CredentialManager.
     * @return the credential manager interface
     */
    CredentialManager getCredentialManager();

    /**
     * This method returns an instance of the CredentialViewer.
     * @return the credential viewer interface
     */
    CredentialViewer getCredentialViewer();

    /**
     * This method returns an instance of the ec2 manager.
     * @param credentials the used amazon credentials
     * @param config the client configuration
     * @param region the used region
     * @return the ec2 manager interface
     */
    EC2Manager getEC2Manager(final AWSCredentials credentials,
                             final ClientConfiguration config,
                             final String region);

    /**
     * This method returns an instance of the ServerInstanceManager.
     * @return the server instance manager
     */
    ServerInstanceManager getServerInstanceManager();

    /**
     * This method returns an instance of the ServerInstanceViewer.
     * @return the server instance viewer
     */
    ServerInstanceViewer getServerInstanceViewer();

}
