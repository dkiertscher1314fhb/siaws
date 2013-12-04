package de.fhb.systemintegration.wikipedics.util;

/**
 * This is an interface to handle provides the method to
 * handle a properties file.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public interface ApplicationPropertiesHandler {

    /**
     * This method return the object to the specific key.
     * @param key the properties key
     * @return the object name
     */
    String getObject(final String key);

    /**
     * This method return the size of the properties.
     * @return the counter of the properties
     */
    int size();
}
