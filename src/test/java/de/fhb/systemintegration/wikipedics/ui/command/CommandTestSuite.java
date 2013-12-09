package de.fhb.systemintegration.wikipedics.ui.command;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This is a test suite which runs all the test of the command package.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ CommandParserTest.class,
        ChangeCredentialCommandTest.class, LoginCommandTest.class,
        DeployCommandTest.class })
public class CommandTestSuite {
}
