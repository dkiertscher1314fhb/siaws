package de.fhb.systemintegration.wikipedics.ui.command;

import org.junit.Test;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * This is a test case for the Parser.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class CommandParserTest {

    /**
     * This method checks that if someone parse an empty command.
     */
    @Test
    public void checkEmptyCommands() {
        Map<String, Map<String, String>> commandMap = new HashMap<>();
        List<Command> commandList = CommandParser.parse(commandMap);
        Assert.assertTrue("No commands should be parsed.",
                commandList.isEmpty());
    }

    /**
     * This method checks if one map entry returns one entry in the list.
     */
    @Test
    public void checkParseOneCommand() {
        Map<String, Map<String, String>> commandMap = new HashMap<>();
        commandMap.put("changeCredentials", new HashMap<String, String>());
        List<Command> commandList = CommandParser.parse(commandMap);
        Assert.assertEquals("One command should be parsed.",
                1L, commandList.size());
    }
}
