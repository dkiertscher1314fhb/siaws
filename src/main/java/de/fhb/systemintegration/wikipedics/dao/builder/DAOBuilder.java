package de.fhb.systemintegration.wikipedics.dao.builder;

import de.fhb.systemintegration.wikipedics.dao.inter.UserSettingsDAO;
import de.fhb.systemintegration.wikipedics.dao.inter.impl.UserSettingsDAOImpl;

/**
 * This is a utility class which provides the dao interfaces
 * and implementation classes.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class DAOBuilder {

    /**
     * This is the default constructor.
     */
    public DAOBuilder() {
        super();
    }

    /**
     * This method builds an instance of the UserSettingsDAO.
     * @return the UserSettingsDAO
     */
    public UserSettingsDAO getUserSettingsDAO() {
        return new UserSettingsDAOImpl();
    }
}
