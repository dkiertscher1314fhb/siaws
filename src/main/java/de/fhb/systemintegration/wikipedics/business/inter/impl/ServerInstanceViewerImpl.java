package de.fhb.systemintegration.wikipedics.business.inter.impl;

import de.fhb.systemintegration.wikipedics.business.inter.ServerInstanceViewer;
import de.fhb.systemintegration.wikipedics.dao.inter.ServerInstanceDAO;
import de.fhb.systemintegration.wikipedics.domain.ServerInstance;
import de.fhb.systemintegration.wikipedics.domain.ServerType;

import java.util.List;

/**
 * This is the implementation of the ServerInstanceViewer interface.
 */
public final class ServerInstanceViewerImpl extends GenericManager
        implements ServerInstanceViewer {

    /**
     * This is the interface to access the dao layer.
     */
    private final ServerInstanceDAO serverInstanceDAO;

    /**
     * This is the default constructor of the class.
     */
    public ServerInstanceViewerImpl() {
        super();
        this.serverInstanceDAO = this.getServerInstanceDAO();
    }

    @Override
    public List<ServerInstance> findAll() {
        return this.serverInstanceDAO.findAll();
    }

    @Override
    public List<ServerInstance> findByType(final ServerType type) {
        return this.serverInstanceDAO.findByType(type);
    }
}
