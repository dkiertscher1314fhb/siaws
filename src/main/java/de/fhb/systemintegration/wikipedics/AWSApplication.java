package de.fhb.systemintegration.wikipedics;

import de.fhb.systemintegration.wikipedics.ui.command.Command;
import de.fhb.systemintegration.wikipedics.ui.command.CommandExecutor;
import de.fhb.systemintegration.wikipedics.ui.command.CommandParser;
import de.fhb.systemintegration.wikipedics.ui.input.CommandLineParser;
import de.fhb.systemintegration.wikipedics.ui.input.InputStreamWrapper;
import de.fhb.systemintegration.wikipedics.ui.input.Parser;
import de.fhb.systemintegration.wikipedics.util.Config;
import de.fhb.systemintegration.wikipedics.util.H2Manager;
import de.fhb.systemintegration.wikipedics.util.ApplicationPropertiesHandler;
import de.fhb.systemintegration.wikipedics.util.
        ApplicationProperitesHandlerImpl;

import java.sql.SQLException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            ApplicationPropertiesHandler handler =
                    new ApplicationProperitesHandlerImpl();
            handleLogin(executor, handler);
            while (wrapper.getInputStream().available() == 0 && !quit) {
                final String line = wrapper.readLine();
                final Map<String, Map<String, String>> parsedLine =
                        parser.parse(line);
                final List<Command> commandList =
                        CommandParser.parse(parsedLine);
                if (commandList.contains(CommandParser.
                        getCommandObject("quit", null))) {
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

    /**
     * This method handles the login of the user with the properties file.
     * @param commandExecutor the actual command executor object
     * @param handler the properties handler
     *                         .
     */
    private static void handleLogin(final CommandExecutor commandExecutor,
                               final ApplicationPropertiesHandler handler) {
        if (handler.size() >= Config.MIN_PROPERITES_SIZE) {
            final String accesskey = handler.getObject("accesskey");
            final String secrekey = handler.getObject("secretkey");
            final String region = handler.getObject("region");
            final String keyname = handler.getObject("keyname");
            if (accesskey != null && !accesskey.isEmpty() && secrekey != null
                    && !secrekey.isEmpty() && region != null
                    && !region.isEmpty() && keyname != null
                    && !keyname.isEmpty()) {

                final Map<String, String> optionsMap = new HashMap(4);
                optionsMap.put("accesskey", accesskey);
                optionsMap.put("secretkey", secrekey);
                optionsMap.put("region", region);
                optionsMap.put("keyname", keyname);
                List<Command> commandList = new ArrayList<>();
                commandList.add(CommandParser.
                        getCommandObject("login", optionsMap));
                commandExecutor.executes(commandList);
            }

        }
    }


}
