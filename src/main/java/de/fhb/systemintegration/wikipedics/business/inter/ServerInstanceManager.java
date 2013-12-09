package de.fhb.systemintegration.wikipedics.business.inter;

import de.fhb.systemintegration.wikipedics.domain.ServerInstance;

/**
 * This interface provides the methods to store the ServerInstances.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public interface ServerInstanceManager {

    /**
     * This method stores the new amazon aws instance.
     * @param instance the aws instance
     * @return the successfully saved instance or an empty object
     */
    ServerInstance saveInstance(final ServerInstance instance);

    /**
     * This method update the amazon aws instance.
     * @param instance the instance to update
     * @return the successfully updated object or an empty object
     */
    ServerInstance updateInstance(final ServerInstance instance);
}
