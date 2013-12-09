package de.fhb.systemintegration.wikipedics.dao.inter.impl;

import de.fhb.systemintegration.wikipedics.dao.inter.ServerInstanceDAO;
import de.fhb.systemintegration.wikipedics.domain.ServerInstance;
import de.fhb.systemintegration.wikipedics.domain.ServerType;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * This class implements the ServerInstanceDAO interface.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class ServerInstanceImpl
        extends GenericDAOImpl<ServerInstance, Long>
        implements ServerInstanceDAO {

    @Override
    public List<ServerInstance> findByType(final ServerType type) {
        Map<String, ServerType> queryParams = new HashMap<>();
        queryParams.put("type", type);
        List<ServerInstance> instanceList = new ArrayList<>();
        instanceList.addAll(this.findByNamedQuery("findInstancesByType",
                queryParams));
        return instanceList;
    }
}
