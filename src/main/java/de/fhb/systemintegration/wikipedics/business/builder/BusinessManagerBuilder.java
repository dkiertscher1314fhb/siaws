package de.fhb.systemintegration.wikipedics.business.builder;

import de.fhb.systemintegration.wikipedics.business.inter.CredentialManager;
import de.fhb.systemintegration.wikipedics.business.inter.
        impl.CredentialManagerImpl;

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
}
