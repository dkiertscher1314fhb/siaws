package de.fhb.systemintegration.wikipedics.dao.inter.impl;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesResult;
import de.fhb.systemintegration.wikipedics.dao.inter.EC2DAO;

/**
 * This is the implementation of the EC2DAO interface.
 */
public final class EC2DAOImpl implements EC2DAO {

    /**
     * This is the used ec2-client.
     */
    private final AmazonEC2Client client;

    /**
     * This is the initialisation constructor.
     * @param credentials the amazon credentials
     * @param config the client configuration
     * @param region to use
     */
    public EC2DAOImpl(final AWSCredentials credentials,
                      final ClientConfiguration config, final String region) {
        this.client = new AmazonEC2Client(credentials, config);
        this.client.setEndpoint(region);
    }

    @Override
    public DescribeImagesResult describeImages(
            final DescribeImagesRequest imageRequest) {
        return client.describeImages(imageRequest);
    }

    @Override
    public RunInstancesResult startInstance(
            final RunInstancesRequest instanceToStart) {
        return client.runInstances(instanceToStart);
    }

    @Override
    public TerminateInstancesResult terminateInstance(
            final TerminateInstancesRequest instanceToTerminate) {
        return client.terminateInstances(instanceToTerminate);
    }

    @Override
    public DescribeInstancesResult describeInstance(
            final DescribeInstancesRequest describeInstance) {
        return client.describeInstances(describeInstance);
    }
}
