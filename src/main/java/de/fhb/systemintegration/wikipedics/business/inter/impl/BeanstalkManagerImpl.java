package de.fhb.systemintegration.wikipedics.business.inter.impl;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationRequest;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationResult;
import de.fhb.systemintegration.wikipedics.business.inter.BeanstalkManager;
import de.fhb.systemintegration.wikipedics.dao.inter.BeanstalkDAO;

/**
 * This class implements the BeanstalkManager interface.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class BeanstalkManagerImpl extends GenericManager
        implements BeanstalkManager {

    /**
     * This is the interface to access the dao-layer.
     */
    private final BeanstalkDAO beanstalkDAO;

    /**
     * This is the initialisation constructor.
     * @param credentials the amazon credentials
     * @param config the client configuration
     * @param region the region where the application should be deployed
     */
    public BeanstalkManagerImpl(final AWSCredentials credentials,
                                final ClientConfiguration config,
                                final String region) {
        super();
        this.beanstalkDAO = this.getBuilder().
                getBeanstalkDAO(credentials, config, region);
    }

    @Override
    public CreateApplicationResult createApplication(
            final CreateApplicationRequest request) {
        return this.beanstalkDAO.createApplication(request);
    }
}
