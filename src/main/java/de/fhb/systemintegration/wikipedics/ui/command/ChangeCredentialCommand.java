package de.fhb.systemintegration.wikipedics.ui.command;

import de.fhb.systemintegration.wikipedics.business.inter.CredentialManager;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialViewer;
import de.fhb.systemintegration.wikipedics.domain.UserSetting;
import de.fhb.systemintegration.wikipedics.util.Config;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class stands for the command to change the credentials.
 *
 * @author mlelansky
 * @version 0.0.1
 */
class ChangeCredentialCommand extends AbstractLoginCommand {

    /**
     * This is the initialisation constructor of the command.
     * @param _options the actual options of the command
     */
    public ChangeCredentialCommand(final Map<String, String> _options) {
        super("ChangeCredentials",
                "This command change the actual used credentials.", _options);
    }

    @Override
    protected final boolean checkOptions() {
        boolean status = false;
        if ((this.getOptions().containsKey("accesskey")
                || this.getOptions().containsKey("secretkey"))
                && Config.getId() > 0) {
            final String accesskey = this.getOptions().get("accesskey");
            final String secretkey = this.getOptions().get("secretkey");
            if (accesskey != null && verifyKeyInput(accesskey,
                    ACCESSKEY_LENGTH, KeyType.ACCESS_KEY)
                    || secretkey != null && verifyKeyInput(secretkey,
                    SECRETKEY_LENGTH, KeyType.SECRET_KEY)) {
                status = true;
            }
        } else {
            this.getMessages().add("You give no changed credentials.");
        }
        return status;
    }

    @Override
    protected final void action() {
        CredentialViewer viewer = this.getBuilder().getCredentialViewer();
        CredentialManager manager = this.getBuilder().getCredentialManager();
        List<KeyType> changedTypes = new ArrayList<>();
        UserSetting settings = viewer.findById(Config.getId());
        if (this.getOptions().containsKey("accesskey")
                && !this.getOptions().get("accesskey").isEmpty()) {
            settings.setAccesskey(this.getOptions().get("accesskey"));
            changedTypes.add(KeyType.ACCESS_KEY);
        }
        if (this.getOptions().containsKey("secretkey")
                && !this.getOptions().get("secretkey").isEmpty()) {
            settings.setSecretkey(this.getOptions().get("secretkey"));
            changedTypes.add(KeyType.SECRET_KEY);
        }
        manager.updateCredential(settings);
        for (KeyType type: changedTypes) {
            this.getMessages().add("The " + type.getName()
                    + " is successfully updated.");
        }
    }
}
