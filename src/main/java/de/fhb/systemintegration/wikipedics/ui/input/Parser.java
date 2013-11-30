package de.fhb.systemintegration.wikipedics.ui.input;

import java.util.Map;

/**
 * This interfaces define the parse method.
 * This method must be implement by the subclasses.
 */
public interface Parser {

    /**
     * This method parses the commandline.
     * @param commandline the commandline to parse
     * @return the parsed commandline
     */
    Map<String, Map<String, String>> parse(String commandline);
}
