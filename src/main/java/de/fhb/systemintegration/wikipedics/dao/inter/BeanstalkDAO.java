package de.fhb.systemintegration.wikipedics.dao.inter;

import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationRequest;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationResult;

/**
 * This interface provides the methods to deploy the application
 * to amazon.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public interface BeanstalkDAO {

    /**
     * This method creates an ElasticBeanstalkApplication.
     * @param request the ApplicationRequest to handle
     * @return the created response
     */
    CreateApplicationResult createApplication(
            final CreateApplicationRequest request);
}
