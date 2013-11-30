package de.fhb.systemintegration.wikipedics;

import de.fhb.systemintegration.wikipedics.ui.command.Command;
import de.fhb.systemintegration.wikipedics.ui.command.CommandExecutor;
import de.fhb.systemintegration.wikipedics.ui.command.CommandParser;
import de.fhb.systemintegration.wikipedics.ui.command.QuitCommand;
import de.fhb.systemintegration.wikipedics.ui.input.CommandLineParser;
import de.fhb.systemintegration.wikipedics.ui.input.InputStreamWrapper;
import de.fhb.systemintegration.wikipedics.ui.input.Parser;
import de.fhb.systemintegration.wikipedics.util.H2Manager;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.io.IOException;

/**
 * This is the main class of the wikipedics deploy application.
 * This class starts the application and
 * controls the complete flow of the deployment.
 */
public final class AWSApplication {

    /**
     * This is the default constructor of the application.
     */
    private AWSApplication() {
        super();
    }

    /**
     * This is the entry point of the application.
     * @param args the commandline arguments
     */
    public static void main(final String... args) {
        boolean quit = false;
        System.out.println("The AWSApplication is started.");
        try (InputStreamWrapper wrapper = new InputStreamWrapper()) {
            H2Manager manager = new H2Manager();
            manager.startServer();
            Parser parser = new CommandLineParser();
            CommandExecutor executor = new CommandExecutor();
            while (wrapper.getInputStream().available() == 0 && !quit) {
                final String line = wrapper.readLine();
                final Map<String, Map<String, String>> parsedLine =
                        parser.parse(line);
                final List<Command> commandList =
                        CommandParser.parse(parsedLine);
                if (commandList.contains(new QuitCommand())) {
                    quit = true;
                }
                executor.executes(commandList);
            }
            manager.stopServer();
        } catch (IOException | SQLException e) {
            System.err.print("System.in has an error detected.");
            System.err.println(e.getLocalizedMessage());
        }
        System.out.println("The AWSApplication is closed.");
    }


}
