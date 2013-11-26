package de.fhb.systemintegration.wikipedics.ui.command;

import java.util.Map;

/**
 * This interface defined the methods for every commands that can be used
 * in the application.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public interface Command {

    /**
     * This method returns the name of the command.
     * @return the name of the command
     */
    String getName();

    /**
     * This method return the description of the command what this command do.
     * @return the description fo the command
     */
    String getDescription();

    /**
     * This method returns the map of the command.
     * @return the actual options
     */
    Map<String, String> getOptions();

    /**
     * This method is the work of the command without any options.
     */
    void doAction();
}
