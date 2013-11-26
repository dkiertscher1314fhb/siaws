package de.fhb.systemintegration.wikipedics.ui.input;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * This is a test case to test the CommandLineParser.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class CommandLineParserTest {

    /**
     * This is the parser object which should be test.
     */
    private Parser parser;

    /**
     * This method setups every test.
     */
    @Before
    public void setUp() {
        parser = new CommandLineParser();
    }

    /**
     * This method checks null command.
     */
    @Test
    public void checkNullCommand() {
        final String command = "";
        Map<String, Map<String, String>> parsedLine =
                parser.parse(command);
        Assert.assertTrue(parsedLine.isEmpty());
    }

    /**
     * This method checks one command.
     */
    @Test
    public void checkOneCommand() {
        final String command = "help";
        Map<String, Map<String, String>> parsedLine =
                parser.parse(command);
        Assert.assertTrue(parsedLine.containsKey("help"));
    }

    /**
     * This method checks that the parser can parse more then one command.
     */
    @Test
    public void checkTwoCommands() {
        final String commands = "help changeCredentials";
        Map<String, Map<String, String>> parsedLine =
                parser.parse(commands);
        Assert.assertTrue(parsedLine.containsKey("changeCredentials"));
    }

    /**
     * This method checks that one command and one options is parsed correctly.
     */
    @Test
    public void checkOneCommandAndOneOption() {
        final String command = "changeCredentials accesskey=12345";
        Map<String, Map<String, String>> parseLine =
                parser.parse(command);
        Map<String, String> changeCredentials =
                parseLine.get("changeCredentials");
        Assert.assertTrue(changeCredentials.containsKey("accesskey"));
    }
}
