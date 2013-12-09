package de.fhb.systemintegration.wikipedics.business.builder;

import de.fhb.systemintegration.wikipedics.business.inter.CredentialViewer;
import de.fhb.systemintegration.wikipedics.business.inter.ServerInstanceViewer;
import de.fhb.systemintegration.wikipedics.business.inter.impl.
        CredentialViewerImpl;
import de.fhb.systemintegration.wikipedics.business.inter.impl.
        ServerInstanceViewerImpl;

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
     * This method builds an instance of the CredentialViewer.
     * @return the created instance
     */
    public static CredentialViewer getCredentialViewer() {
        return new CredentialViewerImpl();
    }

    /**
     * This method builds an instance of the ServerInstanceViewer.
     * @return the created instance
     */
    public static ServerInstanceViewer getServerInstanceViewer() {
        return new ServerInstanceViewerImpl();
    }
}
