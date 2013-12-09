package de.fhb.systemintegration.wikipedics.dao.inter;

import de.fhb.systemintegration.wikipedics.domain.ServerInstance;
import de.fhb.systemintegration.wikipedics.domain.ServerType;
import java.util.List;

/**
 *    This is the data access interface for the ServerInstance object.
 */
public interface ServerInstanceDAO extends GenericDAO<ServerInstance, Long> {

    /**
     * This method returns all the instance for which have the same type.
     * @param type the server type
     * @return the list of instances
     */
    List<ServerInstance> findByType(final ServerType type);
}
