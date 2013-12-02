package de.fhb.systemintegration.wikipedics.ui.command;

import java.util.Map;

/**
 * This class contains the method which are used for all the login commands.
 *
 * @author mlelansky
 * @version 0.0.1
 */
abstract class AbstractLoginCommand extends AbstractCommand {
    /**
     * This constant defines the access key length.
     */
    protected static final int ACCESSKEY_LENGTH = 20;
    /**
     * This constant defines the secret key legth.
     */
    protected static final int SECRETKEY_LENGTH = 40;

    /**
     * This is the default constructor.
     * @param _name the name of the command
     * @param _description the description of the command
     * @param _options the options of the command
     */
    public AbstractLoginCommand(final String _name, final String _description,
                                final Map<String, String> _options) {
        super(_name, _description, _options);
    }

    /**
     * This method checks that the key inputs are valid.
     * @param key the key input to check
     * @param keyLength the key length
     * @param type the key type
     * @return if the key input is valid
     */
    protected boolean verifyKeyInput(final String key,
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
}
