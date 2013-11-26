package de.fhb.systemintegration.wikipedics.ui.input;

import java.util.HashMap;
import java.util.Map;

/**
 * This class parses the commandline.
 */
public class CommandLineParser implements Parser {

    /**
     * This is the delimiter pattern for the hole line.
     */
    private static final String DELIMITER_PATTERN = "\\s+";
    /**
     * This is the delimiter pattern for the options.
     */
    private static final String OPTION_DELIMITER_PATTERN = "=";

    @Override
    public final Map<String, Map<String, String>> parse(
            final String commandline) {
        final Map<String, Map<String, String>> result = new HashMap<>();
        if (commandline != null && !commandline.isEmpty()) {
            String[] chunks = splitList(commandline);
            int i = 0;
            String actualCommand = "";
            while (i < chunks.length) {
                if (!chunks[i].contains("=")) {
                    actualCommand = chunks[i];
                    result.put(chunks[i], new HashMap<String, String>());
                } else {
                    if (actualCommand != null && !actualCommand.isEmpty()) {
                        result.get(actualCommand).putAll(
                                splitOption(chunks[i]));
                    }
                }
                i++;
            }
        }
        return result;
    }

    /**
     * This method splits the hole line into chunks.
     * @param commandLine the commandline to parse.
     * @return the array of chunks
     */
    private String[] splitList(final String commandLine) {
        String parseLine = commandLine.trim();
        String[] chunks = parseLine.split(DELIMITER_PATTERN);
        return chunks;
    }

    /**
     * This method extract the options of a command.
     * @param chunk the chunk which contains the option
     * @return the parsed options
     */
    private Map<String, String> splitOption(final String chunk) {
        Map<String, String> optionMap = new HashMap<>();
        String parseLine = chunk.trim();
        String[] option = parseLine.split("=");
        if (option.length == 2) {
            optionMap.put(option[0], option[1]);
        }
        return optionMap;
    }
}
