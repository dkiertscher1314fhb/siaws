package de.fhb.systemintegration.wikipedics.ui.command;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import de.fhb.systemintegration.wikipedics.business.inter.BeanstalkManager;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialViewer;
import de.fhb.systemintegration.wikipedics.domain.UserSetting;
import de.fhb.systemintegration.wikipedics.util.Config;

import java.util.Map;

import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationRequest;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationResult;

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
        CredentialViewer viewer = this.getBuilder().getCredentialViewer();
        UserSetting actualUser = viewer.findById(Config.getId());
        final AWSCredentials credentials = this.setupCredentials(actualUser);
        //TODO implements the Proxy-Configuration
        final ClientConfiguration config = new ClientConfiguration();
        final String regionString = this.buildRegionString(
                actualUser.getRegion());
        BeanstalkManager manager = this.getBuilder().getBeanstalkManager(
                credentials, config, regionString);
        createApplication(manager);
    }

    /**
     * This method creates the elastic beanstalk application.
     * @param manager the BeanstalkManager instance
     */
    private void createApplication(final BeanstalkManager manager) {
        CreateApplicationRequest request = new CreateApplicationRequest();
        request.setApplicationName(BeanstalkConfig.BEANSTALK_APP_NAME);
        request.setDescription(BeanstalkConfig.BEANSTALK_APP_DESCRIPTION);
        final CreateApplicationResult result =
                manager.createApplication(request);
        this.getMessages().add("The Beanstalk-Application: "
                + result.getApplication().getApplicationName() + " is started");
    }

    /**
     * This method creates the needed amazon aws credentials.
     * @param user the actual user
     * @return the amazon aws credentials
     */
    private AWSCredentials setupCredentials(final UserSetting user) {
        AWSCredentials credentials = new BasicAWSCredentials(
                user.getAccesskey(), user.getSecretkey());
        return credentials;
    }

    /**
     * This method converts the region into the amazon aws-region-string.
     * @param region the used region
     * @return the converted Region String
     */
    private String buildRegionString(final String region) {
        return BeanstalkConfig.BEANSTALK_URL_PREFIX + region.toLowerCase()
                + BeanstalkConfig.BEANSTALK_URL_POSTFIX;
    }
}
