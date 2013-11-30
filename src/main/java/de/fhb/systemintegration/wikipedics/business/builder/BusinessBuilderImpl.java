package de.fhb.systemintegration.wikipedics.business.builder;

import de.fhb.systemintegration.wikipedics.business.inter.CredentialManager;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialViewer;

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

    /**
     * This method builds an instance of the credential manager.
     * @return the created instance
     */
    @Override
    public CredentialManager getCredentialManager() {
        return BusinessManagerBuilder.getCredentialManager();
    }

    /**
     * This method builds an instance of the credential viewer.
     * @return the created instance
     */
    @Override
    public CredentialViewer getCredentialViewer() {
        return BusinessViewerBuilder.getCredentialViewer();
    }
}
