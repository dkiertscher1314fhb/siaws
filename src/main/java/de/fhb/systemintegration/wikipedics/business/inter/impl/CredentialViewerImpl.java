package de.fhb.systemintegration.wikipedics.business.inter.impl;

import de.fhb.systemintegration.wikipedics.business.inter.CredentialViewer;
import de.fhb.systemintegration.wikipedics.dao.inter.UserSettingDAO;
import de.fhb.systemintegration.wikipedics.domain.UserSetting;

/**
 * This class implements the CredentialViewer interface.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class CredentialViewerImpl extends  GenericManager
        implements CredentialViewer {

    /**
     * This is the interface to access the dao layer.
     */
    private final UserSettingDAO userSettingDAO;

    /**
     * This is the default constructor.
     */
    public CredentialViewerImpl() {
        super();
        this.userSettingDAO = this.getUserSettingDAO();
    }

    @Override
    public UserSetting findById(final Long userId) {
        return this.userSettingDAO.findById(userId);
    }
}
