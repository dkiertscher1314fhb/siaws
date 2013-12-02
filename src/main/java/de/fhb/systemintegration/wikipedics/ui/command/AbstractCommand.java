package de.fhb.systemintegration.wikipedics.ui.command;

import de.fhb.systemintegration.wikipedics.business.builder.BusinessBuilder;
import de.fhb.systemintegration.wikipedics.business.builder.BusinessBuilderImpl;

import java.util.List;
import java.util.ArrayList;
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
     * The builder instance.
     */
    private BusinessBuilder builder;

    /**
     * If some errors occurred.
     */
    private List<String> messages;

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
        this.builder = new BusinessBuilderImpl();
        this.messages = new ArrayList<>();
    }

    @Override
    public final void doAction() {
        if (this.checkOptions()) {
            this.action();
        }
        if (this.getMessages() != null && !this.getMessages().isEmpty()) {
            for (String message: this.getMessages()) {
                System.out.println(message);
            }
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

    @Override
    public boolean equals(final Object o) {
        boolean equal = false;
        if (this == o) {
            equal = true;
        } else {
            if (o instanceof AbstractCommand) {
                AbstractCommand that = (AbstractCommand) o;

                if (description.equals(that.description)
                        && name.equals(that.name)) {
                    equal = true;
                }
            }
        }
        return equal;
    }

    @Override
    public int hashCode() {
        final int hash = 31;
        int result = name.hashCode();
        result = hash * result + description.hashCode();
        return result;
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

    /**
     * This method returns the builder instance.
     * @return the actual builder.
     */
    protected BusinessBuilder getBuilder() {
        return this.builder;
    }

    /**
     * This method sets the builder instance.
     * This method is only used for test.
     * @param _builder the new builder instance
     */
    protected void setBuilder(final BusinessBuilder _builder) {
        this.builder = _builder;
    }

    @Override
    public final List<String> getMessages() {
        return this.messages;
    }

    /**
     * This method sets the new error message.
     * @param _messages the new error message
     */
    protected void setMessages(final List<String> _messages) {
        if (_messages != null) {
            this.messages = _messages;
        }
    }

}
