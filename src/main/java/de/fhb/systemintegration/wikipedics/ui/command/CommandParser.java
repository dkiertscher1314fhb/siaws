package de.fhb.systemintegration.wikipedics.ui.command;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * This class parses the Map of the commands into an command list which can be
 * executed.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class CommandParser {

    /**
     * This is the default constructor of the class.
     */
    private CommandParser() {
        super();
    }

    /**
     * This method parse the command map into an list of commands which can be
     * executed.
     * @param commandMap the commands that should be parse
     * @return the list of commands which should be executed
     */
    public static List<Command> parse(final Map<String,
            Map<String, String>> commandMap) {
        List<Command> commands = new ArrayList<>();
        for (Map.Entry<String, Map<String, String>> entry
                : commandMap.entrySet()) {
            try {
                Command command = CommandParser.getCommand(entry.getKey(),
                        entry.getValue());
                commands.add(command);
            } catch (ClassNotFoundException e) {
                System.err.println(e);
            }
        }
        return commands;
    }

    /**
     * This method converts the command string into a command object.
     * @param command the command string to parse
     * @param options the options that should be used
     * @throws ClassNotFoundException if the command not implemented
     * @return the converted command
     */
    private static Command getCommand(final String command,
        final Map<String, String> options) throws ClassNotFoundException {
        Command actualCommand;
        switch (command) {
            case "changeCredentials":
                actualCommand = new ChangeCredentialCommand(options);
                break;
            case "quit":
                actualCommand = new QuitCommand();
                break;
            default:
                throw new ClassNotFoundException(
                "This command is not yet implemented.");
        }
        return actualCommand;
    }
}
