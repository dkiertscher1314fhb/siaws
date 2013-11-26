package de.fhb.systemintegration.wikipedics.ui.command;

import java.util.Map;
import java.util.HashMap;

/**
 * This class stands for the command to change the credentials.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public class ChangeCredentialCommand extends AbstractCommand {

    /**
     * This is the default constructor of the command.
     */
    public ChangeCredentialCommand() {
        this(new HashMap<String, String>());
    }

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
        if (this.getOptions().containsKey("accesskey")
                || this.getOptions().containsKey("secretkey")) {
            status = true;
        }
        return status;
    }

    @Override
    protected final void action() {

    }
}
