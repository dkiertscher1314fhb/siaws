package de.fhb.systemintegration.wikipedics.business.inter.impl;

import de.fhb.systemintegration.wikipedics.dao.builder.DAOBuilder;

/**
 * This class is an abstract manager class.
 *
 * @author mlelansky
 * @version 0.0.1
 */
abstract class GenericManager {

    /**
     * This object is the connection to the dao layer.
     */
    private final DAOBuilder builder;

    /**
     * This is the default constructor of the class.
     */
    public GenericManager() {
        this.builder = new DAOBuilder();
    }

    /**
     * This method return the actual DAOBuilder instance.
     * @return the actual object
     */
    protected DAOBuilder getBuilder() {
        return this.builder;
    }
}
