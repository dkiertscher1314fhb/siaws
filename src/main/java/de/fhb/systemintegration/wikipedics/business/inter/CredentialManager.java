package de.fhb.systemintegration.wikipedics.business.inter;

import de.fhb.systemintegration.wikipedics.domain.UserSetting;

/**
 * This interface provide the methods to change the credentials.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public interface CredentialManager {

    /**
     * This method stores the amazon user settings.
     * @param settings the new user settings
     * @return the successfully saved instance or an empty object
     */
    UserSetting saveCredentials(final UserSetting settings);

    /**
     * This method update the amazon user settings.
     * @param settings the new user settings
     * @return the successfuly updated object or an empty object
     */
    UserSetting updateCredential(final UserSetting settings);
}
