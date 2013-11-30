package de.fhb.systemintegration.wikipedics.business.inter;

import de.fhb.systemintegration.wikipedics.domain.UserSettings;

/**
 * This interface provide the methods to read the credentials.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public interface CredentialViewer {

    /**
     * This method return the actual user form the database.
     * @param userId the id of the user
     * @return the actual user object
     */
    UserSettings findById(final Long userId);
}
