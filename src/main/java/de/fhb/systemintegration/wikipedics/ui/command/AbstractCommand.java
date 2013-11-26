package de.fhb.systemintegration.wikipedics.ui.command;

import java.util.Map;
import java.util.HashMap;

/**
 * This class is the base class for the real commands.
 *
 * @author mlelansky
 * @version 0.0.1
 */
abstract class AbstractCommand implements Command {

    /**
     * The name of the actual command.
     */
    private final String name;
    /**
     * The description of the command.
     */
    private final String description;
    /**
     * The option map of the command.
     */
    private final Map<String, String> options;

    /**
     * This is the initialisation constructor of the command.
     * @param _name the name of the command
     * @param _description the description of the command
     */
    AbstractCommand(final String _name, final String _description) {
        this(_name, _description, new HashMap<String, String>(0));
    }

    /**
     * This is the initialisation constructor of the command.
     * @param _name the name of the command.
     * @param _description the description of the command
     * @param _options the options of the command
     */
    AbstractCommand(final String _name, final String _description,
                           final Map<String, String> _options) {
        this.name = _name;
        this.description = _description;
        this.options = _options;
    }

    @Override
    public final String getName() {
        return this.name;

    }

    @Override
    public final String getDescription() {
        return this.description;
    }

    @Override
    public final Map<String, String> getOptions() {
        return this.options;
    }

    @Override
    public final void doAction() {
        if (this.checkOptions()) {
            this.action();
        }
    }

    /**
     * This method is a callback to checks the needed options of the command.
     * @return true if the options are valid
     */
    protected abstract boolean checkOptions();

    /**
     * This method is a callback which contains the logic
     * that should be executed.
     */
    protected abstract void action();

}
