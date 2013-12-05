package de.fhb.systemintegration.wikipedics.dao.builder;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import de.fhb.systemintegration.wikipedics.dao.inter.BeanstalkDAO;
import de.fhb.systemintegration.wikipedics.dao.inter.UserSettingsDAO;
import de.fhb.systemintegration.wikipedics.dao.inter.impl.BeanstalkDAOImpl;
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

    /**
     * This method builds an instance of the BeanstalkDAO.
     * @param credentials the amazon aws credentials
     * @param config the client configuration
     * @param region the region where the app should be deployed
     * @return the BeanstalkDAO
     */
    public BeanstalkDAO getBeanstalkDAO(final AWSCredentials credentials,
                                        final ClientConfiguration config,
                                        final String region) {
        return new BeanstalkDAOImpl(credentials, config, region);
    }
}
