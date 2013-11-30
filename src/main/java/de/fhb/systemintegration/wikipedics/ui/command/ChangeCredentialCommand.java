package de.fhb.systemintegration.wikipedics.ui.command;

import de.fhb.systemintegration.wikipedics.business.inter.CredentialManager;
import de.fhb.systemintegration.wikipedics.business.inter.CredentialViewer;
import de.fhb.systemintegration.wikipedics.domain.UserSettings;

import java.util.List;
import java.util.ArrayList;
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
     * This constant defines the secret key legth.
     */
    private static final int SECRETKEY_LENGTH = 40;

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
        if ((this.getOptions().containsKey("accesskey")
                || this.getOptions().containsKey("secretkey")) && userId > 0) {
            final String accesskey = this.getOptions().get("accesskey");
            final String secretkey = this.getOptions().get("secretkey");
            if (accesskey != null && verifyKeyInput(accesskey,
                    ACCESSKEY_LENGTH, KeyType.ACCESS_KEY)
                    || secretkey != null && verifyKeyInput(secretkey,
                    SECRETKEY_LENGTH, KeyType.SECRET_KEY)) {
                status = true;
            }
        } else {
            status = false;
            this.getMessages().add("You give no changed credentials.");
        }
        return status;
    }

    /**
     * This method checks that the key inputs are valid.
     * @param key the key input to check
     * @param keyLength the key length
     * @param type the key type
     * @return if the key input is valid
     */
    private boolean verifyKeyInput(final String key,
                                   final int keyLength, final KeyType type) {
        boolean status;
        if (!key.isEmpty()) {
            if (key.length() == keyLength) {
                status = true;
            } else {
                status = false;
                if (key.length() < keyLength) {
                    this.getMessages().add("The " + type.getName()
                            + " is to short.");
                } else {
                    this.getMessages().add("The " + type.getName()
                            + " is to long.");
                }
            }
        } else {
            status = false;
            this.getMessages().add("The " + type.getName() + " is empty.");
        }
        return status;
    }

    @Override
    protected final void action(final Long userId) {
        CredentialViewer viewer = this.getBuilder().getCredentialViewer();
        CredentialManager manager = this.getBuilder().getCredentialManager();
        List<KeyType> changedTypes = new ArrayList<>();
        UserSettings settings = viewer.findById(userId);
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
