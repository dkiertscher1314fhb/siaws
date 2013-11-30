package de.fhb.systemintegration.wikipedics.business.inter.impl;

import de.fhb.systemintegration.wikipedics.business.inter.CredentialManager;
import de.fhb.systemintegration.wikipedics.domain.UserSettings;
import de.fhb.systemintegration.wikipedics.dao.inter.UserSettingsDAO;

/**
 * This class implements the CredentialManager interface.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class CredentialManagerImpl extends  GenericManager
        implements CredentialManager {

    /**
     * This is the interface to access the dao-layer.
     */
    private final UserSettingsDAO userSettingsDAO;

    /**
     * This is the default constructor.
     */
    public CredentialManagerImpl() {
        super();
        this.userSettingsDAO = this.getBuilder().getUserSettingsDAO();
    }

    @Override
    public UserSettings saveCredentials(final UserSettings settings) {
        return this.userSettingsDAO.save(settings);
    }

    @Override
    public UserSettings updateCredential(final UserSettings settings) {
        return this.userSettingsDAO.update(settings);
    }
}
