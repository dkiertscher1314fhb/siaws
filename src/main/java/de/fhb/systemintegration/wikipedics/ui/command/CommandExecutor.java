package de.fhb.systemintegration.wikipedics.ui.command;

import java.util.List;

/**
 * This class executes the commands.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class CommandExecutor {

    /**
     * This is the actual login user.
     */
    private Long userId;

    /**
     * This is the default constructor.
     */
    public CommandExecutor() {
        super();
        this.userId = Long.valueOf(-1);
    }

    /**
     * This method executes all the commands.
     * @param commandList the command list to execute
     */
    public void executes(final List<Command> commandList) {
        for (Command command: commandList) {
            command.doAction(this.userId);
        }
    }
}
