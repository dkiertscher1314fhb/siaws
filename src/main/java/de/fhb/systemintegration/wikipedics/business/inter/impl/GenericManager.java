package de.fhb.systemintegration.wikipedics.business.inter.impl;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import de.fhb.systemintegration.wikipedics.dao.builder.DAOBuilder;
import de.fhb.systemintegration.wikipedics.dao.inter.ServerInstanceDAO;
import de.fhb.systemintegration.wikipedics.dao.inter.UserSettingDAO;
import de.fhb.systemintegration.wikipedics.dao.inter.EC2DAO;

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
     * This method returns an instance of the UserSettingDAO.
     * @return the instance of the UserSettingDAO
     */
    protected final UserSettingDAO getUserSettingDAO() {
        return this.builder.getUserSettingDAO();
    }

    /**
     * This method returns an instance of the Ec2DAO.
     * @param credentials the amazon aws credentials
     * @param config the client configuration
     * @param region the amazon aws region
     * @return the instance of the EC2DAO
     */
    protected final EC2DAO getEC2DAO(final AWSCredentials credentials,
                                     final ClientConfiguration config,
                                     final String region) {
        return this.builder.getEC2DAO(credentials, config, region);
    }

    /**
     * This method returns an instance of the ServerInstanceDAO.
     * @return the instance of the ServerInstanceDAO
     */
    protected final ServerInstanceDAO getServerInstanceDAO() {
        return this.builder.getServerInstanceDAO();
    }
}
