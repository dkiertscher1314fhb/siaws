package de.fhb.systemintegration.wikipedics.business.builder;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialManager;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialViewer;
import de.fhb.systemintegration.wikipedics.business.inter.EC2Manager;
import de.fhb.systemintegration.wikipedics.business.inter.ServerInstanceManager;
import de.fhb.systemintegration.wikipedics.business.inter.ServerInstanceViewer;

/**
 * This is a utility class which provides the viewer and manager classes.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class BusinessBuilderImpl implements BusinessBuilder {

    /**
     * This is the default constructor.
     */
    public BusinessBuilderImpl() {
        super();
    }


    @Override
    public CredentialManager getCredentialManager() {
        return BusinessManagerBuilder.getCredentialManager();
    }

    @Override
    public CredentialViewer getCredentialViewer() {
        return BusinessViewerBuilder.getCredentialViewer();
    }

    @Override
    public EC2Manager getEC2Manager(final AWSCredentials credentials,
                                    final ClientConfiguration config,
                                    final String region) {
        return BusinessManagerBuilder.getEC2Manager(credentials, config,
                region);
    }

    @Override
    public ServerInstanceManager getServerInstanceManager() {
        return BusinessManagerBuilder.getServerInstanceManager();
    }

    @Override
    public ServerInstanceViewer getServerInstanceViewer() {
        return BusinessViewerBuilder.getServerInstanceViewer();
    }

}
