package de.fhb.systemintegration.wikipedics.ui.command;

/**
 * This class stands for the command to close the application.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public class QuitCommand extends AbstractCommand {

    /**
     * This is the default constructor of the command.
     */
    public QuitCommand() {
        super("Quit", "This commands signals to close the application.");
    }

    @Override
    protected final boolean checkOptions() {
        boolean status = false;
        if (this.getOptions().isEmpty()) {
            status = true;
        } else {
            status = false;
            this.getMessages().add("The command quit used no options.");
        }
        return status;
    }

    @Override
    protected final void action() {
        // Nothing to do only a dummy implementation here.;
    }
}
