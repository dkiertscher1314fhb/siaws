package de.fhb.systemintegration.wikipedics.business.builder;

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
}
