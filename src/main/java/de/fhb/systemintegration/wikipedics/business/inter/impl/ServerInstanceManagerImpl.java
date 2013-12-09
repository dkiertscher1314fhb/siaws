package de.fhb.systemintegration.wikipedics.business.inter.impl;

import de.fhb.systemintegration.wikipedics.business.inter.ServerInstanceManager;
import de.fhb.systemintegration.wikipedics.dao.inter.ServerInstanceDAO;
import de.fhb.systemintegration.wikipedics.domain.ServerInstance;

/**
 * This class is the implementation of the ServerInstanceManager interface.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class ServerInstanceManagerImpl extends GenericManager
        implements ServerInstanceManager {

    /**
     * This is the interface to access the dao layer.
     */
    private final ServerInstanceDAO serverInstanceDAO;

    /**
     * This is the default constructor.
     */
    public ServerInstanceManagerImpl() {
        super();
        this.serverInstanceDAO = this.getServerInstanceDAO();
    }


    @Override
    public ServerInstance saveInstance(final ServerInstance instance) {
        return this.serverInstanceDAO.save(instance);
    }

    @Override
    public ServerInstance updateInstance(final ServerInstance instance) {
        return this.serverInstanceDAO.update(instance);
    }
}
