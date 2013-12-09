package de.fhb.systemintegration.wikipedics.business.inter;

import de.fhb.systemintegration.wikipedics.domain.ServerInstance;
import de.fhb.systemintegration.wikipedics.domain.ServerType;

import java.util.List;

/**
 * This interface provides the method to read the ServerInstances.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public interface ServerInstanceViewer {

    /**
     * This method returns the list of ServerInstances.
     * @return the full list of instances.
     */
    List<ServerInstance> findAll();

    /**
     * This method returns all the instance for which have the same type.
     * @param type the server type
     * @return the list of instances
     */
    List<ServerInstance> findByType(final ServerType type);
}
