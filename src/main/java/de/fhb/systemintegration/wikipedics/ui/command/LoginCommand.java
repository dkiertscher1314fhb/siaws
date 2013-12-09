package de.fhb.systemintegration.wikipedics.ui.command;

import de.fhb.systemintegration.wikipedics.business.inter.CredentialManager;
import de.fhb.systemintegration.wikipedics.domain.UserSetting;
import de.fhb.systemintegration.wikipedics.util.Config;

import java.util.Map;

/**
 * This class stands for the command that a user wants to login
 * into the application.
 *
 * @author mlelansky
 * @version 0.0.1
 */
class LoginCommand extends AbstractLoginCommand {

    /**
     * This is the initialisation constructor of the command.
     * @param _options the actual options of the command
     */
    public LoginCommand(final Map<String, String> _options) {
        super("Login", "This command sets the initial configuration "
                + "of the application.", _options);
    }

    @Override
    protected final boolean checkOptions() {
        boolean status = false;
        if (this.getOptions().containsKey("accesskey")
                && this.getOptions().containsKey("secretkey")
                && this.getOptions().containsKey("keyname")
                && this.getOptions().containsKey("region")
                && Config.getId() < 0) {
            final String accesskey = this.getOptions().get("accesskey");
            final String secretkey = this.getOptions().get("secretkey");
            if (accesskey != null && verifyKeyInput(accesskey,
                    ACCESSKEY_LENGTH, KeyType.ACCESS_KEY)
                    || secretkey != null && verifyKeyInput(secretkey,
                    SECRETKEY_LENGTH, KeyType.SECRET_KEY)) {
                status = true;
            }
        } else {
            if (!(Config.getId() < 0)) {
                this.getMessages().add("You are successfully login, "
                        + "please use the changeCredentials-Command "
                        + "to change any Credentials.");
            }
            if (!this.getOptions().containsKey("accesskey")) {
                this.getMessages().add("You give no access key.");
            }
            if (!this.getOptions().containsKey("secretkey")) {
                this.getMessages().add("You give no secret key.");
            }
            if (!this.getOptions().containsKey("keyname")) {
                this.getMessages().add("You give no ssh key name.");
            }
            if (!this.getOptions().containsKey("region")) {
                this.getMessages().add("You give no region.");
            }

        }
        return status;
    }

    @Override
    protected final void action() {
        CredentialManager manager = this.getBuilder().getCredentialManager();
        final String accesskey = this.getOptions().get("accesskey");
        final String secretkey = this.getOptions().get("secretkey");
        final String keyname = this.getOptions().get("keyname");
        final String region = this.getOptions().get("region");
        final UserSetting setting = new UserSetting();
        setting.setAccesskey(accesskey);
        setting.setKeyname(keyname);
        setting.setRegion(region);
        setting.setSecretkey(secretkey);
        final UserSetting result = manager.saveCredentials(setting);
        Config.setId(result.getId());
        this.getMessages().add("You are successfully login, your credentials "
                + "are stored successfully.");
    }
}
