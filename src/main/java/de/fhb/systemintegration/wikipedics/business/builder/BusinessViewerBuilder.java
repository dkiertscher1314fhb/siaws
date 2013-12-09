package de.fhb.systemintegration.wikipedics.business.builder;

import de.fhb.systemintegration.wikipedics.business.inter.CredentialViewer;
import de.fhb.systemintegration.wikipedics.business.inter.impl.
        CredentialViewerImpl;

/**
 * This is an utility class which connect the viewer interfaces with the
 * implementation classes.
 *
 * @author mlelansky
 * @version 0.0.1
 */
final class BusinessViewerBuilder {

    /**
     * This is the default constructor.
     */
    private BusinessViewerBuilder() {
        super();
    }

    /**
     * This method builds an instance of the credential viewer.
     * @return the created instance
     */
    public static CredentialViewer getCredentialViewer() {
        return new CredentialViewerImpl();
    }
}
