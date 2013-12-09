package de.fhb.systemintegration.wikipedics.dao.builder;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import de.fhb.systemintegration.wikipedics.dao.inter.EC2DAO;
import de.fhb.systemintegration.wikipedics.dao.inter.ServerInstanceDAO;
import de.fhb.systemintegration.wikipedics.dao.inter.UserSettingDAO;
import de.fhb.systemintegration.wikipedics.dao.inter.impl.EC2DAOImpl;
import de.fhb.systemintegration.wikipedics.dao.inter.impl.ServerInstanceImpl;
import de.fhb.systemintegration.wikipedics.dao.inter.impl.UserSettingDAOImpl;

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
     * This method builds an instance of the UserSettingDAO.
     * @return the UserSettingDAO
     */
    public UserSettingDAO getUserSettingDAO() {
        return new UserSettingDAOImpl();
    }

    /**
     * This method create an instance of the EC2DAO interface.
     * @param credentials the aws credentials
     * @param config the http configuration
     * @param region the region to use
     * @return the created instance
     */
    public EC2DAO getEC2DAO(final AWSCredentials credentials,
                            final ClientConfiguration config,
                            final String region) {
        return new EC2DAOImpl(credentials, config, region);
    }

    /**
     * This method builds an instance of the ServerInstanceDAO.
     * @return the ServerInstanceDAO
     */
    public ServerInstanceDAO getServerInstanceDAO() {
        return new ServerInstanceImpl();
    }

}
