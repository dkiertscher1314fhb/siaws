package de.fhb.systemintegration.wikipedics.dao.inter.impl;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationRequest;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationResult;
import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalkClient;
import de.fhb.systemintegration.wikipedics.dao.inter.BeanstalkDAO;

/**
 * This is the implementation of the BeanstalkDAO interface.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class BeanstalkDAOImpl implements BeanstalkDAO {

    /**
     * This is the client to connect to amazon.
     */
    private final AWSElasticBeanstalkClient client;

    /**
     * This is the initialisation constructor.
     * @param credentials the amazon credentials
     * @param config the client configuration
     * @param region the region where the application should be deployed
     */
    public BeanstalkDAOImpl(final AWSCredentials credentials,
                            final ClientConfiguration config,
                            final String region) {
        super();
        this.client = new AWSElasticBeanstalkClient(credentials, config);
        this.client.setEndpoint(region);
    }

    @Override
    public CreateApplicationResult createApplication(
            final CreateApplicationRequest request) {
        return this.client.createApplication(request);
    }


}
