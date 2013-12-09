package de.fhb.systemintegration.wikipedics.ui.command;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialViewer;
import de.fhb.systemintegration.wikipedics.business.inter.EC2Manager;
import de.fhb.systemintegration.wikipedics.business.inter.ServerInstanceManager;
import de.fhb.systemintegration.wikipedics.domain.ServerInstance;
import de.fhb.systemintegration.wikipedics.domain.ServerType;
import de.fhb.systemintegration.wikipedics.domain.UserSetting;
import de.fhb.systemintegration.wikipedics.util.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This is the command which deploys the wikipedics
 * application to the amazon aws cloud.
 *
 * @author mlelansky
 * @version 0.0.1
 */
class DeployCommand extends AbstractCommand {

    /**
     * This is the initialisation constructor of the command.
     * @param _options the actual options of the command
     */
    public DeployCommand(final Map<String, String> _options) {
        super("DeployApplication",
                "This command deploys the application to amazon.", _options);
    }

    @Override
    protected final boolean checkOptions() {
        boolean status = false;
        final Long userId = Config.getId();
        if (userId != null && userId > 0) {
            status = true;
        } else {
            this.getMessages().add("No User Information are found.");
        }
        return status;
    }

    @Override
    protected final void action() {
        final CredentialViewer viewer =  this.getBuilder().
                getCredentialViewer();
        final UserSetting user = viewer.findById(Config.getId());
        final AWSCredentials credentials = new BasicAWSCredentials(
                user.getAccesskey(), user.getSecretkey());
        final ClientConfiguration config = new ClientConfiguration();
        final EC2Manager ec2Manager = this.getBuilder().getEC2Manager(
                credentials, config, getRegion(user.getRegion()));
        final List<Image> images = getImageResult(ec2Manager);
        startNEO4JInstance(ec2Manager, images.get(0), "wikipedics", null);

    }

    /**
     * This method starts the neo4J-ec2-instance.
     * @param ec2manager the ec2-manager instance
     * @param serverManager the ServerInstanceManager instance
     * @param image the ec2-instance image
     * @param keyname the ssh keynmame
     */
    private void startNEO4JInstance(final EC2Manager ec2manager,
        final Image image, final String keyname,
        final ServerInstanceManager serverManager) {
        List<Instance> instances = runInstance(image, keyname, ec2manager, 1, 1,
                InstanceType.M1Small);
        //saveInstances(instances, ServerType.NEO4J, serverManager);
    }

    /**
     * This method starts the neo4J-ec2-instance.
     * @param ec2manager the ec2-manager instance
     * @param serverManager the ServerInstanceManager instance
     * @param image the ec2-instance image
     * @param keyname the ssh keynmame
     */
    private void startBackendInstance(final EC2Manager ec2manager,
                                    final Image image, final String keyname,
                                    final ServerInstanceManager serverManager) {
        List<Instance> instances = runInstance(image, keyname, ec2manager, 1, 1,
                InstanceType.M1Small);
        saveInstances(instances, ServerType.JAVA_BACKEND, serverManager);
    }


    /**
     * This method starts the neo4J-ec2-instance.
     * @param ec2manager the ec2-manager instance
     * @param serverManager the ServerInstanceManager instance
     * @param image the ec2-instance image
     * @param keyname the ssh keynmame
     */
    private void startFrontendInstance(final EC2Manager ec2manager,
                                    final Image image, final String keyname,
                                    final ServerInstanceManager serverManager) {
        List<Instance> instances = runInstance(image, keyname, ec2manager, 1, 1,
                InstanceType.M1Small);
        saveInstances(instances, ServerType.RAILS_FRONTEND, serverManager);
    }

    /**
     * This method stores the server instances.
     * @param instances the instances to store
     * @param type the type of instances
     * @param manager the ServerInstanceManager instance
     */
    private void saveInstances(final List<Instance> instances,
                               final ServerType type,
                               final ServerInstanceManager manager) {
        for (Instance instance: instances) {
            ServerInstance serverInstance = new ServerInstance();
            serverInstance.setImageId(instance.getImageId());
            serverInstance.setInstanceId(instance.getInstanceId());
            serverInstance.setType(type);
            serverInstance.setUrl(instance.getPublicDnsName());
            manager.saveInstance(serverInstance);
        }

    }

    /**
     * This method creates the full region string.
     * @param region the region string to build
     * @return the full region string
     */
    private String getRegion(final String region) {
        return "ec2." + region.toLowerCase() + ".amazonaws.com";
    }

    /**
     * This method describe the available ami images.
     * @param manager the ec2 manager instance
     * @return the image list
     */
    private List<Image> getImageResult(final EC2Manager manager) {
        List<Image> images = new ArrayList<>();
        DescribeImagesRequest imagesRequest = new DescribeImagesRequest();
        Filter architectureFilter = new Filter();
        List<String> architectures = new ArrayList<>();
        architectures.add("x86_64");
        architectureFilter.setName("architecture");
        architectureFilter.setValues(architectures);
        List<String> virtualisationType = new ArrayList<>();
        virtualisationType.add("paravirtual");
        Filter virtualisationFilter = new Filter();
        virtualisationFilter.setName("virtualization-type");
        virtualisationFilter.setValues(virtualisationType);
        List<String> rootDeviceList = new ArrayList<>();
        rootDeviceList.add("instance-store");
        Filter rootDeviceFilter = new Filter();
        rootDeviceFilter.setName("root-device-type");
        rootDeviceFilter.setValues(rootDeviceList);
        List<Filter> filterList = new ArrayList<>();
        filterList.add(architectureFilter);
        filterList.add(virtualisationFilter);
        filterList.add(rootDeviceFilter);
        imagesRequest.setFilters(filterList);
        final DescribeImagesResult result =
                manager.describeImages(imagesRequest);
        for (Image image: result.getImages()) {
            if (image.getPublic().equals(Boolean.TRUE) && image.getImageId().
                    contains("ami") && image.getState().contains("available")
                    && image.getImageLocation().contains("amzn")) {
                images.add(image);
            }
        }
        return images;
    }

    /**
     * This method starts an ec2-instance.
     * @param keyname the ssh keyname
     * @param image the instance to start
     * @param manager the ec2 manager
     * @param min the minimum instances
     * @param max the maximum instances
     * @param type the instance type
     * @return the list of running instances
     */
    private List<Instance> runInstance(final Image image, final String keyname,
                             final EC2Manager manager, final int min,
                             final int max, final InstanceType type) {
        RunInstancesRequest instanceToStart = new RunInstancesRequest();
        instanceToStart.setImageId(image.getImageId());
        instanceToStart.setKeyName(keyname);
        instanceToStart.setInstanceType(type);
        instanceToStart.setMaxCount(max);
        instanceToStart.setMinCount(min);
        List<String> securityGroups = new ArrayList<>();
        securityGroups.add("default");
        instanceToStart.setSecurityGroups(securityGroups);
        final RunInstancesResult runningInstance =
                manager.startInstance(instanceToStart);
        return runningInstance.getReservation().getInstances();
    }


}
