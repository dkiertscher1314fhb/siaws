package de.fhb.systemintegration.wikipedics.business.inter.impl;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesResult;
import de.fhb.systemintegration.wikipedics.business.inter.EC2Manager;
import de.fhb.systemintegration.wikipedics.dao.inter.EC2DAO;

/**
 * This class is the implementation of the EC2Manager interface.
 */
public final class EC2ManagerImpl extends GenericManager implements EC2Manager {

    /**
     * This is the dao instance.
     */
    private final EC2DAO ec2DAO;

    /**
     * This is the initialisation constructor of the class.
     * @param credentials the amazon aws credentials
     * @param config the client configuration
     * @param region the used region
     */
    public EC2ManagerImpl(final AWSCredentials credentials,
                          final ClientConfiguration config,
                          final String region) {
        super();
        this.ec2DAO = this.getEC2DAO(credentials, config, region);
    }

    @Override
    public DescribeImagesResult describeImages(
            final DescribeImagesRequest imageRequest) {
        return this.ec2DAO.describeImages(imageRequest);
    }

    @Override
    public RunInstancesResult startInstance(
            final RunInstancesRequest instanceToStart) {
        return this.ec2DAO.startInstance(instanceToStart);
    }

    @Override
    public TerminateInstancesResult terminateInstance(
            final TerminateInstancesRequest instanceToTerminate) {
        return this.ec2DAO.terminateInstance(instanceToTerminate);
    }

    @Override
    public DescribeInstancesResult describeInstance(
            final DescribeInstancesRequest describeInstance) {
        return this.ec2DAO.describeInstance(describeInstance);
    }
}
