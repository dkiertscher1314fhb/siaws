package de.fhb.systemintegration.wikipedics.business.inter.impl;

import de.fhb.systemintegration.wikipedics.business.inter.CredentialManager;
import de.fhb.systemintegration.wikipedics.domain.UserSetting;
import de.fhb.systemintegration.wikipedics.dao.inter.UserSettingDAO;

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
    private final UserSettingDAO userSettingDAO;

    /**
     * This is the default constructor.
     */
    public CredentialManagerImpl() {
        super();
        this.userSettingDAO = this.getUserSettingDAO();
    }

    @Override
    public UserSetting saveCredentials(final UserSetting settings) {
        return this.userSettingDAO.save(settings);
    }

    @Override
    public UserSetting updateCredential(final UserSetting settings) {
        return this.userSettingDAO.update(settings);
    }
}
