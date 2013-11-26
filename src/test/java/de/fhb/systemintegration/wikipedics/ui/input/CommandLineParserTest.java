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
        this.parser = new CommandLineParser();
    }

    /**
     * This method checks null command.
     */
    @Test
    public void checkNullCommand() {
        final String command = "";
        Map<String, Map<String, String>> parsedLine =
                this.parser.parse(command);
        Assert.assertTrue("No command should be parsed.",
                parsedLine.isEmpty());
    }

    /**
     * This method checks one command.
     */
    @Test
    public void checkOneCommand() {
        final String command = "help";
        Map<String, Map<String, String>> parsedLine =
                this.parser.parse(command);
        Assert.assertTrue("One command should be parsed.",
                parsedLine.containsKey("help"));
    }

    /**
     * This method checks that the parser can parse more then one command.
     */
    @Test
    public void checkTwoCommands() {
        final String commands = "help changeCredentials";
        Map<String, Map<String, String>> parsedLine =
                this.parser.parse(commands);
        Assert.assertTrue("Two commands should be parsed.",
                parsedLine.containsKey("changeCredentials"));
    }

    /**
     * This method checks that one command and one options is parsed correctly.
     */
    @Test
    public void checkOneCommandAndOneOption() {
        final String command = "changeCredentials accesskey=12345";
        Map<String, Map<String, String>> parseLine =
                this.parser.parse(command);
        Map<String, String> changeCredentials =
                parseLine.get("changeCredentials");
        Assert.assertTrue("One command and one options should be parsed.",
                changeCredentials.containsKey("accesskey"));
    }
}
