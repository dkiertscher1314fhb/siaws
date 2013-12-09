package de.fhb.systemintegration.wikipedics.business.inter;

import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesResult;

/**
 * This interface handles the ec2 requests.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public interface EC2Manager {


    /**
     * This method list the available images.
     * @param imageRequest the image request.
     * @return the images
     */
    DescribeImagesResult describeImages(
            final DescribeImagesRequest imageRequest);

    /**
     * This method starts an ec2-instance.
     * @param instanceToStart the ec2-instance to start
     * @return the started ec2-instance
     */
    RunInstancesResult startInstance(
            final RunInstancesRequest instanceToStart);

    /**
     * This method terminates running ec2-instance.
     * @param instanceToTerminate the ec2-instance to stop
     * @return the stoped instances
     */
    TerminateInstancesResult terminateInstance(
            final TerminateInstancesRequest instanceToTerminate);

    /**
     * This method describe the running instance.
     * @param describeInstance the ec2 instance to describe
     * @return the describtion of the ec2 instance
     */
    DescribeInstancesResult describeInstance(
            final DescribeInstancesRequest describeInstance);

}
