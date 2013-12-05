package de.fhb.systemintegration.wikipedics.business.inter;

import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationRequest;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationResult;

/**
 * This interface provides the method to controls the elastic beanstalk.
 */
public interface BeanstalkManager {

    /**
     * This method creates an ElasticBeanstalkApplication.
     * @param request the ApplicationRequest to handle
     * @return the created response
     */
    CreateApplicationResult createApplication(
            final CreateApplicationRequest request);


}
