package de.fhb.systemintegration.wikipedics.ui.command;

import de.fhb.systemintegration.wikipedics.business.inter.CredentialManager;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialViewer;
import de.fhb.systemintegration.wikipedics.domain.UserSettings;

import java.util.Map;

/**
 * This class stands for the command to change the credentials.
 *
 * @author mlelansky
 * @version 0.0.1
 */
class ChangeCredentialCommand extends AbstractCommand {

    /**
     * This constant defines the access key length.
     */
    private static final int ACCESSKEY_LENGTH = 20;

    /**
     * This is the initialisation constructor of the command.
     * @param _options the actual options of the command
     */
    public ChangeCredentialCommand(final Map<String, String> _options) {
        super("ChangeCredentials",
                "This command change the actual used credentials.", _options);
    }

    @Override
    protected final boolean checkOptions(final Long userId) {
        boolean status = false;
        if (this.getOptions().containsKey("accesskey") && userId > 0) {
            final String accesskey = this.getOptions().get("accesskey");
            if (accesskey != null && !accesskey.isEmpty()) {
                if (accesskey.length() == ACCESSKEY_LENGTH) {
                    status = true;
                } else {
                    status = false;
                    if (accesskey.length() < ACCESSKEY_LENGTH) {
                        this.setMessage("The access key is to short.");
                    } else {
                        this.setMessage("The access key is to long.");
                    }
                }
            } else {
                status = false;
                this.setMessage("The access key is empty.");
            }
        } else {
            status = false;
            this.setMessage("You give no changed credentials.");
        }
        return status;
    }

    @Override
    protected final void action(final Long userId) {
        CredentialViewer viewer = this.getBuilder().getCredentialViewer();
        CredentialManager manager = this.getBuilder().getCredentialManager();
        UserSettings settings = viewer.findById(userId);
        if (this.getOptions().containsKey("accesskey")
                && !this.getOptions().get("accesskey").isEmpty()) {
            settings.setAccesskey(this.getOptions().get("accesskey"));
        }
        manager.updateCredential(settings);
        this.setMessage("The access key is successfully updated.");
    }
}
